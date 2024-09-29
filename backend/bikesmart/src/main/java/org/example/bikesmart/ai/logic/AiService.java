package org.example.bikesmart.ai.logic;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bikesmart.ai.model.*;
import org.example.bikesmart.geojsonParsers.Feature;
import org.example.bikesmart.geojsonParsers.GeoJson;
import org.example.bikesmart.geojsonParsers.Properties;
import org.example.bikesmart.maps.logic.RoutingService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiService {
    private final OpenAiChatModel chatModel;
    private final RoutingService routingService;
    public static final String behaviour = """
            You are a professional trip assistan who answers questions accurately and naturally.
            Answer the user with his own language.
            Answer in minutes and hours instead of seconds.
            This endpoint allows you to chat with AI.
            Currently implemented functions:
            - geocodeSearch - translate address to coordinates
            - reverseGeosearch - translate coordinates to address
                                    
            Your task is to assist users with these functions. If you cannot answer a question or the question is outside your scope, kindly ask for clarification or state that you are unable to assist with that query.
            Respond in a professional manner as a logistician but do not mention your role unless specifically asked.
                        
            Instruction: "{customInstruction}"
            """;
    public RouteBrouteAiModel.Response chatWithAi(RequestFromFrontend userInput) {
        LanguageAiJson.Response response = giveMeLanguage(userInput.getMessage());
        RouteBrouteAiModel.Request requirements = giveMeRequirementsOfRoute(userInput);
        log.info("Detected language: {}", response.language());
        String customInstruction = "ANSWER IN " + response.language();

        PromptTemplate systemPromptTemplate = new PromptTemplate(behaviour);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("customInstruction", customInstruction));

        Message userMessage = new UserMessage(userInput.getMessage());

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage), OpenAiChatOptions.builder()
                .withN(1)
                .withFunctions(Set.of("geocodeSearch", "reverseGeosearch","getRouteFromApi"))
                .build());

        // Extract coordinates from the requirements
        List<Double> startCoords = Arrays.asList(
                requirements.origin().getLat(),
                requirements.origin().getLng()
        );
        List<Double> endCoords = Arrays.asList(
                requirements.destination().getLat(),
                requirements.destination().getLng()
        );

        try {
            // Make the routing request with the extracted coordinates
            GeoJson geoJson = routingService.createRouting(
                    startCoords,
                    endCoords,
                    BrouterTypeClass.SAFE.getName(),
                    1
            );
            // Process the messages to extract WayTags and generate safety scores
            List<Feature> updatedFeatures = geoJson.getFeatures()
                    .stream()
                    .map(feature -> {
                        Properties properties = feature.getProperties();
                        List<List<String>> messages = properties.getMessages();

                        // Skip the header row
                        List<List<String>> dataRows = messages.subList(1, messages.size());

                        // Extract WayTags from each row
                        List<String> wayTagsList = dataRows.stream()
                                .map(row -> row.get(9)) // Assuming WayTags is at index 9
                                .collect(Collectors.toList());

                        // Generate safety scores for each WayTag
                        List<Score> safetyScores = wayTagsList.stream()
                                .map(wayTags -> generateSafetyScore(wayTags))
                                .collect(Collectors.toList());

                        // You can aggregate the safety scores or assign them back to properties
                        // For simplicity, let's assume we take the lowest safety score as the overall score
                        Score overallScore = safetyScores.stream().min(Comparator.naturalOrder()).orElse(Score.MEDIUM);

                        // Set the safety score in properties
                        properties.setSafety(overallScore.getName());

                        // Update the feature with the new properties
                        feature.setProperties(properties);
                        return feature;
                    })
                    .collect(Collectors.toList());

            // Update the geoJson with the updated features
            geoJson.setFeatures(updatedFeatures);

            return new RouteBrouteAiModel.Response(geoJson, LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null;
        }
    }

    private Score generateSafetyScore(String wayTags) {
        // Prepare the prompt for the AI model
        String prompt = "Evaluate the following way tags and assign a safety score (Very Low, Low, Medium, High, Very High). Provide only the score:\n" + wayTags;

        // Call the AI model using Spring AI and get the response as a String
        return ChatClient.create(chatModel)
                .prompt()
                .user(u -> u.text(prompt))
                .call()
                .entity(SafetyScore.Response.class).score(); // Get the AI response as a String

//        // Log the AI response for debugging
//        log.info("AI response: {}", aiResponse);
//
//        // Map the AI response to the Score enum
//        return mapAiResponseToScore(aiResponse);
    }

    private Score mapAiResponseToScore(String aiResponse) {
        String cleanedResponse = aiResponse.trim().toLowerCase();

        // Optionally, extract the score if the AI returns additional text
        if (cleanedResponse.contains("very low")) {
            return Score.VERY_LOW;
        } else if (cleanedResponse.contains("low")) {
            return Score.LOW;
        } else if (cleanedResponse.contains("medium")) {
            return Score.MEDIUM;
        } else if (cleanedResponse.contains("high")) {
            return Score.HIGH;
        } else if (cleanedResponse.contains("very high")) {
            return Score.VERY_HIGH;
        } else {
            // Default score if unrecognized
            log.warn("Unrecognized safety score from AI: {}", aiResponse);
            return Score.MEDIUM;
        }
    }




    public RouteBrouteAiModel.Request giveMeRequirementsOfRoute(RequestFromFrontend request) {
        final String promptText = "Create example race when user not provided specyfic " + request.getMessage();


        return ChatClient.create(chatModel)
                .prompt()
                .system("Create example race when user not provided specyfic parameters when user not provided specyfic parameters")
                .user(u -> u.text(promptText + request.getLocations()))
                .call()
                .entity(RouteBrouteAiModel.Request.class);
    }
    public LanguageAiJson.Response giveMeLanguage(String message) {
        final String promptText = "Provide language of that message:\n" + message;

        return ChatClient.create(chatModel)
                .prompt()
                .user(u -> u.text(promptText))
                .call()
                .entity(LanguageAiJson.Response.class);
    }

    public LanguageAiJson.Response giveMeLanguage(List<String> messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append("\n");
        }
        final String promptText = "Provide language of that messages:\n" + sb;

        return ChatClient.create(chatModel)
                .prompt()
                .user(u -> u.text(promptText))
                .call()
                .entity(LanguageAiJson.Response.class);
    }
}


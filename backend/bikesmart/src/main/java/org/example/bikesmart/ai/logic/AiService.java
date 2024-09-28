package org.example.bikesmart.ai.logic;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bikesmart.ai.model.LanguageAiJson;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiService {
    private final OpenAiChatModel chatModel;
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
    public ChatResponse chatWithAi(String userInput) {
        LanguageAiJson.Response response = giveMeLanguage(userInput);
        log.info("Detected language: {}", response.language());
        String customInstruction = "ANSWER IN " + response.language();

        PromptTemplate systemPromptTemplate = new PromptTemplate(behaviour);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("customInstruction", customInstruction));

        Message userMessage = new UserMessage(userInput);

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage), OpenAiChatOptions.builder()
                .withN(1)
                .withFunctions(Set.of("geocodeSearch", "reverseGeosearch"))
                .build());
        return chatModel.call(prompt);
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


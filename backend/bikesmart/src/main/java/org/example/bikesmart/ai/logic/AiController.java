package org.example.bikesmart.ai.logic;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.bikesmart.ai.model.RouteBrouteAiModel;
import org.example.bikesmart.geojsonParsers.GeoJson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService;

    @PostMapping("/ai")
    @Operation(
            summary = "Chat and comunicate with AI to create safe bike route")
    private RouteBrouteAiModel.Response chatWithAi(@RequestBody RequestFromFrontend request) {
        return aiService.chatWithAi(request);
    }
}


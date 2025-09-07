package com.fitness.AIService.controller;

import com.fitness.AIService.model.Activity;
import com.fitness.AIService.model.Recommendation;
import com.fitness.AIService.service.ActivityAIService;
import com.fitness.AIService.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")
public class RecommendstionController {

    private final RecommendationService recommendationService;

    private final ActivityAIService activityAIService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUsersAllRecommendations(@PathVariable String userId){
        return ResponseEntity.ok(recommendationService.getUsersAllRecommendations(userId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable String activityId){
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }

    @GetMapping("/test")
    public ResponseEntity<Recommendation> testAiResponse(@RequestBody Activity request){
        return ResponseEntity.ok(activityAIService.generateRecommendation(request));
    }


}

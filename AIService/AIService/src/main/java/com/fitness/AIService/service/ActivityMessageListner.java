package com.fitness.AIService.service;

import com.fitness.AIService.model.Activity;
import com.fitness.AIService.model.Recommendation;
import com.fitness.AIService.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListner {

    private final ActivityAIService activityAIService;
    private final RecommendationRepository recommendationRepository;

    @KafkaListener(topics= "${kafka.topic.name}", groupId = "activity-processer-group")
    public void processActivity(Activity activity){
        log.info("Received Activity for processing: {}", activity.getUserId());

        //saving into db
        Recommendation recommendation = activityAIService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}

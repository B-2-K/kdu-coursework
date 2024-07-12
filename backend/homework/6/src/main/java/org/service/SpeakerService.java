package org.service;

import org.beans.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SpeakerService {
    @Bean(name = "Sony")
    @Primary
    Speaker sonySpeaker(){
        return new Speaker("Sony", 5000);
    }

    @Bean(name = "Bose")
    Speaker boseSpeaker(){
        return new Speaker("Bose", 7000);
    }
}

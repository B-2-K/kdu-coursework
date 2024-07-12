package org.service;

import org.springframework.stereotype.Component;
import org.utility.Speaker;
import org.springframework.context.annotation.Primary;

/**
 * Service class responsible for providing Speaker instances.
 */
@Component
public class SpeakerService {

    /**
     * Provides a Sony Speaker instance.
     *
     * @return Sony Speaker instance.
     */
    @Primary
    Speaker sonySpeaker() {
        return new Speaker("Sony", 5000);
    }

    /**
     * Provides a Bose Speaker instance.
     *
     * @return Bose Speaker instance.
     */
    Speaker boseSpeaker() {
        return new Speaker("Bose", 7000);
    }
}

package com.zachglossip.creepifier.config;

import com.zachglossip.creepifier.functions.CreepyImageEdit;
import com.zachglossip.creepifier.models.CreepyImageConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Configuration
public class CreepyEditConfig {

    private final CreepyImageEdit RED_EYE_EDIT = (img, leftEye, rightEye) -> img;
    private final CreepyImageEdit INVERT_EDIT = (img, leftEye, rightEye) -> img;

    @Bean
    public Map<Predicate<CreepyImageConfig>, CreepyImageEdit> imageEdits() {
        Map<Predicate<CreepyImageConfig>, CreepyImageEdit> imageEdits = new HashMap<>();
        imageEdits.put(CreepyImageConfig::redEye, RED_EYE_EDIT);
        imageEdits.put(CreepyImageConfig::inverted, INVERT_EDIT);
        return imageEdits;
    }
}

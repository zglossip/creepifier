package com.zachglossip.creepifier.services;

import com.zachglossip.creepifier.functions.CreepyImageEdit;
import com.zachglossip.creepifier.models.CreepyImageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class CreepyImageService {

    private final Map<Predicate<CreepyImageConfig>, CreepyImageEdit> imageEdits;

    @Autowired
    public CreepyImageService(Map<Predicate<CreepyImageConfig>, CreepyImageEdit> imageEdits) {
        this.imageEdits = imageEdits;
    }

    public BufferedImage creepify(final BufferedImage image, final CreepyImageConfig config) {
        BufferedImage edited = image;

        for (Map.Entry<Predicate<CreepyImageConfig>, CreepyImageEdit> entry : imageEdits.entrySet()) {
            Predicate<CreepyImageConfig> p = entry.getKey();
            CreepyImageEdit e = entry.getValue();
            if (p.test(config)) {
                edited = e.edit(edited);
            }
        }

        return edited;
    }

}

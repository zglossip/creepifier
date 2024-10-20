package com.zachglossip.creepifier.functions;

import java.awt.image.BufferedImage;

@FunctionalInterface
public interface CreepyImageEdit {
    BufferedImage edit(BufferedImage image);
}

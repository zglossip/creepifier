package com.zachglossip.creepifier.services;

import com.zachglossip.creepifier.functions.CreepyImageEdit;
import com.zachglossip.creepifier.models.CreepyImageConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CopyImageServiceTest {

    @Mock
    CreepyImageEdit redEyeMock;

    @Mock
    CreepyImageEdit invertMock;

    BufferedImage testImage = new BufferedImage(1, 2, 3);

    CreepyImageService service;

    @BeforeEach
    void setUp() {
        when(redEyeMock.edit(any())).thenReturn(testImage);

        Map<Predicate<CreepyImageConfig>, CreepyImageEdit> imageEdits = new HashMap<>();
        imageEdits.put(CreepyImageConfig::redEye, redEyeMock);
        imageEdits.put(CreepyImageConfig::inverted, invertMock);

        service = new CreepyImageService(imageEdits);
    }

    @Test
    void testEditsCalled() {
        //Given a test image
        BufferedImage startingImage = new BufferedImage(9, 8, 7);

        // and a test config
        CreepyImageConfig config = new CreepyImageConfig(true, false);

        //When
        BufferedImage result = service.creepify(startingImage, config);

        //Then
        assert result.equals(testImage);

        verify(redEyeMock, atMost(1)).edit(startingImage);
        verify(invertMock, never()).edit(startingImage);
    }

}

package io.github.eggy03.ferrumx.example.display;

import lombok.extern.slf4j.Slf4j;
import io.github.eggy03.ferrumx.core.entity.display.VideoController;
import io.github.eggy03.ferrumx.core.service.display.VideoControllerService;

import java.util.List;

/**
 * Example class demonstrating how to fetch and display video controller
 * information using {@link VideoControllerService}.
 * <p>
 * This class executes a CIM query to retrieve all video controllers on the
 * system and logs the JSON representation of each {@link VideoController} object.
 * </p>
 * Individual attributes of each video controller can be accessed via
 * the getter methods of the {@link VideoController} class.
 */
@Slf4j
public class VideoControllerExample {

    public static void main(String[] args) {

        List<VideoController> videoControllers = new VideoControllerService().get();
        videoControllers.forEach(videoController -> log.info("VideoController: \n{}", videoController));

        // individual fields are accessible via getter methods
    }
}

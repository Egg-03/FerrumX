package org.ferrumx.example.display;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.display.VideoController;
import org.ferrumx.core.service.display.VideoControllerService;

import java.util.List;

@Slf4j
public class VideoControllerExample {

    public static void main(String[] args) {

        List<VideoController> videoControllers = new VideoControllerService().getVideoControllers();
        videoControllers.forEach(videoController -> log.info("VideoController: \n{}", videoController));

        // individual fields are accessible via getter methods
    }
}

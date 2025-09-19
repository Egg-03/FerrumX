package org.ferrumx.example.mainboard;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.mainboard.Mainboard;
import org.ferrumx.core.service.mainboard.MainboardService;

import java.util.Optional;

@Slf4j
public class MainboardExample {

    public static void main(String[] args) {

        // with Optional
        Optional<Mainboard> optionalMainboard = new MainboardService().getMainboard();
        optionalMainboard.ifPresent(mainboard -> log.info("Mainboard: \n{}", mainboard));

        // without Optional
        Mainboard mainboard = new MainboardService().getMainboard().orElseThrow(); // will throw NoSuchElementException if not present
        log.info("Mainboard: \n{}", mainboard);

        // individual fields are accessible via getter methods

    }
}

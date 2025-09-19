package org.ferrumx.example.mainboard;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.mainboard.Bios;
import org.ferrumx.core.service.mainboard.BiosService;

import java.util.List;

@Slf4j
public class BiosExample {

    public static void main(String[] args) {

        List<Bios> biosList = new BiosService().getBios();
        biosList.forEach(bios -> log.info("Bios : \n{}", bios));

        // individual fields are accessible via getter methods

    }
}

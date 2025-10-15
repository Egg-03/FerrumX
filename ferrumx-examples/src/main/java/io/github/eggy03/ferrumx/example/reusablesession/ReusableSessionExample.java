package io.github.eggy03.ferrumx.example.reusablesession;

import com.profesorfalken.jpowershell.PowerShell;
import lombok.extern.slf4j.Slf4j;
import io.github.eggy03.ferrumx.core.entity.processor.Processor;
import io.github.eggy03.ferrumx.core.entity.processor.ProcessorCache;
import io.github.eggy03.ferrumx.core.service.processor.ProcessorCacheService;
import io.github.eggy03.ferrumx.core.service.processor.ProcessorService;

import java.util.List;

/**
 * Unlike other examples where a session is managed for you automatically, this example contains methods
 * that allow you to pass a re-usable session as a parameter, that you create yourself.
 * This is useful if you want to run multiple queries in the same session,
 * since each session spawns it's individual powershell process.
 * <p>
 * You are responsible for maintaining the scope of the session
 * PowerShell implements auto-closeable.
 * </p>
 */
@Slf4j
public class ReusableSessionExample {

    public static void main (String[] args) {

        try(PowerShell powerShell = PowerShell.openSession()) {

            List<Processor> processorList = new ProcessorService().get(powerShell);

            List<ProcessorCache> processorCacheList = new ProcessorCacheService().get(powerShell);

            processorList.forEach(processor -> log.info(processor.toString()));
            processorCacheList.forEach(processorCache -> log.info(processorCache.toString()));

        }
    }
}

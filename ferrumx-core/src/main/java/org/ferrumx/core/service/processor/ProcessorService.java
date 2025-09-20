package org.ferrumx.core.service.processor;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.processor.Processor;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Service class for fetching CPU information from the system.
 * <p>
 * This class executes the {@link CimQuery#PROCESSOR_QUERY} PowerShell command
 * and maps the resulting JSON into an {@link Optional} {@link Processor} object.
 */
public class ProcessorService {

    /**
     * Retrieves an {@link Optional} containing the processor information.
     *
     * @return an {@link Optional} of {@link Processor} representing the CPU.
     *         Returns {@link Optional#empty()} if no processor information is detected.
     * @throws RuntimeException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public Optional<Processor> getProcessor() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PROCESSOR_QUERY.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), Processor.class);
    }
}

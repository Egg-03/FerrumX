package org.ferrumx.core.service.processor;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.processor.Processor;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * Service class for fetching CPU information from the system.
 * <p>
 * This class executes the {@link CimQuery#PROCESSOR_QUERY} PowerShell command
 * and maps the resulting JSON into {@link Processor} objects.
 * <p>
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * ProcessorService processorService = new ProcessorService();
 * List<Processor> processors = processorService.getProcessors();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     ProcessorService processorService = new ProcessorService();
 *     List<Processor> processors = processorService.getProcessors(session);
 * }
 * }</pre>
 */

public class ProcessorService {

    /**
     * Retrieves an {@link Optional} containing the processor information.
     * <p>
     * @deprecated This method is not reliable on systems with multiple processors,
     * as it only maps a single {@link Processor} entry from the PowerShell output.
     * It will fail in multi-CPU environments.
     * <p>
     * Use one of the following instead:
     * <ul>
     *   <li>{@link #getProcessors()} — creates a short-lived PowerShell session internally.</li>
     *   <li>{@link #getProcessors(PowerShell)} — uses a caller-managed PowerShell session.</li>
     * </ul>
     *
     * @return an {@link Optional} of {@link Processor} representing a single CPU entry.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                                            or parsing the output.
     */
    @NotNull
    @Deprecated(forRemoval = true)
    public Optional<Processor> getProcessor() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PROCESSOR_QUERY.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), Processor.class);
    }

    /**
     * Retrieves a non-null list of processor entries present in the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     * Not thread-safe.
     *
     * @return a list of {@link Processor} objects representing the CPU(s).
     *         Returns an empty list if no processors are detected.
     */
    @NotNull
    public List<Processor> getProcessors() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PROCESSOR_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Processor.class);
    }

    /**
     * Retrieves a non-null list of processor entries using the caller's {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link Processor} objects representing the CPU(s).
     *         Returns an empty list if no processors are detected.
     */
    @NotNull
    public List<Processor> getProcessors(PowerShell powerShell) {
        PowerShellResponse response = powerShell.executeCommand(CimQuery.PROCESSOR_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Processor.class);
    }
}

package org.ferrumx.core.service.memory;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.memory.PhysicalMemory;
import org.ferrumx.core.mapping.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching information about physical memory modules (RAM) in the system.
 * <p>
 * This class executes the {@link CimQuery#PHYSICAL_MEMORY_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link PhysicalMemory} objects.
 * <p>
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * PhysicalMemoryService memoryService = new PhysicalMemoryService();
 * List<PhysicalMemory> memories = memoryService.getPhysicalMemories();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     PhysicalMemoryService memoryService = new PhysicalMemoryService();
 *     List<PhysicalMemory> memories = memoryService.getPhysicalMemories(session);
 * }
 * }</pre>
 */

public class PhysicalMemoryService {

    /**
     * Retrieves a list of physical memory modules present in the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     * <p>
     * Not thread-safe.
     * <p>
     * As a workaround, you may create and close an empty {@link PowerShell} session before
     * calling this method or other methods of the same signature, concurrently.
     *
     * @return a list of {@link PhysicalMemory} objects representing the system's RAM.
     *         Returns an empty list if no memory modules are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<PhysicalMemory> getPhysicalMemories() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PHYSICAL_MEMORY_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), PhysicalMemory.class);
    }

    /**
     * Retrieves a list of physical memory modules using the caller's {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link PhysicalMemory} objects representing the system's RAM.
     *         Returns an empty list if no memory modules are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<PhysicalMemory> getPhysicalMemories(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.PHYSICAL_MEMORY_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), PhysicalMemory.class);
    }

}
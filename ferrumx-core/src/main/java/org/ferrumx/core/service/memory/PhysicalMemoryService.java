package org.ferrumx.core.service.memory;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.memory.PhysicalMemory;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching information about physical memory modules (RAM) in the system.
 * <p>
 * This class executes the {@link CimQuery#PHYSICAL_MEMORY_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link PhysicalMemory} objects.
 */
public class PhysicalMemoryService {

    /**
     * Retrieves a non-null list of physical memory modules present in the system.
     *
     * @return a list of {@link PhysicalMemory} objects representing the system's RAM.
     *         Returns an empty list if no memory modules are detected.
     * @throws RuntimeException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<PhysicalMemory> getPhysicalMemories() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PHYSICAL_MEMORY_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), PhysicalMemory.class);
    }

}

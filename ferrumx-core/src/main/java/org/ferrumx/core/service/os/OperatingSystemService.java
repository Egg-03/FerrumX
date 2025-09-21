package org.ferrumx.core.service.os;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.os.OperatingSystem;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching operating system information from the system.
 * <p>
 * This class executes the {@link CimQuery#OPERATING_SYSTEM_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link OperatingSystem} objects.
 * <p>
 * This service is stateless and thread-safe; multiple threads can safely
 * invoke {@link #getOperatingSystems()} concurrently.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * OperatingSystemService osService = new OperatingSystemService();
 * List<OperatingSystem> operatingSystems = osService.getOperatingSystems();
 * operatingSystems.forEach(System.out::println);
 * }</pre>
 */

public class OperatingSystemService {

    /**
     * Retrieves a non-null list of operating systems present on the system.
     *
     * @return a list of {@link OperatingSystem} objects representing the system's operating systems.
     *         If no operating systems are present, returns an empty list.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<OperatingSystem> getOperatingSystems() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.OPERATING_SYSTEM_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), OperatingSystem.class);
    }
}

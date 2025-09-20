package org.ferrumx.core.service.mainboard;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.mainboard.Mainboard;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Service class for fetching mainboard information from the system.
 * <p>
 * This class executes the {@link CimQuery#MAINBOARD_QUERY} PowerShell command
 * and maps the resulting JSON into a {@link Mainboard} object.
 */
public class MainboardService {

    /**
     * Retrieves an {@link Optional} containing the system's mainboard information.
     *
     * @return an {@link Optional} of {@link Mainboard} representing the system's mainboard.
     *         Returns {@link Optional#empty()} if no mainboard information is detected.
     * @throws RuntimeException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public Optional<Mainboard> getMainboard() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.MAINBOARD_QUERY.getQuery());
        return MapperUtil.mapToObject(response.getCommandOutput(), Mainboard.class);
    }
}

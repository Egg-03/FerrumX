package org.ferrumx.core.service.battery;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.battery.Battery;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching battery information from the system.
 * <p>
 * This class executes the {@link CimQuery#BATTERY_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link Battery} objects.
 */
public class BatteryService {

    /**
     * Retrieves a non-null list of batteries present on the system.
     *
     * @return a list of {@link Battery} objects representing the system's batteries.
     *         If no batteries are present, returns an empty list.
     * @throws RuntimeException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<Battery> getBatteries() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.BATTERY_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Battery.class);
    }
}
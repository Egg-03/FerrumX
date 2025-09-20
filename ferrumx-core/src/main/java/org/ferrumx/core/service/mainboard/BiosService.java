package org.ferrumx.core.service.mainboard;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.mainboard.Bios;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching BIOS information from the system.
 * <p>
 * This class executes the {@link CimQuery#BIOS_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link Bios} objects.
 */
public class BiosService {

    /**
     * Retrieves a non-null list of BIOS entries present in the system.
     *
     * @return a list of {@link Bios} objects representing the system BIOS.
     *         Returns an empty list if no BIOS entries are detected.
     * @throws RuntimeException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<Bios> getBios() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.BIOS_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), Bios.class);
    }
}

package org.ferrumx.core.service.storage;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.storage.DiskDrive;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching information about disk drives.
 * <p>
 * This class executes the {@link CimQuery#DISK_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link DiskDrive} objects.
 */
public class DiskDriveService {

    /**
     * Retrieves a non-null list of disk drives present in the system.
     *
     * @return a list of {@link DiskDrive} objects representing the disk drives.
     *         Returns an empty list if no disk drives are detected.
     * @throws RuntimeException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<DiskDrive> getDiskDrives() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.DISK_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), DiskDrive.class);
    }
}

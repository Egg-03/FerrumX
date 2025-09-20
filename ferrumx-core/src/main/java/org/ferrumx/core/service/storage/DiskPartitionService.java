package org.ferrumx.core.service.storage;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.storage.DiskPartition;
import org.ferrumx.core.util.MapperUtil;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching information about disk partitions.
 * <p>
 * This class executes the {@link CimQuery#DISK_PARTITION_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link DiskPartition} objects.
 */
public class DiskPartitionService {

    /**
     * Retrieves a non-null list of disk partitions present in the system.
     *
     * @return a list of {@link DiskPartition} objects representing the disk partitions.
     *         Returns an empty list if no partitions are detected.
     * @throws RuntimeException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<DiskPartition> getDiskPartitions() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.DISK_PARTITION_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), DiskPartition.class);
    }

}

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
 * <p>
 * <h2>Thread safety</h2>
 * This class is not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * DiskPartitionService partitionService = new DiskPartitionService();
 * List<DiskPartition> partitions = partitionService.getDiskPartitions();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     DiskPartitionService partitionService = new DiskPartitionService();
 *     List<DiskPartition> partitions = partitionService.getDiskPartitions(session);
 * }
 * }</pre>
 */

public class DiskPartitionService {

    /**
     * Retrieves a non-null list of disk partitions present in the system.
     * <p>
     * Not thread-safe.
     *
     * @return a list of {@link DiskPartition} objects representing the disk partitions.
     *         Returns an empty list if no partitions are detected.
     */
    @NotNull
    public List<DiskPartition> getDiskPartitions() {
        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.DISK_PARTITION_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), DiskPartition.class);
    }

    /**
     * Retrieves a non-null list of disk partitions using the caller's {@link PowerShell} session.
     * <p>
     * Not thread-safe. The provided session must not be shared across threads.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link DiskPartition} objects representing the disk partitions.
     *         Returns an empty list if no partitions are detected.
     */
    @NotNull
    public List<DiskPartition> getDiskPartitions(PowerShell powerShell) {
        PowerShellResponse response = powerShell.executeCommand(CimQuery.DISK_PARTITION_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), DiskPartition.class);
    }

}
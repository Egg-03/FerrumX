package org.ferrumx.core.service.processor;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.processor.ProcessorCache;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.mapping.MapperUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching processor cache information from the system.
 * <p>
 * This class executes the {@link CimQuery#PROCESSOR_CACHE_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link ProcessorCache} objects.
 * <p>
 * <h2>Thread safety</h2>
 * Methods of class are not thread safe.
 *
 * <h2>Usage examples</h2>
 * <pre>{@code
 * // Convenience API (creates its own short-lived session)
 * ProcessorCacheService cacheService = new ProcessorCacheService();
 * List<ProcessorCache> caches = cacheService.getProcessorCaches();
 *
 * // API with re-usable session (caller manages session lifecycle, not thread-safe)
 * try (PowerShell session = PowerShell.openSession()) {
 *     ProcessorCacheService cacheService = new ProcessorCacheService();
 *     List<ProcessorCache> caches = cacheService.getProcessorCaches(session);
 * }
 * }</pre>
 */

public class ProcessorCacheService {

    /**
     * Retrieves a list of processor cache entries present in the system.
     * <p>
     * Each invocation creates and uses a short-lived PowerShell session internally.
     *
     * @return a list of {@link ProcessorCache} objects representing the CPU caches.
     *         Returns an empty list if none are detected.
     */
    @NotNull
    public List<ProcessorCache> getProcessorCaches() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PROCESSOR_CACHE_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), ProcessorCache.class);
    }

    /**
     * Retrieves a list of processor cache entries using the caller's {@link PowerShell} session.
     *
     * @param powerShell an existing PowerShell session managed by the caller
     * @return a list of {@link ProcessorCache} objects representing the CPU caches.
     *         Returns an empty list if none are detected.
     */
    @NotNull
    public List<ProcessorCache> getProcessorCaches(PowerShell powerShell) {

        PowerShellResponse response = powerShell.executeCommand(CimQuery.PROCESSOR_CACHE_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), ProcessorCache.class);
    }

}

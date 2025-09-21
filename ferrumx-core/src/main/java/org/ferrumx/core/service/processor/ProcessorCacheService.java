package org.ferrumx.core.service.processor;

import org.ferrumx.core.constant.CimQuery;
import org.ferrumx.core.entity.processor.ProcessorCache;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.util.MapperUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Service class for fetching processor cache information from the system.
 * <p>
 * This class executes the {@link CimQuery#PROCESSOR_CACHE_QUERY} PowerShell command
 * and maps the resulting JSON into a list of {@link ProcessorCache} objects.
 * <p>
 * This service is stateless and thread-safe; multiple threads can safely
 * invoke {@link #getProcessorCaches()} concurrently.
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * ProcessorCacheService cacheService = new ProcessorCacheService();
 * List<ProcessorCache> caches = cacheService.getProcessorCaches();
 * caches.forEach(System.out::println);
 * }</pre>
 */

public class ProcessorCacheService {

    /**
     * Retrieves a non-null list of processor cache entries present in the system.
     *
     * @return a list of {@link ProcessorCache} objects representing the CPU caches.
     *         Returns an empty list if no cache entries are detected.
     * @throws com.google.gson.JsonSyntaxException if there is an error executing the PowerShell command
     *                          or parsing the output.
     */
    @NotNull
    public List<ProcessorCache> getProcessorCaches() {

        PowerShellResponse response = PowerShell.executeSingleCommand(CimQuery.PROCESSOR_CACHE_QUERY.getQuery());
        return MapperUtil.mapToList(response.getCommandOutput(), ProcessorCache.class);
    }

}

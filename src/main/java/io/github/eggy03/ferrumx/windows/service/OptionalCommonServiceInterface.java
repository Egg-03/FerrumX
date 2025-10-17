package io.github.eggy03.ferrumx.windows.service;

import com.profesorfalken.jpowershell.PowerShell;
import io.github.eggy03.ferrumx.windows.mapping.MapperUtil;

import java.util.Optional;

/**
 * Common service interface whose method implementations provide a way to fetch WMI data from Powershell
 * in the form of {@link Optional}
 * @param <S> the entity type returned by the service implementation
 * @author Egg-03
 * @since 2.2.0
 * @see CommonServiceInterface
 */
public interface OptionalCommonServiceInterface<S> {

    /**
     * Implementations of this method are expected to query the Powershell using methods
     * that automatically handle the Powershell process lifecycle and then use {@link MapperUtil}
     * to map the results to the expected entity types
     * @return an {@link Optional} entity of type {@code <S>} defined by the caller
     */
    Optional<S> get();

    /**
     * Implementations of this method are expected to query the Powershell using methods
     * that delegate the responsibility of managing the Powershell session to the caller
     * @param powerShell the caller-managed powershell session passed to the method
     * @return an {@link Optional} entity of type {@code <S>} defined by the caller
     */
    Optional<S> get(PowerShell powerShell);
}
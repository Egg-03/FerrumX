package unit.processor;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.entity.processor.ProcessorCache;
import org.ferrumx.core.service.processor.ProcessorCacheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class ProcessorCacheServiceTest {

    private ProcessorCacheService processorCacheService;

    private final String jsonProcessor = """
                [
                  {
                    "DeviceID": "1",
                    "Purpose": "Level 1 Cache",
                    "InstalledSize": 32,
                    "Associativity": 0
                  },
                  {
                    "DeviceID": "2",
                    "Purpose": "Level 2 Cache",
                    "InstalledSize": 256,
                    "Associativity": 7
                  }
                ]
                """;

    @BeforeEach
    void setUp() {
        processorCacheService = new ProcessorCacheService();
    }

    @Test
    void test_getProcessorCache_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonProcessor);

        try(MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<ProcessorCache> cache = processorCacheService.getProcessorCaches();
            assertFalse(cache.isEmpty());
            assertEquals("1", cache.get(0).getDeviceId());
            assertEquals("2", cache.get(1).getDeviceId());
        }
    }

    @Test
    void test_getProcessorCache_emptyJson_empty() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try(MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<ProcessorCache> cache = processorCacheService.getProcessorCaches();
            assertTrue(cache.isEmpty());
        }
    }

    @Test
    void test_getProcessorCache_malformedJson_throwsException() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try(MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, ()-> processorCacheService.getProcessorCaches());
        }
    }

    @Test
    void test_getProcessorCache_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonProcessor);

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<ProcessorCache> cache = processorCacheService.getProcessorCaches(mockShell);
            assertFalse(cache.isEmpty());
            assertEquals("1", cache.get(0).getDeviceId());
            assertEquals("2", cache.get(1).getDeviceId());
        }
    }

    @Test
    void test_getProcessorCache_withSession_emptyJson_empty() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<ProcessorCache> cache = processorCacheService.getProcessorCaches(mockShell);
            assertTrue(cache.isEmpty());
        }
    }

    @Test
    void test_getProcessorCache_withSession_malformedJson_throwsException() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, ()-> processorCacheService.getProcessorCaches(mockShell));
        }
    }
}
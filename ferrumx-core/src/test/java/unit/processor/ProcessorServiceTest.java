package unit.processor;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.entity.processor.Processor;
import org.ferrumx.core.service.processor.ProcessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class ProcessorServiceTest {

    private ProcessorService processorService;

    @BeforeEach
    void setUp() {
        processorService = new ProcessorService();
    }

    @Test
    void test_GetProcessor_success() {

        String jsonProcessor = """
                {
                  "DeviceID": "CPU0",
                  "Name": "Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz"
                }
                """;

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonProcessor);

        try(MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            Optional<Processor> processor = processorService.getProcessor();
            assertTrue(processor.isPresent());
            assertEquals("CPU0", processor.get().getDeviceId());
            assertEquals("Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz", processor.get().getName());
        }
    }

    @Test
    void test_getProcessor_emptyJson_empty() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try(MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            Optional<Processor> processor = processorService.getProcessor();
            assertTrue(processor.isEmpty());
        }
    }

    @Test
    void test_getProcessor_malformedJson_throwsException() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try(MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, ()-> processorService.getProcessor());
        }
    }
}

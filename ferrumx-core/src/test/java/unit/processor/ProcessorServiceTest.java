package unit.processor;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.entity.processor.Processor;
import org.ferrumx.core.service.processor.ProcessorService;
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

class ProcessorServiceTest {

    private ProcessorService processorService;

    private final String jsonProcessorArray = """
                [
                    {
                        "DeviceID": "CPU0",
                        "Name": "Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz"
                    },
                    {
                        "DeviceID": "CPU1",
                        "Name": "AMD Ryzen 3 1200 @ 3.10GHz"
                    }
                ]
                """;

    @BeforeEach
    void setUp() {
        processorService = new ProcessorService();
    }

    @Test
    void test_getProcessorList_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonProcessorArray);

        try(MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<Processor> processorList = processorService.get();
            assertFalse(processorList.isEmpty());
            assertEquals("CPU0", processorList.getFirst().getDeviceId());
            assertEquals("Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz", processorList.getFirst().getName());
            assertEquals("CPU1", processorList.getLast().getDeviceId());
            assertEquals("AMD Ryzen 3 1200 @ 3.10GHz", processorList.getLast().getName());
        }
    }

    @Test
    void test_getProcessorList_emptyJson_empty() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try(MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<Processor> processorList = processorService.get();
            assertTrue(processorList.isEmpty());
        }
    }

    @Test
    void test_getProcessorList_malformedJson_throwsException() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try(MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, ()-> processorService.get());
        }
    }

    @Test
    void test_getProcessor_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonProcessorArray);

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<Processor> processorList = processorService.get(mockShell);
            assertFalse(processorList.isEmpty());
            assertEquals("CPU0", processorList.getFirst().getDeviceId());
            assertEquals("Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz", processorList.getFirst().getName());
            assertEquals("CPU1", processorList.getLast().getDeviceId());
            assertEquals("AMD Ryzen 3 1200 @ 3.10GHz", processorList.getLast().getName());
        }
    }

    @Test
    void test_getProcessor_withSession_emptyJson_empty() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<Processor> processorList = processorService.get(mockShell);
            assertTrue(processorList.isEmpty());
        }
    }

    @Test
    void test_getProcessor_withSession_malformedJson_throwsException() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);
            assertThrows(JsonSyntaxException.class, ()-> processorService.get(mockShell));
        }
    }
}

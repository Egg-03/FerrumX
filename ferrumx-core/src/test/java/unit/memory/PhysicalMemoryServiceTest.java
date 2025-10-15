package unit.memory;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import io.github.eggy03.ferrumx.core.entity.memory.PhysicalMemory;
import io.github.eggy03.ferrumx.core.service.memory.PhysicalMemoryService;
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

class PhysicalMemoryServiceTest {

    private PhysicalMemoryService physicalMemoryService;

    private final String json = """
                [
                  {
                    "Tag": "Physical Memory 0",
                    "Name": "DIMM0",
                    "Manufacturer": "Kingston",
                    "Capacity": 8589934592,
                    "Speed": 3200,
                    "SerialNumber": "12345678"
                  },
                  {
                    "Tag": "Physical Memory 1",
                    "Name": "DIMM1",
                    "Manufacturer": "Corsair",
                    "Capacity": 8589934592,
                    "Speed": 3200,
                    "SerialNumber": "87654321"
                  }
                ]
                """;

    @BeforeEach
    void setUp() {
        physicalMemoryService = new PhysicalMemoryService();
    }

    @Test
    void test_getPhysicalMemories_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (MockedStatic<PowerShell> mockPowershell = mockStatic(PowerShell.class)) {
            mockPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<PhysicalMemory> memories = physicalMemoryService.get();
            assertFalse(memories.isEmpty());
            assertEquals("Physical Memory 0", memories.get(0).getTag());
            assertEquals("DIMM0", memories.get(0).getName());
            assertEquals("Physical Memory 1", memories.get(1).getTag());
            assertEquals("DIMM1", memories.get(1).getName());
        }
    }

    @Test
    void test_getPhysicalMemories_emptyJson_returnsEmptyList() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString()))
                    .thenReturn(mockResponse);

            List<PhysicalMemory> memories = physicalMemoryService.get();
            assertTrue(memories.isEmpty());
        }
    }

    @Test
    void test_getPhysicalMemories_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (MockedStatic<PowerShell> mockPowershell = mockStatic(PowerShell.class)) {
            mockPowershell.when(() -> PowerShell.executeSingleCommand(anyString()))
                    .thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class,
                    () -> physicalMemoryService.get());
        }
    }

    @Test
    void test_getPhysicalMemories_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<PhysicalMemory> memories = physicalMemoryService.get(mockShell);
            assertFalse(memories.isEmpty());
            assertEquals("Physical Memory 0", memories.get(0).getTag());
            assertEquals("DIMM0", memories.get(0).getName());
            assertEquals("Physical Memory 1", memories.get(1).getTag());
            assertEquals("DIMM1", memories.get(1).getName());
        }
    }

    @Test
    void test_getPhysicalMemories_withSession_emptyJson_returnsEmptyList() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<PhysicalMemory> memories = physicalMemoryService.get(mockShell);
            assertTrue(memories.isEmpty());
        }
    }

    @Test
    void test_getPhysicalMemories_withSession_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);
            assertThrows(JsonSyntaxException.class, () -> physicalMemoryService.get(mockShell));
        }
    }
}

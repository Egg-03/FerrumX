package unit.storage;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import io.github.eggy03.ferrumx.windows.entity.storage.DiskPartition;
import io.github.eggy03.ferrumx.windows.service.storage.DiskPartitionService;
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

class DiskPartitionServiceTest {

    private DiskPartitionService diskPartitionService;

    private final String json = """
                [
                  {
                    "DeviceID": "Disk0_Part1",
                    "Name": "C:",
                    "Description": "System Partition",
                    "Size": 500107862016,
                    "Bootable": true,
                    "PrimaryPartition": true,
                    "BootPartition": true,
                    "DiskIndex": 0
                  },
                  {
                    "DeviceID": "Disk0_Part2",
                    "Name": "D:",
                    "Description": "Data Partition",
                    "Size": 500107862016,
                    "Bootable": false,
                    "PrimaryPartition": true,
                    "BootPartition": false,
                    "DiskIndex": 0
                  }
                ]
                """;

    @BeforeEach
    void setUp() {
        diskPartitionService = new DiskPartitionService();
    }

    @Test
    void test_getDiskPartitions_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<DiskPartition> partitions = diskPartitionService.get();
            assertFalse(partitions.isEmpty());
            assertEquals("Disk0_Part1", partitions.get(0).getDeviceId());
            assertEquals("C:", partitions.get(0).getName());
            assertEquals("Disk0_Part2", partitions.get(1).getDeviceId());
            assertEquals("D:", partitions.get(1).getName());
        }
    }

    @Test
    void test_getDiskPartitions_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<DiskPartition> partitions = diskPartitionService.get();
            assertTrue(partitions.isEmpty());
        }
    }

    @Test
    void test_getDiskPartitions_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> diskPartitionService.get());
        }
    }

    @Test
    void test_getDiskPartitions_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<DiskPartition> partitions = diskPartitionService.get(mockShell);
            assertFalse(partitions.isEmpty());
            assertEquals("Disk0_Part1", partitions.get(0).getDeviceId());
            assertEquals("C:", partitions.get(0).getName());
            assertEquals("Disk0_Part2", partitions.get(1).getDeviceId());
            assertEquals("D:", partitions.get(1).getName());
        }
    }

    @Test
    void test_getDiskPartitions_withSession_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<DiskPartition> partitions = diskPartitionService.get(mockShell);
            assertTrue(partitions.isEmpty());
        }
    }

    @Test
    void test_getDiskPartitions_withSession_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> diskPartitionService.get(mockShell));
        }
    }
}

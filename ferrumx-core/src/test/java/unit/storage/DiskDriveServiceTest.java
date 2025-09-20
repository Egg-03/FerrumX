package unit.storage;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.entity.storage.DiskDrive;
import org.ferrumx.core.service.storage.DiskDriveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class DiskDriveServiceTest {

    private DiskDriveService diskDriveService;

    @BeforeEach
    void setUp() {
        diskDriveService = new DiskDriveService();
    }

    @Test
    void test_getDiskDrives_success() {
        String json = """
                [
                  {
                    "DeviceID": "Disk0",
                    "Caption": "Samsung SSD 970",
                    "Model": "970 EVO Plus",
                    "Size": 1000204886016,
                    "SerialNumber": "S4EMNX0K123456",
                    "Partitions": 3,
                    "Status": "OK",
                    "InterfaceType": "NVMe"
                  },
                  {
                    "DeviceID": "Disk1",
                    "Caption": "WD Blue HDD",
                    "Model": "WD10EZEX",
                    "Size": 1000204886016,
                    "SerialNumber": "WD-WCC4E1234567",
                    "Partitions": 1,
                    "Status": "OK",
                    "InterfaceType": "SATA"
                  }
                ]
                """;

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<DiskDrive> disks = diskDriveService.getDiskDrives();
            assertFalse(disks.isEmpty());
            assertEquals("Disk0", disks.get(0).getDeviceId());
            assertEquals("Samsung SSD 970", disks.get(0).getCaption());
            assertEquals("Disk1", disks.get(1).getDeviceId());
            assertEquals("WD Blue HDD", disks.get(1).getCaption());
        }
    }

    @Test
    void test_getDiskDrives_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<DiskDrive> disks = diskDriveService.getDiskDrives();
            assertTrue(disks.isEmpty());
        }
    }

    @Test
    void test_getDiskDrives_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> diskDriveService.getDiskDrives());
        }
    }
}

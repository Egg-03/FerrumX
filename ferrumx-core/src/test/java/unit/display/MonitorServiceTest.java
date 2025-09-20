package unit.display;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.entity.display.Monitor;
import org.ferrumx.core.service.display.MonitorService;
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

class MonitorServiceTest {

    private MonitorService monitorService;

    @BeforeEach
    void setUp() {
        monitorService = new MonitorService();
    }

    @Test
    void test_getMonitors_success() {

        String json = """
                [
                  {
                    "DeviceID": "MON1",
                    "Name": "Dell U2720Q",
                    "PNPDeviceID": "DISPLAY\\\\DELA0B1\\\\5&12345&0&UID4352",
                    "Status": "OK",
                    "MonitorManufacturer": "Dell Inc.",
                    "MonitorType": "LCD",
                    "PixelsPerXLogicalInch": 96,
                    "PixelsPerYLogicalInch": 96
                  },
                  {
                    "DeviceID": "MON2",
                    "Name": "LG UltraGear 27GL850",
                    "PNPDeviceID": "DISPLAY\\\\LGD1234\\\\5&67890&0&UID9832",
                    "Status": "OK",
                    "MonitorManufacturer": "LG Electronics",
                    "MonitorType": "LED",
                    "PixelsPerXLogicalInch": 110,
                    "PixelsPerYLogicalInch": 110
                  }
                ]
                """;

        PowerShellResponse mockedResponse = mock(PowerShellResponse.class);
        when(mockedResponse.getCommandOutput()).thenReturn(json);

        try(MockedStatic<PowerShell> powerShellMockedStatic = mockStatic(PowerShell.class)) {
            powerShellMockedStatic.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockedResponse);

            List<Monitor> monitors = monitorService.getMonitors();
            assertFalse(monitors.isEmpty());
            assertEquals("MON1", monitors.get(0).getDeviceId());
            assertEquals("Dell U2720Q", monitors.get(0).getName());
            assertEquals("MON2", monitors.get(1).getDeviceId());
            assertEquals("LG UltraGear 27GL850", monitors.get(1).getName());
        }
    }

    @Test
    void test_getMonitors_empty() {

        PowerShellResponse mockedResponse = mock(PowerShellResponse.class);
        when(mockedResponse.getCommandOutput()).thenReturn("");

        try(MockedStatic<PowerShell> powerShellMockedStatic = mockStatic(PowerShell.class)) {
            powerShellMockedStatic.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockedResponse);

            List<Monitor> monitors = monitorService.getMonitors();
            assertTrue(monitors.isEmpty());
        }
    }

    @Test
    void test_getMonitors_malformedJson_throwsException() {

        PowerShellResponse mockedResponse = mock(PowerShellResponse.class);
        when(mockedResponse.getCommandOutput()).thenReturn("not a valid json");

        try (MockedStatic<PowerShell> powerShellMockedStatic = mockStatic(PowerShell.class)) {
            powerShellMockedStatic.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockedResponse);

            assertThrows(JsonSyntaxException.class, () -> monitorService.getMonitors());
        }
    }
}

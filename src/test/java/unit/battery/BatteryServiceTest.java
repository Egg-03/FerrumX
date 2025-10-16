package unit.battery;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import io.github.eggy03.ferrumx.windows.entity.battery.Battery;
import io.github.eggy03.ferrumx.windows.service.battery.BatteryService;
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

class BatteryServiceTest {

    private BatteryService batteryService;

    private final String json = """
                [
                  {
                    "DeviceID": "BAT0",
                    "Caption": "Internal Battery",
                    "Name": "Battery 0",
                    "Status": "OK",
                    "BatteryStatus": 2,
                    "EstimatedChargeRemaining": 85
                  },
                  {
                    "DeviceID": "BAT1",
                    "Caption": "External Battery",
                    "Name": "Battery 1",
                    "Status": "OK",
                    "BatteryStatus": 2,
                    "EstimatedChargeRemaining": 100
                  }
                ]
                """;

    @BeforeEach
    void setUp() {
        batteryService = new BatteryService();
    }

    @Test
    void test_getBatteries_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<Battery> batteries = batteryService.get();
            assertFalse(batteries.isEmpty());
            assertEquals("BAT0", batteries.get(0).getDeviceId());
            assertEquals("Battery 0", batteries.get(0).getName());
            assertEquals("BAT1", batteries.get(1).getDeviceId());
            assertEquals("Battery 1", batteries.get(1).getName());
        }
    }

    @Test
    void test_getBatteries_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<Battery> batteries = batteryService.get();
            assertTrue(batteries.isEmpty());
        }
    }

    @Test
    void test_getBatteries_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> batteryService.get());
        }
    }

    @Test
    void test_getBatteries_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (PowerShell mockSession = mock(PowerShell.class)) {
            when(mockSession.executeCommand(anyString())).thenReturn(mockResponse);

            List<Battery> batteries = batteryService.get(mockSession);
            assertFalse(batteries.isEmpty());
            assertEquals("BAT0", batteries.get(0).getDeviceId());
            assertEquals("Battery 0", batteries.get(0).getName());
            assertEquals("BAT1", batteries.get(1).getDeviceId());
            assertEquals("Battery 1", batteries.get(1).getName());
        }
    }

    @Test
    void test_getBatteries_withSession_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockSession = mock(PowerShell.class)) {
            when(mockSession.executeCommand(anyString())).thenReturn(mockResponse);

            List<Battery> batteries = batteryService.get(mockSession);
            assertTrue(batteries.isEmpty());
        }
    }

    @Test
    void test_getBatteries_withSession_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (PowerShell mockSession = mock(PowerShell.class)) {
            when(mockSession.executeCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> batteryService.get(mockSession));
        }
    }
}

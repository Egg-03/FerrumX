package unit.network;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import io.github.eggy03.ferrumx.core.entity.network.NetworkAdapter;
import io.github.eggy03.ferrumx.core.service.network.NetworkAdapterService;
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

class NetworkAdapterServiceTest {

    private NetworkAdapterService networkAdapterService;

    private final String json = """
                [
                  {
                    "DeviceID": "1",
                    "Index": 0,
                    "Name": "Ethernet Adapter",
                    "MACAddress": "00-14-22-01-23-45",
                    "NetEnabled": true
                  },
                  {
                    "DeviceID": "2",
                    "Index": 1,
                    "Name": "Wi-Fi Adapter",
                    "MACAddress": "00-16-36-FF-EE-11",
                    "NetEnabled": false
                  }
                ]
                """;

    @BeforeEach
    void setUp() {
        networkAdapterService = new NetworkAdapterService();
    }

    @Test
    void test_getNetworkAdapters_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<NetworkAdapter> adapters = networkAdapterService.get();
            assertFalse(adapters.isEmpty());
            assertEquals("1", adapters.get(0).getDeviceId());
            assertEquals("Ethernet Adapter", adapters.get(0).getName());
            assertEquals("2", adapters.get(1).getDeviceId());
            assertEquals("Wi-Fi Adapter", adapters.get(1).getName());
        }
    }

    @Test
    void test_getNetworkAdapters_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<NetworkAdapter> adapters = networkAdapterService.get();
            assertTrue(adapters.isEmpty());
        }
    }

    @Test
    void test_getNetworkAdapters_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> networkAdapterService.get());
        }
    }

    @Test
    void test_getNetworkAdapters_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<NetworkAdapter> adapters = networkAdapterService.get(mockShell);
            assertFalse(adapters.isEmpty());
            assertEquals("1", adapters.get(0).getDeviceId());
            assertEquals("Ethernet Adapter", adapters.get(0).getName());
            assertEquals("2", adapters.get(1).getDeviceId());
            assertEquals("Wi-Fi Adapter", adapters.get(1).getName());
        }
    }

    @Test
    void test_getNetworkAdapters_withSession_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<NetworkAdapter> adapters = networkAdapterService.get(mockShell);
            assertTrue(adapters.isEmpty());
        }
    }

    @Test
    void test_getNetworkAdapters_withSession_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> networkAdapterService.get(mockShell));
        }
    }
}
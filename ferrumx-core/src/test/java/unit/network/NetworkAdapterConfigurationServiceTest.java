package unit.network;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import io.github.eggy03.ferrumx.core.entity.network.NetworkAdapterConfiguration;
import io.github.eggy03.ferrumx.core.service.network.NetworkAdapterConfigurationService;
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

class NetworkAdapterConfigurationServiceTest {

    private NetworkAdapterConfigurationService networkAdapterConfigurationService;

    private final String json = """
                [
                  {
                    "Index": 0,
                    "Description": "Ethernet Adapter",
                    "IPEnabled": true,
                    "IPAddress": ["192.168.0.10"],
                    "IPSubnet": ["255.255.255.0"],
                    "DefaultIPGateway": ["192.168.0.1"]
                  },
                  {
                    "Index": 1,
                    "Description": "Wi-Fi Adapter",
                    "IPEnabled": false,
                    "IPAddress": [],
                    "IPSubnet": [],
                    "DefaultIPGateway": []
                  }
                ]
                """;

    @BeforeEach
    void setUp() {
        networkAdapterConfigurationService = new NetworkAdapterConfigurationService();
    }

    @Test
    void test_getNetworkAdapterConfiguration_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<NetworkAdapterConfiguration> configs = networkAdapterConfigurationService.get();
            assertFalse(configs.isEmpty());
            assertEquals(0, configs.get(0).getIndex());
            assertEquals(Boolean.TRUE, configs.get(0).getIpEnabled());
            assertEquals(1, configs.get(1).getIndex());
            assertEquals(Boolean.FALSE, configs.get(1).getIpEnabled());
        }
    }

    @Test
    void test_getNetworkAdapterConfiguration_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<NetworkAdapterConfiguration> configs = networkAdapterConfigurationService.get();
            assertTrue(configs.isEmpty());
        }
    }

    @Test
    void test_getNetworkAdapterConfiguration_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> networkAdapterConfigurationService.get());
        }
    }

    @Test
    void test_getNetworkAdapterConfiguration_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<NetworkAdapterConfiguration> configs = networkAdapterConfigurationService.get(mockShell);
            assertFalse(configs.isEmpty());
            assertEquals(0, configs.get(0).getIndex());
            assertEquals(Boolean.TRUE, configs.get(0).getIpEnabled());
            assertEquals(1, configs.get(1).getIndex());
            assertEquals(Boolean.FALSE, configs.get(1).getIpEnabled());
        }
    }

    @Test
    void test_getNetworkAdapterConfiguration_withSession_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<NetworkAdapterConfiguration> configs = networkAdapterConfigurationService.get(mockShell);
            assertTrue(configs.isEmpty());
        }
    }

    @Test
    void test_getNetworkAdapterConfiguration_malformedJson_withSession_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> networkAdapterConfigurationService.get(mockShell));
        }
    }
}
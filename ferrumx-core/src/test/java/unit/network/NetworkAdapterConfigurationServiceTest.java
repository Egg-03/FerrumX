package unit.network;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.entity.network.NetworkAdapterConfiguration;
import org.ferrumx.core.service.network.NetworkAdapterConfigurationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class NetworkAdapterConfigurationServiceTest {

    private NetworkAdapterConfigurationService networkAdapterConfigurationService;

    @BeforeEach
    void setUp() {
        networkAdapterConfigurationService = new NetworkAdapterConfigurationService();
    }

    @Test
    void test_getNetworkAdapterConfiguration_success() {
        String json = """
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

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<NetworkAdapterConfiguration> configs = networkAdapterConfigurationService.getNetworkAdapterConfiguration();
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

            List<NetworkAdapterConfiguration> configs = networkAdapterConfigurationService.getNetworkAdapterConfiguration();
            assertTrue(configs.isEmpty());
        }
    }

    @Test
    void test_getNetworkAdapterConfiguration_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> networkAdapterConfigurationService.getNetworkAdapterConfiguration());
        }
    }
}

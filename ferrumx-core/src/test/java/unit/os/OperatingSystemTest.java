package unit.os;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.entity.os.OperatingSystem;
import org.ferrumx.core.service.os.OperatingSystemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class OperatingSystemServiceTest {

    private OperatingSystemService operatingSystemService;

    @BeforeEach
    void setUp() {
        operatingSystemService = new OperatingSystemService();
    }

    @Test
    void test_getOperatingSystem_success() {
        String json = """
                {
                  "Name": "Windows 11 Pro",
                  "Caption": "Microsoft Windows 11 Pro",
                  "InstallDate": "20230915090000.000000+000",
                  "CSName": "DESKTOP-1234",
                  "LastBootUpTime": "20250920070000.000000+000",
                  "NumberOfUsers": 1,
                  "Version": "11.0.22000",
                  "OSArchitecture": "64-bit"
                }
                """;

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<OperatingSystem> os = operatingSystemService.getOperatingSystems();
            assertFalse(os.isEmpty());
            assertEquals("Windows 11 Pro", os.getFirst().getName());
            assertEquals("Microsoft Windows 11 Pro", os.getFirst().getCaption());
            assertEquals("DESKTOP-1234", os.getFirst().getCsName());
            assertEquals("11.0.22000", os.getFirst().getVersion());
        }
    }

    @Test
    void test_getOperatingSystem_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<OperatingSystem> os = operatingSystemService.getOperatingSystems();
            assertTrue(os.isEmpty());
        }
    }

    @Test
    void test_getOperatingSystem_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> operatingSystemService.getOperatingSystems());
        }
    }
}

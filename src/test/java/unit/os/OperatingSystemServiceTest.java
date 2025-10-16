package unit.os;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import io.github.eggy03.ferrumx.windows.entity.os.OperatingSystem;
import io.github.eggy03.ferrumx.windows.service.os.OperatingSystemService;
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

class OperatingSystemServiceTest {

    private OperatingSystemService operatingSystemService;

    private final String json = """
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

    @BeforeEach
    void setUp() {
        operatingSystemService = new OperatingSystemService();
    }

    @Test
    void test_getOperatingSystem_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<OperatingSystem> os = operatingSystemService.get();
            assertFalse(os.isEmpty());
            assertEquals("Windows 11 Pro", os.get(0).getName());
            assertEquals("Microsoft Windows 11 Pro", os.get(0).getCaption());
            assertEquals("DESKTOP-1234", os.get(0).getCsName());
            assertEquals("11.0.22000", os.get(0).getVersion());
        }
    }

    @Test
    void test_getOperatingSystem_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<OperatingSystem> os = operatingSystemService.get();
            assertTrue(os.isEmpty());
        }
    }

    @Test
    void test_getOperatingSystem_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (MockedStatic<PowerShell> powerShellMock = mockStatic(PowerShell.class)) {
            powerShellMock.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> operatingSystemService.get());
        }
    }

    @Test
    void test_getOperatingSystem_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<OperatingSystem> os = operatingSystemService.get(mockShell);
            assertFalse(os.isEmpty());
            assertEquals("Windows 11 Pro", os.get(0).getName());
            assertEquals("Microsoft Windows 11 Pro", os.get(0).getCaption());
            assertEquals("DESKTOP-1234", os.get(0).getCsName());
            assertEquals("11.0.22000", os.get(0).getVersion());
        }
    }

    @Test
    void test_getOperatingSystem_withSession_empty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<OperatingSystem> os = operatingSystemService.get(mockShell);
            assertTrue(os.isEmpty());
        }
    }

    @Test
    void test_getOperatingSystem_withSession_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not a json");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> operatingSystemService.get(mockShell));
        }
    }
}
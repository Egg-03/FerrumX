package unit.mainboard;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.entity.mainboard.Mainboard;
import org.ferrumx.core.service.mainboard.MainboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MainboardServiceTest {

    private MainboardService mainboardService;

    private final String jsonMainboard = """
                {
                    "Manufacturer": "ASUS",
                    "Model": "ROG STRIX",
                    "Version": "1.0.0",
                    "SerialNumber": "123456789"
                }
               """;

    @BeforeEach
    void setUp() {
        mainboardService = new MainboardService();
    }

    @Test
    void test_getMainboard_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonMainboard);

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            Optional<Mainboard> mainboard = mainboardService.getMainboard();
            assertFalse(mainboard.isEmpty());
            assertEquals("ASUS", mainboard.get().getManufacturer());
            assertEquals("ROG STRIX", mainboard.get().getModel());
        }
    }

    @Test
    void test_getMainboard_emptyJson_returnsEmpty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            Optional<Mainboard> mainboard = mainboardService.getMainboard();
            assertTrue(mainboard.isEmpty());
        }
    }

    @Test
    void test_getMainboard_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> mainboardService.getMainboard());
        }
    }

    @Test
    void test_getMainboard_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonMainboard);

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            Optional<Mainboard> mainboard = mainboardService.getMainboard(mockShell);
            assertFalse(mainboard.isEmpty());
            assertEquals("ASUS", mainboard.get().getManufacturer());
            assertEquals("ROG STRIX", mainboard.get().getModel());
        }
    }

    @Test
    void test_getMainboard_withSession_emptyJson_returnsEmpty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            Optional<Mainboard> mainboard = mainboardService.getMainboard(mockShell);
            assertTrue(mainboard.isEmpty());
        }
    }

    @Test
    void test_getMainboard_withSession_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> mainboardService.getMainboard(mockShell));
        }
    }
}


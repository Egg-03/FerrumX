package unit.mainboard;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import io.github.eggy03.ferrumx.windows.entity.mainboard.Bios;
import io.github.eggy03.ferrumx.windows.service.mainboard.BiosService;
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

class BiosServiceTest {

    private BiosService biosService;

    private final String jsonBios = """
                {
                    "Caption": "BIOS",
                    "Description": "BIOS Description",
                    "Manufacturer": "Dell",
                    "Name": "MyPC",
                    "SMBIOSBIOSVersion": "1.0",
                    "Version": "1.0.0",
                    "ReleaseDate": "20230101000000.000000+000"
                }
                """;

    @BeforeEach
    void setUp() {
        biosService = new BiosService();
    }

    @Test
    void test_getBios_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonBios);

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<Bios> bios = biosService.get();
            assertFalse(bios.isEmpty());
            assertEquals("BIOS", bios.getFirst().getCaption());
            assertEquals("1.0.0", bios.getFirst().getVersion());
        }
    }

    @Test
    void test_getBios_emptyJson_returnsEmpty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<Bios> bios = biosService.get();
            assertTrue(bios.isEmpty());
        }
    }

    @Test
    void test_getBios_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> biosService.get());
        }
    }

    @Test
    void test_getBios_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonBios);

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<Bios> bios = biosService.get(mockShell);
            assertFalse(bios.isEmpty());
            assertEquals("BIOS", bios.getFirst().getCaption());
            assertEquals("1.0.0", bios.getFirst().getVersion());
        }
    }

    @Test
    void test_getBios_withSession_emptyJson_returnsEmpty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<Bios> bios = biosService.get(mockShell);
            assertTrue(bios.isEmpty());
        }
    }

    @Test
    void test_getBios_withSession_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (PowerShell mockShell = mock(PowerShell.class)) {
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);
            assertThrows(JsonSyntaxException.class, () -> biosService.get(mockShell));
        }
    }
}

package unit.mainboard;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.entity.mainboard.Bios;
import org.ferrumx.core.service.mainboard.BiosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BiosServiceTest {

    private BiosService biosService;

    @BeforeEach
    void setUp() {
        biosService = new BiosService();
    }

    @Test
    void test_getBios_success() {
        String jsonBios = """
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

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonBios);

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<Bios> bios = biosService.getBios();
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

            List<Bios> bios = biosService.getBios();
            assertTrue(bios.isEmpty());
        }
    }

    @Test
    void test_getBios_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> biosService.getBios());
        }
    }
}

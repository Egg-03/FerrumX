package unit.display;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import io.github.eggy03.ferrumx.windows.entity.display.VideoController;
import io.github.eggy03.ferrumx.windows.service.display.VideoControllerService;
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

class VideoControllerServiceTest {

    private VideoControllerService videoControllerService;

    private final String json = """
                [
                  {
                    "DeviceID": "Video1",
                    "Name": "NVIDIA RTX 3080",
                    "CurrentHorizontalResolution": 2560,
                    "CurrentVerticalResolution": 1440
                  },
                  {
                    "DeviceID": "Video2",
                    "Name": "Intel UHD Graphics 630",
                    "CurrentHorizontalResolution": 1920,
                    "CurrentVerticalResolution": 1080
                  }
                ]
                """;

    @BeforeEach
    void setUp() {
        videoControllerService = new VideoControllerService();
    }

    @Test
    void test_getVideoController_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)){
            mockedPowershell.when(()-> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<VideoController> videoControllers = videoControllerService.get();
            assertFalse(videoControllers.isEmpty());
            assertEquals("Video1", videoControllers.getFirst().getDeviceId());
            assertEquals("NVIDIA RTX 3080", videoControllers.getFirst().getName());
            assertEquals("Video2", videoControllers.get(1).getDeviceId());
            assertEquals("Intel UHD Graphics 630", videoControllers.get(1).getName());
        }
    }

    @Test
    void test_getVideoControllers_emptyJson_returnsEmptyList() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> mockedPowerShell = mockStatic(PowerShell.class)) {
            mockedPowerShell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            List<VideoController> controllers = videoControllerService.get();
            assertTrue(controllers.isEmpty());
        }
    }

    @Test
    void test_getVideoControllers_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not valid json");

        try (MockedStatic<PowerShell> mockedPowerShell = mockStatic(PowerShell.class)) {
            mockedPowerShell.when(() -> PowerShell.executeSingleCommand(anyString()))
                    .thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> videoControllerService.get());
        }
    }

    @Test
    void test_getVideoController_withSession_success() {

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(json);

        try (PowerShell mockShell = mock(PowerShell.class)){
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<VideoController> videoControllers = videoControllerService.get(mockShell);
            assertFalse(videoControllers.isEmpty());
            assertEquals("Video1", videoControllers.getFirst().getDeviceId());
            assertEquals("NVIDIA RTX 3080", videoControllers.getFirst().getName());
            assertEquals("Video2", videoControllers.get(1).getDeviceId());
            assertEquals("Intel UHD Graphics 630", videoControllers.get(1).getName());
        }
    }

    @Test
    void test_getVideoControllers_withSession_emptyJson_returnsEmptyList() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (PowerShell mockShell = mock(PowerShell.class)){
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            List<VideoController> videoControllers = videoControllerService.get(mockShell);
            assertTrue(videoControllers.isEmpty());
        }
    }

    @Test
    void test_getVideoControllers_withSession_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("not valid json");

        try (PowerShell mockShell = mock(PowerShell.class)){
            when(mockShell.executeCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> videoControllerService.get(mockShell));
        }
    }
}

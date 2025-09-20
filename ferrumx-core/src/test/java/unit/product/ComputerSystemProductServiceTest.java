package unit.product;

import com.google.gson.JsonSyntaxException;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import org.ferrumx.core.entity.product.ComputerSystemProduct;
import org.ferrumx.core.service.product.ComputerSystemProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ComputerSystemProductServiceTest {

    private ComputerSystemProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ComputerSystemProductService();
    }

    @Test
    void test_getProduct_success() {
        String jsonProduct = """
                {
                  "Caption": "Computer System Product",
                  "Description": "Some workstation",
                  "IdentifyingNumber": "12345-67890",
                  "Name": "MyPC",
                  "SKUNumber": "SKU-001",
                  "Vendor": "Dell",
                  "Version": "1.0",
                  "UUID": "550e8400-e29b-41d4-a716-446655440000"
                }
                """;

        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn(jsonProduct);

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            Optional<ComputerSystemProduct> product = productService.getProduct();
            assertTrue(product.isPresent());
            assertEquals("MyPC", product.get().getName());
            assertEquals("Dell", product.get().getVendor());
            assertEquals("SKU-001", product.get().getSkuNumber());
        }
    }

    @Test
    void test_getProduct_emptyJson_returnsEmpty() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("");

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            Optional<ComputerSystemProduct> product = productService.getProduct();
            assertTrue(product.isEmpty());
        }
    }

    @Test
    void test_getProduct_malformedJson_throwsException() {
        PowerShellResponse mockResponse = mock(PowerShellResponse.class);
        when(mockResponse.getCommandOutput()).thenReturn("invalid json");

        try (MockedStatic<PowerShell> mockedPowershell = mockStatic(PowerShell.class)) {
            mockedPowershell.when(() -> PowerShell.executeSingleCommand(anyString())).thenReturn(mockResponse);

            assertThrows(JsonSyntaxException.class, () -> productService.getProduct());
        }
    }
}

package io.github.eggy03.ferrumx.example.product;

import lombok.extern.slf4j.Slf4j;
import io.github.eggy03.ferrumx.core.entity.product.ComputerSystemProduct;
import io.github.eggy03.ferrumx.core.service.product.ComputerSystemProductService;

import java.util.Optional;

/**
 * Example class demonstrating how to fetch and display computer system product information
 * using {@link ComputerSystemProductService}.
 * <p>
 * This class retrieves an {@link Optional} {@link ComputerSystemProduct} object and logs its JSON
 * representation. Individual attributes of the product can be accessed via the getter methods
 * of the {@link ComputerSystemProduct} class.
 * </p>
 */
@Slf4j
public class ComputerSystemProductExample {

    public static void main(String[] args) {

        Optional<ComputerSystemProduct> optionalProduct = new ComputerSystemProductService().get();
        optionalProduct.ifPresent(product -> log.info("Product Details: \n{}", product));

        ComputerSystemProduct product = new ComputerSystemProductService().get().orElseThrow(); // will throw NoSuchElementException if not found
        log.info("Product Details: \n{}", product);

        // individual fields are accessible via getter methods
    }
}

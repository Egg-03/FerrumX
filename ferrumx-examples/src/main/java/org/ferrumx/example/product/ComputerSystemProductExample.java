package org.ferrumx.example.product;

import lombok.extern.slf4j.Slf4j;
import org.ferrumx.core.entity.product.ComputerSystemProduct;
import org.ferrumx.core.service.product.ComputerSystemProductService;

import java.util.Optional;

@Slf4j
public class ComputerSystemProductExample {

    public static void main(String[] args) {

        Optional<ComputerSystemProduct> optionalProduct = new ComputerSystemProductService().getProduct();
        optionalProduct.ifPresent(product -> log.info("Product Details: \n{}", product));

        ComputerSystemProduct product = new ComputerSystemProductService().getProduct().orElseThrow(); // will throw NoSuchElementException if not found
        log.info("Product Details: \n{}", product);

        // individual fields are accessible via getter methods
    }
}

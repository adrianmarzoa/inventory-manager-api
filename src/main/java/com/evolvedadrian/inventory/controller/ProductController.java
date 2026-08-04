package com.evolvedadrian.inventory.controller;

import com.evolvedadrian.inventory.dto.request.ProductRequestDTO;
import com.evolvedadrian.inventory.dto.response.ProductResponseDTO;
import com.evolvedadrian.inventory.entity.Product;
import com.evolvedadrian.inventory.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Integer id) {
        return this.productService.getProductById(id);
    }

    @GetMapping("/sku/{sku}")
    public Optional<ProductResponseDTO> findProductBySku(@PathVariable String sku) {
        return this.productService.findProductBySku(sku);
    }

    @GetMapping("/name/{name}")
    public List<ProductResponseDTO> findProductByName(@PathVariable String name) {
        return this.productService.findProductsByName(name);
    }

    @GetMapping("/category/{id}")
    public List<ProductResponseDTO> findProductByCategory(@PathVariable Integer id) {
        return this.productService.findProductsByCategory(id);
    }

    @GetMapping("/supplier/{id}")
    public List<ProductResponseDTO> findProductBySupplier(@PathVariable Integer id) {
        return this.productService.findProductsBySupplier(id);
    }

    @GetMapping(params = {"minPrice", "maxPrice"})
    public List<ProductResponseDTO> findProductByPriceBetween(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        return this.productService.findProductsByPriceBetween(minPrice, maxPrice);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        this.productService.deleteProduct(id);
    }

    @PostMapping
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO dto) {
        return this.productService.createProduct(dto);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Integer id, @RequestBody ProductRequestDTO dto) {
        return this.productService.updateProduct(id, dto);
    }
}

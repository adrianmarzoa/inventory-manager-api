package com.evolvedadrian.inventory.controller;

import com.evolvedadrian.inventory.dto.request.ProductRequestDTO;
import com.evolvedadrian.inventory.dto.response.ProductResponseDTO;
import com.evolvedadrian.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return this.productService.getAllProducts(pageable);
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
    public Page<ProductResponseDTO> findProductByName(@PathVariable String name, Pageable pageable) {
        return this.productService.findProductsByName(name, pageable);
    }

    @GetMapping("/category/{id}")
    public Page<ProductResponseDTO> findProductByCategory(@PathVariable Integer id, Pageable pageable) {
        return this.productService.findProductsByCategory(id, pageable);
    }

    @GetMapping("/supplier/{id}")
    public Page<ProductResponseDTO> findProductBySupplier(@PathVariable Integer id, Pageable pageable) {
        return this.productService.findProductsBySupplier(id, pageable);
    }

    @GetMapping(params = {"minPrice", "maxPrice"})
    public Page<ProductResponseDTO> findProductByPriceBetween(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice, Pageable pageable) {
        return this.productService.findProductsByPriceBetween(minPrice, maxPrice, pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        this.productService.deleteProduct(id);
    }

    @PostMapping
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO dto) {
        return this.productService.createProduct(dto);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductRequestDTO dto) {
        return this.productService.updateProduct(id, dto);
    }
}

package com.evolvedadrian.inventory.service;

import com.evolvedadrian.inventory.entity.Product;
import com.evolvedadrian.inventory.repository.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found."));
    }

    public Product createProduct(Product product) {
        if(this.productRepository.existsBySku(product.getSku())){
            throw new RuntimeException("Sku already exists.");
        }
        return this.productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        if (!this.productRepository.existsById(product.getId())) {
            throw new RuntimeException("Product does not exist.");
        }
        return this.productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        Product product = getProductById(id);
        this.productRepository.delete(product);
    }

    public Optional<Product> findProductBySku(String sku) {
        return this.productRepository.findBySku(sku);
    }

    public List<Product> findProductsByName(String name) {
        return this.productRepository.findByName(name);
    }

    public List<Product> findProductsByCategory(Integer categoryId) {
        return this.productRepository.findByCategoryId(categoryId);
    }

    public List<Product> findProductsBySupplier(Integer supplierId) {
        return this.productRepository.findBySupplierId(supplierId);
    }

    public List<Product> findProductsByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return this.productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
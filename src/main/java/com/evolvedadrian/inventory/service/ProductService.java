package com.evolvedadrian.inventory.service;

import com.evolvedadrian.inventory.dto.request.ProductRequestDTO;
import com.evolvedadrian.inventory.dto.response.ProductResponseDTO;
import com.evolvedadrian.inventory.entity.Category;
import com.evolvedadrian.inventory.entity.Product;
import com.evolvedadrian.inventory.entity.Supplier;
import com.evolvedadrian.inventory.mapper.ProductMapper;
import com.evolvedadrian.inventory.repository.CategoryRepository;
import com.evolvedadrian.inventory.repository.ProductRepository;
import com.evolvedadrian.inventory.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        List<ProductResponseDTO> dtos = new ArrayList<>();

        for (Product product : products) {
            dtos.add(this.productMapper.toResponse(product));
        }

        return dtos;
    }

    public ProductResponseDTO getProductById(Integer id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found."));

        return this.productMapper.toResponse(product);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        if (this.productRepository.existsBySku(dto.getSku())) {
            throw new RuntimeException("Sku already exists.");
        }

        Product product = this.productMapper.toEntity(dto);

        Product saved = this.productRepository.save(product);

        return this.productMapper.toResponse(saved);
    }

    public ProductResponseDTO updateProduct(Integer id, ProductRequestDTO dto) {
        if (!this.productRepository.existsById(id)) {
            throw new RuntimeException("Product does not exist.");
        }

        Product product = this.productMapper.toEntity(dto);

        Category category = this.categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found."));

        Supplier supplier = this.supplierRepository.findById(dto.getSupplierId()).orElseThrow(() -> new RuntimeException("Supplier not found."));

        product.setId(id);
        product.setCategory(category);
        product.setSupplier(supplier);

        Product saved = this.productRepository.save(product);

        return this.productMapper.toResponse(saved);
    }

    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
    }

    public Optional<ProductResponseDTO> findProductBySku(String sku) {
        Optional<Product> product = this.productRepository.findBySku(sku);

        return product.map(this.productMapper::toResponse);
    }

    public List<ProductResponseDTO> findProductsByName(String name) {
        List<Product> products = this.productRepository.findByName(name);

        return products.stream().map(this.productMapper::toResponse).toList();
    }

    public List<ProductResponseDTO> findProductsByCategory(Integer categoryId) {
        List<Product> products = this.productRepository.findByCategoryId(categoryId);

        return products.stream().map(this.productMapper::toResponse).toList();
    }

    public List<ProductResponseDTO> findProductsBySupplier(Integer supplierId) {
        List<Product> products = this.productRepository.findBySupplierId(supplierId);

        return products.stream().map(this.productMapper::toResponse).toList();
    }

    public List<ProductResponseDTO> findProductsByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = this.productRepository.findByPriceBetween(minPrice, maxPrice);

        return products.stream().map(this.productMapper::toResponse).toList();
    }
}
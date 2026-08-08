package com.evolvedadrian.inventory.service;

import com.evolvedadrian.inventory.dto.request.ProductRequestDTO;
import com.evolvedadrian.inventory.dto.response.ProductResponseDTO;
import com.evolvedadrian.inventory.entity.Category;
import com.evolvedadrian.inventory.entity.Product;
import com.evolvedadrian.inventory.entity.Supplier;
import com.evolvedadrian.inventory.exception.DuplicatedResourceException;
import com.evolvedadrian.inventory.exception.ResourceNotFoundException;
import com.evolvedadrian.inventory.mapper.ProductMapper;
import com.evolvedadrian.inventory.repository.CategoryRepository;
import com.evolvedadrian.inventory.repository.ProductRepository;
import com.evolvedadrian.inventory.repository.SupplierRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
         return this.productRepository.findAll(pageable).map(this.productMapper::toResponse);
    }

    public ProductResponseDTO getProductById(Integer id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        return this.productMapper.toResponse(product);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        if (this.productRepository.existsBySku(dto.getSku())) {
            throw new DuplicatedResourceException("Sku already exists.");
        }

        Product product = this.productMapper.toEntity(dto);

        Product created = this.productRepository.save(product);

        return this.productMapper.toResponse(created);
    }

    public ProductResponseDTO updateProduct(Integer id, ProductRequestDTO dto) {
        if (!this.productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found.");
        }

        Product product = this.productMapper.toEntity(dto);

        Category category = this.categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found."));

        Supplier supplier = this.supplierRepository.findById(dto.getSupplierId()).orElseThrow(() -> new ResourceNotFoundException("Supplier not found."));

        product.setId(id);
        product.setCategory(category);
        product.setSupplier(supplier);

        Product updated = this.productRepository.save(product);

        return this.productMapper.toResponse(updated);
    }

    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
    }

    public Optional<ProductResponseDTO> findProductBySku(String sku) {
        Optional<Product> product = this.productRepository.findBySku(sku);

        return product.map(this.productMapper::toResponse);
    }

    public Page<ProductResponseDTO> findProductsByName(String name, Pageable pageable) {
        Page<Product> products = this.productRepository.findByName(name, pageable);

        return products.map(this.productMapper::toResponse);
    }

    public Page<ProductResponseDTO> findProductsByCategory(Integer categoryId, Pageable pageable) {
        Page<Product> products = this.productRepository.findByCategoryId(categoryId, pageable);

        return products.map(this.productMapper::toResponse);
    }

    public Page<ProductResponseDTO> findProductsBySupplier(Integer supplierId, Pageable pageable) {
        Page<Product> products = this.productRepository.findBySupplierId(supplierId, pageable);

        return products.map(this.productMapper::toResponse);
    }

    public Page<ProductResponseDTO> findProductsByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        Page<Product> products = this.productRepository.findByPriceBetween(minPrice, maxPrice, pageable);

        return products.map(this.productMapper::toResponse);
    }
}
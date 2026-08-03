package com.evolvedadrian.inventory.repository;

import com.evolvedadrian.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findBySku(String sku);

    boolean existsBySku(String sku);

    List<Product> findByName(String name);

    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findBySupplierId(Integer supplierId);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}

package com.evolvedadrian.inventory.repository;

import com.evolvedadrian.inventory.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findByName(String name);

    boolean existsByEmail(String email);
}

package com.evolvedadrian.inventory.controller;

import com.evolvedadrian.inventory.dto.request.SupplierRequestDTO;
import com.evolvedadrian.inventory.dto.response.SupplierResponseDTO;
import com.evolvedadrian.inventory.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<SupplierResponseDTO> getAllSuppliers() {
        return this.supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierResponseDTO getSupplierById(@PathVariable Integer id) {
        return this.supplierService.getSupplierById(id);
    }

    @GetMapping("/name/{name}")
    public List<SupplierResponseDTO> findSupplierByName(@PathVariable String name) {
        return this.supplierService.findSupplierByName(name);
    }

    @PostMapping
    public SupplierResponseDTO createSupplier(@Valid @RequestBody SupplierRequestDTO dto) {
        return this.supplierService.createSupplier(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Integer id) {
        this.supplierService.deleteSupplier(id);
    }

    @PutMapping("/{id}")
    public SupplierResponseDTO updateSupplier(
            @PathVariable Integer id,
            @Valid @RequestBody SupplierRequestDTO dto) {
        return this.supplierService.updateSupplier(id, dto);
    }
}

package com.evolvedadrian.inventory.controller;

import com.evolvedadrian.inventory.dto.request.WarehouseRequestDTO;
import com.evolvedadrian.inventory.dto.response.WarehouseResponseDTO;
import com.evolvedadrian.inventory.entity.Warehouse;
import com.evolvedadrian.inventory.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<WarehouseResponseDTO> getAllWarehouses() {
        return this.warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public WarehouseResponseDTO getWarehouseById(@PathVariable Integer id) {
        return this.warehouseService.getWarehouseById(id);
    }

    @GetMapping("/name/{name}")
    public List<WarehouseResponseDTO> findWarehouseByName(@PathVariable String name) {
        return this.warehouseService.findWarehouseByName(name);
    }

    @GetMapping("/location/{location}")
    public List<WarehouseResponseDTO> findWarehouseByLocation(@PathVariable String location) {
        return this.warehouseService.findWarehouseByLocation(location);
    }

    @PostMapping
    public WarehouseResponseDTO createWarehouse(@RequestBody WarehouseRequestDTO dto) {
        return this.warehouseService.createWarehouse(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteWarehouse(@PathVariable Integer id) {
        this.warehouseService.deleteWarehouse(id);
    }

    @PutMapping("/{id}")
    public WarehouseResponseDTO updateWarehouse(@PathVariable Integer id, @RequestBody WarehouseRequestDTO dto) {
        return this.warehouseService.updateWarehouse(id, dto);
    }
}

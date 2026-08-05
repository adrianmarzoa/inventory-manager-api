package com.evolvedadrian.inventory.service;

import com.evolvedadrian.inventory.dto.request.WarehouseRequestDTO;
import com.evolvedadrian.inventory.dto.response.WarehouseResponseDTO;
import com.evolvedadrian.inventory.entity.Warehouse;
import com.evolvedadrian.inventory.exception.ResourceNotFoundException;
import com.evolvedadrian.inventory.mapper.WarehouseMapper;
import com.evolvedadrian.inventory.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    public WarehouseService(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    public List<WarehouseResponseDTO> getAllWarehouses() {
        List<Warehouse> warehouses = this.warehouseRepository.findAll();

        return warehouses.stream().map(this.warehouseMapper::toResponse).toList();
    }

    public WarehouseResponseDTO getWarehouseById(Integer id) {
        Warehouse warehouse = this.warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse not found."));
        return this.warehouseMapper.toResponse(warehouse);
    }

    public WarehouseResponseDTO createWarehouse(WarehouseRequestDTO dto) {
        Warehouse warehouse = this.warehouseMapper.toEntity(dto);

        Warehouse created = this.warehouseRepository.save(warehouse);

        return this.warehouseMapper.toResponse(created);
    }

    public WarehouseResponseDTO updateWarehouse(Integer id, WarehouseRequestDTO dto) {
        if (!this.warehouseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Warehouse not found.");
        }

        Warehouse warehouse = this.warehouseMapper.toEntity(dto);

        warehouse.setId(id);

        Warehouse updated = this.warehouseRepository.save(warehouse);

        return this.warehouseMapper.toResponse(updated);
    }

    public void deleteWarehouse(Integer id) {
        this.warehouseRepository.deleteById(id);
    }

    public List<WarehouseResponseDTO> findWarehouseByName(String name) {
        List<Warehouse> warehouses = this.warehouseRepository.findByName(name);

        return warehouses.stream().map(this.warehouseMapper::toResponse).toList();
    }

    public List<WarehouseResponseDTO> findWarehouseByLocation(String location) {
        List<Warehouse> warehouses = this.warehouseRepository.findByLocation(location);

        return warehouses.stream().map(this.warehouseMapper::toResponse).toList();
    }
}
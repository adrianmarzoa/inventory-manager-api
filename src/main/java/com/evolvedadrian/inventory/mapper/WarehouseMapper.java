package com.evolvedadrian.inventory.mapper;

import com.evolvedadrian.inventory.dto.request.WarehouseRequestDTO;
import com.evolvedadrian.inventory.dto.response.WarehouseResponseDTO;
import com.evolvedadrian.inventory.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {
    public Warehouse toEntity(WarehouseRequestDTO dto) {
        Warehouse warehouse = new Warehouse();

        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());

        return warehouse;
    }

    public WarehouseResponseDTO toResponse(Warehouse warehouse) {
        WarehouseResponseDTO dto = new WarehouseResponseDTO();

        dto.setId(warehouse.getId());
        dto.setName(warehouse.getName());
        dto.setLocation(warehouse.getLocation());

        return dto;
    }
}

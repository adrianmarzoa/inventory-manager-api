package com.evolvedadrian.inventory.mapper;

import com.evolvedadrian.inventory.dto.request.StockMovementRequestDTO;
import com.evolvedadrian.inventory.dto.response.StockMovementResposeDTO;
import com.evolvedadrian.inventory.entity.StockMovement;
import org.springframework.stereotype.Component;

@Component
public class StockMovementMapper {
    public StockMovement toEntity(StockMovementRequestDTO dto) {
        StockMovement stockMovement = new StockMovement();

        stockMovement.setType(dto.getType());
        stockMovement.setQuantity(dto.getQuantity());
        stockMovement.setDate(dto.getDate());

        return stockMovement;
    }

    public StockMovementResposeDTO toResponse(StockMovement stockMovement) {
        StockMovementResposeDTO dto = new StockMovementResposeDTO();

        dto.setId(stockMovement.getId());
        dto.setType(stockMovement.getType());
        dto.setDate(stockMovement.getDate());

        dto.setProductId(stockMovement.getProduct().getId());
        dto.setProductName(stockMovement.getProduct().getName());
        dto.setWarehouseId(stockMovement.getWarehouse().getId());
        dto.setWarehouseName(stockMovement.getWarehouse().getName());

        return dto;
    }

}

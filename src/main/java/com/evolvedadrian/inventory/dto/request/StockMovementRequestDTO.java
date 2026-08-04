package com.evolvedadrian.inventory.dto.request;

import com.evolvedadrian.inventory.enums.MovementType;

import java.time.LocalDateTime;

public class StockMovementRequestDTO {
    private MovementType type;

    private Integer quantity;

    private LocalDateTime date;

    private Integer productId;

    private Integer warehouseId;

    public MovementType getType() {
        return this.type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
}

package com.evolvedadrian.inventory.repository;

import com.evolvedadrian.inventory.entity.StockMovement;
import com.evolvedadrian.inventory.enums.MovementType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {
    Page<StockMovement> findByType(MovementType type, Pageable pageable);

    Page<StockMovement> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Page<StockMovement> findByProductId(Integer productId, Pageable pageable);

    Page<StockMovement> findByWarehouseId(Integer warehouseId, Pageable pageable);
}

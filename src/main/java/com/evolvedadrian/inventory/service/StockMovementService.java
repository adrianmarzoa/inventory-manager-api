package com.evolvedadrian.inventory.service;

import com.evolvedadrian.inventory.dto.request.StockMovementRequestDTO;
import com.evolvedadrian.inventory.dto.response.StockMovementResposeDTO;
import com.evolvedadrian.inventory.entity.Product;
import com.evolvedadrian.inventory.entity.StockMovement;
import com.evolvedadrian.inventory.entity.Warehouse;
import com.evolvedadrian.inventory.enums.MovementType;
import com.evolvedadrian.inventory.exception.ResourceNotFoundException;
import com.evolvedadrian.inventory.mapper.StockMovementMapper;
import com.evolvedadrian.inventory.repository.ProductRepository;
import com.evolvedadrian.inventory.repository.StockMovementRepository;
import com.evolvedadrian.inventory.repository.WarehouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockMovementService {
    private final StockMovementRepository stockMovementRepository;
    private final StockMovementMapper stockMovementMapper;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository, StockMovementMapper stockMovementMapper, ProductRepository productRepository, WarehouseRepository warehouseRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.stockMovementMapper = stockMovementMapper;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public Page<StockMovementResposeDTO> getAllStockMovements(Pageable pageable) {
        return this.stockMovementRepository.findAll(pageable).map(this.stockMovementMapper::toResponse);
    }

    public StockMovementResposeDTO getStockMovementById(Integer id) {
        StockMovement stockMovement = this.stockMovementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock Movement not found."));

        return this.stockMovementMapper.toResponse(stockMovement);
    }

    public StockMovementResposeDTO createStockMovement(StockMovementRequestDTO dto) {
        StockMovement stockMovement = this.stockMovementMapper.toEntity(dto);

        StockMovement created = this.stockMovementRepository.save(stockMovement);

        return this.stockMovementMapper.toResponse(created);
    }

    public StockMovementResposeDTO updateStockMovement(Integer id, StockMovementRequestDTO dto) {
        if (!this.stockMovementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock movement not found.");
        }

        StockMovement stockMovement = this.stockMovementMapper.toEntity(dto);

        Product product = this.productRepository.findById(dto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        Warehouse warehouse = this.warehouseRepository.findById(dto.getWarehouseId()).orElseThrow(() -> new ResourceNotFoundException("Warehouse not found."));

        stockMovement.setId(id);
        stockMovement.setProduct(product);
        stockMovement.setWarehouse(warehouse);

        StockMovement updated = this.stockMovementRepository.save(stockMovement);

        return this.stockMovementMapper.toResponse(updated);
    }

    public void deleteStockMovement(Integer id) {
        this.stockMovementRepository.deleteById(id);
    }

    public Page<StockMovementResposeDTO> findStockMovementsByType(MovementType type, Pageable pageable) {
        Page<StockMovement> stockMovements = this.stockMovementRepository.findByType(type, pageable);

        return stockMovements.map(this.stockMovementMapper::toResponse);
    }

    public Page<StockMovementResposeDTO> findStockMovementsByDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        Page<StockMovement> stockMovements = this.stockMovementRepository.findByDateBetween(startDate, endDate, pageable);

        return stockMovements.map(this.stockMovementMapper::toResponse);
    }

    public Page<StockMovementResposeDTO> findStockMovementsByProduct(Integer productId, Pageable pageable) {
        Page<StockMovement> stockMovements = this.stockMovementRepository.findByProductId(productId, pageable);

        return stockMovements.map(this.stockMovementMapper::toResponse);
    }

    public Page<StockMovementResposeDTO> findStockMovementsByWarehouse(Integer warehouseId, Pageable pageable) {
        Page<StockMovement> stockMovements = this.stockMovementRepository.findByWarehouseId(warehouseId, pageable);

        return stockMovements.map(this.stockMovementMapper::toResponse);
    }
}
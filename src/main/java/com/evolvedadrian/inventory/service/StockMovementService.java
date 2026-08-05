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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<StockMovementResposeDTO> getAllStockMovements() {
        return this.stockMovementRepository.findAll().stream().map(this.stockMovementMapper::toResponse).toList();
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

    public List<StockMovementResposeDTO> findStockMovementsByType(MovementType type) {
        List<StockMovement> stockMovements = this.stockMovementRepository.findByType(type);

        return stockMovements.stream().map(this.stockMovementMapper::toResponse).toList();
    }

    public List<StockMovementResposeDTO> findStockMovementsByDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<StockMovement> stockMovements = this.stockMovementRepository.findByDateBetween(startDate, endDate);

        return stockMovements.stream().map(this.stockMovementMapper::toResponse).toList();
    }

    public List<StockMovementResposeDTO> findStockMovementsByProduct(Integer productId) {
        List<StockMovement> stockMovements = this.stockMovementRepository.findByProductId(productId);

        return stockMovements.stream().map(this.stockMovementMapper::toResponse).toList();
    }

    public List<StockMovementResposeDTO> findStockMovementsByWarehouse(Integer warehouseId) {
        List<StockMovement> stockMovements = this.stockMovementRepository.findByWarehouseId(warehouseId);

        return stockMovements.stream().map(this.stockMovementMapper::toResponse).toList();
    }
}
package com.evolvedadrian.inventory.controller;

import com.evolvedadrian.inventory.dto.request.StockMovementRequestDTO;
import com.evolvedadrian.inventory.dto.response.StockMovementResposeDTO;
import com.evolvedadrian.inventory.enums.MovementType;
import com.evolvedadrian.inventory.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/stock-movements")
public class StockMovementController {
    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @GetMapping
    public Page<StockMovementResposeDTO> getAllStockMovements(Pageable pageable) {
        return this.stockMovementService.getAllStockMovements(pageable);
    }

    @GetMapping("/{id}")
    public StockMovementResposeDTO getStockMovementById(@PathVariable Integer id) {
        return this.stockMovementService.getStockMovementById(id);
    }

    @GetMapping("/type/{type}")
    public Page<StockMovementResposeDTO> findStockMovementByType(@PathVariable MovementType type, Pageable pageable) {
        return this.stockMovementService.findStockMovementsByType(type, pageable);
    }

    @GetMapping(params = {"startDate", "endDate"})
    public Page<StockMovementResposeDTO> findStockMovementByDateBetween(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate, Pageable pageable) {
        return this.stockMovementService.findStockMovementsByDateBetween(startDate, endDate, pageable);
    }

    @GetMapping("/product/{id}")
    public Page<StockMovementResposeDTO> findStockMovementsByProduct(@PathVariable Integer id, Pageable pageable) {
        return this.stockMovementService.findStockMovementsByProduct(id, pageable);
    }

    @GetMapping("/warehouse/{id}")
    public Page<StockMovementResposeDTO> findStockMovementsByWarehouse(@PathVariable Integer id, Pageable pageable) {
        return this.stockMovementService.findStockMovementsByWarehouse(id, pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteStockMovementById(@PathVariable Integer id) {
        this.stockMovementService.deleteStockMovement(id);
    }

    @PostMapping
    public StockMovementResposeDTO createStockMovement(@Valid @RequestBody StockMovementRequestDTO dto) {
        return this.stockMovementService.createStockMovement(dto);
    }

    @PutMapping("/{id}")
    public StockMovementResposeDTO updateStockMovement(@PathVariable Integer id, @Valid @RequestBody StockMovementRequestDTO dto) {
        return this.stockMovementService.updateStockMovement(id, dto);
    }
}

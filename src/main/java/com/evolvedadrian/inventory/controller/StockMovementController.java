package com.evolvedadrian.inventory.controller;

import com.evolvedadrian.inventory.dto.request.StockMovementRequestDTO;
import com.evolvedadrian.inventory.dto.response.StockMovementResposeDTO;
import com.evolvedadrian.inventory.enums.MovementType;
import com.evolvedadrian.inventory.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/stock-movements")
public class StockMovementController {
    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @GetMapping
    public List<StockMovementResposeDTO> getAllStockMovements() {
        return this.stockMovementService.getAllStockMovements();
    }

    @GetMapping("/{id}")
    public StockMovementResposeDTO getStockMovementById(@PathVariable Integer id) {
        return this.stockMovementService.getStockMovementById(id);
    }

    @GetMapping("/type/{type}")
    public List<StockMovementResposeDTO> findStockMovementByType(@PathVariable MovementType type) {
        return this.stockMovementService.findStockMovementsByType(type);
    }

    @GetMapping(params = {"startDate", "endDate"})
    public List<StockMovementResposeDTO> findStockMovementByDateBetween(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return this.stockMovementService.findStockMovementsByDateBetween(startDate, endDate);
    }

    @GetMapping("/product/{id}")
    public List<StockMovementResposeDTO> findStockMovementsByProduct(@PathVariable Integer id) {
        return this.stockMovementService.findStockMovementsByProduct(id);
    }

    @GetMapping("/warehouse/{id}")
    public List<StockMovementResposeDTO> findStockMovementsByWarehouse(@PathVariable Integer id) {
        return this.stockMovementService.findStockMovementsByWarehouse(id);
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

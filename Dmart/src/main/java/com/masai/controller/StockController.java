package com.masai.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.StockItem;
import com.masai.entity.StockItemDTO;
import com.masai.entity.StockMovement;
import com.masai.service.StockService;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/add")
    public ResponseEntity<StockItem> addStockItem(@RequestBody StockItemDTO stockItemDTO) {
        StockItem stockItem = stockService.addStockItem(stockItemDTO);
        return ResponseEntity.ok(stockItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockItem> updateStockItem(@PathVariable Long id, @RequestBody StockItemDTO stockItemDTO) {
        StockItem updatedStockItem = stockService.updateStockItem(id, stockItemDTO);
        if (updatedStockItem != null) {
            return ResponseEntity.ok(updatedStockItem);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockItem(@PathVariable Long id) {
        stockService.deleteStockItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockItem> getStockItemById(@PathVariable Long id) {
        StockItem stockItem = stockService.getStockItemById(id);
        if (stockItem != null) {
            return ResponseEntity.ok(stockItem);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<StockItem>> getAllStockItems() {
        List<StockItem> stockItems = stockService.getAllStockItems();
        return ResponseEntity.ok(stockItems);
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<StockItem>> getStockItemsByStoreLocation(@PathVariable Long locationId) {
        List<StockItem> stockItems = stockService.getStockItemsByStoreLocation(locationId);
        return ResponseEntity.ok(stockItems);
    }

    @GetMapping("/track/{movementId}")
    public ResponseEntity<StockMovement> trackStockMovement(@PathVariable Long movementId) {
        StockMovement stockMovement = stockService.trackStockMovement(movementId);
        if (stockMovement != null) {
            return ResponseEntity.ok(stockMovement);
        }
        return ResponseEntity.notFound().build();
    }
}

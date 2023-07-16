package com.masai.service;

import java.util.List;

import com.masai.entity.StockItem;
import com.masai.entity.StockItemDTO;
import com.masai.entity.StockMovement;

public interface StockService {
	StockItem addStockItem(StockItemDTO stockItemDTO);
    StockItem updateStockItem(Long id, StockItemDTO stockItemDTO);
    void deleteStockItem(Long id);
    List<StockItem> getAllStockItems();
    List<StockItem> getStockItemsByStoreLocation(Long storeLocationId);
    StockMovement trackStockMovement(Long movementId);
	StockItem addStockItem1(StockItemDTO stockItemDTO);
	StockItem getStockItemById(Long id);
}

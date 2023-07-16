package com.masai.service;

import java.sql.Connection;
import java.util.List;

import com.masai.entity.StockItem;
import com.masai.entity.StockItemDTO;
import com.masai.entity.StockMovement;
import com.masai.entity.StoreLocation;
import com.masai.repository.StockRepository;
import com.masai.repository.StoreLocationRepository;

public class StockServiceImpl implements StockService {
    private Connection connection;
    private StockRepository stockRepository;
    private StoreLocationRepository storeLocationRepository;

    public StockServiceImpl(Connection connection, StockRepository stockRepository, StoreLocationRepository storeLocationRepository) {
        this.connection = connection;
        this.stockRepository = stockRepository;
        this.storeLocationRepository = storeLocationRepository;
    }

    @Override
    public StockItem addStockItem1(StockItemDTO stockItemDTO) {
        StockItem stockItem = mapStockItemDTO(stockItemDTO);
        return stockRepository.save(stockItem);
    }

    @Override
    public StockItem updateStockItem(Long id, StockItemDTO stockItemDTO) {
        StockItem stockItem = stockRepository.findById(id);
        if (stockItem != null) {
            stockItem.setName(stockItemDTO.getName());
            stockItem.setQuantity(stockItemDTO.getQuantity());
            StoreLocation storeLocation = storeLocationRepository.getStoreLocationById(stockItemDTO.getStoreLocationId());
            stockItem.setStoreLocation(storeLocation);
            return stockRepository.update(stockItem);
        }
        return null;
    }

    @Override
    public void deleteStockItem(Long id) {
        stockRepository.delete(id);
    }

    @Override
    public List<StockItem> getAllStockItems() {
        return stockRepository.findAll();
    }

    @Override
    public List<StockItem> getStockItemsByStoreLocation(Long storeLocationId) {
        return stockRepository.findByStoreLocation(storeLocationId);
    }

    @Override
    public StockMovement trackStockMovement(Long movementId) {
        // Implement tracking stock movement logic here
        // Return the corresponding StockMovement object
        return null;
    }

    private StockItem mapStockItemDTO(StockItemDTO stockItemDTO) {
        StockItem stockItem = new StockItem();
        stockItem.setName(stockItemDTO.getName());
        stockItem.setQuantity(stockItemDTO.getQuantity());
        StoreLocation storeLocation = storeLocationRepository.getStoreLocationById(stockItemDTO.getStoreLocationId());
        stockItem.setStoreLocation(storeLocation);
        return stockItem;
    }

	@Override
	public StockItem addStockItem(StockItemDTO stockItemDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public StockItem updateStockItem1(Long id, StockItemDTO stockItemDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockItem getStockItemById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

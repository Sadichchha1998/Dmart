package com.masai.repository;

import java.util.List;

import com.masai.entity.StockItem;

public interface StockRepository {
	StockItem save(StockItem stockItem);
    StockItem update(StockItem stockItem);
    void delete(Long id);
    StockItem findById(Long id);
    List<StockItem> findAll();
    List<StockItem> findByStoreLocation(Long storeLocationId);
}

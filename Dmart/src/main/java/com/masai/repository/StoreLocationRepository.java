package com.masai.repository;

import java.util.List;

import com.masai.entity.StoreLocation;

public abstract class StoreLocationRepository {
	   abstract StoreLocation findById(Long id);
	    abstract List<StoreLocation> findAll();
		public abstract StoreLocation getStoreLocationById(Long storeLocationId);
}

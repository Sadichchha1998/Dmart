package com.masai.service;

import java.util.List;

import com.masai.entity.StoreLocation;

public interface StoreLocationService {
	  StoreLocation getStoreLocationById(Long id);
	    List<StoreLocation> getAllStoreLocations();
}

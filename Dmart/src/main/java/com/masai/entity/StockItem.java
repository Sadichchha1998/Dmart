package com.masai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockItem {
	 private Long id;
	    private String name;
	    private int quantity;
	    private StoreLocation storeLocation;
}

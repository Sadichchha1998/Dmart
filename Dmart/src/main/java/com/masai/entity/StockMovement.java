package com.masai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockMovement {
	private Long id;
    private Long stockItemId;
    private int quantity;
    private Long sourceLocationId;
    private Long destinationLocationId;
}

package com.masai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementDTO {
	private Long stockItemId;
    private int quantity;
    private Long sourceLocationId;
    private Long destinationLocationId;
}

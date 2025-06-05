package com.java.spr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class order {
	private int orderId;
	private int qtyOrd;
	private Product product;
}

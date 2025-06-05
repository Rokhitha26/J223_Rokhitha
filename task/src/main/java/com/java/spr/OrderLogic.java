package com.java.spr;

import java.util.List;

public class OrderLogic {
	private List<order> items;

	public List<order> getItems() {
		return items;
	}

	public void setItems(List<order> items) {
		this.items = items;
	}

	public void displayInfo() {
		for (order order : items) {
			System.out.println(order);
			int diff = order.getQtyOrd() - order.getProduct().getQuantityAvail();
			if (diff > 0) {
				System.out.println("Insufficient Quantity...");
			} else {
				System.out.println("Total Bill Amount  " +order.getProduct().getPrice() * order.getQtyOrd());
			}
		}
	}
}

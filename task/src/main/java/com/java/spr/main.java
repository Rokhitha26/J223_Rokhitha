package com.java.spr;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("orders.xml");

		Product p1 = (Product) ctx.getBean("product1");
		Product p2 = (Product) ctx.getBean("product2");
		Product p3 = (Product) ctx.getBean("product3");

		
		System.out.println("Available Products:");
		System.out.println("1. " + p1.getProductName() + " - ₹" + p1.getPrice() + " (Available: " + p1.getQuantityAvail() + ")");
		System.out.println("2. " + p2.getProductName() + " - ₹" + p2.getPrice() + " (Available: " + p2.getQuantityAvail() + ")");
		System.out.println("3. " + p3.getProductName() + " - ₹" + p3.getPrice() + " (Available: " + p3.getQuantityAvail() + ")");

	
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter product number (1-3): ");
		int choice = sc.nextInt();

		System.out.print("Enter quantity to order: ");
		int qty = sc.nextInt();

		Product selectedProduct = null;

		if (choice == 1) {
			selectedProduct = p1;
		} else if (choice == 2) {
			selectedProduct = p2;
		} else if (choice == 3) {
			selectedProduct = p3;
		} else {
			System.out.println("Invalid product choice.");
			sc.close();
			return;
		}

	
		if (qty > selectedProduct.getQuantityAvail()) {
			System.out.println("Insufficient Quantity...");
		} else {
			double total = qty * selectedProduct.getPrice();
			System.out.println("Total Bill Amount: ₹" + total);
		}

		sc.close();
	}
}

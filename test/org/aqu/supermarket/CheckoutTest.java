package org.aqu.supermarket;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CheckoutTest {

	private Basket userBasket = new Basket(1);
	private Basket userBasket2 = new Basket(2);
	private Basket userBasket3 = new Basket(3);
	private Basket userBasket4 = new Basket(4);

	@Before
	public void setUp() throws Exception {
		for (int i = 1; i <= 9; i++) {
			Catalog.addItem(ItemFactory.createItem(i));
		}
		
		userBasket.addItem(Catalog.getItem(1), 1);
		userBasket.addItem(Catalog.getItem(2), 1);
		userBasket.addItem(Catalog.getItem(3), 1);
		
		userBasket2.addItem(Catalog.getItem(1), 3);
		userBasket2.addItem(Catalog.getItem(4), 3);
		userBasket2.addItem(Catalog.getItem(8), 4);
		
		userBasket3.addItem(Catalog.getItem(5), 4);
		userBasket3.addItem(Catalog.getItem(6), 1);
		userBasket3.addItem(Catalog.getItem(7), 1);
	
		userBasket4.addItem(Catalog.getItem(6), 5);
	}

	@Test
	public void testCoBasket1() {
		System.out.println("======================");

		int qty = userBasket.getTotalQuantity();
		assertTrue(qty == 3);
		System.out.println("Quantity: " +qty+"\n");
		float basketCost = userBasket.getTotal();

		assertTrue(basketCost == 6.78f);
		Checkout checkoutPoint = new Checkout(userBasket);
		Receipt receipt = checkoutPoint.doCheckout();
		System.out.println(receipt.toString());
	}

	@Test
	public void testCoBasket2() {
		System.out.println("======================");
		int qty = userBasket2.getTotalQuantity();
		System.out.println("Quantity: " +qty+"\n");

		Checkout checkoutPoint = new Checkout(userBasket2);
		Receipt receipt = checkoutPoint.doCheckout();
		System.out.println(receipt.toString());
	}
	
	@Test
	public void testCoBasket3() {
		System.out.println("======================");
		int qty = userBasket3.getTotalQuantity();
		System.out.println("Quantity: " +qty+"\n");

		Checkout checkoutPoint = new Checkout(userBasket3);
		Receipt receipt = checkoutPoint.doCheckout();
		System.out.println(receipt.toString());
	}
	
	@Test
	public void testCoBasket4() {
		System.out.println("======================");
		int qty = userBasket4.getTotalQuantity();
		assertTrue(qty == 5);
		Checkout checkoutPoint = new Checkout(userBasket4);
		Receipt receipt = checkoutPoint.doCheckout();
		System.out.println(receipt.toString());
	}
}

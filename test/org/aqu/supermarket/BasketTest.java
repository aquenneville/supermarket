package org.aqu.supermarket;

import static org.junit.Assert.*;

import java.util.Set;

import org.aqu.supermarket.model.Category;
import org.junit.Before;
import org.junit.Test;

public class BasketTest {

	private Basket userBasket = new Basket(1);

	@Before
	public void setUp() throws Exception {
		Catalog.addItem(ItemFactory.createItem(1));
		Catalog.addItem(ItemFactory.createItem(2));
		Catalog.addItem(ItemFactory.createItem(3));
		Catalog.addItem(ItemFactory.createItem(4));

		userBasket.addItem(Catalog.getItem(1), 1);
		userBasket.addItem(Catalog.getItem(2), 1);
		userBasket.addItem(Catalog.getItem(3), 1);
		userBasket.addItem(Catalog.getItem(4), 1);
	}

	@Test
	public void testBasketSize() {
		assertTrue(userBasket.getItemList().size() == userBasket.getTotalQuantity());
	}

	@Test
	public void testBasketCategory() {
		Set<Category> categorySet = userBasket.getItemCategorySet();
		assertTrue(categorySet.contains(Category.DAIRY));
		assertTrue(categorySet.contains(Category.HOT_DRINKS));
		assertTrue(categorySet.size() == 2);
	}
	
	@Test
	public void testBasketTotalPrice() {
		System.out.println(userBasket.getTotal());
		assertTrue(userBasket.getTotal() == 11.27f);
	}
}

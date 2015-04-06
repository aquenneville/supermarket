package org.aqu.supermarket;

import static org.junit.Assert.*;

import org.aqu.supermarket.model.Item;
import org.junit.Before;
import org.junit.Test;

public class CatalogueTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		for (int i = 1; i <= 9; i++) {
			Catalog.addItem(ItemFactory.createItem(i));
			Item item = Catalog.getItem(i);
			assertTrue(item != null);
			System.out.println("===================");
			System.out.println(item.toString());
		}
		assertTrue(Catalog.getSize() == 9);
	}

}

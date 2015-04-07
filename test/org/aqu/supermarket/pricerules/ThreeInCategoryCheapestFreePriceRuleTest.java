package org.aqu.supermarket.pricerules;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.aqu.supermarket.Catalog;
import org.aqu.supermarket.ItemFactory;
import org.aqu.supermarket.model.Item;
import org.junit.Before;
import org.junit.Test;

public class ThreeInCategoryCheapestFreePriceRuleTest {

	ThreeInCategoryCheapestFreePriceRule rule;
	List<Item> itemListAllowed = new ArrayList<Item>();
	List<Item> itemListNotAllowed = new ArrayList<Item>();
	
	@Before
	public void setUp() throws Exception {
		Catalog.addItem(ItemFactory.createItem(1));
		Catalog.addItem(ItemFactory.createItem(6));
		Catalog.addItem(ItemFactory.createItem(7));
		Catalog.addItem(ItemFactory.createItem(8));
		
		rule = new ThreeInCategoryCheapestFreePriceRule();
		itemListAllowed.add(Catalog.getItem(6));
		itemListAllowed.add(Catalog.getItem(7));
		itemListAllowed.add(Catalog.getItem(8));
		
		itemListNotAllowed.add(Catalog.getItem(1));
		itemListNotAllowed.add(Catalog.getItem(6));
		itemListNotAllowed.add(Catalog.getItem(7));
		itemListNotAllowed.add(Catalog.getItem(8));
	}

	@Test
	public void testCanApplyRule() {
		assertTrue(rule.canApplyRule(itemListAllowed) == true);
		assertTrue(!rule.canApplyRule(itemListNotAllowed));
	}

	@Test
	public void testCalculateDiscount() {
		assertTrue(rule.calculateDiscount(itemListAllowed) == 2.66f);
		assertTrue(rule.calculateDiscount(itemListAllowed) == 0.0f);
	}

	@Test
	public void testGetSmallestPriceInList() {
		assertTrue(
				itemListAllowed.get(rule.getSmallestPriceInList(itemListAllowed)).getPrice() == 2.66f);
		assertTrue(rule.getSmallestPriceInList(itemListNotAllowed) == -1);
	}

	@Test
	public void testGetItemsProcessedNumber() {
		rule.calculateDiscount(itemListAllowed);
		assertTrue(rule.getItemsProcessedNumber() == 3);
	}

}

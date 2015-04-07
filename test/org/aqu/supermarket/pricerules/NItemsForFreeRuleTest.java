package org.aqu.supermarket.pricerules;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.aqu.supermarket.Catalog;
import org.aqu.supermarket.ItemFactory;
import org.aqu.supermarket.model.Item;
import org.junit.Before;
import org.junit.Test;

public class NItemsForFreeRuleTest {

	NItemsForFreeRule rule = new NItemsForFreeRule();
	List<Item> itemsAllowed = new ArrayList<Item>();
	List<Item> itemsNotAllowed = new ArrayList<Item>();
	
	@Before
	public void setUp() throws Exception {
		Catalog.addItem(ItemFactory.createItem(8));
		Catalog.addItem(ItemFactory.createItem(9));
		Catalog.addItem(ItemFactory.createItem(3));
		
		itemsAllowed.add(Catalog.getItem(8));
		itemsAllowed.get(0).setQuantity(4);
		
		itemsNotAllowed.add(Catalog.getItem(3));
		itemsNotAllowed.get(0).setQuantity(4);
	}

	@Test
	public void testCanApplyRule() {
		assertTrue(rule.canApplyRule(itemsAllowed));
		assertTrue(!rule.canApplyRule(itemsNotAllowed));
	}

	@Test
	public void testCalculateDiscount() {
		assertTrue(rule.calculateDiscount(itemsAllowed)==0);
		assertTrue(rule.calculateDiscount(itemsNotAllowed) == 0);
	}

	@Test
	public void testGetItemForFreeOnOffer() {
		Item item = Catalog.getItem(rule.getItemForFreeOnOffer());
		assertTrue(item != null);
	}

	@Test
	public void testGetItemsProcessedNumber() {
		rule.calculateDiscount(itemsAllowed);
		assertTrue(rule.getItemsProcessedNumber() == 4);
	}

}

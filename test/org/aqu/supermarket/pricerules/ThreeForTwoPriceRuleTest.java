package org.aqu.supermarket.pricerules;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.aqu.supermarket.Catalog;
import org.aqu.supermarket.ItemFactory;
import org.aqu.supermarket.model.Item;
import org.aqu.supermarket.pricerules.ThreeForTwoPriceRule;
import org.junit.Before;
import org.junit.Test;

public class ThreeForTwoPriceRuleTest {

	ThreeForTwoPriceRule rule = new ThreeForTwoPriceRule();
	List<Item> itemListForDiscount = new ArrayList<Item>();
	List<Item> itemList = new ArrayList<Item>();
	
	@Before
	public void setUp() throws Exception {
		Catalog.addItem(ItemFactory.createItem(1));
		Catalog.addItem(ItemFactory.createItem(2));
		Catalog.addItem(ItemFactory.createItem(3));
		
		Item item = ItemFactory.createItem(1);
		item.setQuantity(3);
		itemListForDiscount.add(item);
		
		Item item2 = ItemFactory.createItem(1);
		item2.setQuantity(1);
		itemList.add(item2);
	}

	@Test
	public void testCanApplyRule() {
		if (rule.getItemsAllowed().contains(itemListForDiscount.get(0).getItemId())) {
			System.out.println(Catalog.getItem(itemListForDiscount.get(0).getItemId()).toString());
		}
		boolean applyForDiscount = rule.canApplyRule(itemListForDiscount);
		assertTrue(applyForDiscount);
		
		boolean apply = rule.canApplyRule(itemList);
		assertFalse(apply);
	}

	@Test
	public void testCalculateDiscount() {
		float discount = rule.calculateDiscount(itemListForDiscount);
		assertTrue(itemListForDiscount.get(0).getPrice() == discount);
		
		float discount2 = rule.calculateDiscount(itemList);
		assertTrue(discount2 == 0.0f);
	}

}

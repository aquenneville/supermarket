package org.aqu.supermarket.pricerules;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.aqu.supermarket.Catalog;
import org.aqu.supermarket.ItemFactory;
import org.aqu.supermarket.model.Item;
import org.aqu.supermarket.pricerules.TwoForSpecialPriceRule;
import org.junit.Before;
import org.junit.Test;

public class TwoForSpecialPriceRuleTest {

	TwoForSpecialPriceRule rule = new TwoForSpecialPriceRule();
	List<Item> itemListForDiscount = new ArrayList<Item>();
	List<Item> itemList = new ArrayList<Item>();
	
	@Before
	public void setUp() throws Exception {
		Catalog.addItem(ItemFactory.createItem(1));
		Catalog.addItem(ItemFactory.createItem(2));
		Catalog.addItem(ItemFactory.createItem(3));
		Catalog.addItem(ItemFactory.createItem(4));
		
		Item item = ItemFactory.createItem(4);
		item.setQuantity(2);
		itemListForDiscount.add(item);
		
		Item itemMilk = ItemFactory.createItem(1);
		itemMilk.setQuantity(1);
		itemList.add(itemMilk);
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
		float test = ((float) Math.round(itemListForDiscount.get(0).getPrice() *0.25 *100)/100);
		assertTrue(test == discount);
		
		float discount2 = rule.calculateDiscount(itemList);
		assertTrue(discount2 == 0.0f);
	}

}

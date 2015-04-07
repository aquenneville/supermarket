package org.aqu.supermarket.pricerules;

import java.util.List;

import org.aqu.supermarket.model.Category;
import org.aqu.supermarket.model.Item;

public class ThreeInCategoryCheapestFreePriceRule implements IPriceRule {

	private final int _RULE_QUANTITY = 3;
	private int itemsProcessedNumber = 0;
	private static final Category CATEGORY_ALLOWED = Category.HEALTH_MEDICINE;
	
	@Override
	public boolean canApplyRule(List<Item> items) {
		int index = 0;
		while(index < items.size() && items.get(index).getCategory() == CATEGORY_ALLOWED) {
			index ++;
		}
		return (index >= 3);
				
	}

	@Override
	public float calculateDiscount(List<Item> items) {
		float discount = 0;
		
		if (items.size() >= _RULE_QUANTITY) {
			int nbrTimesDiscountCanApply = items.size() / _RULE_QUANTITY;
			itemsProcessedNumber = nbrTimesDiscountCanApply * _RULE_QUANTITY;
			for (int i = 0; i < nbrTimesDiscountCanApply; i ++) {
				int pos = getSmallestPriceInList(items);
				discount += items.get(pos).getPrice();
				items.remove(pos);
			}
		} 
		return discount;
	}
	
	public int getSmallestPriceInList(List<Item> items) {
		int index = 0; 
		float minPrice = -1;
		int minPricePosition = -1;
		
		for (Item item: items) {
			float currentPrice = item.getPrice();
			if (index > 0 && currentPrice < minPrice ) {
				minPrice = item.getPrice();
				minPricePosition = index; 
			} 
			index ++;
		}
		return minPricePosition;
	}
	
	@Override
	public String getRuleName() {
		return _RULE_QUANTITY + " in same category cheapest is free ";
	}

	@Override
	public int getItemsProcessedNumber() {
		return itemsProcessedNumber;
	}
	
	public Category getCategory() {
		return CATEGORY_ALLOWED;
	}
}

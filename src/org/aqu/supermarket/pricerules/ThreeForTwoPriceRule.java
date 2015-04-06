package org.aqu.supermarket.pricerules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aqu.supermarket.model.Item;

public class ThreeForTwoPriceRule implements IPriceRule {

	private static Set<Integer> itemsAllowed = new HashSet<Integer>();
	private final int _RULE_QUANTITY = 3;
	private int itemsProcessedNumber = 0;
	
	static {
		itemsAllowed.add(1);
		itemsAllowed.add(2);
		itemsAllowed.add(3);
		itemsAllowed.add(6);
	}
	
	@Override
	public boolean canApplyRule(List<Item> items) {
		Item item = items.get(0);
		if (itemsAllowed.contains(item.getItemId()) && item.getQuantity() >= _RULE_QUANTITY) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public float calculateDiscount(List<Item> items) {
		Item item = items.get(0);
		float discount = 0;
		if (itemsAllowed.contains(item.getItemId()) && item.getQuantity() >= _RULE_QUANTITY) {
			int nbrTimesDiscountCanApply = item.getQuantity() / _RULE_QUANTITY;
			itemsProcessedNumber = nbrTimesDiscountCanApply * _RULE_QUANTITY;
			discount = (nbrTimesDiscountCanApply * item.getPrice());
		} 
		return discount;
	}

	public Set<Integer> getItemsAllowed() {
		return itemsAllowed;
	}

	@Override
	public String getRuleName() {
		return _RULE_QUANTITY + " for 2 ";
	}
	
	public int getItemsProcessedNumber() {
		return itemsProcessedNumber;
	}
}

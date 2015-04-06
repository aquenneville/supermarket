package org.aqu.supermarket.pricerules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aqu.supermarket.model.Item;

public class TwoForSpecialPriceRule implements IPriceRule {

	public static final float _SPECIAL_PRICE = 0.30f;
	private final int _RULE_QUANTITY = 2;
	private int itemsProcessedNumber = 0;
	
	private static Set<Integer> itemsAllowed = new HashSet<Integer>();

	static {
		itemsAllowed.add(4);
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
		float discount = 0f;
		if (itemsAllowed.contains(item.getItemId()) && item.getQuantity() >= _RULE_QUANTITY) {
			int nbrTimesDiscountCanApply = item.getQuantity() / _RULE_QUANTITY;
			itemsProcessedNumber = nbrTimesDiscountCanApply * _RULE_QUANTITY;
			discount = ((float) nbrTimesDiscountCanApply * 
					((float)Math.round(item.getPrice() * _SPECIAL_PRICE * 100)
							/100));
		} 
		return discount;
	}

	public Set<Integer> getItemsAllowed() {
		return itemsAllowed;
	}
	
	@Override
	public String getRuleName() {
		return "2 for special price -"+_SPECIAL_PRICE+"%";
	}

	@Override
	public int getItemsProcessedNumber() {
		return itemsProcessedNumber;
	}
}

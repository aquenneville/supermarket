package org.aqu.supermarket.pricerules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aqu.supermarket.model.Item;
/**
 * Class representation of the n items for free:  
 * when purchasing item id 8, 4 times,  
 * 
 * @author aqu
 *
 */
public class NItemsForFreeRule implements IPriceRule {

	private static Set<Integer> itemsAllowed = new HashSet<Integer>();
	private static int itemForFreeOnOffer = 9;
	private static int _RULE_QUANTITY = 4;
	public static int _RULE_QUANTITY_FREE = 2;
	private int itemsProcessedNumber = 0;
	
	static {
		itemsAllowed.add(8);
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
		int nbrTimesDiscountCanApply = item.getQuantity() / _RULE_QUANTITY;
		itemsProcessedNumber = nbrTimesDiscountCanApply * _RULE_QUANTITY;
		return 0;
	}

	public int getItemForFreeOnOffer() {
		return itemForFreeOnOffer;
	}

	@Override
	public String getRuleName() {
		return null;
	}

	@Override
	public int getItemsProcessedNumber() {
		return itemsProcessedNumber;
	}
	
}

package org.aqu.supermarket.pricerules;

import java.util.List;

import org.aqu.supermarket.model.Item;

/**
 * Interface representation of the price rule
 * 
 * @author aqu
 *
 */
public interface IPriceRule {

	public boolean canApplyRule(List<Item> items);
	public float calculateDiscount(List<Item> items);
	public String getRuleName();
	public int getItemsProcessedNumber();
}

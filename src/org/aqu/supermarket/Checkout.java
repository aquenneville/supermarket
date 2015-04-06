package org.aqu.supermarket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aqu.supermarket.model.Category;
import org.aqu.supermarket.model.Item;
import org.aqu.supermarket.pricerules.IPriceRule;
import org.aqu.supermarket.pricerules.NItemsForFreeRule;
import org.aqu.supermarket.pricerules.ThreeForTwoPriceRule;
import org.aqu.supermarket.pricerules.ThreeInCategoryCheapestFreePriceRule;
import org.aqu.supermarket.pricerules.TwoForSpecialPriceRule;

/**
 * Class representation of the checkout
 * Types of discounts: 
 * - one based on the quantity
 * - one if a set of items is based in a specific category 
 * 
 * Order of priority: 
 * 3 for 2, 2 for a special price
 * n quantity of items bought, k quantity is given
 * 3 items in the same category cheapest is free
 * 
 * 
 * @author aqu
 *
 */
public class Checkout {

	private Basket basket;
	private Basket basketCopy; 
	
	public Checkout(Basket basket) {
		this.basket = basket;
		basketCopy = new Basket(basket);
	}
	
	/**
	 * Do check out 
	 */
	public Receipt doCheckout() {
	
		Receipt receipt = new Receipt(basket.getBasketId());
		List<Item> list = basketCopy.getItemList();
		float totalDiscount = 0f;
		
		/*
		 *  apply the price rules based on the quantity
		 *  3 for 2;
		 *  2 for special
		 *  repeat
		 */
		for (Item item: list) {
			float discount = 0f;
			receipt.addLine(item.getQuantity() + " " + 
					item.getBrand() + " " + 
					item.getDescription() + " " + 
					item.getPrice());
			
			String name = getPriceDiscountName(item);
			while (name.length() > 0) {

				discount = getPriceDiscount(item);
				totalDiscount += discount;
				receipt.addLine("\tdiscount: "+ name+ " -"+discount);
				name = getPriceDiscountName(item);
				item.setQuantity(basketCopy.getCategoryGroup(item.getCategory()).get(item.getItemId()).getQuantity());
				
			}
			/*
			 * apply the price rule if the product is selected n times 
			 * then get a different product k times.
			 */
			NItemsForFreeRule nifRule = new NItemsForFreeRule();
			List<Item> itemList = new ArrayList<Item>();
			itemList.add(item);
			if (nifRule.canApplyRule(itemList)) {
				String discountName = "Free " + NItemsForFreeRule._RULE_QUANTITY_FREE + "x " + 
			Catalog.getItem(nifRule.getItemForFreeOnOffer()).getDescription();
				receipt.addLine("\tdiscount: "+ discountName);
				discount = nifRule.calculateDiscount(itemList);
			}
		}
		
		/*
		 * apply the set of items in the same category
		 */
		IPriceRule ticRule = new ThreeInCategoryCheapestFreePriceRule();
		for (Category c: basketCopy.getItemCategorySet()) {
			Map<Integer, Item> group = basketCopy.getCategoryGroup(c);

			if (ticRule.canApplyRule(new ArrayList<Item>(group.values()))) {
				totalDiscount += ticRule.calculateDiscount(new ArrayList<Item>(group.values()));
				if (totalDiscount > 0) {
					for (Integer id: group.keySet()) {
						Item item = group.get(id);
						item.decQuantity();
					}
				}
			}
		}
		
		if (totalDiscount > 0) {
			receipt.addLine("\nsub total cost of basket: " + basket.getTotal());
			receipt.addLine("total discount: " + ((float) Math.round(totalDiscount *100)/100));
			receipt.addLine("total cost after discount: " + (basket.getTotal() - totalDiscount)+"\n");
		} else {
			receipt.addLine("\ntotal discount: 0");
			receipt.addLine("total cost of basket: " + basket.getTotal()+"\n");
		}
		return receipt;
	}
	
	/**
	 * Gets the name of the discount to be applied 
	 * In order of importance: 3for2, 2for special price
	 * 
	 * @param item the item
	 * @return	the name of the price rule
	 */
	public String getPriceDiscountName(Item item) {
		IPriceRule tftpr = new ThreeForTwoPriceRule();
		IPriceRule tfspr = new TwoForSpecialPriceRule();
		String discount = "";
		
		List<Item> items = new ArrayList<Item>();
		items.add(item); 
		if (tfspr.canApplyRule(items)) {
			discount = "2 for special price " + ((int) (TwoForSpecialPriceRule._SPECIAL_PRICE*100))+"%";
		}
		if (tftpr.canApplyRule(items)) {
			discount = "3 for 2 ";
		}
		
		return discount;
	}
	
	/**
	 * Gets the price discount
	 * 
	 * @param item the item 
	 * @return the amount of the discount
	 */
	public float getPriceDiscount(Item item) {
		ThreeForTwoPriceRule tftpRule = new ThreeForTwoPriceRule();
		TwoForSpecialPriceRule tfspRule = new TwoForSpecialPriceRule();

		float discount = 0f;
		int itemsProcessed = 0;
		
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		if (tftpRule.canApplyRule(items)) {
			discount = tftpRule.calculateDiscount(items);
			itemsProcessed = tftpRule.getItemsProcessedNumber();
		} else if (tfspRule.canApplyRule(items)) {
			discount = tfspRule.calculateDiscount(items);
			itemsProcessed = tfspRule.getItemsProcessedNumber();
		}
		basketCopy.updateItemQuantity(item, itemsProcessed);
		
		return discount;
	}
	
	/**
	 * uses the basketInput to get the load the items into a basket
	 * first element is the itemId; followed by the quantity;
	 * 1;1; -> item 1; quantity of 1
	 * change values as needed.
	 * @param args
	 */
	public static void main(String[] args) {
		Basket basket = null;
		Checkout co = null;
		
		try {
			basket = Basket.readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (basket != null) {
			co = new Checkout(basket);
			co.doCheckout();
		}
	}
}

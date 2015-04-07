package org.aqu.supermarket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aqu.supermarket.model.Category;
import org.aqu.supermarket.model.Item;

/**
 * Class representation of the Supermarket customer basket.
 * Items are grouped together by category.
 * Model:
 *  Category 1: 
 *          Id1, Item1
 *          Id2, Item2
 *          Id3, Item3
 *          Id6, Item6
 *  Category 2:
 *          Id4, Item4
 *          Id5, Item5
 * ...
 * 
 * @author aqu
 *
 */
public class Basket {
	
	private Map<Category, Map<Integer, Item>> content;
	
	private int basketId;
	
	public Basket(int basketId) {
		content = new HashMap<Category, Map<Integer, Item>>();
		this.basketId = basketId;
	}
	
	/*
	 * Copy constructor 
	 */
	public Basket(Basket basket) {
		
		content = new HashMap<Category, Map<Integer, Item>>();
		
		for (Category c: basket.getContent().keySet()) {
			HashMap<Integer, Item> map = new HashMap<Integer, Item>();
			
			for (Integer id:  basket.getCategoryGroup(c).keySet()) {
				
				Item item = new Item(basket.getCategoryGroup(c).get(id));
				map.put(item.getItemId(), item);
			}
			content.put(c, map);
		}
		basketId = basket.getBasketId();
	}
	
	/**
	 * Calculates the total of the basket
	 * 
	 * @return the grand total of the basket
	 */
	public float getTotal() {
		
		float total = 0f;
		for (Category c: content.keySet()) {
			
			HashMap<Integer, Item> group = 
					(HashMap<Integer, Item>) content.get(c);
			
			for (Integer itemKey: group.keySet()) {
				
				float itemPrice = Catalog.getItem(itemKey)
								.getPrice();
				
				int itemQty = group.get(itemKey)
								.getQuantity();
				
				total += (itemPrice * itemQty);
			}
		}
		return (float)Math.round(total * 100) / 100;
	}

	/**
	 * Adds an item to the basket
	 * 
	 * @param item	the item
	 * @param theQty the quantity of the item
	 */
	public void addItem(Item item, int theQty) {
		
		if (content.containsKey(item.getCategory())) {
			
			HashMap<Integer, Item> group = 
					(HashMap<Integer, Item>) content.get(item.getCategory());
			
			item.setQuantity(theQty);
			group.put(item.getItemId(), item);
			content.put(item.getCategory(), group);
			
		} else {
			HashMap<Integer, Item> group = 
					new HashMap<Integer, Item>();
			
			item.setQuantity(theQty);
			group.put(item.getItemId(), item);
			content.put(item.getCategory(), group);
		}
	}

	/**
	 * Gets the total quantity of items in the basket 
	 * 
	 * @return the number of items in the basket
	 */
	public int getTotalQuantity() {
		
		int quantity = 0;
		
		/*
		 * Get the items in each category and summarize the total of items
		 */
		for (Category c: content.keySet()) {
			HashMap<Integer, Item> group = 
					(HashMap<Integer, Item>) content.get(c);
			
			Iterator<Item> it = group.values().iterator();
			
			while (it.hasNext()) {
				Item item = it.next();
				quantity += item.getQuantity();
			}
		}
		return quantity;
	}

	/**
	 * Gets the items of the basket in a list
	 * 
	 * @return List<Item> containing the items of the basket
	 */
	public List<Item> getItemList() {
		List<Item> itemList = new ArrayList<Item>();
		
		for (Category c: content.keySet()) {
			Map<Integer, Item> group = 
					(HashMap<Integer, Item>) content.get(c);
			
			Iterator<Item> it = group.values().iterator();
			while (it.hasNext()) {
				Item item = it.next();
				itemList.add(item);
			}
		}
		return itemList;
	}
	
	/**
	 * Updates the quantity of the item
	 * 
	 * @param item 	the item
	 * @param qtyToProcess	the quantity to take out
	 */
	public void updateItemQuantity(Item item, int qtyToProcess) {
		Map<Integer, Item> group = 
				(HashMap<Integer, Item>) content.get(item.getCategory());
		int itemQuantity = group.get(item.getItemId()).getQuantity();
		int newItemQuantity = (itemQuantity - qtyToProcess);
		item.setQuantity(newItemQuantity);
		group.put(item.getItemId(), item);
		content.put(item.getCategory(), group);		
	}

	public int getBasketId() {
		return basketId;
	}
	
	public Set<Category> getItemCategorySet() {
		return content.keySet();
	}
	
	public Map<Integer, Item> getCategoryGroup(Category c) {
		return (HashMap<Integer, Item>) content.get(c);
	}
	
	public Map<Category, Map<Integer, Item>> getContent() {
		return content;
	}
	
	public static Basket readFile() throws IOException {
		
		Basket basket = new Basket(123);
		for (int i = 1; i < 9; i++) {
			Catalog.addItem(ItemFactory.createItem(i));
		}
		
		String contentRaw = new String(Files.readAllBytes(Paths.get("basketInput.csv")));
		String[] lines = contentRaw.split("\n");
		for(String line: lines) {
			if (line != null) {
			    int itemId = Integer.parseInt(line.split(";")[0]);
			    int itemQty = Integer.parseInt(line.split(";")[1]);
			    basket.addItem(Catalog.getItem(itemId), itemQty);
			}
		}
		return basket;
	}
}

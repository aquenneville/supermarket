package org.aqu.supermarket;

import java.util.HashMap;
import java.util.Map;

import org.aqu.supermarket.model.Item;

/**
 * Class representation of product catalog of the supermarket.
 * 
 * @author aqu
 *
 */
public class Catalog {

	private static Map<Integer, Item> catalogue = 
			new HashMap<Integer, Item>();

	public static void addItem(Item item) {
		catalogue.put(item.getItemId(), item);
	}

	public static Item getItem(Integer itemKey) {
		if (catalogue.containsKey(itemKey)) {
			return new Item(catalogue.get(itemKey));
		}
		return null;
	}
	
	public static int getSize() {
		return catalogue.size();
	}

}

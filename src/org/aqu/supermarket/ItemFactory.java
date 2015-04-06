package org.aqu.supermarket;

import org.aqu.supermarket.model.Category;
import org.aqu.supermarket.model.Item;

/**
 * The responsibility of the Item factory is to create the 
 * individual items of the supermarket.
 * 
 * All items created in the beginning of a default quantity of 1.
 * 
 * @author aqu
 *
 */
public class ItemFactory {

	public static Item createItem(int itemKey) {
		Item item = null;
		switch (itemKey) {

		case 1:
			item = new Item(1, "YEO VALLEY", "MILK 2L", Category.DAIRY, 1.99f);
			break;
		case 2:
			item = new Item(2, "LACTOFREE", "MILK 1L", Category.DAIRY, 2.50f);
			break;
		case 3:
			item = new Item(3, "JUST MILK", "MILK 1L", Category.DAIRY, 2.29f);
			break;
		case 4:
			item = new Item(4, "CARTE NOIRE", "FREEZE DRIED INSTANT COFFEE 200G", Category.HOT_DRINKS, 4.49f);
			break;
		case 5: 
			item = new Item(5, "HALIBORANGE", "VITAMIN C 1000G - 20 PER PACK", Category.HEALTH_MEDICINE, 2.59f);
			break;
		case 6:	
			item = new Item(6, "HALIBORANGE", "VITAMIN D 10G - 90 PER PACK", Category.HEALTH_MEDICINE, 2.66f);
			break;
		case 7:	
			item = new Item(7, "VITABIOTICS", "VITAMIN E OPTIMUM STRENGTH 200 IU - 60 CAPSULES", Category.HEALTH_MEDICINE, 4.99f);
			break;
		case 8:	
			item = new Item(8, "HUGGIES", "NIGHT TIME PULL UPS - 12 PER PACK", Category.HEALTH_MEDICINE, 4.99f);
			break;
		case 9:	
			item = new Item(9, "HUGGIES", "QUAD WIPES", Category.HEALTH_MEDICINE, 1.59f);
			break;
		default:
			item = null;
		}
		return item;
	}
}

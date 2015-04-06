package org.aqu.supermarket.model;

/**
 * Class representation of the Item (Product) in a Supermarket.
 * 
 * An item is composed of: 
 * - unique item identifier
 * - product brand
 * - description
 * - supermarket category 
 * - price 
 * - quantity 
 * 
 * Every field is immutable except the quantity.
 * 
 * @author aqu
 *
 */
public class Item {
	
	final private int itemId;
	final private String brand;
	final private String description;
	final private Category category;
	final private float price;
	private int quantity;
	
	public Item(int id, String brand, String description, Category category, float price) {
		itemId = id;
		this.category = category;
		this.price = price;
		this.description = description;
		this.brand = brand;
		this.quantity = 1;
	}

	/*
	 * Copy constructor 
	 */
	public Item(Item item) {
		itemId = item.getItemId();
		brand = item.getBrand();
		description = item.getDescription();
		category = item.getCategory();
		price = item.getPrice();
		quantity = item.getQuantity();
	}
	
	public float getPrice() {
		return price;
	}

	public int getItemId() {
		return itemId;
	}

	public Category getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int newQty) {
		quantity = newQty;
	}
	
	public void decQuantity() {
		if (quantity > 0) {
			quantity --;
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Item id: ").append(itemId).append("\n")
		  .append("Brand: ").append(brand).append("\n")
		  .append("Desc: ").append(description).append("\n")
		  .append("Category: ").append(category).append("\n")
		  .append("Price: ").append(price).append("\n")
		  .append("Quantity: ").append(quantity);
		return sb.toString();
	}
}

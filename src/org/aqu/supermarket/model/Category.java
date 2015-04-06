package org.aqu.supermarket.model;

/**
 * Class representation of the Product category in a Supermarket.
 * 
 * @author aqu
 */
public enum Category {
	
    DAIRY(0),
    HOT_DRINKS(1),
    HEALTH_MEDICINE(2);

    private int value;
    
    private Category(int value){
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}

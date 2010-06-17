/** 
 * Project:    BarberShop
 * Author:     Sicheng Chen
 * Date:       10.June.2010 
 * Version:    v1.0
 * Class:      Item
 */

/**
 * This Class defines the data structure of a unit of items.
 */

public class Item {
	String item_name;
	float price;
	
	Item(String name, float price){
		this.item_name=name;
		this.price=price;
	}
	
	void setPrice(float price){
		this.price=price;
	}
	
	float getPrice(){
		return this.price;
	}

}

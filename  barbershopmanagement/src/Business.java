/** 
 * Project:    Barbershop
 * Author:     Sicheng Chen
 * Date:       10.June.2010  
 * Version:    v1.0
 * Class:      Business
 */

public class Business {
	String busi_id;                       	 
	String member_id;                                            
	Item[] items;            //use linked list to replace the array
	float total;
	
    public Business(String busi_num){
		this.busi_id=busi_num;
		this.total=(float)0;
	}
        
        public void setItem(Item[] it){
        	this.items=it;
        }
        public void addItem(Item it){
        	//add item to the linked list
        	this.total+=it.getPrice();
        }
        
        public void setMember(String memberID){
        	this.member_id=memberID;
        }
        public void calTotal(){
        	//Irritate the linked list
        	this.total+=items.getPrice();
        }
        
        public String getBusinessID(){
        	return this.busi_id;
        }
        public String getMemberID(){
        	return this.member_id;
        }
        public float getTotal(){
        	return this.total;
        }
}

/** 
 * Project:    BarberShop
 * Author:     Sicheng Chen
 * Date:       10.June.2010 
 * Version:    v1.0
 * Class:      Member
 */

/**
 * This Class defines the data structure of a unit of membership.
 */
class Member {
	private String id;        
	private String name;
	private String phone_num;                          
    private int level;                      
	private float balance;
	
	Member(){
		this.balance=(float)0;
	}
	
    Member(String id){
		this.id=id;
		this.balance=(float)0;
	}
        public void setName(String name){
        	this.name=name;
        }
        public void setPhoneNum(String number){
        	this.phone_num=number;
        }
        public void setLevel(int level){
        	this.level=level;
        }
        public void setBalance(float balance){
        	this.balance=balance;
        }
        public void addBalance(float add){
        	this.balance+=add;
        }
        public void cutBalance(float cut){
        	this.balance-=cut;
        }
        public String getName(){
        	return this.name;
        }
        public String getPhoneNum(){
        	return this.phone_num;
        }
        public int getLevel(){
        	return this.level;
        }
        public float getBalance(){
        	return this.balance;
        }
        
}

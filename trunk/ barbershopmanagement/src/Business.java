/** 
 * Project:    Barbershop
 * Author:     Sicheng Chen
 * Date:       10.June.2010  
 * Version:    v1.0
 * Class:      Business
 */

import java.sql.Timestamp;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


public class Business {
	String busi_id;                       	 
	String time;
	String member_id;                                            
	float total;
	
    public Business(String busi_num){
		this.busi_id=busi_num;
		this.total=(float)0;
	}
    
    public Business(String member,float total){
    	this.time = getTime();
    	this.busi_id = getID(this.time);
		this.member_id=member;
		this.total=total;
	}
        
    	public String getTime(){
    		Date date = new Date();
    		String time = new Timestamp(date.getTime()).toString();
    		System.out.println(time);
    		return time;
    	}
    	
    	public String getID(String time){
    		String id = new String("t");
    		String y = (time.subSequence(0, 4)).toString();
    		String mo = (time.subSequence(5, 7)).toString();
    		String d = (time.subSequence(8, 10)).toString();
    		String h = (time.subSequence(11, 13)).toString();
    		String mi = (time.subSequence(14, 16)).toString();
    		id = (((y.concat(mo)).concat(d)).concat(h)).concat(mi);
    		System.out.print(id);
    		return id;
    	} 
    	
    	public void addBusi(){
    		try{  
        		String [] data={this.busi_id,this.time,this.member_id,String.valueOf(this.total)};
        		//addxx();
        		Database.addData("transaction", data);
        		//deleteData("member", "id", "S654x");
        		//javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");            
        	}catch(Exception e){e.printStackTrace();}  
    	}
        
        public void setMember(String memberID){
        	this.member_id=memberID;
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

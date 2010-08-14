/** 
 * Project:    BarberShop
 * Author:     Sicheng Chen
 * Date:       10.June.2010 
 * Version:    v1.0
 * Class:      MemberManage
 */

public class MemberManage {

	//Database db = new Database(this);
	
	MemberManage(){
		
	}
	
	//this method is for returning the list of memberships; 
	void getMembership(){}
	//this method is for returning a member unit of certain ID;
	void getMember(String id){}
	//add a member;
	void addMember(String id, String name, String tele, String level, String balance){
		String [] data={id,name,tele,level,balance};
		Database.addData("member", data);
	}
	//delete a member;
	void delMember(String id){}
	//modify a certain member;
	void modMember(String id){}
	//show all the members in the list chart;
	void showMembership(){}
}

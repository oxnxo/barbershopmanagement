/** 
 * Project:    BarberShop
 * Author:     Sicheng Chen
 * Date:       10.June.2010 
 * Version:    v1.0
 * Class:      Member
 */

/**
 * This Class defines the methods for insert/read data from the database.
 */

import java.sql.*;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Database{
	//static MemberManage mm;
	
	Database(){
	}
	/*Database(MemberManage mm){
		Database.mm = mm;
	}
	*/
	// Get the database connection;
	public static Connection getCon(String table_name){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");//Load the driver for MySQL.
			System.out.println("OK,DownLoad the driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			/*"test" is the database built in MySQL, username is root, keywords is root.*/
			con.setCatalog("test");
			System.out.println("GOOD,Connect the DataBase");
		}catch(Exception ex){System.out.println(ex);}
		return con;
	}
		
/*	public static void addDataNull(String table_name,String[] data){		
		int i=data.length;
		try{
			Class.forName("com.mysql.jdbc.Driver");//Load the driver for MySQL.
			System.out.println("OK,DownLoad the driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			"test" is the database built in MySQL, username is root, keywords is root.
			Statement statement = con.createStatement();
			String sql=new String();
			sql=sql.concat("insert "+table_name+" values(null");
			for(int j=0;j<i;j++){
			sql=sql.concat(",'"+data[j]+"'");
			}
			sql=sql.concat(")");
			System.out.println("sql:"+sql);
			statement.execute(sql);
			}catch(Exception ex){System.out.println(ex);}
	}*/
	
	public static void addData(String table_name,String[] data){		
		int i=data.length;
		try{
			Connection con= getCon(table_name);
			/*"test" is the database built in MySQL, username is root, keywords is root.*/
			Statement statement = con.createStatement();
			String sql=new String();
			sql=sql.concat("insert "+table_name+" values('"+data[0]+"'");
			for(int j=1;j<i;j++){
			sql=sql.concat(",'"+data[j]+"'");
			}
			sql=sql.concat(")");
			System.out.println("sql:"+sql);
			statement.execute(sql);
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		
	}
	
	public static void deleteData(String table_name,String column_name,String num){
		try{
			Connection con= getCon(table_name);
			Statement statement = con.createStatement();
			statement.execute("DELETE FROM "+table_name+" WHERE "+column_name+" = '"+num+"'");
			}catch(Exception ex){System.out.println(ex);}
	}
	
	//modify
	public static void updateData(String table_name,String column_name,String num,String[] column,String[] data){
		int i=column.length;
		try{
			Connection con= getCon(table_name);
			Statement statement = con.createStatement();
			String sql=new String();
			sql=sql.concat("UPDATE "+table_name+" SET "+column[0]+"='"+data[0]+"'");
			for(int j=1;j<i;j++){
			sql=sql.concat(","+column[j]+"='"+data[j]+"'");
			}
			sql=sql.concat(" WHERE "+column_name+" = '"+num+"'");
			System.out.println("sql:"+sql);
			statement.execute(sql);
			}catch(Exception ex){System.out.println(ex);}
	}
	
	public static void showDB(String table_name,String[] column_name,Vector vector,JTable table) {
		int i=column_name.length;
		try{
			Connection con= getCon(table_name);
			Statement statement = con.createStatement();
			String sql="SELECT * FROM "+table_name;
			ResultSet rs = statement.executeQuery(sql);
			if(!rs.next()){
				System.out.println("reach the bottom or name errors.");
				((DefaultTableModel)table.getModel()).getDataVector().removeAllElements();
				}
			else {
				vector.clear();
				do{	
					Vector newdata=new Vector(i);
					for(int j=0;j<i;j++){
					String result=rs.getString(column_name[j]);
					result=new String(result.getBytes("UTF-8"));//("ISO-8859-1"),"GBK");//"GB2312");
					newdata.add(result);
					}			 	   	
			 	   	vector.add(newdata);
				}while(rs.next());
			}
			table.repaint();
	 	   	table.updateUI();
			rs.close();
			con.close();
		}catch(Exception ex){
			System.out.println(ex);
			System.exit(0);
		}
	}
	
	public static String[][] getColumn(String table_name,String column_name,int row) {
		String[][] data=new String[row][2];
		try{
			Class.forName("com.mysql.jdbc.Driver");//Load the driver for MySQL.
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			/*"test" is the database built in MySQL, username is root, keywords is root.*/
			con.setCatalog("test");
			Statement statement = con.createStatement();
			String sql="SELECT * FROM "+table_name;
			ResultSet rs = statement.executeQuery(sql);
			if(!rs.next())
				System.out.println("reach the bottom or name errors.");
			else {
				int k=0;
				do{	
					String result=rs.getString("no");
					result=new String(result.getBytes("ISO-8859-1"),"GB2312");
			 	   	data[k][0]=result;
					result=rs.getString(column_name);
					result=new String(result.getBytes("ISO-8859-1"),"GB2312");
			 	   	data[k][1]=result;
					k++;
				}while(rs.next()&& k<row);
			}
			rs.close();
			con.close();
			return data;
		}catch(Exception ex){
			System.out.println(ex);
			System.exit(0);
			return null;
		}
	}

	public static String[] getRow(String table_name,String key,String row_num,String[] items) {
		int i=items.length;
		String[] data=new String[i];
		try{
			Class.forName("com.mysql.jdbc.Driver");//Load the driver for MySQL.
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			/*"test" is the database built in MySQL, username is root, keywords is root.*/
			con.setCatalog("test");
			Statement statement = con.createStatement();
			String sql="SELECT * FROM "+table_name;
			ResultSet rs = statement.executeQuery(sql);
			if(!rs.next())
				System.out.println("reach the bottom or name errors.");
			else {
				do
				{	
					 
					String no=rs.getString(key);
					no=new String(no.getBytes("ISO-8859-1"),"GB2312");
					if(no.equalsIgnoreCase(row_num)){
					for(int j=0;j<i;j++){
						String result=rs.getString(items[j]);
						data[j]=new String(result.getBytes("ISO-8859-1"),"GB2312");
						System.out.print(data[j]);
					}
					System.out.println("");	
					}
				}while(rs.next());
			}
			rs.close();
			con.close();
			return data;
		}catch(Exception ex){
			System.out.println(ex);
			System.exit(0);
			return null;
		}
	}	
	
	public static void main(String []args)
    {
		Date date = new Date();
		String dateStr2 = new Timestamp(date.getTime()).toString();
		System.out.println(dateStr2);
		String yy = (dateStr2.subSequence(14, 16)).toString();
		System.out.println(yy);
    	try{  
    		String [] data={"201008030248",dateStr2,"S654x","50.25"};
    		//addxx();
    		addData("transaction", data);
    		//deleteData("member", "id", "S654x");
    		//javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");            
    	}catch(Exception e){e.printStackTrace();}  
    	//SMSBooking s=new SMSBooking();
    	
    }   
}
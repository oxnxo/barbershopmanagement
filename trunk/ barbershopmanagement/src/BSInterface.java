/** 
 * Project:    BarberShop
 * Author:     Sicheng Chen
 * Date:       12.June.2010  
 * Version:    v1.0
 * Class:      BSInterface
 */	

/**
 * This Class implements the graphic user interfaces of the application.
 */

import java.lang.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class BSInterface {
    JFrame mainFrame=new JFrame("理发店运营管理系统 v1.0");
   // BSMenu menu=new BSMenu(mainFrame);
    ToolPanel toolPanel=new ToolPanel();//menu
    OperPanel operPanel=new OperPanel();
    //ConfigPanel configPanel=new ConfigPanel();
    //MessagePanel messagePanel=new MessagePanel();
    JDialog dialog_config=new JDialog(mainFrame,true);
    JDialog dialog_send=new JDialog(mainFrame,true);
    //SMScore sms=new SMScore(this);
    //DBcore db=new DBcore(this);
    MemberManage mm = new MemberManage();
    
    static Font font = new Font("微软雅黑",Font.PLAIN,14);
    static Font font_title = new Font("微软雅黑",Font.BOLD,14);
    
    public BSInterface(){
        mainFrame.setSize(960,720);
        mainFrame.setResizable(false);
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().createImage("pic/ticket_16.png")); 
        Container contentPane=mainFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(toolPanel,BorderLayout.NORTH);
        contentPane.add(operPanel,BorderLayout.CENTER);
        mainFrame.setContentPane(contentPane);
        mainFrame.setVisible(true);
        mainFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
            	//device_close();
            	System.exit(0);
            }
        });
    }
    
    class ToolPanel extends JToolBar{
    	ImageIcon icon_connect=new ImageIcon("pic/connect_24.png");
    	ImageIcon icon_disconnect=new ImageIcon("pic/disconnect_24.png");
    	JButton button_connect=new JButton("Connect",icon_connect);
        JButton button_disconnect=new JButton("Disconnect",icon_disconnect);
        ToolPanel(){
        	this.setRollover(true);
        	this.setFloatable(false);
        	addSeparator();
        	add(button_connect);
        	button_connect.setToolTipText("Connect");
        	button_connect.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){                    
                	//device_Initial();
                }
            });
        	add(button_disconnect);
        	button_disconnect.setToolTipText("Disconnect");
        	button_disconnect.setEnabled(false);
        	button_disconnect.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){                    
                	//device_close();
                }
            });
        }
    }
    
    class OperPanel extends JPanel{
        TabPanel tabPanel=new TabPanel();
        ModiPanel modiPanel=new ModiPanel(tabPanel.membPane,tabPanel.busiPane); //This panel is for modifying the info
        OperPanel(){
            setLayout(null);
            add(tabPanel);
            Border border_1=BorderFactory.createEtchedBorder(1);
            tabPanel.setBorder(border_1);
            tabPanel.setBounds(5,5,630,630);
            tabPanel.setLayout(new BorderLayout());     
            add(modiPanel);
            Border border_2=BorderFactory.createEtchedBorder(1);
            modiPanel.setBorder(border_2);
            modiPanel.setBounds(640,5,310,630);
            modiPanel.setLayout(null);
        }
    }
    
    class TabPanel extends JPanel{
    	JTabbedPane tabPane=null;
    	ImageIcon icon_info=new ImageIcon("pic/tab_info_16.png");
    	ImageIcon icon_order=new ImageIcon("pic/tab_order_16.png");
    	MemberPane membPane=new MemberPane();
    	BusinessPane busiPane=new BusinessPane();
    	FinancePane finaPane=new FinancePane();
    	
    	TabPanel(){
    		tabPane=new JTabbedPane();
    		this.add(tabPane);
    		tabPane.setBounds(0,0,631,630);
    		tabPane.addTab("会员信息",icon_info,membPane);
    		tabPane.addTab("流水管理",icon_order,busiPane);
    		tabPane.addTab("财务状态",icon_order,finaPane);
    		tabPane.setFont(font);
    	}
    }
    
    class MemberPane extends JPanel{
    	JLabel info_label=new JLabel("会员信息:");
    	ImageIcon icon_B_add=new ImageIcon("pic/button_info_add_16.png");
    	ImageIcon icon_B_delete=new ImageIcon("pic/button_info_delete_16.png");
    	ImageIcon icon_B_edit=new ImageIcon("pic/button_info_edit_16.png");
    	ImageIcon icon_B_refresh=new ImageIcon("pic/button_info_refresh_16.png");
    	JButton B_add=new JButton("Add",icon_B_add);
    	JButton B_delete=new JButton("删除用户",icon_B_delete);
    	JButton B_edit=new JButton("Edit",icon_B_edit);
    	JButton B_refresh=new JButton("刷新列表",icon_B_refresh);
    	JTable info_table=new JTable();
    	JScrollPane info_scroll=new JScrollPane(info_table);
    	Vector info_data=new Vector();    	
    	String[] columnName={"会员编号","会员姓名","联系电话","会员级别","账户余额"};
    	String[] member_column={"id","name","telephone_no","level","balance"};
    	//String[] detail_column={"name","director","actor","actress"};
    	Vector info_name=new Vector(5);
    	JPanel edit_panel=new JPanel();
    	JLabel L_title=new JLabel("Title:");
    	JLabel L_director=new JLabel("Director:");
    	JLabel L_actor=new JLabel("Leading Actor:");
    	JLabel L_actress=new JLabel("Leading Actress:");
    	JTextField T_title=new JTextField("");
    	JTextField T_director=new JTextField("");
    	JTextField T_actor=new JTextField("");
    	JTextField T_actress=new JTextField("");
    	String id=null;
    	MemberPane(){
    		setLayout(null);
    		/*add(B_add);
    		B_add.setMargin(new java.awt.Insets(1, 1, 1, 1));
    		B_add.setBounds(230,440,90,25);
    		B_add.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event){ 
                	String[] data={T_title.getText(),T_director.getText(),T_actor.getText(),T_actress.getText()};
                	DBcore.addData("movie_info",data);
                	DBcore.showDB("movie_info", movie_column,info_data,info_table);
                }
            });*/
    		add(B_delete);
    		B_delete.setMargin(new java.awt.Insets(1, 1, 1, 1));
    		B_delete.setBounds(330,440,90,25);
    		B_delete.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event){        
                	if(id!=null){
                		int confirm = JOptionPane.showConfirmDialog(mainFrame,"是否删除该用户？","删除提示",JOptionPane.OK_CANCEL_OPTION);
                		if(confirm == JOptionPane.OK_OPTION){
                			Database.deleteData("member", "id", id);
                		}
                 	    T_title.setText("");
                 	    T_director.setText("");
                 	    T_actor.setText("");
                 	    T_actress.setText("");
                	}
                	update_table();
                }
            });
    		/*add(B_edit);
    		B_edit.setMargin(new java.awt.Insets(1, 1, 1, 1));
    		B_edit.setBounds(430,440,90,25);
    		B_edit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event){                    
                	String[] data={T_title.getText(),T_director.getText(),T_actor.getText(),T_actress.getText()};
                	if(id!=null){
                	DBcore.updateData("movie_info", "no", id, detail_column, data);
                	}
                	DBcore.showDB("movie_info", movie_column,info_data,info_table);
                }
            });*/
    		
    		add(B_refresh);
    		B_refresh.setMargin(new java.awt.Insets(1, 1, 1, 1));
    		B_refresh.setBounds(530,440,90,25);
    		B_refresh.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event){                    
                	update_table();
                }
            });
    		
    		add(info_label);
    		info_label.setBounds(10,10,300,20);
    		info_label.setFont(new Font("TimesRoman",Font.BOLD,12));  
    		add(info_scroll);
    		info_scroll.setBounds(5, 35, 615, 400);
    		info_name.add(columnName[0]);
    		info_name.add(columnName[1]);
    		info_name.add(columnName[2]);
    		info_name.add(columnName[3]);
    		info_name.add(columnName[4]);
            DefaultTableModel info_model=new DefaultTableModel(info_data,info_name);
            info_table.setModel(info_model);
            info_table.getColumnModel().getColumn(0).setWidth(25);
            info_table.addMouseListener(new MouseAdapter(){ 
                public void mouseClicked(MouseEvent e) {
                   if(e.getClickCount()==1){//click to trigger the event
                	   show_selected();
                	   System.out.print(table_selected(0));       
                   }
                }
               });
            /*add(edit_panel);
            Border border=BorderFactory.createEtchedBorder(1);
            Border title1=BorderFactory.createTitledBorder(border,"Edit Area");
            edit_panel.setBorder(title1);
            edit_panel.setBounds(5,470,615,125);
            edit_panel.setLayout(null);
            edit_panel.add(L_title);
            edit_panel.add(L_director);
            edit_panel.add(L_actor);
            edit_panel.add(L_actress);
            edit_panel.add(T_title);
            edit_panel.add(T_director);
            edit_panel.add(T_actor);
            edit_panel.add(T_actress);
            L_title.setBounds(40,30,100,25);
            L_director.setBounds(325,30,100,25);
            L_actor.setBounds(40,75,100,25);
            L_actress.setBounds(325,75,100,25);
            T_title.setBounds(140,30,150,25);
            T_director.setBounds(425,30,150,25);
            T_actor.setBounds(140,75,150,25);
            T_actress.setBounds(425,75,150,25);*/
    	}
    	public void update_table(){
    		Database.showDB("member", member_column,info_data,info_table);
    	}
    	
    	public void show_selected(){
    		id=table_selected(0);
     	    T_title.setText(table_selected(1));
     	    T_director.setText(table_selected(2));
     	    T_actor.setText(table_selected(3));
     	    T_actress.setText(table_selected(4));
    	}
    	public String table_selected(int i){
    		int row=info_table.getSelectedRow();    
    		return info_table.getValueAt(row,i).toString(); 
    	}
    }
    
    
    class BusinessPane extends JPanel{	
    	ImageIcon icon_B_refresh=new ImageIcon("pic/button_info_refresh_16.png");
    	JButton B_refresh=new JButton("刷新",icon_B_refresh);
    	JLabel info_label=new JLabel("消费记录:");
    	JTable info_table=new JTable();
    	JScrollPane info_scroll=new JScrollPane(info_table);
    	Vector info_data=new Vector();    	
    	String[] columnName={"消费编号","消费时间","客户编号","消费金额"};
    	String[] transaction_column={"id","date","member_id","amount"};
    	//String[] detail_column={"name","director","actor","actress"};
    	Vector info_name=new Vector(4);
    	
    	BusinessPane(){
    		setLayout(null);
    		add(info_label);
    		info_label.setBounds(10,10,300,20);
    		info_label.setFont(new Font("TimesRoman",Font.BOLD,12));  
    		add(info_scroll);
    		info_scroll.setBounds(5, 35, 615, 400);
    		info_name.add(columnName[0]);
    		info_name.add(columnName[1]);
    		info_name.add(columnName[2]);
    		info_name.add(columnName[3]);
            DefaultTableModel info_model=new DefaultTableModel(info_data,info_name);
            info_table.setModel(info_model);
            info_table.getColumnModel().getColumn(0).setWidth(25);
            info_table.addMouseListener(new MouseAdapter(){ 
                public void mouseClicked(MouseEvent e) {
                   if(e.getClickCount()==1){//click to trigger the event
                	   //show_selected();
                	   //System.out.print(table_selected(0));       
                   }
                }
               });
            
            add(B_refresh);
    		B_refresh.setMargin(new java.awt.Insets(1, 1, 1, 1));
    		B_refresh.setBounds(530,440,90,25);
    		B_refresh.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent event){                    
                	update_table();
                }
            });
    		
    	}
    	public void update_table(){
    		Database.showDB("transaction", transaction_column,info_data,info_table);
    	}
    	
    	
    }
    
    
    class FinancePane extends JPanel{
    }
    class ModiPanel extends JPanel{
    	//添加会员区
    	MemberPane member_pane = new MemberPane();
    	BusinessPane busi_pane = new BusinessPane();
    	JLabel member_title = new JLabel("新增会员");
    	JLabel member_id = new JLabel("会员编号：");
    	JLabel member_name = new JLabel("会员姓名：");
    	JLabel member_tele = new JLabel("联系电话：");
    	JLabel member_level = new JLabel("会员级别：");
    	JLabel member_bal = new JLabel("账户余额：");
    	JTextField text_id = new JTextField();
    	JTextField text_name = new JTextField();
    	JTextField text_tele = new JTextField();
    	JTextField text_level = new JTextField();
    	JTextField text_bal = new JTextField();
    	JButton button_add = new JButton("添加会员");
    	SpinnerModel model = new SpinnerNumberModel(0,0,3,1);          //initial,min,max,step
    	JSpinner spinner_level = new JSpinner(model);
    	//添加流水区
    	JLabel trans_title = new JLabel("新增消费");
    	JLabel trans_member = new JLabel("会员编号：");
    	JLabel trans_money = new JLabel("消费金额：");
    	JTextField text_member = new JTextField();
    	JTextField text_money = new JTextField();
    	JButton button_add_trans = new JButton("新增消费");
    	
    	
    	ModiPanel(MemberPane mp, BusinessPane bp){
    		this.member_pane = mp;
    		this.busi_pane = bp;
    		setLayout(null);
    		add(member_title);
    		member_title.setBounds(20,10,80,20);
    		add(member_id);
    		member_id.setBounds(20,35,80,20);
    		add(member_name);
    		member_name.setBounds(20,60,80,20);
    		add(member_tele);
    		member_tele.setBounds(20,85,80,20);
    		add(member_level);
    		member_level.setBounds(20,110,80,20);
    		add(member_bal);
    		member_bal.setBounds(20,135,80,20);
    		add(text_id);
    		text_id.setBounds(100,35,180,20);
    		add(text_name);
    		text_name.setBounds(100,60,180,20);
    		add(text_tele);
    		text_tele.setBounds(100,85,180,20);
    		/*add(text_level);
    		text_level.setBounds(100,90,180,20);*/
    		add(text_bal);
    		text_bal.setBounds(100,135,180,20);
    		add(spinner_level);
    		spinner_level.setBounds(100,110,180,20);
    		
    		add(trans_title);
    		trans_title.setBounds(20,220,80,20);
    		add(trans_member);
    		trans_member.setBounds(20,245,80,20);
    		add(trans_money);
    		trans_money.setBounds(20,270,80,20);
    		add(text_member);
    		text_member.setBounds(100,245,180,20);
    		add(text_money);
    		text_money.setBounds(100,270,180,20);
    		
    		
    		
    		add(button_add);
    		button_add.setBounds(100,165,100,25);
    		button_add.addActionListener(new ActionListener()
            {   public void actionPerformed(ActionEvent e){
            		try{
            			System.out.println("add");
            			mm.addMember(text_id.getText(), text_name.getText(), text_tele.getText(), spinner_level.getValue().toString(), text_bal.getText());
            			member_pane.update_table();
            		}catch (Exception ex){
            			JOptionPane.showMessageDialog(mainFrame,"Adding Failure!","ERROR",JOptionPane.ERROR_MESSAGE);
            		}
            		
                    //dialog_send.setVisible(false);                    
                }
            });
    		
    		add(button_add_trans);
    		button_add_trans.setBounds(100,305,100,25);
    		button_add_trans.addActionListener(new ActionListener()
            {   public void actionPerformed(ActionEvent e){
            		try{
            			System.out.println("add transaction");
            			Business busi = new Business(text_member.getText(), Float.valueOf(text_money.getText()).floatValue());
            			busi.addBusi();
            			busi_pane.update_table();
            		}catch (Exception ex){
            			JOptionPane.showMessageDialog(mainFrame,"Adding Failure!","ERROR",JOptionPane.ERROR_MESSAGE);
            		}
            		
                    //dialog_send.setVisible(false);                    
                }
            });
    		
    		//set font;
    		member_title.setFont(font_title);
    		member_id.setFont(font);
    		member_name.setFont(font);
    		member_tele.setFont(font);
    		member_level.setFont(font);
    		member_bal.setFont(font);
    		button_add.setFont(font);
    		trans_title.setFont(font_title);
        	trans_member.setFont(font);  
        	trans_money.setFont(font);
        	button_add_trans.setFont(font);
    	}
    }
    
    
    public static void main(String []args)
    {
    	try{  
    		javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");            
    	}catch(Exception e){e.printStackTrace();}  
    	BSInterface bs=new BSInterface();
    	
    }   
    
}

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
        ModiPanel modiPanel=new ModiPanel(); //This panel is for modifying the info
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
    	}
    }
    
    class MemberPane extends JPanel{
    }
    class BusinessPane extends JPanel{	
    }
    class FinancePane extends JPanel{
    }
    class ModiPanel extends JPanel{
    }
    
    
    public static void main(String []args)
    {
    	try{  
    		javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");            
    	}catch(Exception e){e.printStackTrace();}  
    	BSInterface bs=new BSInterface();
    	
    }   
    
}

package com.advjava.jdbc.resultset;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class Gui_Application extends JFrame implements ActionListener, WindowListener {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	private static final String GET_RECORD="SELECT SID,SNAME,SADDRESS,SAVG FROM STUDENT";
	private JLabel lsno, lsname,lsadd,lsavg;
	private JTextField tsno, tsname, tsadd, tsavg;
	private JButton bfirst, bnext, bprevious,blast;
	public Gui_Application()
	{
		System.out.println(" No ParamConstructor  Initialize");
		setTitle("Student Application ");
		setBackground(Color.RED);
		setSize(500,300);
		setLayout(new FlowLayout());
		
		
		lsno= new JLabel("Student number");
		tsno=new JTextField(10);
		add(lsno);
		add(tsno);
		
		lsname= new JLabel("Student Name");
		tsname= new JTextField(10);
		add(lsname);
		add(tsname);
		
		lsadd= new JLabel("Student Address");
		tsadd= new JTextField(10);
		add(lsadd);
		add(tsadd);
		
		lsavg=new JLabel("Student Average");
		tsavg=new JTextField(10);
		add(lsavg);
		add(tsavg);
		
		bfirst=new JButton("First Rec.");
		bnext=new JButton("Next Rec.");
		bprevious=new JButton("Previous Rec.");
		blast=new JButton("Last Rec.");
		add(bfirst);
		add(bnext);
		add(bprevious);
		add(blast);
//		Add current class object as ActionListener to Button Comp.To Handle ActionEvent Raised By them
		bfirst.addActionListener(this);
		bnext.addActionListener(this);
		bprevious.addActionListener(this);
		blast.addActionListener(this);
//		Add WindowListener To frame
		this.addWindowListener(this);
		
//		Making textboxes not editable
		tsno.setEditable(false);
		tsname.setEditable(false);
		tsavg.setEditable(false);
		tsadd.setEditable(false);
		
		
		setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Calling JDBC Connection Realted Method
		initJdbc();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Constructor Is Created !!!!!11");
		new Gui_Application();   // Anonymous Object Creation

	}
	private void initJdbc()
	{
		try {
			 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			 ps = con.prepareStatement(GET_RECORD,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			 rs=ps.executeQuery();
		}//try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
	}//initJdbc()
	public void actionPerformed(ActionEvent ae)
	{
		
		try {
			boolean flag=false;
			System.out.println("Event Is Fired By User");
		if(ae.getSource()==bfirst)
		{
			System.out.println("First Rec. Button Pressed By User");
			 rs.first();
			flag=true;
			  
			  
		}
		else if(ae.getSource()==bnext)
		{
			System.out.println("Next Rec. Button Pressed By User");
			  if(!rs.isLast()) {
				  rs.next();
				  flag=true;
				 
				 
			  }//if
		}//else if
		else if(ae.getSource()==bprevious)
		{
			System.out.println("Previous Rec. Button Pressed By User");
			 if(!rs.isFirst())
			 {
				rs.previous();
				 flag=true;
			 }//if
		}//else if
		else
		{
			System.out.println("Last Rec . Buttton Pressed By User");
			   rs.last();
			   flag=true;
		}
		
		if(flag)
		{
		tsno.setText(rs.getString(1));
		tsname.setText(rs.getString(2));
		tsadd.setText(rs.getString(3));
		tsavg.setText(rs.getString(4));
		}//if
		
	}//try	
		
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
		
		
	}//end of actionPerformed
	
	public void windowOpened(WindowEvent we)
	{
		
	}
	
    public void windowClosing(WindowEvent we)
    {
    	
    	  System.out.println("JDBC Object is Closed");
    	try {
    		if(rs!=null)
    			 rs.close();
    	}//try
    	catch(SQLException sql)
    	{
    		sql.printStackTrace();
    	}//catch
    	
    	try {
    		if(ps!=null)
    			ps.close();
    	}//try
    	catch(SQLException sql)
    	{
    		sql.printStackTrace();
    	}//catch
    	
    	try {
    		if(con!=null)
    			 con.close();
    		}//try
    	catch(SQLException sql)
    	{
    		sql.printStackTrace();
    	}
    } // method end
	
	  public void windowClosed(WindowEvent we)
	  {
		  
	 
	  }
	  
	public void  windowIconified(WindowEvent we)
	{
		
	}
	 public void windowDeiconified(WindowEvent we)
     {

    }
	
	public void windowActivated(WindowEvent we)
	{
		
	}

   
     public void windowDeactivated(WindowEvent we)
        {
	
           }
	
	

}//main


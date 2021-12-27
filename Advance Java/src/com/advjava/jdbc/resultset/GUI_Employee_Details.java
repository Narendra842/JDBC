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
public class GUI_Employee_Details extends JFrame implements ActionListener, WindowListener {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	private static final String GET_EMP_TABLE="SELECT EMPNO, ENAME,JOB,SAL FROM EMP";
	
	JLabel lempNo,lempName,lempDesg,lempSal;
	JTextField tempNo, tempName, tempDesg, tempSal;
	JButton bfirstRec, blastRec, bprevRec, bnextRec;
	
	public GUI_Employee_Details()
	{
		System.out.println("--No Pram COnstructor Executed--");
//		Set Title, Color, Size , Layout of Frame
		setTitle("EMPLOYEE RECORD VIEWER");
		setSize(250, 300);
		setBackground(Color.blue);
		setLayout(new FlowLayout() );
		
//		Add  Label, TextField, Buttton
		
		lempNo= new JLabel("Employee No");
		add(lempNo);
		tempNo= new JTextField(10);
		add(tempNo);
		
		lempName= new JLabel("Employee Name");
		add(lempName);
		tempName= new JTextField(10);
		add(tempName);
		
		lempDesg= new JLabel("Employee Job");
		add(lempDesg);
		tempDesg=new JTextField(10);
		add(tempDesg);
		
		lempSal=new JLabel("Employee Salary");
		add(lempSal);
		tempSal=new JTextField(10);
		add(tempSal);
		
		bfirstRec=new JButton("First Rec.");
		add(bfirstRec);
		
		bnextRec=new JButton("NExt Rec.");
		add(bnextRec);
		
		bprevRec= new JButton("Previous Rec.");
		add(bprevRec);
		
		blastRec= new JButton("Last Rec.");
		add(blastRec);
		
//		Perform Event Handling on Button
		bfirstRec.addActionListener(this);
		blastRec.addActionListener(this);
		bprevRec.addActionListener(this);
		bnextRec.addActionListener(this);
//		Add WindowListener To frame
		this.addWindowListener(this);
		
		
//	 Setting Visible Property and Closing Window
		setVisible(true);

//		Calling JDBC ConnectionMethod
		jdbcConnections();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	} //constructor end

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("--Main Method (Start)-------");
		new GUI_Employee_Details();
		System.out.println("--Main Method (End)--");

	}//main
	
	private void jdbcConnections()
	{
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			ps=con.prepareStatement(GET_EMP_TABLE,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs=ps.executeQuery();
		}///try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
	}//method jdbcConnection 
	
	  public void actionPerformed(ActionEvent ae)
	{
		  System.out.println("Event is Fired By User");
		  boolean flag=false;
		  try { 
			  if(ae.getSource()==bfirstRec)
			  {
				  rs.first();
				    System.out.println("rs.first () Method is Called");
				     flag=true;		  
			  }//if
			  else if(ae.getSource()==blastRec)
			  {
				
					  System.out.println("rs.last () Method is Called");
					  rs.last();
					  flag=true;			  
			  }//if else
			  else if(ae.getSource()==bprevRec)
			  {
				  if(!rs.isFirst())
				  {
					  System.out.println("rs.previous () Method is Called");
					  rs.previous();
					   flag=true;
				  }//if
			  }//if else
			  else if(ae.getSource()==bnextRec)
			  {
				if(!rs.isLast())
				{
				  System.out.println("rs.next () Method is Called");
				  rs.next();
				  flag=true;
				}//if
			  }//else
			  
			  if(flag)
			  {
				  System.out.println("Cursor goes Into Result Set And Showing Result");
				  tempNo.setText(rs.getString(1));
				  tempName.setText(rs.getString(2));
				  tempDesg.setText(rs.getString(3));
				  tempSal.setText(rs.getString(4));
			  }//if
			  
			  
		  }//try
		  catch(SQLException sql)
		  {
			  sql.printStackTrace();
		  }//catch
				
	}//actionPerformed()
		public void windowOpened(WindowEvent we)
		{
			
		}
	  
	  public void windowClosing (WindowEvent we)
	  {
		  System.out.println("Closing All JDBC Connection ");
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
		  }//catch
		  
		  System.out.println("Closed All JDBC Connection");
	  }// method closing
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
	  

}//class

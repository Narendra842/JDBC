package com.advjava.jdbc.resultset;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui_With_Swing {

	private static final String SHOW_STUD_DETAILS="SELECT SID,SNAME,SADDRESS,SAVG FROM STUDENT";
	private JFrame frame;
	private final JLabel lblNewLabel = new JLabel("Sno");
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui_With_Swing window = new Gui_With_Swing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_With_Swing() {
		initialize();
		jdbcConnections();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("JDBC Connection closed Successfully");
				try {
					if(rs!=null)
						rs.close();
				}//try
				catch(SQLException sql)
				{
					sql.printStackTrace();
				}
				try {
					if(ps!=null)
						ps.close();
				}//try
				catch(SQLException sql)
				{
					sql.printStackTrace();
				}
				try {
					if(con!=null)
						con.close();
				}//try
				catch(SQLException sql)
				{
					sql.printStackTrace();
				}
			}
		});
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setFont(new Font("Tahoma", Font.ITALIC, 10));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(27, 49, 96, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("sname");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setBounds(27, 87, 68, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("sadd");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setBounds(27, 132, 68, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("savg");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setBounds(27, 171, 68, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setBackground(SystemColor.inactiveCaptionBorder);
		textField.setBounds(129, 46, 173, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(129, 87, 173, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(129, 129, 173, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(129, 171, 173, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("First");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					  rs.first();
					  
					  textField.setText(rs.getString(1));
					  textField_1.setText(rs.getString(2));
					  textField_2.setText(rs.getString(3));
					  textField_3.setText(rs.getString(4));
					  				
				}
				catch(SQLException sql)
				{
					sql.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 217, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isLast()) {
						rs.next();
						  
						  textField.setText(rs.getString(1));
						  textField_1.setText(rs.getString(2));
						  textField_2.setText(rs.getString(3));
						  textField_3.setText(rs.getString(4));
					}
						
					}//try
					catch(SQLException sql)
					{
						sql.printStackTrace();
					}//catch
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(126, 217, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isFirst()) {
					    rs.previous();
						  
						  textField.setText(rs.getString(1));
						  textField_1.setText(rs.getString(2));
						  textField_2.setText(rs.getString(3));
						  textField_3.setText(rs.getString(4));
					}
						
					}//try
					catch(SQLException sql)
					{
						sql.printStackTrace();
					}//catch
			}
			}
		);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(233, 217, 85, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Last");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					  rs.last();
					  
					  textField.setText(rs.getString(1));
					  textField_1.setText(rs.getString(2));
					  textField_2.setText(rs.getString(3));
					  textField_3.setText(rs.getString(4));
					  				
				}
				catch(SQLException sql)
				{
					sql.printStackTrace();
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.setBounds(341, 217, 85, 21);
		frame.getContentPane().add(btnNewButton_3);
		
		
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		
		JLabel lblNewLabel_4 = new JLabel("Display Student Details from Database");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBounds(129, 10, 262, 13);
		frame.getContentPane().add(lblNewLabel_4);
	}
	private void jdbcConnections()
	{
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			ps=con.prepareStatement(SHOW_STUD_DETAILS,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs=ps.executeQuery();
		}///try
		catch(SQLException sql)
		{
			sql.printStackTrace();
		}//catch
	}//method jdbcConnection 
}

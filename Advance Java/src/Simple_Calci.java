import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Simple_Calci {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simple_Calci window = new Simple_Calci();
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
	public Simple_Calci() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(Color.RED);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Number");
		lblNewLabel.setBounds(36, 57, 96, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Second Number");
		lblNewLabel_1.setBounds(36, 109, 96, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Result");
		lblNewLabel_2.setBounds(36, 173, 96, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(212, 54, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(212, 106, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(212, 170, 96, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_2.setEditable(false);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 int fno = Integer.parseInt(textField.getText());
		     int sno = Integer.parseInt(textField_1.getText());
		     int res= fno + sno;
		     String resConv= String.valueOf(res);
		     textField_2.setText(resConv);		
			}
		});
		btnNewButton.setBounds(23, 232, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sub");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int fno = Integer.parseInt(textField.getText());
			     int sno = Integer.parseInt(textField_1.getText());
			     int res= fno - sno;
			     String resConv= String.valueOf(res);
			     textField_2.setText(resConv);
				 
			}
		});
		btnNewButton_1.setBounds(124, 232, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Multiplication");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int fno = Integer.parseInt(textField.getText());
			     int sno = Integer.parseInt(textField_1.getText());
			     int res= fno * sno;
			     String resConv= String.valueOf(res);
			     textField_2.setText(resConv);
				 
			}
		});
		btnNewButton_2.setBounds(219, 232, 112, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Division");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int fno = Integer.parseInt(textField.getText());
			     int sno = Integer.parseInt(textField_1.getText());
			     int res= fno + sno;
			     String resConv= String.valueOf(res);
			     textField_2.setText(resConv);
				 
			}
		});
		btnNewButton_3.setBounds(341, 232, 85, 21);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel_3 = new JLabel("Calculator Application");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(144, 21, 204, 13);
		frame.getContentPane().add(lblNewLabel_3);
	}
}

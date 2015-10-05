package com.ipims;

import javax.swing.*;

import java.awt.event.*;

public class Login extends JFrame {
	
	private static JButton button;
	private static JLabel userName;
	private static JLabel password;
	private static JLabel authentication;
	private static JTextField userNameField;
	private static JTextField passwordField;
	
	
	public static void main(String[] args) {
		// frame code
		JFrame frame = new JFrame();
		final int FRAME_WIDTH = 300;
		final int FRAME_HEIGHT = 400;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Interactive Patient Information Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// declare button, labels, text fields
		//ActionListener listener = new ClickListener();
		//button.addActionListener(listener);
		button = new JButton("Login");
		userName = new JLabel("Username: ");
		password = new JLabel("Password: ");
		//authentication = new JLabel("Authentication..");
		
		final int FIELD_WIDTH = 10;
		userNameField = new JTextField(FIELD_WIDTH);
		passwordField = new JTextField(FIELD_WIDTH);
		
		
		// panel code
		JPanel panel = new JPanel();
		panel.add(userName);
		panel.add(userNameField);
		panel.add(password);
		panel.add(passwordField);
		panel.add(button);
		//panel.add(authentication);
		frame.add(panel);
	}
	
	class ClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			authentication.setText("In progress");
			
		}
		
	}

}

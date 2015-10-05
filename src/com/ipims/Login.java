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
	
	static class ClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			authentication.setText("In progress");
			
		}
		
	}
	
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
		button = new JButton("Login");
		button.addActionListener(new ClickListener());
		userName = new JLabel("Username: ");
		password = new JLabel("Password: ");
		authentication = new JLabel("Authentication..");
		
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
		panel.add(authentication);
		frame.add(panel);
	}
}

/*

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PizzaToppingFrame extends JFrame
{
	private JTextField display;
	private JLabel label;
	
	private JButton button;
	
	private JCheckBox mushrooms;
	private JCheckBox bacon;
	private JCheckBox extraCheese;
	private JRadioButton cheeseButton;
	private JRadioButton pepperoniButton;
	private JRadioButton veggieButton;

	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 400;
	private double price;
	
	public PizzaToppingFrame()
	{
		label = new JLabel("Joe's Pizza");
		add(label, BorderLayout.NORTH);
		createControlPanel();
		createDisplayPanel();
		createButtonPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	public void createControlPanel()
	{
	      JPanel panel1 = createRadioButtonPanel();
	      JPanel panel2 = createCheckBoxPanel();
	      // Line up component panels
	      JPanel controlPanel = new JPanel();
	      controlPanel.setLayout(new GridLayout(1, 2));
	      controlPanel.add(panel1);
	      controlPanel.add(panel2);
	      // Add panels to content pane
	      add(controlPanel, BorderLayout.CENTER);
	}
	public JPanel createRadioButtonPanel()
	{
		 JPanel radioPanel = new JPanel();
		 radioPanel.setLayout(new GridLayout(3,1));
		 cheeseButton  = new JRadioButton("Cheese");
		 pepperoniButton = new JRadioButton("Pepperoni");
		 veggieButton  = new JRadioButton("Veggie");
		 radioPanel.add(cheeseButton);
		 radioPanel.add(pepperoniButton);
		 radioPanel.add(veggieButton);
		 radioPanel.setBorder(new TitledBorder(new EtchedBorder(), "Pizza Type"));
		 return radioPanel;
	}
	public JPanel createCheckBoxPanel()
	{
		
		 italicCheckBox = new JCheckBox("Italic");
	      italicCheckBox.addActionListener(listener);

	      boldCheckBox = new JCheckBox("Bold");
	      boldCheckBox.addActionListener(listener);

	      JPanel panel = new JPanel();
	      panel.add(italicCheckBox);
	      panel.add(boldCheckBox);
	      panel.setBorder(new TitledBorder(new EtchedBorder(), "Style"));

	      return panel; 
		extraCheese = new JCheckBox("Extra Cheese");
		bacon = new JCheckBox("Bacon");
		mushrooms = new JCheckBox("Mushrooms");
		JPanel checkPanel = new JPanel();
		checkPanel.setLayout(new GridLayout(3, 1));
		checkPanel.add(extraCheese);
		checkPanel.add(bacon);
		checkPanel.add(mushrooms);
		checkPanel.setBorder(new TitledBorder(new EtchedBorder(), "Toppings"));
		return checkPanel;
	}
	public JPanel createDisplay()
	{
		final int FIELD_WIDTH = 10;
		display = new JTextField(FIELD_WIDTH);
		JPanel displayPanel = new JPanel();
		displayPanel.add(display);
		return displayPanel;
	}
	public void createDisplayPanel()
	{
		JPanel panel3 = createDisplay();
		JPanel eastPanel = new JPanel();
		eastPanel.add(panel3);
		add(eastPanel, BorderLayout.EAST);
	}
	public JPanel createButton()
	{
		button = new JButton("Calculate Total");
		ActionListener listener = new CalculatePriceListener();
		button.addActionListener(listener);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(button);
		return buttonPanel;
	}
	public void createButtonPanel()
	{
		JPanel panel4 = createButton();
		JPanel southPanel = new JPanel();
		southPanel.add(panel4);
		add(southPanel, BorderLayout.SOUTH);
	}
	class CalculatePriceListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			double price = 0;
			if (mushrooms.isSelected())
			{
				price = price + 1.50;
			}
			if (bacon.isSelected())
			{
				price = price + 1.50;
			}
			if (extraCheese.isSelected())
			{
				price = price + 1.50;
			}
			if (cheeseButton.isSelected())
			{
				price = price + 10.00;
			}
			if (pepperoniButton.isSelected())
			{
				price = price + 12.00;
			}
			if (veggieButton.isSelected())
			{
				price = price + 15.00;
			}
			String string = ("$" + price);
			display.setText(string);
		}
	}
}
*/

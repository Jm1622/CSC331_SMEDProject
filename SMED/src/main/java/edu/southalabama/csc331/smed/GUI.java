package edu.southalabama.csc331.smed;
import java.awt.*;
import javax.swing.*;
public class GUI extends JFrame{
	private static final long serialVersionUID = -9091569339266269626L;
	private JLabel sourceLabel;
	private JTextArea eventMessageText, searchText, noneventMessageText;
	private JPanel selectionPanel;
	private JScrollPane searchPane, eventMessagePane, noneventMessagePane;
	private JComboBox<?> dropDown;
	private JButton startButton, stopButton, dlButton;
	private String[] socialMediaChoices = {"Twitter"};
	GridBagConstraints gbc = new GridBagConstraints();
	SMEDController f_controller = new SMEDController(this);
	public GUI(){
		// Creates  the GUI and the layout, this was made by Ron so I do not know how most of this stuff works right now, I added and improved some tings but still primarily ron's design choices
		// TODO make better looking and comment
		super("Social Media Event Detection");
		setLayout(new GridBagLayout());
		selectionPanel = new JPanel(new FlowLayout());
		searchPane = new JScrollPane(searchText);
		eventMessagePane = new JScrollPane(eventMessageText);
		noneventMessagePane = new JScrollPane(noneventMessagePane);
		sourceLabel = new JLabel("Select a source: ");
		selectionPanel.add(sourceLabel);
		dropDown = new JComboBox<Object>(socialMediaChoices);
		selectionPanel.add(dropDown);
		
		searchText = new JTextArea(10,0);
		searchText.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18) );
		searchText.setLineWrap(true);
        searchText.setWrapStyleWord(true);
        
        searchPane.setBorder(
        	BorderFactory.createTitledBorder("Search for: (Please separate by commas!)"));
        searchPane.setViewportView(searchText);
		
        eventMessageText = new JTextArea(10,0);
        eventMessageText.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18) );
        eventMessageText.setLineWrap(true);
        eventMessageText.setWrapStyleWord(true);
        eventMessageText.setEditable(false);
        
        eventMessagePane.setBorder(
        	BorderFactory.createTitledBorder("Event Messages:"));
        eventMessagePane.setViewportView(eventMessageText);
		
        noneventMessageText = new JTextArea(10,0);
        noneventMessageText.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18) );
        noneventMessageText.setLineWrap(true);
        noneventMessageText.setWrapStyleWord(true);
        noneventMessageText.setEditable(false);
        
        noneventMessagePane.setBorder(
        BorderFactory.createTitledBorder("NonEvent Messages:"));
        noneventMessagePane.setViewportView(noneventMessageText);
        
		startButton = new JButton("Start");
		startButton.addActionListener(new StartDetectingActionListener());
		stopButton = new JButton("Stop");
		stopButton.addActionListener(new EndDetectionActionListener());
		dlButton = new JButton("Download Result");
		dlButton.addActionListener(new DownloadTextActionListener());
		
		gbc.fill  = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.weightx = 0.1;
		add(selectionPanel,gbc);
		
		gbc.fill  = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		add(searchPane,gbc);
		
		gbc.fill  = GridBagConstraints.HORIZONTAL;
		gbc.anchor = (GridBagConstraints.LINE_END+GridBagConstraints.LINE_START)/2;
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		add(eventMessagePane,gbc);
		
		gbc.fill  = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridy = 1;
		gbc.gridx = 2;
		gbc.weightx = 0.1;
		gbc.gridwidth = 1;
		add(noneventMessagePane,gbc);
		
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.weightx = 0.1;
		add(startButton, gbc);
		
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.gridy = 2;
		gbc.gridx = 1;
		gbc.weightx = 0.1;
		add(stopButton, gbc);
		
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		gbc.gridy = 2;
		gbc.gridx = 2;
		gbc.weightx = 0.1;
		add(dlButton, gbc);
	}
	public void addEventMessage(String message) {
		eventMessageText.setText(eventMessageText.getText()+message);
	}
	public void addNonEventMessage(String message) {
		noneventMessageText.setText(noneventMessageText.getText()+message);
	}
	public String getKeyWords() {
		return searchText.getText();
	}
	public String getDropdownText() {
		if(dropDown.getSelectedIndex() != -1) {
			return this.dropDown.getSelectedItem().toString();
		}
		else {
			return null;
		}
	}
	public void clearMessages() {
		eventMessageText.setText("");
		noneventMessageText.setText("");
	}
	public SMEDController getController() {
		return f_controller;
	}
	
	
}


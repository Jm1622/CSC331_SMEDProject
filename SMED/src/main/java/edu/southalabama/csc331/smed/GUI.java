package edu.southalabama.csc331.smed;
import java.awt.*;
import java.util.ArrayList;

import javax.json.JsonObject;
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
	private Source source;
	private boolean keepGetting = false;
	private MessageLoopThread r;
	private Thread thread;
	private ArrayList<JsonObject> eventMessages = new ArrayList<JsonObject>();
	private ArrayList<JsonObject> nonEventMessages = new ArrayList<JsonObject>();
	public GUI(){
		// Creates  the GUI and the layout
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
		searchText.setLineWrap(true);
        searchText.setWrapStyleWord(true);
        
        searchPane.setBorder(
        	BorderFactory.createTitledBorder("Search for: (Please separate by commas!)"));
        searchPane.setViewportView(searchText);
		
        eventMessageText = new JTextArea(10,0);
        eventMessageText.setLineWrap(true);
        eventMessageText.setWrapStyleWord(true);
        eventMessageText.setEditable(false);
        
        eventMessagePane.setBorder(
        	BorderFactory.createTitledBorder("Event Messages:"));
        eventMessagePane.setViewportView(eventMessageText);
		
        noneventMessageText = new JTextArea(10,0);
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
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	public boolean isKeepgetting() {
		return keepGetting;
	}
	public void setKeepGetting(boolean keepGetting) {
		this.keepGetting = keepGetting;
	}
	public void addEventMessage(String message) {
		eventMessageText.setText(eventMessageText.getText()+message);
	}
	public void addNonEventMessage(String message) {
		noneventMessageText.setText(noneventMessageText.getText()+message);
	}
	public void setMessageprocessingThread(MessageLoopThread r) {
		this.r = r;
	}
	public MessageLoopThread getMessageProcessingThread() {
		return r;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public Thread getThread() {
		return thread;
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
		eventMessages.clear();
		nonEventMessages.clear();
	}
	public void addEventMessage(JsonObject message) {
		eventMessages.add(message);
	}
	public void addNonEventMessage(JsonObject message) {
		nonEventMessages.add(message);
	}
	public ArrayList<JsonObject> getEventMessages() {
		return eventMessages;
	}
	public ArrayList<JsonObject> getNonEventMessages() {
		return nonEventMessages;
	}
}


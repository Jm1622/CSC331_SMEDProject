package edu.southalabama.csc331.smed;
import java.awt.FlowLayout;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.Platform;

import javax.swing.JScrollPane;
public class GUI extends javax.swing.JPanel{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form Main
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        titleSource = new javax.swing.JLabel();
        twitterCheck = new javax.swing.JCheckBox();
        redditCheck = new javax.swing.JCheckBox();
        body = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        searchFor = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        eventMessages = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        nonEventMessages = new javax.swing.JTextArea();
        titleSearch = new javax.swing.JLabel();
        titleEvent = new javax.swing.JLabel();
        titleNonEvent = new javax.swing.JLabel();
        footer = new javax.swing.JPanel();
        start = new javax.swing.JButton();
        stop = new javax.swing.JButton();
        download = new javax.swing.JButton();
        map = new javax.swing.JPanel();
        progressBar = new javax.swing.JProgressBar();

        setMinimumSize(new java.awt.Dimension(1123, 665));

        header.setBackground(new java.awt.Color(51, 153, 255));
        header.setMinimumSize(new java.awt.Dimension(558, 130));

        title.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Social Media Event Detection");
        title.setName("title"); // NOI18N

        logo.setForeground(new java.awt.Color(255, 255, 255));
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.png"))); // NOI18N

        titleSource.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleSource.setForeground(new java.awt.Color(255, 255, 255));
        titleSource.setText("Source Select:");

        twitterCheck.setForeground(new java.awt.Color(255, 255, 255));
        twitterCheck.setText("Twitter");
        twitterCheck.setContentAreaFilled(false);

        redditCheck.setForeground(new java.awt.Color(255, 255, 255));
        redditCheck.setText("Reddit");
        redditCheck.setContentAreaFilled(false);

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleSource)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(twitterCheck)
                    .addComponent(redditCheck))
                .addGap(20, 20, 20))
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(headerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 562, Short.MAX_VALUE)))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(twitterCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(redditCheck))
                    .addComponent(titleSource)
                    .addComponent(logo)
                    .addComponent(title))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(headerLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        title.getAccessibleContext().setAccessibleDescription("title");

        body.setBackground(new java.awt.Color(0, 128, 255));
        body.setMinimumSize(new java.awt.Dimension(1120, 414));

        searchFor.setBackground(new java.awt.Color(153, 194, 255));
        searchFor.setColumns(20);
        searchFor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchFor.setRows(5);
        searchFor.setToolTipText("User commas to separate");
        jScrollPane1.setViewportView(searchFor);

        eventMessages.setBackground(new java.awt.Color(153, 194, 255));
        eventMessages.setColumns(20);
        eventMessages.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eventMessages.setLineWrap(true);
        eventMessages.setRows(5);
        eventMessages.setToolTipText("");
        eventMessages.setName("Search For:"); // NOI18N
        jScrollPane2.setViewportView(eventMessages);

        nonEventMessages.setBackground(new java.awt.Color(153, 194, 255));
        nonEventMessages.setColumns(20);
        nonEventMessages.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nonEventMessages.setLineWrap(true);
        nonEventMessages.setRows(5);
        jScrollPane3.setViewportView(nonEventMessages);

        titleSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titleSearch.setForeground(new java.awt.Color(255, 255, 255));
        titleSearch.setText("Search for: (Use commas to separate keywords)");

        titleEvent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titleEvent.setForeground(new java.awt.Color(255, 255, 255));
        titleEvent.setText("Event Messages:");

        titleNonEvent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titleNonEvent.setForeground(new java.awt.Color(255, 255, 255));
        titleNonEvent.setText("Non-event messages:");

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleSearch)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(titleEvent))
                .addGap(18, 18, 18)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addComponent(titleNonEvent)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleSearch)
                    .addComponent(titleEvent)
                    .addComponent(titleNonEvent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        

        footer.setBackground(new java.awt.Color(51, 153, 255));
        footer.setMinimumSize(new java.awt.Dimension(618, 120));

        start.setBackground(new java.awt.Color(77, 166, 255));
        start.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        start.setForeground(new java.awt.Color(255, 255, 255));
        start.setText("Start");
        start.setToolTipText("Starts the event detection");
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        start.addActionListener(new StartDetectingActionListener(this));

        stop.setBackground(new java.awt.Color(77, 166, 255));
        stop.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        stop.setForeground(new java.awt.Color(255, 255, 255));
        stop.setText("Stop");
        stop.setToolTipText("Stops the event detection");
        stop.setBorderPainted(false);
        stop.setContentAreaFilled(false);
        stop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stop.addActionListener(new EndDetectionActionListener(this));

        download.setBackground(new java.awt.Color(77, 166, 255));
        download.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        download.setForeground(new java.awt.Color(255, 255, 255));
        download.setText("Download Results");
        download.setToolTipText("Download the results");
        download.setBorderPainted(false);
        download.setContentAreaFilled(false);
        download.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        download.addActionListener(new DownloadTextActionListener(this));

        
        
        
        
	   

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(fxPanel, 400, 400, 400)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(start)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(download)
                .addContainerGap())
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(footerLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(fxPanel, 275, 275, 275))
                    .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(start)
                        .addComponent(stop)
                        .addComponent(download)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        

        header.getAccessibleContext().setAccessibleName("Title");
        header.getAccessibleContext().setAccessibleDescription("");
        eventMessages.setEditable(false);
        nonEventMessages.setEditable(false);
        stop.setEnabled(false);
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)
                    .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(1, 1, 1))
            );
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
					initializeFXPanel(fxPanel);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
	   
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JButton download;
    private javax.swing.JTextArea eventMessages;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JPanel map;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel logo;
    private javax.swing.JTextArea nonEventMessages;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JTextArea searchFor;
    private javax.swing.JButton start;
    private javax.swing.JButton stop;
    private javax.swing.JLabel title;
    private javax.swing.JLabel titleEvent;
    private javax.swing.JLabel titleNonEvent;
    private javax.swing.JLabel titleSearch;
    private javax.swing.JLabel titleSource;
    private javax.swing.JCheckBox twitterCheck;
    private javax.swing.JCheckBox redditCheck;
    private SMEDController f_controller = new SMEDController(this);
    private final JFXPanel fxPanel = new JFXPanel();
    
    public Scene createScene() throws URISyntaxException {
    		Scene scene = new Scene(new Group());
	    final WebView browser = new WebView();
	    final WebEngine webEngine = browser.getEngine();
	    browser.setMaxSize(500, 500);
	    ScrollPane scrollPane = new ScrollPane();
	    scrollPane.setContent(browser);
	    scrollPane.setMaxSize(500, 500);
	    File webpage = new File(System.getProperty("user.dir")+"/Map.html");
	    webEngine.load(webpage.toURI().toString());
	    scene.setRoot(scrollPane);
	    
	   
	    return scene;
    }
    public void initializeFXPanel(JFXPanel fxPanel) throws URISyntaxException {
    		Scene scene = this.createScene();
    		fxPanel.setScene(scene);
    		fxPanel.setVisible(true);
    		
    }
    
    // End of variables declaration//GEN-END:variables
	public void addEventMessage(String message) {
		eventMessages.setText(message+eventMessages.getText());
	}
	public void addNonEventMessage(String message) {
		nonEventMessages.setText(message+nonEventMessages.getText());
	}
	public String getKeyWords() {
		return searchFor.getText();
	}
	public void clearMessages() {
		eventMessages.setText("");
		nonEventMessages.setText("");
	}
	public void disableKeyWordSearch() {
		this.searchFor.setEditable(false);
	}
	public void enableKeyWordSearch() {
		this.searchFor.setEditable(true);
	}
	public SMEDController getController() {
		return f_controller;
	}
	
	public ArrayList<String> getSelectedSources(){
		ArrayList<String> sources = new ArrayList<String>();
		//Add each selected source to the sourcesList, if none are selected return null
		if(twitterCheck.isSelected()){
			sources.add("Twitter");
		}
		if(redditCheck.isSelected()) {
			sources.add("Reddit");
		}
		if(sources.isEmpty()) {
			return null;
		}
		else {
			return sources;
		}
	}
	public void disableStartButton() {
		this.start.setEnabled(false);
	}
	public void enableStartButton() {
		this.start.setEnabled(true);
	}
	public void disableStopButton() {
		this.stop.setEnabled(false);
	}
	public void enableStopButton() {
		this.stop.setEnabled(true);
	}
	public void disableDownloadButton() {
		this.download.setEnabled(false);
	}
	public void enableDownloadButton() {
		this.download.setEnabled(true);
	}
	public void disableScrolling() {
		this.jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	}
	public void enableScrolling() {
		this.jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
}


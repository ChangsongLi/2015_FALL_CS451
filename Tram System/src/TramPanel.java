import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TramPanel implements ActionListener{
	JFrame window;
	JPanel mainPanel, screenPanel;
	JComboBox<String> selectToChange;
	TextField input;
	JButton setting,startButton;
	boolean start = false;
	JLabel tram,notification,door,leftDoor, rightDoor, speedLabel,stopDurationLabel;
	int windowHeight = 660;
	int windowWidth = 1000;
	int screenWidth = 800;
	int screenHeight = 378;
	
	//all colors
	Color mainPanelColor = Color.white;
	Color screePanelColor = Color.gray;
	TramPanel(){
		setUpWindow();
	}
	
	public void setUpWindow(){
		window = new JFrame();
		window.setTitle("Tram System 1.0");
		window.setBounds(100,100,windowWidth,windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0,0,windowWidth,windowHeight);
		mainPanel.setBackground(mainPanelColor);
		mainPanel.setLayout(null);
		window.add(mainPanel);
		
		screenPanel = new JPanel();
		screenPanel.setBounds((windowWidth - screenWidth)/2 ,20,screenWidth,screenHeight);
		screenPanel.setBackground(screePanelColor);
		screenPanel.setLayout(null);
		
		ImageIcon image = new ImageIcon("Tram route copy.jpg");
		JLabel label = new JLabel(image);
		label.setBounds(0, 0, screenWidth, screenHeight);
		label.setOpaque(true);
		screenPanel.add( label);
		
		ImageIcon image2 = new ImageIcon("tram.png");
		tram = new JLabel(image2);
		tram.setBounds(340, 260, 100, 100);
		tram.setOpaque(true);
		label.add( tram);
		
		notification = new JLabel("The tram is in the Station A.",SwingConstants.CENTER);
		notification.setFont(new Font("Serif", Font.PLAIN, 24));
		tram.setOpaque(true);
		notification.setForeground(Color.red);
		int labelWidth = 300;
		int labelHeight = 40;
		notification.setBounds((windowWidth - labelWidth)/2, 20 + screenHeight, labelWidth, labelHeight);
		mainPanel.add(notification);
		
		mainPanel.add(screenPanel);
		
		// doors label
		door = new JLabel();	
		door.setBounds(100, screenHeight + 100 , 200, 100);
		door.setOpaque(true);
		mainPanel.add( door );
		
		leftDoor = new JLabel();
		leftDoor.setBounds(0, 0, 100, 100);
		leftDoor.setBackground(Color.black);
		leftDoor.setOpaque(true);
		door.add( leftDoor );
		
		rightDoor = new JLabel();
		rightDoor.setBounds(100, 0, 100, 100);
		rightDoor.setBackground(Color.black);
		rightDoor.setOpaque(true);
		door.add( rightDoor );

		
		// speed labels
		speedLabel = new JLabel("Current speed: 0 MPH");
		speedLabel.setBackground(Color.white);
		speedLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		speedLabel.setBounds((windowWidth)/3, 105 + screenHeight , 200, 40);
		speedLabel.setOpaque(true);
		mainPanel.add( speedLabel );
		
		// stop duration label
		stopDurationLabel = new JLabel("Current Stop duration: 10s");
		stopDurationLabel.setBackground(Color.white);
		stopDurationLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		stopDurationLabel.setBounds((windowWidth)/3, 135 + screenHeight , 200, 40);
		stopDurationLabel.setOpaque(true);
		mainPanel.add( stopDurationLabel );
		
		
		String[] names = {"Select","Speed(MPH)","Stop duration(second)"};
		selectToChange = new JComboBox<String>(names);
		selectToChange.setBounds((windowWidth)/3 + 250, 100 + screenHeight , 150, 30);
		mainPanel.add( selectToChange );
		
		input = new TextField("");
		input.setBounds((windowWidth)/3 + 255, 150 + screenHeight , 100, 20);
		mainPanel.add( input );
		
		setting = new JButton("Set");
		setting.setBounds((windowWidth)/3 + 250, 185 + screenHeight , 100, 30);
		setting.addActionListener(this);
		mainPanel.add( setting );
		
		
		startButton = new JButton(new ImageIcon("start.jpg"));
		startButton .setBounds((windowWidth)/3 + 450, 80+screenHeight , 100, 100);
		startButton .addActionListener(this);
		mainPanel.add( startButton );
		window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton){
			if(start == true){
				startButton.setIcon(new ImageIcon("start.jpg"));
				start = false;
			}else{
				startButton.setIcon(new ImageIcon("stop.jpg"));
				start = true;
			}
		}
	}
}

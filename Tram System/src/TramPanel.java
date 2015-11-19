import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TramPanel implements ActionListener {
	JFrame window;
	JPanel mainPanel, screenPanel;
	TextField input;
	JButton startButton, increaseSpeedButton, decreaseSpeedButton, increaseTimeButton, decreaseTimeButton;
	Vector<Point> points;
	boolean running = false;
	
	int theme = 1;
	// train map
	JLabel label;
	JLabel tram, notification, timeLabel, door, leftDoor, rightDoor, speedLabel, stopDurationLabel;
	int windowHeight = 660;
	int windowWidth = 1000;
	int screenWidth = 800;
	int screenHeight = 378;
	Timer timer = new Timer(0, this), stopTimer = new Timer(0, this), openDoor = new Timer(0, this),
			closeDoor = new Timer(0, this),currentTime;
	int timerRunning = 0;
	JLabel[] arriveTime = new JLabel[5];
	int p = 1;
	int speed = 60;
	int stopTime = 12;
	int[] allStop = {0, 429 ,836 ,1222 ,1427};
	// all colors
	Color mainPanelColor = new Color(153, 204, 255);
	Color secondColor = new Color(255, 255, 204);
	Color thirdColor = new Color(255, 204, 51);
	JLabel[] station = new JLabel[5];
	JComboBox<String> themes;
	
	TramPanel() {
		setUpWindow();
	}

	public void setUpWindow() {
		window = new JFrame();
		window.setTitle("Tram System 1.0");
		window.setBounds(100, 100, windowWidth, windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, windowWidth, windowHeight);
		mainPanel.setBackground(mainPanelColor);
		mainPanel.setLayout(null);
		window.add(mainPanel);

		ImageIcon image = new ImageIcon("Tram route.png");
		label = new JLabel(image);
		label.setBounds(101, 10, 797, 311);
		label.setOpaque(true);
		mainPanel.add(label);

		createStationLabel();
		
		tram = new JLabel();
		tram.setBounds(400, 242, 40, 40);
		tram.setBackground(thirdColor);
		tram.setOpaque(true);
		label.add(tram);

		door = new JLabel();
		door.setBounds(320, 100, 170, 109);
		door.setBackground(mainPanelColor);
		door.setOpaque(true);
		label.add(door);

		ImageIcon doorImage = new ImageIcon("door.png");
		leftDoor = new JLabel(doorImage);
		leftDoor.setBounds(0, 0, 85, 109);
		leftDoor.setOpaque(true);
		door.add(leftDoor);

		rightDoor = new JLabel(doorImage);
		rightDoor.setBounds(85, 0, 85, 109);
		rightDoor.setOpaque(true);
		door.add(rightDoor);

		Border b = BorderFactory.createLineBorder(thirdColor, 2);

		speedLabel = new JLabel(" Speed: 60 MPH");
		speedLabel.setBackground(secondColor);
		speedLabel.setOpaque(true);
		speedLabel.setBounds(200, 400, 140, 40);
		speedLabel.setForeground(thirdColor);
		speedLabel.setBorder(b);
		speedLabel.setFont(new Font("Serif", Font.BOLD, 18));
		mainPanel.add(speedLabel);

		ImageIcon leftArrow = new ImageIcon("leftArrow.png");
		decreaseSpeedButton = new JButton(leftArrow);
		decreaseSpeedButton.setBounds(161, 400, 39, 40);
		decreaseSpeedButton.setOpaque(true);
		decreaseSpeedButton.setBorder(b);
		decreaseSpeedButton.addActionListener(this);
		mainPanel.add(decreaseSpeedButton);

		ImageIcon rightArrow = new ImageIcon("rightArrow.png");
		increaseSpeedButton = new JButton(rightArrow);
		increaseSpeedButton.setBounds(340, 400, 39, 40);
		increaseSpeedButton.setOpaque(true);
		increaseSpeedButton.setBorder(b);
		increaseSpeedButton.addActionListener(this);
		mainPanel.add(increaseSpeedButton);

		stopDurationLabel = new JLabel(" Stop: 12 second");
		stopDurationLabel.setBackground(secondColor);
		stopDurationLabel.setOpaque(true);
		stopDurationLabel.setBounds(500, 400, 140, 40);
		stopDurationLabel.setForeground(thirdColor);
		stopDurationLabel.setFont(new Font("Serif", Font.BOLD, 18));
		stopDurationLabel.setBorder(b);
		mainPanel.add(stopDurationLabel);

		decreaseTimeButton = new JButton(leftArrow);
		decreaseTimeButton.setBounds(461, 400, 39, 40);
		decreaseTimeButton.setOpaque(true);
		decreaseTimeButton.addActionListener(this);
		decreaseTimeButton.setBorder(b);
		mainPanel.add(decreaseTimeButton);

		increaseTimeButton = new JButton(rightArrow);
		increaseTimeButton.setBounds(640, 400, 39, 40);
		increaseTimeButton.setOpaque(true);
		increaseTimeButton.setBorder(b);
		increaseTimeButton.addActionListener(this);
		mainPanel.add(increaseTimeButton);

		startButton = new JButton(new ImageIcon("start.png"));
		startButton.setBounds(770, 390, 60, 60);
		startButton.setOpaque(true);
		startButton.setBorder(b);
		startButton.addActionListener(this);
		mainPanel.add(startButton);

		currentTime = new Timer(1000,this);
		currentTime.start();
		
		timeLabel = new JLabel("  "+new java.util.Date());
		timeLabel.setBounds(565, 500, 270, 30);
		timeLabel.setBackground(secondColor);
		timeLabel.setFont(new Font("Serif", Font.BOLD, 18));
		timeLabel.setForeground(thirdColor);
		timeLabel.setOpaque(true);
		mainPanel.add(timeLabel);
		
		timer = new Timer((int) (5 / (60 / 60.0)), this);

		for(int i = 0; i < 5; i++){
			arriveTime[i] = new JLabel();
			arriveTime[i].setBounds(60,470 + i * 29, 400,30);
			arriveTime[i].setBackground(secondColor);
			arriveTime[i].setOpaque(true);
			arriveTime[i].setForeground(thirdColor);
			arriveTime[i].setFont(new Font("Serif", Font.BOLD, 16));
			arriveTime[i].setBorder(b);
			mainPanel.add(arriveTime[i]);
		}
		getPoints();
		
		String[] theme = {"Choose a theme.","Defaut","Bright","Dark"};
		themes = new JComboBox<String>(theme);
		themes.setBounds(700,570,200,40);
		themes.addActionListener(this);
		mainPanel.add(themes);
		
		window.setVisible(true);
	}
	
	private void createStationLabel(){
		station[0] = new JLabel("A");
		station[0].setBounds(415, 242, 20, 30);
		station[0].setBackground(secondColor);
		station[0].setFont(new Font("Serif", Font.BOLD, 18));
		station[0].setForeground(thirdColor);
		station[0].setOpaque(false);
		label.add(station[0]);
		
		station[1] = new JLabel("B");
		station[1].setBounds(725, 116, 20, 30);
		station[1].setBackground(secondColor);
		station[1].setFont(new Font("Serif", Font.BOLD, 18));
		station[1].setForeground(thirdColor);
		station[1].setOpaque(false);
		label.add(station[1]);
		
		station[2] = new JLabel("C");
		station[2].setBounds(390, 25, 20, 30);
		station[2].setBackground(secondColor);
		station[2].setFont(new Font("Serif", Font.BOLD, 18));
		station[2].setForeground(thirdColor);
		station[2].setOpaque(false);
		label.add(station[2]);
		
		station[3] = new JLabel("D");
		station[3].setBounds(60, 95, 20, 30);
		station[3].setBackground(secondColor);
		station[3].setFont(new Font("Serif", Font.BOLD, 18));
		station[3].setForeground(thirdColor);
		station[3].setOpaque(false);
		label.add(station[3]);
		
		station[4] = new JLabel("E");
		station[4].setBounds(130, 242, 20, 30);
		station[4].setBackground(secondColor);
		station[4].setFont(new Font("Serif", Font.BOLD, 18));
		station[4].setForeground(thirdColor);
		station[4].setOpaque(false);
		label.add(station[4]);
	}

	private void getPoints() {
		points = new Vector<Point>();
		for (int i = 400; i <= 670; i++) {
			points.add(new Point(i, 242));
		}
		for (int i = 1; i <= 40; i++) {
			points.add(new Point(670 + i, 242 - i / 3));
		}
		for (int i = 230; i >= 40; i--) {
			points.add(new Point(710, i));
		}

		for (int i = 1; i <= 40; i++) {
			points.add(new Point(710 - i, 40 - i / 3));
		}

		for (int i = 670; i >= 70; i--) {
			points.add(new Point(i, 25));
		}

		for (int i = 1; i <= 24; i++) {
			points.add(new Point(70 - i, 25 + i / 3 * 2));
		}

		for (int i = 40; i <= 230; i++) {
			points.add(new Point(46, i));
		}

		for (int i = 1; i <= 24; i++) {
			points.add(new Point(46 + i, 230 + i / 3 * 2));
		}

		for (int i = 70; i < 399; i++) {
			points.add(new Point(i, 242));
		}
	}

	private void setArriveTime(){
		boolean atStation = false;
		int first = 0;
		for(int i = 0; i < 5; i++){
			if(p >= allStop[i]){
				if(p == allStop[i]){
					atStation = true;
				}
				first = (i + 1) % 5;
			}
		}
		
		for(int i = 0; i < 5; i++){
			Calendar cal = Calendar.getInstance();
			String s = " Arrving Station "+((char)('A'+first)) + " : ";
			int mul = i;
			if(atStation){
				mul++;
			}
			int time;
			if(allStop[first] > p){
				time = (((allStop[first] - p) * ( (int) (5 / (speed / 60.0)) ) + (mul * 1000 * (stopTime + 2) )))/1000;
				cal.add(Calendar.SECOND, time);
				s += cal.getTime();
				arriveTime[i].setText(s);
			}else if(allStop[first] == p){
				arriveTime[i].setText(" Arrving Station "+((char)('A'+first)) + " : Arrived");
			}else{
				time = (((1711 - (p - allStop[first]))* ( (int) (5 / (speed / 60.0)) ) + (mul * 1000 * (stopTime + 2) )))/1000;
				cal.add(Calendar.SECOND, time);
				s += cal.getTime();
				arriveTime[i].setText(s);
			}
			
			
			first = (first + 1) % 5;
		}
	}
	
	private void openDoor() {
		openDoor = new Timer(125, this);
		openDoor.start();
	}

	private void closeDoor() {
		closeDoor = new Timer(125, this);
		closeDoor.start();
	}

	private void stop() {
		timer.stop();
		openDoor();

	}

	private void move() {
		tram.setLocation(points.get(p));
		setArriveTime();
		if (p == 0 || p == 429 || p == 836 || p == 1222 || p == 1427) {
			if(stopTime != 0)
				stop();
		}else{
		}
		p++;
		p = p % 1711;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			if (!running) {
				setArriveTime();
				if(theme == 1)
					startButton.setIcon(new ImageIcon("stop.png"));
				else if(theme == 2)
					startButton.setIcon(new ImageIcon("stopBlue.png"));
				
				if (timerRunning == 1 || timerRunning == 0)
					timer.start();
				else if (timerRunning == 2)
					stopTimer.start();
				else if (timerRunning == 3)
					closeDoor.start();
				else if (timerRunning == 4)
					openDoor.start();
				running = true;
			} else {
				if(theme == 1){
					startButton.setIcon(new ImageIcon("start.png"));
				}else if(theme == 2){
					startButton.setIcon(new ImageIcon("startBlue.png"));
				}
				if (timer.isRunning()) {
					timer.stop();
					timerRunning = 1;
				} else if (stopTimer.isRunning()) {
					stopTimer.stop();
					timerRunning = 2;
				} else if (closeDoor.isRunning()) {
					closeDoor.stop();
					timerRunning = 3;
				} else if (openDoor.isRunning()) {
					openDoor.stop();
					timerRunning = 4;
				}
				running = false;
			}
		} else if (e.getSource() == timer) {
			move();
		} else if (e.getSource() == decreaseTimeButton) {
			stopTime -= 4;
			stopDurationLabel.setText(" Stop: " + stopTime + " second");
			mainPanel.repaint();
			if (stopTime == 0) {
				decreaseTimeButton.setVisible(false);
			}

		} else if (e.getSource() == increaseTimeButton) {
			stopTime += 4;
			stopDurationLabel.setText(" Stop: " + stopTime + " second");
			mainPanel.repaint();
			decreaseTimeButton.setVisible(true);
		} else if (e.getSource() == decreaseSpeedButton) {
			speed -= 5;
			speedLabel.setText(" Speed: " + speed + " MPH");
			timer.setDelay((int) (5 / (speed / 60.0)));
			mainPanel.repaint();
			if (speed == 0) {
				decreaseSpeedButton.setVisible(false);
			}
			increaseSpeedButton.setVisible(true);
		} else if (e.getSource() == increaseSpeedButton) {
			speed += 5;
			speedLabel.setText(" Speed: " + speed + " MPH");
			timer.setDelay((int) (5 / (speed / 60.0)));
			mainPanel.repaint();
			decreaseSpeedButton.setVisible(true);
			if (speed == 90) {
				increaseSpeedButton.setVisible(false);
			}
		} else if (e.getSource() == closeDoor) {
			leftDoor.setLocation(leftDoor.getX() + 10, 0);
			rightDoor.setLocation(rightDoor.getX() - 10, 0);
			if (leftDoor.getX() == 0) {
				closeDoor.stop();
				timer.start();
			}

		} else if (e.getSource() == openDoor) {
			leftDoor.setLocation(leftDoor.getX() - 10, 0);
			rightDoor.setLocation(rightDoor.getX() + 10, 0);
			if (leftDoor.getX() == -80) {
				openDoor.stop();
				stopTimer = new Timer(stopTime * 1000, this);
				stopTimer.start();
			}
		}
		else if (e.getSource() == stopTimer) {
			stopTimer.stop();
			closeDoor();
		}else if(e.getSource() == currentTime){
			timeLabel.setText("  "+new java.util.Date());
		}
		else if(e.getSource() == themes){
			if(themes.getSelectedIndex() != 0 && themes.getSelectedIndex() != theme){
				if(themes.getSelectedIndex() == 2){
					leftDoor.setIcon(new ImageIcon("doorBlue.png"));
					rightDoor.setIcon(new ImageIcon("doorBlue.png"));
					startButton.setIcon(new ImageIcon("startBlue.png"));
					increaseSpeedButton.setIcon(new ImageIcon("rightArrowBlue.png"));
					decreaseSpeedButton.setIcon(new ImageIcon("leftArrowBlue.png"));
					decreaseTimeButton.setIcon(new ImageIcon("leftArrowBlue.png"));
					increaseTimeButton.setIcon(new ImageIcon("rightArrowBlue.png"));
					label.setIcon(new ImageIcon("Tram routeBlue.png"));
					mainPanelColor = new Color(255, 255, 255);
					secondColor = new Color(153, 204, 255);
					thirdColor = new Color(102, 153, 204);
					
					Border b = BorderFactory.createLineBorder(thirdColor, 2);
					for(int i = 0; i < 5; i++){
						station[i].setBackground(secondColor);
						station[i].setForeground(mainPanelColor);
						arriveTime[i].setBackground(secondColor);
						arriveTime[i].setForeground(mainPanelColor);
						arriveTime[i].setBorder(b);
					}
					startButton.setBorder(b);
					increaseSpeedButton.setBorder(b);
					decreaseSpeedButton.setBorder(b);
					decreaseTimeButton.setBorder(b);
					increaseTimeButton.setBorder(b);
					door.setBackground(mainPanelColor);
					speedLabel.setBackground(secondColor);
					speedLabel.setForeground(thirdColor);
					speedLabel.setBorder(b);
					mainPanel.setBackground(mainPanelColor);
					tram.setBackground(thirdColor);
					stopDurationLabel.setBackground(secondColor);
					stopDurationLabel.setForeground(thirdColor);
					stopDurationLabel.setBorder(b);
					timeLabel.setBackground(secondColor);
					timeLabel.setBorder(b);
					timeLabel.setForeground(thirdColor);
					theme = 2;
				}else if(themes.getSelectedIndex() == 1){
					leftDoor.setIcon(new ImageIcon("door.png"));
					rightDoor.setIcon(new ImageIcon("door.png"));
					startButton.setIcon(new ImageIcon("start.png"));
					increaseSpeedButton.setIcon(new ImageIcon("rightArrow.png"));
					decreaseSpeedButton.setIcon(new ImageIcon("leftArrow.png"));
					decreaseTimeButton.setIcon(new ImageIcon("leftArrow.png"));
					increaseTimeButton.setIcon(new ImageIcon("rightArrow.png"));
					label.setIcon(new ImageIcon("Tram route.png"));
					mainPanelColor = new Color(153, 204, 255);
					secondColor = new Color(255, 255, 204);
					thirdColor = new Color(255, 204, 51);
					
					Border b = BorderFactory.createLineBorder(thirdColor, 2);
					
					for(int i = 0; i < 5; i++){
						station[i].setBackground(secondColor);
						station[i].setForeground(thirdColor);
						arriveTime[i].setBackground(secondColor);
						arriveTime[i].setForeground(thirdColor);
						arriveTime[i].setBorder(b);
					}
					increaseSpeedButton.setBorder(b);
					decreaseSpeedButton.setBorder(b);
					decreaseTimeButton.setBorder(b);
					increaseTimeButton.setBorder(b);
					startButton.setBorder(b);
					
					speedLabel.setBackground(secondColor);
					speedLabel.setForeground(thirdColor);
					speedLabel.setBorder(b);
					door.setBackground(mainPanelColor);
					tram.setBackground(thirdColor);
					mainPanel.setBackground(mainPanelColor);	
					stopDurationLabel.setBackground(secondColor);
					stopDurationLabel.setForeground(thirdColor);
					stopDurationLabel.setBorder(b);
					timeLabel.setBackground(secondColor);
					timeLabel.setBorder(b);
					timeLabel.setForeground(thirdColor);
					theme = 1;
				}else if(themes.getSelectedIndex() == 3){
					leftDoor.setIcon(new ImageIcon("doorDark.png"));
					rightDoor.setIcon(new ImageIcon("doorDark.png"));
					startButton.setIcon(new ImageIcon("startDark.png"));
					increaseSpeedButton.setIcon(new ImageIcon("rightArrowDark.png"));
					decreaseSpeedButton.setIcon(new ImageIcon("leftArrowDark.png"));
					decreaseTimeButton.setIcon(new ImageIcon("leftArrowDark.png"));
					increaseTimeButton.setIcon(new ImageIcon("rightArrowDark.png"));
					label.setIcon(new ImageIcon("Tram routeDark.png"));
					mainPanelColor = new Color(204, 153, 153);
					secondColor = new Color(204, 204, 153);
					thirdColor = new Color(0, 0, 0);
					
					Border b = BorderFactory.createLineBorder(thirdColor, 2);
					
					
					for(int i = 0; i < 5; i++){
						station[i].setBackground(secondColor);
						station[i].setForeground(mainPanelColor);
						arriveTime[i].setBackground(secondColor);
						arriveTime[i].setForeground(thirdColor);
						arriveTime[i].setBorder(b);
					}
					increaseSpeedButton.setBorder(b);
					decreaseSpeedButton.setBorder(b);
					decreaseTimeButton.setBorder(b);
					increaseTimeButton.setBorder(b);
					startButton.setBorder(b);
					
					speedLabel.setBackground(secondColor);
					speedLabel.setForeground(thirdColor);
					speedLabel.setBorder(b);
					door.setBackground(mainPanelColor);
					tram.setBackground(secondColor);
					mainPanel.setBackground(mainPanelColor);	
					stopDurationLabel.setBackground(secondColor);
					stopDurationLabel.setForeground(thirdColor);
					stopDurationLabel.setBorder(b);
					timeLabel.setBackground(secondColor);
					timeLabel.setBorder(b);
					timeLabel.setForeground(thirdColor);
					theme = 3;
				}
			}
		}
	}
}

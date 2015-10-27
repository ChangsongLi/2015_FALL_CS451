import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu implements ComponentListener{
	private JFrame menu;
	private JPanel panel;
	private JLabel mode;
	private MyButton beginnerButton, graduatedButton, challengerButton,color;
	public static Color backgroundColor = new Color(173, 214, 255);
	public static Color tileColor = new Color(204, 255, 51);
	public static Color buttonColor = Color.white;
	
	public Menu(){
		createMenu();
	}
	
	public void createMenu(){
		menu = new JFrame("scrabble game");
		menu.setVisible(false);
		menu.setBounds(340, 140, 400, 600);
		menu.setBackground(Color.white);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.addComponentListener(this);
		
		panel = new JPanel();
		panel.setBounds(0,0,menu.getWidth(),menu.getHeight());
		panel.setBackground(Color.gray);
		panel.setLayout(null);
		menu.add(panel);
		
		mode = new JLabel("Select a mode");
		mode.setBounds(menu.getWidth()/3, menu.getHeight()/10 , menu.getWidth()/3, menu.getHeight()/15);
		mode.setOpaque(true);
		mode.setBackground(Color.gray);
		mode.setHorizontalAlignment(SwingConstants.CENTER);
		mode.setVerticalAlignment(SwingConstants.CENTER);
		panel.add(mode);
		
		beginnerButton = new MyButton("Beginners");
		beginnerButton.setBounds(menu.getWidth()/3, menu.getHeight()/10 * 2 , menu.getWidth()/3, menu.getHeight()/15);
		beginnerButton.setOpaque(true);
		beginnerButton.setBorderPainted(false);
		panel.add(beginnerButton);
		
		graduatedButton = new MyButton("Graduate");
		graduatedButton.setBounds(menu.getWidth()/3, menu.getHeight()/10 * 4, menu.getWidth()/3, menu.getHeight()/15);
		graduatedButton.setOpaque(true);
		graduatedButton.setBorderPainted(false);
		panel.add(graduatedButton);
		
		challengerButton = new MyButton("Challenger");
		challengerButton.setBounds(menu.getWidth()/3, menu.getHeight()/10 * 6, menu.getWidth()/3, menu.getHeight()/15);
		challengerButton.setOpaque(true);
		challengerButton.setBorderPainted(false);
		panel.add(challengerButton);
		
		color= new MyButton("Color DIY");
		color.setBounds(menu.getWidth()/3, menu.getHeight()/10 * 9, menu.getWidth()/3, menu.getHeight()/20);
		color.setOpaque(true);
		color.setBorderPainted(false);
		color.setBackground(Color.cyan);
		panel.add(color);
		
		
		menu.repaint();
		menu.setVisible(true);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		mode.setBounds(menu.getWidth()/3, menu.getHeight()/10 , menu.getWidth()/3, menu.getHeight()/15);
		challengerButton.setBounds(menu.getWidth()/3, menu.getHeight()/10 * 6, menu.getWidth()/3, menu.getHeight()/15);
		beginnerButton.setBounds(menu.getWidth()/3, menu.getHeight()/10 * 2, menu.getWidth()/3, menu.getHeight()/15);
		graduatedButton.setBounds(menu.getWidth()/3, menu.getHeight()/10 * 4 , menu.getWidth()/3, menu.getHeight()/15);
		color.setBounds(menu.getWidth()/3, menu.getHeight()/10 * 9, menu.getWidth()/3, menu.getHeight()/20);
		panel.setBounds(0,0,menu.getWidth(),menu.getHeight());
		
		menu.repaint();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

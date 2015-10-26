import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class BeginerGame implements ComponentListener{
	private JFrame board;
	private JPanel panel;
	private BoardButton[][] buttonOnBoard; 
	private JLabel score,turn;
	
	public BeginerGame(){
		createBoard();
	}
	
	private void createBoard(){
		board = new JFrame("Beginer Game");
		board.setVisible(false);
		board.setBounds(200, 50, 700, 700);
		board.setBackground(new Color(173, 214, 255));
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.addComponentListener(this);
		
		panel = new JPanel();
		panel.setBounds(0,0,board.getWidth(),board.getHeight());
		panel.setBackground(new Color(173, 214, 255));
		panel.setLayout(null);
		board.add(panel);
		
		initBoardButton();
		
		score = new JLabel("Player1: 0      Player2: 0");
		score.setBounds(board.getWidth()/2,20, board.getWidth()/2, 60);
		score.setFont(new Font("Serif", Font.PLAIN, 20));
		panel.add(score);
		
		turn = new JLabel("Player1's turn");
		turn.setBounds(board.getWidth()/15,10, board.getWidth()/2, 80);
		turn.setFont(new Font("Serif", Font.PLAIN, 30));
		panel.add(turn);
		
		board.repaint();
		board.setVisible(true);
	}

	private void initBoardButton(){
		buttonOnBoard = new BoardButton[11][11];
		Border thickBorder = new LineBorder(Color.black, 1);
		for(int row = 0; row < 11; row++){
			for(int col = 0; col < 11; col++){
				int type = getType(row, col);
				buttonOnBoard[row][col] = new BoardButton(type);
				buttonOnBoard[row][col].setBounds(board.getWidth()/6+board.getWidth()/14 *col,100 +board.getHeight()/14 * row,board.getWidth()/14,board.getHeight()/14);
				buttonOnBoard[row][col].setOpaque(true);
				buttonOnBoard[row][col].setBorder(thickBorder);
				buttonOnBoard[row][col].setBorderPainted(true);
				buttonOnBoard[row][col].setBackground(getTileColor(type));
				
				panel.add(buttonOnBoard[row][col]);
			}
		}
	}
	
	private Color getTileColor(int type){
		if(type == BoardButton.TRIPLEWORD)
			return Color.red;
		else if(type == BoardButton.DOUBLEWORD)
			return new Color(71, 145, 255);
		else if(type == BoardButton.DOUBLELETTER)
			return Color.cyan;
		else if(type == BoardButton.TRIPLELETTER)
			return Color.pink;
		else if(type == BoardButton.START)
			return Color.pink;
		
		Color c = new Color(255, 255, 153);
		return c;
	}
	
	private int getType(int row, int col){
		if(row == 0){
			if(col == 0||col == 5||col == 10)
				return BoardButton.TRIPLEWORD;
		}else if(row == 1){
			if(col == 1||col == 9)
				return BoardButton.DOUBLEWORD;
			else if(col == 3 || col == 7)
				return BoardButton.TRIPLELETTER;
		}else if(row == 2){
			if(col == 2||col == 8)
				return BoardButton.DOUBLEWORD;
			if(col == 5)
				return BoardButton.DOUBLELETTER;
		}else if(row == 3){
			if(col == 1||col == 9)
				return BoardButton.TRIPLELETTER;
			else if(col == 3 || col == 7)
				return BoardButton.DOUBLEWORD;
		}else if(row == 4){
			if(col == 4||col == 6)
				return BoardButton.DOUBLEWORD;
		}else if(row == 5){
			if(col==0||col==10)
				return BoardButton.TRIPLEWORD;
			else if(col == 5)
				return BoardButton.START;
			else if(col == 2||col == 8)
				return BoardButton.DOUBLELETTER;
		}else if(row == 6){
			if(col == 4||col == 6)
				return BoardButton.DOUBLEWORD;
		}else if(row == 7){
			if(col == 1||col == 9)
				return BoardButton.TRIPLELETTER;
			else if(col == 3 || col == 7)
				return BoardButton.DOUBLEWORD;
		}else if(row == 8){
			if(col == 2||col == 8)
				return BoardButton.DOUBLEWORD;
			if(col == 5)
				return BoardButton.DOUBLELETTER;
		}else if(row == 9){
			if(col == 1||col == 9)
				return BoardButton.DOUBLEWORD;
			else if(col == 3 || col == 7)
				return BoardButton.TRIPLELETTER;
		}else if(row == 10){
			if(col == 0||col == 5||col == 10)
				return BoardButton.TRIPLEWORD;
		}
		
		return BoardButton.BLANK;
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		panel.setBounds(0,0,board.getWidth(),board.getHeight());
		for(int row = 0; row < 11; row++){
			for(int col = 0; col < 11; col++){
				buttonOnBoard[row][col].setBounds(board.getWidth()/6+board.getWidth()/14 *col,100 +board.getHeight()/14 * row,board.getWidth()/14,board.getHeight()/14);
			}
		}
		score.setBounds(board.getWidth()/2,20, board.getWidth()/2, 60);
		turn.setBounds(board.getWidth()/15,10, board.getWidth()/2, 80);
		board.repaint();
		board.setVisible(true);
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

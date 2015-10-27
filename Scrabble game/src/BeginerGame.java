import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class BeginerGame implements ComponentListener{
	public static JFrame board;
	private JPanel panel;
	private BoardButton[][] buttonOnBoard; 
	public JLabel score,turn;
	private Vector<Tiles> tiles; 
	private Tiles[] player1Tiles, player2Tiles;
	public static Vector<String> positionOfWord;
	public static MyButton[] player1TilesButton, player2TilesButton;
	public static MyButton submit,pass,color;
	public boolean firstTurn = true;
	public static MyButton choose = null;
	public static int turnNum = 1;
	public Vector<String> allWords = new Vector<String>();
	public int scorePlayer1 = 0,scorePlayer2 = 0;
	
	
	
	
	public BeginerGame(){
		createBoard();
	}
	
	private void createBoard(){
		turnNum = 1;
		board = new JFrame("Beginer Game");
		board.setVisible(false);
		board.setBounds(200, 50, 700, 700);
		board.setBackground(Menu.backgroundColor);
		board.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		board.addComponentListener(this);
		
		panel = new JPanel();
		panel.setBounds(0,0,board.getWidth(),board.getHeight());
		panel.setBackground(Menu.backgroundColor);
		panel.setLayout(null);
		board.add(panel);
		
		initBoardButton();
		
		score = new JLabel("Player1: "+scorePlayer1+"     Player2: "+scorePlayer2);
		score.setBounds(board.getWidth()/2,20, board.getWidth()/2, 60);
		score.setFont(new Font("Serif", Font.PLAIN, 20));
		panel.add(score);
		
		turn = new JLabel("Player"+turnNum+"'s turn");
		turn.setBounds(board.getWidth()/15,10, board.getWidth()/2, 80);
		turn.setFont(new Font("Serif", Font.PLAIN, 30));
		panel.add(turn);
		
		tiles = getTiles();
		player1Tiles = new Tiles[7];
		player2Tiles = new Tiles[7];
		player1TilesButton = new MyButton[7];
		player2TilesButton = new MyButton[7];
		Border thickBorder = new LineBorder(Color.black, 1);
		for(int i = 0; i <player1Tiles.length ; i++){
			//add p1 tiles to panel
			player1Tiles[i] = getATile();
			player1TilesButton[i] = new MyButton(player1Tiles[i].getNameOfTile()+" "+player1Tiles[i].getScoreOfTile());
			player1TilesButton[i].setBounds(board.getWidth()/20, board.getHeight()/6 + board.getHeight()/14 * i, board.getWidth()/14, board.getHeight()/14);
			player1TilesButton[i].setOpaque(true);
			player1TilesButton[i].setBorder(thickBorder);
			player1TilesButton[i].setBorderPainted(true);
			player1TilesButton[i].setBackground(Menu.tileColor);
			player1TilesButton[i].setBG(this);
			panel.add(player1TilesButton[i]);
			
			//add p2 tiles to panel
			player2Tiles[i] = getATile();
			player2TilesButton[i] = new MyButton(player2Tiles[i].getNameOfTile()+" "+player1Tiles[i].getScoreOfTile());
			player2TilesButton[i].setBounds(board.getWidth()/20, board.getHeight()/6 + board.getHeight()/14 * i, board.getWidth()/14, board.getHeight()/14);
			player2TilesButton[i].setOpaque(true);
			player2TilesButton[i].setBorder(thickBorder);
			player2TilesButton[i].setBorderPainted(true);
			player2TilesButton[i].setBackground(Menu.tileColor);
			player2TilesButton[i].setBG(this);
			panel.add(player2TilesButton[i]);
		
		}
		setTileOnWindow(1);
		initPositionOfWord();
		submit = new MyButton("Submit");
		submit.setBounds(board.getWidth()/25, board.getHeight()/4 + board.getHeight()/14 * 7, board.getWidth()/10, board.getHeight()/14);
		submit.setOpaque(true);
		submit.setBorder(thickBorder);
		submit.setBackground(Menu.buttonColor);
		submit.setBG(this);
		panel.add(submit);
		
		pass = new MyButton("Pass turn");
		pass.setBounds(board.getWidth()/25, board.getHeight()/4 + board.getHeight()/13 * 8, board.getWidth()/10, board.getHeight()/14);
		pass.setOpaque(true);
		pass.setBorder(thickBorder);
		pass.setBackground(Menu.buttonColor);
		pass.setBG(this);
		panel.add(pass);
		

		
		addAllWords();
		
		board.repaint();
		board.setVisible(true);
	}
	
	private void addAllWords(){
		try {
			BufferedReader br = new BufferedReader(new FileReader("Words.txt"));
			String line = br.readLine();
			while(line != null){
				Scanner scan = new Scanner(line);
				line = scan.next();
				allWords.add(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setTileOnWindow(int player){
		if(player == 1){
			for(int i = 0; i <player1Tiles.length ; i++){
				player1TilesButton[i].setVisible(true);
				player2TilesButton[i].setVisible(false);
			}
		}else{
			for(int i = 0; i <player1Tiles.length ; i++){
				player2TilesButton[i].setVisible(true);
				player1TilesButton[i].setVisible(false);
			}
		}
		board.repaint();
		board.setVisible(true);
	}
	
	public void updataTilesForPlayer(int player){
		if(player == 1){
			for(int i = 0; i < player1TilesButton.length; i++){
				if(player1TilesButton[i].isSetted()){
					player1TilesButton[i].setSetted(false);
					Tiles t = getATile();
					String name = t.getNameOfTile()+" "+t.getScoreOfTile();
					player1TilesButton[i].setText(name);
					System.out.println(name);
					Border thickBorder = new LineBorder(Color.black, 1);
					player1TilesButton[i].setBorder(thickBorder);
					player1TilesButton[i].setBackground(Menu.tileColor);
					player1TilesButton[i].setOpaque(true);
					player1TilesButton[i].setBorder(thickBorder);
					player1TilesButton[i].setBorderPainted(true);
				}
			}
		}else{
			for(int i = 0; i < player2TilesButton.length; i++){
				if(player2TilesButton[i].isSetted()){
					Tiles t = getATile();
					String name = t.getNameOfTile()+" "+t.getScoreOfTile();
					player2TilesButton[i].setText(name);
					Border thickBorder = new LineBorder(Color.black, 1);
					player2TilesButton[i].setBorder(thickBorder);
					player2TilesButton[i].setBackground(Menu.tileColor);
				}
			}
		}
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
				buttonOnBoard[row][col].setPosition(row, col);
				panel.add(buttonOnBoard[row][col]);
			}
		}
	}
	
	private Tiles getATile(){
		Tiles ret = null;
		if(tiles.size() != 0){
			int index = (int)(Math.random() * tiles.size());
			ret = tiles.remove(index);
		}
		
		return ret;
	}
	
	
	// get all tiles
	private Vector<Tiles> getTiles(){
		Vector<Tiles> tiles = new Vector<Tiles>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("Tiles.txt"));
			String line = br.readLine();
			while(line != null){
				Scanner scan = new Scanner(line);
				String name = scan.next();
				int score = scan.nextInt();
				int number = scan.nextInt();
				for(int i = 0; i < number; i++)
					tiles.add(new Tiles(name,score));
				scan.close();
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tiles;
	}
	
	public void initPositionOfWord(){
		positionOfWord = new Vector<String>();
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
	
	public String getWord(int[][] location){
		String s = "";
		for(int i = 0; i < location.length; i++){
			Scanner scan = new Scanner(buttonOnBoard[location[i][0]][location[i][1]].getText());
			s = s  + scan.next();
		}
		return s;
	}
	
	// word : word  location: index of each char
	public int checkWord(int[][] location){
		String word = getWord(location);
		if(allWords.contains(word)){
			int total = 0;
			int wordMult = 1;
			for(int i = 0; i < word.length(); i++){
				
				Scanner scanner = new Scanner(buttonOnBoard[location[i][0]][location[i][1]].getText());
				scanner.next();
				int score = scanner.nextInt();
				int letMult = 1;
				if(buttonOnBoard[location[i][0]][location[i][1]].getType() == BoardButton.TRIPLELETTER){
					wordMult = wordMult * 3;
				}else if(buttonOnBoard[location[i][0]][location[i][1]].getType() == BoardButton.DOUBLELETTER){
					letMult = 2;
				}else if(buttonOnBoard[location[i][0]][location[i][1]].getType() == BoardButton.DOUBLEWORD){
					wordMult = wordMult * 2;
				}else if(buttonOnBoard[location[i][0]][location[i][1]].getType() == BoardButton.TRIPLEWORD){
					letMult = 3;
				}
				total = total +score * letMult;
				scanner.close();
			}
			total = total * wordMult;
			return total;
		}else{
			return -1;
		}
	}
	

	
	public boolean validWord(){
		for(int i = 0; i < positionOfWord.size(); i++){
			System.out.println(positionOfWord.get(i));
		}
		return true;
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
		for(int i = 0; i <player1Tiles.length ; i++){
			player1TilesButton[i].setBounds(board.getWidth()/20, board.getHeight()/6 + board.getHeight()/14 * i, board.getWidth()/14, board.getHeight()/14);
			player2TilesButton[i].setBounds(board.getWidth()/20, board.getHeight()/6 + board.getHeight()/14 * i, board.getWidth()/14, board.getHeight()/14);
		}
		submit.setBounds(board.getWidth()/25, board.getHeight()/4 + board.getHeight()/14 * 7, board.getWidth()/10, board.getHeight()/14);
		score.setBounds(board.getWidth()/2,20, board.getWidth()/2, 60);
		turn.setBounds(board.getWidth()/15,10, board.getWidth()/2, 80);
		pass.setBounds(board.getWidth()/25, board.getHeight()/4 + board.getHeight()/13 * 8, board.getWidth()/10, board.getHeight()/14);
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

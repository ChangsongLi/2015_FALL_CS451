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


public class ProGame implements ComponentListener{
	public static final int GRADUATE = 0;
	public static final int CHANLENGER = 1;
	public static Vector<String> positionOfWord;
	public int type;
	public boolean firstTurn = true;
	public static ProButton choose = null;
	public static ProButton[] player1TilesButton;
	public static ProButton[] player2TilesButton;
	public ProButton submit,pass;
	public JLabel score,turn;
	private ProBoardButton[][] buttonOnBoard; 
	public JFrame board;
	private JPanel panel;
	public int scorePlayer1 = 0,scorePlayer2 = 0,scorePlayer3 = 0,scorePlayer4 = 0;
	private Vector<Tiles> tiles; 
	public static int turnNum = 1;
	private Tiles[] player1Tiles, player2Tiles;
	public Vector<String> allWords = new Vector<String>();
	String numberOfPlayer;
	public ProGame(int typeBoard,String num){
		numberOfPlayer = num;
		type =  typeBoard;
		createBoard();
	}
	
	private void createBoard(){
		turnNum = 1;
		board = new JFrame("Pro Game");
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
		
		if(numberOfPlayer.equals("2")){
			score = new JLabel("Player1: "+scorePlayer1+" Player2: "+scorePlayer2);
		}else if(numberOfPlayer.equals("3")){
			score = new JLabel("Player1: "+scorePlayer1+" Player2: "+scorePlayer2+" Player3: "+scorePlayer3);
		}else if(numberOfPlayer.equals("4")){
			score = new JLabel("Player1: "+scorePlayer1+" Player2: "+scorePlayer2+" Player3: "+scorePlayer3+" Player4: "+scorePlayer4);
		}
		score.setBounds(board.getWidth()/3,20, board.getWidth()/2, 60);
		score.setFont(new Font("Serif", Font.PLAIN, 20));
		panel.add(score);
		
		turn = new JLabel("Player"+turnNum+"'s turn");
		turn.setBounds(board.getWidth()/15,10, board.getWidth()/2, 80);
		turn.setFont(new Font("Serif", Font.PLAIN, 30));
		panel.add(turn);
		
		tiles = getTiles();
		player1Tiles = new Tiles[7];
		player2Tiles = new Tiles[7];
		player1TilesButton = new ProButton[7];
		player2TilesButton = new ProButton[7];
		Border thickBorder = new LineBorder(Color.black, 1);
		
		turnNum = 1;
	
		for(int i = 0; i <player1Tiles.length ; i++){
			//add p1 tiles to panel
			player1Tiles[i] = getATile();
			player1TilesButton[i] = new ProButton(player1Tiles[i].getNameOfTile()+" "+player1Tiles[i].getScoreOfTile());
			player1TilesButton[i].setBounds(board.getWidth()/20, board.getHeight()/6 + board.getHeight()/14 * i, board.getWidth()/14, board.getHeight()/14);
			player1TilesButton[i].setOpaque(true);
			player1TilesButton[i].setBorder(thickBorder);
			player1TilesButton[i].setBorderPainted(true);
			player1TilesButton[i].setBackground(Menu.tileColor);
			player1TilesButton[i].setBG(this);
			panel.add(player1TilesButton[i]);
			
			//add p2 tiles to panel
			player2Tiles[i] = getATile();
			player2TilesButton[i] = new ProButton(player2Tiles[i].getNameOfTile()+" "+player1Tiles[i].getScoreOfTile());
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
		submit = new ProButton("Submit");
		submit.setBounds(board.getWidth()/25, board.getHeight()/4 + board.getHeight()/14 * 7, board.getWidth()/10, board.getHeight()/14);
		submit.setOpaque(true);
		submit.setBorder(thickBorder);
		submit.setBackground(Menu.buttonColor);
		submit.setBG(this);
		panel.add(submit);
		
		pass = new ProButton("Pass turn");
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

	public String getWord(int[][] location){
		String s = "";
		for(int i = 0; i < location.length; i++){
			Scanner scan = new Scanner(buttonOnBoard[location[i][0]][location[i][1]].getText());
			s = s  + scan.next();
		}
		return s;
	}
	
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
	
	public void initPositionOfWord(){
		positionOfWord = new Vector<String>();
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
	
	private void initBoardButton(){
		buttonOnBoard = new ProBoardButton[15][15];
		Border thickBorder = new LineBorder(Color.black, 1);
		for(int row = 0; row < 15; row++){
			for(int col = 0; col < 15; col++){
				int type = getType(row, col);
				buttonOnBoard[row][col] = new ProBoardButton(type);
				buttonOnBoard[row][col].setBounds(board.getWidth()/5+board.getWidth()/20 *col,100 +board.getHeight()/20 * row,board.getWidth()/20,board.getHeight()/20);
				buttonOnBoard[row][col].setOpaque(true);
				buttonOnBoard[row][col].setBorder(thickBorder);
				buttonOnBoard[row][col].setBorderPainted(true);
				buttonOnBoard[row][col].setBackground(getTileColor(type));
				buttonOnBoard[row][col].setPosition(row, col);
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
			if(col == 0||col == 7||col == 14)
				return BoardButton.TRIPLEWORD;
			else if(col == 3||col == 11)
				return BoardButton.DOUBLELETTER;
		}else if(row == 1){
			if(col == 1||col == 13)
				return BoardButton.DOUBLEWORD;
			else if(col == 5 || col == 9)
				return BoardButton.TRIPLELETTER;
		}else if(row == 2){
			if(col == 2||col == 12)
				return BoardButton.DOUBLEWORD;
			else if(col == 6||col == 8)
				return BoardButton.DOUBLELETTER;
		}else if(row == 3){
			if(col == 3||col == 11)
				return BoardButton.DOUBLEWORD;
			else if(col == 0||col == 7||col == 14)
				return BoardButton.DOUBLELETTER;
		}else if(row == 4){
			if(col == 4||col == 10)
				return BoardButton.DOUBLEWORD;
		}else if(row == 5){
			if(col==1||col==5||col==9||col==13)
				return BoardButton.TRIPLEWORD;
			
		}else if(row == 6){
			if(col == 2||col == 6||col == 8||col == 12)
				return BoardButton.DOUBLEWORD;
		}else if(row == 7){
			if(col == 0||col == 14)
				return BoardButton.TRIPLEWORD;
			else if(col == 7)
				return BoardButton.START;
			else if(col == 3 || col == 11)
				return BoardButton.DOUBLELETTER;
		}else if(row == 8){
			if(col == 2||col == 6||col == 8||col == 12)
				return BoardButton.DOUBLEWORD;
		}else if(row == 9){
			if(col==1||col==5||col==9||col==13)
				return BoardButton.TRIPLEWORD;
			
		}
		else if(row == 10){
			if(col == 4||col == 10)
				return BoardButton.DOUBLEWORD;
		}
		else if(row == 11){
			if(col == 3||col == 11)
				return BoardButton.DOUBLEWORD;
			else if(col == 0||col == 7||col == 14)
				return BoardButton.DOUBLELETTER;
		}
		else if(row == 12){
			if(col == 2||col == 12)
				return BoardButton.DOUBLEWORD;
			if(col == 6||col == 8)
				return BoardButton.DOUBLELETTER;
		}
		else if(row == 13){
			if(col == 1||col == 13)
				return BoardButton.DOUBLEWORD;
			else if(col == 5 || col == 9)
				return BoardButton.TRIPLELETTER;
		}
		else if(row == 14){
			if(col == 0||col == 7||col == 14)
				return BoardButton.TRIPLEWORD;
			else if(col == 3||col == 11)
				return BoardButton.DOUBLELETTER;
		}
		return BoardButton.BLANK;
	}
	
	private Tiles getATile(){
		Tiles ret = null;
		if(tiles.size() != 0){
			int index = (int)(Math.random() * tiles.size());
			ret = tiles.remove(index);
		}
		
		return ret;
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		for(int row = 0; row < 15; row++){
			for(int col = 0; col < 15; col++){
				buttonOnBoard[row][col].setBounds(board.getWidth()/5+board.getWidth()/20 *col,100 +board.getHeight()/20 * row,board.getWidth()/20,board.getHeight()/20);
			}
		}
		for(int i = 0; i <player1Tiles.length ; i++){
			player1TilesButton[i].setBounds(board.getWidth()/20, board.getHeight()/6 + board.getHeight()/14 * i, board.getWidth()/14, board.getHeight()/14);
			player2TilesButton[i].setBounds(board.getWidth()/20, board.getHeight()/6 + board.getHeight()/14 * i, board.getWidth()/14, board.getHeight()/14);
		}
		pass.setBounds(board.getWidth()/25, board.getHeight()/4 + board.getHeight()/13 * 8, board.getWidth()/10, board.getHeight()/14);
		submit.setBounds(board.getWidth()/25, board.getHeight()/4 + board.getHeight()/14 * 7, board.getWidth()/10, board.getHeight()/14);
		panel.setBounds(0,0,board.getWidth(),board.getHeight());
		score.setBounds(board.getWidth()/3,20, board.getWidth()/2, 60);
		turn.setBounds(board.getWidth()/15,10, board.getWidth()/2, 80);
		
		board.repaint();
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

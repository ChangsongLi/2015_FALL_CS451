import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MyButton extends JButton implements ActionListener{

	private boolean isSetted = false;
	BeginerGame beginerGame;
	public MyButton(String name){
		super(name);
		addActionListener(this);
	}
	
	
	public boolean isSetted(){
		return isSetted;
	}
	
	public void setSetted(boolean set){
		isSetted = set;
	}
	
	public void setBG(BeginerGame bg){
		beginerGame = bg;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(getText().equals("Beginners")){
			new BeginerGame();
		}else if(getText().equals("Graduate")){
			new ProGame(ProGame.GRADUATE);
		}else if(getText().equals("Challenger")){
			new ProGame(ProGame.CHANLENGER);
		}
		else if(getText().equals("Color DIY")){
		
		}
		else if(getText().equals("Pass turn")){
			if(beginerGame.submit.isVisible()){
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					if(beginerGame.turnNum == 1){
						beginerGame.turnNum = 2;
					}else{
						beginerGame.turnNum = 1;
					}
					beginerGame.turn.setText("Player"+beginerGame.turnNum+"'s turn");
					beginerGame.setTileOnWindow(beginerGame.turnNum);
				}
			}else{
				beginerGame.turn.setText("Player"+beginerGame.turnNum+"'s turn");
				beginerGame.initPositionOfWord();
				beginerGame.setTileOnWindow(beginerGame.turnNum);
				beginerGame.submit.setVisible(true);
			}
			
		}
		else if(getText().equals("Submit")){
			//if put tiles
			if(beginerGame.positionOfWord.size() == 0){
				JOptionPane.showMessageDialog(beginerGame.board, "No tiles on the board.");
			}else{
				//if put tile on star
				if(beginerGame.firstTurn){
					if(!beginerGame.positionOfWord.contains("5 5")){
						JOptionPane.showMessageDialog(beginerGame.board, "First word should place on the Star.");
					}else{
						 Vector<String> positionOfWord = (Vector<String>)beginerGame.positionOfWord.clone();
						boolean lineRow = true;
						boolean lineCol = true;
						int[] cols = new int[positionOfWord.size()];
						int[] rows = new int[positionOfWord.size()];
						Scanner scan = new Scanner(positionOfWord.remove(0));
						int row = scan.nextInt();
						int col = scan.nextInt();
						
						cols[0] = col;
						rows[0] = row;
						
						int i = 1;
						scan.close();
						while(positionOfWord.size() != 0){
							Scanner scan2 = new Scanner(positionOfWord.remove(0));
							rows[i] = scan2.nextInt();
							cols[i] = scan2.nextInt();
							if(row != rows[i]){
								lineRow = false;
							}
							if(col != cols[i]){
								lineCol = false;
							}
							i++;
						}
						// not in a line.
						if(lineRow == false && lineCol == false){
							JOptionPane.showMessageDialog(beginerGame.board, "Tiles are not in a line.");
						}
						// in line
						else{
							// its a row
							if(lineRow){
								Arrays.sort(cols);
								int num = cols[0];
								boolean checkConnect = true;
								for(int index = 1; index <cols.length; index++ ){
									if(cols[index] != (num +1)){
										checkConnect = false;
									}
									num = cols[index];
								}
								//connect
								if(checkConnect){
									//check word validate
									int[][] pos = new int[cols.length][2];
									for(int index = 0; index <cols.length; index++ ){
										pos[index][0] = row;
										pos[index][1] = cols[index];
										
									}
									
									if(pos.length > 4){
										JOptionPane.showMessageDialog(beginerGame.board, "Word too long for begineer.");
									}
									
									int ret = beginerGame.checkWord(pos);
									if(ret == -1){
										JOptionPane.showMessageDialog(beginerGame.board, "Not a word");
									}else{
										if(beginerGame.turnNum == 1){
											beginerGame.scorePlayer1 = beginerGame.scorePlayer1 + ret;
										}else{
											beginerGame.scorePlayer2 = beginerGame.scorePlayer2 + ret;
										}
										beginerGame.score.setText("Player1: "+beginerGame.scorePlayer1+"     Player2: "+beginerGame.scorePlayer2);
										if(beginerGame.turnNum == 1){
											beginerGame.updataTilesForPlayer(1);
											beginerGame.turnNum = 2;
											
										}
										else{
											beginerGame.updataTilesForPlayer(2);
											beginerGame.turnNum = 1;
										}
										
										beginerGame.board.repaint();
										beginerGame.board.setVisible(true);
										
										this.setVisible(false);
										beginerGame.firstTurn = false;
									}	
									
								}
								//not connect
								else{
									JOptionPane.showMessageDialog(beginerGame.board, "Tiles is not connected in row.");
								}
							}
							// its a col
							else if(lineCol){
								Arrays.sort(rows);
								int num = rows[0];
								boolean checkConnect = true;
								for(int index = 1; index <rows.length; index++ ){
									if(rows[index] != (num +1)){
										checkConnect = false;
									}
									num = rows[index];
								}
								
								
								// connect
								if(checkConnect){
									//check word validate
									int[][] pos = new int[rows.length][2];
									for(int index = 0; index <rows.length; index++ ){
										pos[index][0] = rows[index];
										pos[index][1] = col;
										
									}
									if(pos.length > 4){
										JOptionPane.showMessageDialog(beginerGame.board, "Word too long for begineer.");
									}
									
									int ret = beginerGame.checkWord(pos);
									if(ret == -1){
										JOptionPane.showMessageDialog(beginerGame.board, "Not a word");
									}else{
										if(beginerGame.turnNum == 1){
											beginerGame.scorePlayer1 = beginerGame.scorePlayer1 + ret;
										}else{
											beginerGame.scorePlayer2 = beginerGame.scorePlayer2 + ret;
										}
										beginerGame.score.setText("Player1: "+beginerGame.scorePlayer1+"     Player2: "+beginerGame.scorePlayer2);
										if(beginerGame.turnNum == 1){
											beginerGame.updataTilesForPlayer(1);
											beginerGame.turnNum = 2;
											
										}
										else{
											beginerGame.updataTilesForPlayer(2);
											beginerGame.turnNum = 1;
										}
										
										beginerGame.board.repaint();
										beginerGame.board.setVisible(true);
										
										this.setVisible(false);
										beginerGame.firstTurn = false;
										
									}
									
								}
								//not connect
								else{
									JOptionPane.showMessageDialog(beginerGame.board, "Tiles is not connected in col.");
								}
							}
						}
					}
				}
				else{
					
				}
			}
		}
		else{
			if(BeginerGame.turnNum == 1 && this.isSetted == false){
				BeginerGame.choose = this;
				for(int i = 0; i <BeginerGame.player1TilesButton.length;i++){
					if(BeginerGame.player1TilesButton[i].isSetted() == false)
						BeginerGame.player1TilesButton[i].setBackground(Menu.tileColor);
				}
				setBackground(Color.gray);
			}else if(BeginerGame.turnNum == 2 && this.isSetted == false){
				BeginerGame.choose = this;
				for(int i = 0; i <BeginerGame.player2TilesButton.length;i++){
					if(BeginerGame.player2TilesButton[i].isSetted() == false)
						BeginerGame.player2TilesButton[i].setBackground(Menu.tileColor);
				}
				setBackground(Color.gray);
			}
		}
		
	}
	
	
}

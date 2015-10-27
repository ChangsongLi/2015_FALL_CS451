import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ProButton extends JButton implements ActionListener{

	private boolean isSetted = false;
	ProGame proGame;
	public ProButton(String name){
		super(name);
		addActionListener(this);
	}
	public boolean isSetted(){
		return isSetted;
	}
	
	public void setSetted(boolean set){
		isSetted = set;
	}
	
	public void setBG(ProGame bg){
		proGame = bg;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(getText().equals("Pass turn")){
			if(proGame.submit.isVisible()){
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure?","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					if(proGame.turnNum == 1){
						proGame.turnNum = 2;
					}else{
						proGame.turnNum = 1;
					}
					proGame.turn.setText("Player"+proGame.turnNum+"'s turn");
					proGame.setTileOnWindow(proGame.turnNum);
				}
			}else{
				proGame.turn.setText("Player"+proGame.turnNum+"'s turn");
				proGame.initPositionOfWord();
				proGame.setTileOnWindow(proGame.turnNum);
				proGame.submit.setVisible(true);
			}
		}else if(getText().equals("Submit")){
			//if put tiles
			if(proGame.positionOfWord.size() == 0){
				JOptionPane.showMessageDialog(proGame.board, "No tiles on the board.");
			}else{
				//if put tile on star
				if(proGame.firstTurn){
					if(!proGame.positionOfWord.contains("7 7")){
						JOptionPane.showMessageDialog(proGame.board, "First word should place on the Star.");
					}else{
						 Vector<String> positionOfWord = (Vector<String>)proGame.positionOfWord.clone();
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
							JOptionPane.showMessageDialog(proGame.board, "Tiles are not in a line.");
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
										JOptionPane.showMessageDialog(proGame.board, "Word too long for begineer.");
									}
									
									int ret = proGame.checkWord(pos);
									if(ret == -1){
										JOptionPane.showMessageDialog(proGame.board, "Not a word");
									}else{
										if(proGame.turnNum == 1){
											proGame.scorePlayer1 = proGame.scorePlayer1 + ret;
										}else{
											proGame.scorePlayer2 = proGame.scorePlayer2 + ret;
										}
										proGame.score.setText("Player1: "+proGame.scorePlayer1+"     Player2: "+proGame.scorePlayer2);
										if(proGame.turnNum == 1){
											proGame.updataTilesForPlayer(1);
											proGame.turnNum = 2;
											
										}
										else{
											proGame.updataTilesForPlayer(2);
											proGame.turnNum = 1;
										}
										
										proGame.board.repaint();
										proGame.board.setVisible(true);
										
										this.setVisible(false);
										proGame.firstTurn = false;
									}	
									
								}
								//not connect
								else{
									JOptionPane.showMessageDialog(proGame.board, "Tiles is not connected in row.");
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
									if(proGame.type == proGame.GRADUATE){
									
										if(pos.length > 7){
										JOptionPane.showMessageDialog(proGame.board, "Word too long for begineer.");
										}
									}
									int ret = proGame.checkWord(pos);
									if(ret == -1){
										JOptionPane.showMessageDialog(proGame.board, "Not a word");
									}else{
										if(proGame.turnNum == 1){
											proGame.scorePlayer1 = proGame.scorePlayer1 + ret;
										}else{
											proGame.scorePlayer2 = proGame.scorePlayer2 + ret;
										}
										
										if(proGame.numberOfPlayer.equals("2")){
											proGame.score.setText("Player1: "+proGame.scorePlayer1+" Player2: "+proGame.scorePlayer2);
										}else if(proGame.numberOfPlayer.equals("3")){
											proGame.score.setText("Player1: "+proGame.scorePlayer1+" Player2: "+proGame.scorePlayer2+" Player3: "+proGame.scorePlayer3);
										}else if(proGame.numberOfPlayer.equals("4")){
											proGame.score.setText("Player1: "+proGame.scorePlayer1+" Player2: "+proGame.scorePlayer2+" Player3: "+proGame.scorePlayer3+" Player4: "+proGame.scorePlayer4);
										}
										
										if(proGame.turnNum == 1){
											proGame.updataTilesForPlayer(1);
											proGame.turnNum = 2;
											
										}
										else{
											proGame.updataTilesForPlayer(2);
											proGame.turnNum = 1;
										}
										
										proGame.board.repaint();
										proGame.board.setVisible(true);
										
										this.setVisible(false);
										proGame.firstTurn = false;
										
									}
									
								}
								//not connect
								else{
									JOptionPane.showMessageDialog(proGame.board, "Tiles is not connected in col.");
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
			if(proGame.turnNum == 1 && this.isSetted == false){
				proGame.choose = this;
				for(int i = 0; i <proGame.player1TilesButton.length;i++){
					if(proGame.player1TilesButton[i].isSetted() == false)
						proGame.player1TilesButton[i].setBackground(Menu.tileColor);
				}
				setBackground(Color.gray);
			}else if(proGame.turnNum == 2 && this.isSetted == false){
				proGame.choose = this;
				for(int i = 0; i <proGame.player2TilesButton.length;i++){
					if(proGame.player2TilesButton[i].isSetted() == false)
						proGame.player2TilesButton[i].setBackground(Menu.tileColor);
				}
				setBackground(Color.gray);
			}
		}
	}

}

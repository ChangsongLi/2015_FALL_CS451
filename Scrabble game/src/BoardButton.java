import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class BoardButton extends JButton implements ActionListener {

	public final static int START = 0, TRIPLEWORD = 1, TRIPLELETTER = 2, BLANK = -1, DOUBLEWORD = 3, DOUBLELETTER = 4;
	private int thisType;
	private int[] pos = new int[2];
	private boolean isChosen = false;
	
	public BoardButton(int type){
		super();
		thisType = type;
		if(type == START){
			setText("★");
		}else if(type == TRIPLEWORD){
			setText("TW");
		}else if(type == DOUBLEWORD){
			setText("DW");
		}else if(type == DOUBLELETTER){
			setText("DL");
		}else if(type == TRIPLELETTER){
			setText("TL");
		}
		
		addActionListener(this);
	}
	public void setPosition(int row, int col){
		pos[0] = row;
		pos[1] = col;
	}
	public int[] getPosition(){
		return pos;
	}
	
	
	public int getType(){
		return thisType;
	}
	public void setChosen(boolean choose){
		isChosen = choose;
	}
	
	public boolean isChosen(){
		return isChosen;
	}
	
	public void addPostionOfWord(int row, int col){
		BeginerGame.positionOfWord.add(row+" "+col);
		BeginerGame.positionOfWord.remove(row+" "+col);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
			if(isChosen){
				
				
				isChosen = false;
				boolean found = false;
				
				//change background when cancel the tile from board
				if(BeginerGame.turnNum == 1){
					for(int i = 0; i < BeginerGame.player1TilesButton.length && !found;i++){
						if(BeginerGame.player1TilesButton[i].getText().equals(getText())){
							if(BeginerGame.player1TilesButton[i].getBackground().equals(Color.gray)){
								found = true;
								BeginerGame.player1TilesButton[i].setBackground(Menu.tileColor);
								BeginerGame.player1TilesButton[i].setSetted(false);
							}
						}
					}
				}else{
					for(int i = 0; i < BeginerGame.player2TilesButton.length && !found;i++){
						if(BeginerGame.player2TilesButton[i].getText().equals(getText())){
							if(BeginerGame.player2TilesButton[i].getBackground().equals(Color.gray)){
								found = true;
								BeginerGame.player2TilesButton[i].setBackground(Menu.tileColor);
								BeginerGame.player2TilesButton[i].setSetted(false);
							}
						}
					}
				}
				
				if(thisType == START){
					setText("★");
				}else if(thisType == TRIPLEWORD){
					setText("TW");
				}else if(thisType == DOUBLEWORD){
					setText("DW");
				}else if(thisType == DOUBLELETTER){
					setText("DL");
				}else if(thisType == TRIPLELETTER){
					setText("TL");
				}else{
					setText("");
				}
				
				if( BeginerGame.choose!= null){
					isChosen = true;
					setText(BeginerGame.choose.getText());
					BeginerGame.choose.setSetted(true);
					BeginerGame.choose = null;
				}
				else{
					setBorder(new LineBorder(Color.black, 1));
					BeginerGame.positionOfWord.remove(pos[0]+" "+pos[1]);
				}
				
			}else{
				if(BeginerGame.choose != null){
					isChosen = true;
					BeginerGame.choose.setSetted(true);
					setText(BeginerGame.choose.getText());
					BeginerGame.choose = null;
					BeginerGame.positionOfWord.add(pos[0]+" "+pos[1]);
					setBorder(new LineBorder(Color.black, 6));
				}
			}

	}

}

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ProBoardButton extends JButton implements ActionListener {
	public final static int START = 0, TRIPLEWORD = 1, TRIPLELETTER = 2, BLANK = -1, DOUBLEWORD = 3, DOUBLELETTER = 4;
	private int type;
	private int[] pos = new int[2];
	private boolean isChosen = false;
	
	public ProBoardButton(int thisType){
		super();
		type = thisType;
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
		return type;
	}
	public void setChosen(boolean choose){
		isChosen = choose;
	}
	
	public boolean isChosen(){
		return isChosen;
	}
	
	public void addPostionOfWord(int row, int col){
		ProGame.positionOfWord.add(row+" "+col);
		ProGame.positionOfWord.remove(row+" "+col);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(isChosen){
			
			
			isChosen = false;
			boolean found = false;
			
			//change background when cancel the tile from board
			if(ProGame.turnNum == 1){
				for(int i = 0; i < ProGame.player1TilesButton.length && !found;i++){
					if(ProGame.player1TilesButton[i].getText().equals(getText())){
						if(ProGame.player1TilesButton[i].getBackground().equals(Color.gray)){
							found = true;
							ProGame.player1TilesButton[i].setBackground(Menu.tileColor);
							ProGame.player1TilesButton[i].setSetted(false);
						}
					}
				}
			}else{
				for(int i = 0; i < ProGame.player2TilesButton.length && !found;i++){
					if(ProGame.player2TilesButton[i].getText().equals(getText())){
						if(ProGame.player2TilesButton[i].getBackground().equals(Color.gray)){
							found = true;
							ProGame.player2TilesButton[i].setBackground(Menu.tileColor);
							ProGame.player2TilesButton[i].setSetted(false);
						}
					}
				}
			}
			
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
			}else{
				setText("");
			}
			
			if( ProGame.choose!= null){
				isChosen = true;
				setText(ProGame.choose.getText());
				ProGame.choose.setSetted(true);
				ProGame.choose = null;
			}
			else{
				setBorder(new LineBorder(Color.black, 1));
				ProGame.positionOfWord.remove(pos[0]+" "+pos[1]);
			}
			
		}else{
			if(ProGame.choose != null){
				isChosen = true;
				ProGame.choose.setSetted(true);
				setText(ProGame.choose.getText());
				ProGame.choose = null;
				ProGame.positionOfWord.add(pos[0]+" "+pos[1]);
				setBorder(new LineBorder(Color.black, 6));
			}
		
	}
	}
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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
		BeginerGame.positionOfWord.add(row+" "+col);
		BeginerGame.positionOfWord.remove(row+" "+col);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

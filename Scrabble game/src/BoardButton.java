import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BoardButton extends JButton implements ActionListener {

	public final static int START = 0, TRIPLEWORD = 1, TRIPLELETTER = 2, BLANK = -1, DOUBLEWORD = 3, DOUBLELETTER = 4;
	private int thisType;
	
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
	}
	
	public int getType(){
		return thisType;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

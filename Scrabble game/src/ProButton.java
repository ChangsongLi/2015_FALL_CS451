import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ProButton extends JButton implements ActionListener{

	private boolean isSetted = false;
	ProGame beginerGame;
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
		beginerGame = bg;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

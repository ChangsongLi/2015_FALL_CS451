import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MyButton extends JButton implements ActionListener{

	public MyButton(String name){
		super(name);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(getText().equals("Beginners")){
			System.out.println("Beginners");
		}else if(getText().equals("Graduate")){
			
		}else if(getText().equals("Challenger")){
			
		}
		
	}
	
	
}

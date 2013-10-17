import javax.swing.JFrame;
import javax.swing.JPanel;


public class ButtonWindow extends JFrame {

	String pressed_button = "0";
	
	public ButtonWindow(String pressed) {
		pressed_button = pressed;
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		panel.setLayout(null);
		
		setTitle("Button " + pressed_button + " window");
	    setSize(300, 200);
	    setLocationRelativeTo(null);
	}
}

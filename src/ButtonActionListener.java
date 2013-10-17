import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;


public class ButtonActionListener implements ActionListener {

	String button_string = "";
	@Override
	public void actionPerformed(ActionEvent arg0) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ButtonWindow window = new ButtonWindow(button_string);
                window.setVisible(true);
            }
        });
	}
	
	public ButtonActionListener(String button) {
		button_string = button;
	}

}

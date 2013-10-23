import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.leapmotion.leap.Controller;

class Sample extends JFrame {
	
	JButton b1;
	JButton b2;
	JButton b3;
	JLabel label;
	long time_selected = 0;
	long last_time = 0;
	int n_fingers_last_frame = 0;
	JButton selected;
	public Controller controller = new Controller();
	public SampleListener listener;
	
	public Sample() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		panel.setLayout(new FlowLayout());
		
		b1 = new JButton("1");
	    b1.setBounds(10, 10, 50, 30);
	    
	    b2 = new JButton("2");
	    b2.setBounds(10, 50, 50, 30);
	    
	    b3 = new JButton("3");
	    b3.setBounds(10, 90, 50, 30);

	    b1.addActionListener(new ButtonActionListener("1"));
	    b2.addActionListener(new ButtonActionListener("2"));
	    b3.addActionListener(new ButtonActionListener("3"));
	    
	    label = new JLabel("0");
	    
	    panel.add(b1);
	    panel.add(b2);
	    panel.add(b3);
	    panel.add(label);
	    
		setTitle("Simple example");
	    setSize(600, 400);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);      
	    
	   listener = new SampleListener(this);
	   controller.addListener(listener);
	}
	
	
	public void updateFingerCount(int nfingers, long time) {
		if(!this.isActive())
			return;
		
		else {
			long time_dif = time - last_time;
			last_time = time;
			System.out.println(time_dif);
			if(nfingers == n_fingers_last_frame && nfingers != 0) {
				time_selected += time_dif;
				label.setText(String.valueOf(time_selected));
				if(time_selected >= 1000000) {
					selected.doClick();
					label.setText("0");
					time_selected = 0;
				}
			}
			else {
				time_selected = 0;
				if(nfingers == 1) {
					n_fingers_last_frame = 1;
					selected = b1;
					b1.requestFocus();
				}
				else if(nfingers == 2) {
					n_fingers_last_frame = 2;
					selected = b2;
					b2.requestFocus();
				}
				else if(nfingers == 3) {
					n_fingers_last_frame = 3;
					selected = b3;
					b3.requestFocus();
				}
				else {
					n_fingers_last_frame = 0;
				}
			}
		}
	}
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Sample ex = new Sample();
                ex.setVisible(true);
            }
        });
        
        while(true);
    }
}
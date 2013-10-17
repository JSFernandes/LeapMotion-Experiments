import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;

class Sample extends JFrame {
	
	static JButton b1;
	static JButton b2;
	static JButton b3;
	static JLabel label;
	static JFrame j_frame;
	
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
	}
	
    public static void main(String[] args) {
        // Create a sample listener and controller
        SampleListener listener = new SampleListener();
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Sample ex = new Sample();
                j_frame = ex;
                ex.setVisible(true);
            }
        });
        
        int last_fingercount = 0;
        short repeated_fingercount_frames = 0;
        int n_fingers;
        
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        while(true) {
        	if(j_frame.isActive()) {
	        	Frame frame = controller.frame();
	        	n_fingers = frame.fingers().count();
	        	label.setText(String.valueOf(repeated_fingercount_frames));
	        	if(n_fingers == last_fingercount && last_fingercount != 0) {
	        		++repeated_fingercount_frames;
	        		if(repeated_fingercount_frames >= 10) {
	        			repeated_fingercount_frames = 0;
	        			if(n_fingers == 1)
	        				b1.doClick();
	        			else if(n_fingers == 2)
	        				b2.doClick();
	        			else if(n_fingers == 3)
	        				b3.doClick();
	        		}
	        	}
	        	else {
	        		repeated_fingercount_frames = 0;
		        	if(n_fingers == 1) {
		        		b1.requestFocus();
		        		last_fingercount = 1;
		        	}
		        	else if(n_fingers == 2) {
		        		b2.requestFocus();
		        		last_fingercount = 2;
		        	}
		        	else if(n_fingers == 3) {
		        		b3.requestFocus();
		        		last_fingercount = 3;
		        	}
	        	}
        	}
	        	//System.out.println("Frame id: " + frame.id()
	        	//                 + ", timestamp: " + frame.timestamp()
	        	//                 + ", hands: " + frame.hands().count()
	        	//                 + ", fingers: " + frame.fingers().count()
	        	//                 + ", tools: " + frame.tools().count());
	        try {
	        	Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        /*
        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);*/
    }
}
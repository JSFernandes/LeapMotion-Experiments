import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;

class Sample extends JFrame {
	
	static JButton b1;
	static JButton b2;
	static JButton b3;
	
	public Sample() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		panel.setLayout(null);
		
		b1 = new JButton("1");
	    b1.setBounds(10, 10, 50, 30);
	    
	    b2 = new JButton("2");
	    b2.setBounds(10, 50, 50, 30);
	    
	    b3 = new JButton("3");
	    b3.setBounds(10, 90, 50, 30);

	    panel.add(b1);
	    panel.add(b2);
	    panel.add(b3);
	    
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
                ex.setVisible(true);
            }
        });
        
        while(true) {
        	Frame frame = controller.frame();
        	if(frame.fingers().count() == 1)
        		b1.requestFocus();
        	else if(frame.fingers().count() == 2)
        		b2.requestFocus();
        	else if(frame.fingers().count() == 3)
        		b3.requestFocus();
        	//System.out.println("Frame id: " + frame.id()
        	//                 + ", timestamp: " + frame.timestamp()
        	//                 + ", hands: " + frame.hands().count()
        	//                 + ", fingers: " + frame.fingers().count()
        	//                 + ", tools: " + frame.tools().count());
        	try {
				Thread.sleep(100);
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
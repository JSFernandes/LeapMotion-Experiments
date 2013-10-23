import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;


public class SampleListener extends Listener {
	Sample ui;
	boolean working = false;
	
	public void onFrame(Controller controller) {
		ui.updateFingerCount(controller.frame().fingers().count(), controller.frame().timestamp());
	}
	
	public SampleListener(Sample s) {
		ui = s;
	}
}

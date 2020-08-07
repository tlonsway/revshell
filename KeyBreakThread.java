import java.awt.Robot;
import java.awt.event.KeyEvent;
public class KeyBreakThread implements Runnable{
    boolean enabled;
    final int[] keyIDs = new int[]{KeyEvent.VK_A,KeyEvent.VK_B,KeyEvent.VK_C,KeyEvent.VK_D,KeyEvent.VK_E,KeyEvent.VK_F,KeyEvent.VK_G,KeyEvent.VK_H,KeyEvent.VK_I,KeyEvent.VK_J,KeyEvent.VK_K,KeyEvent.VK_L,KeyEvent.VK_M,KeyEvent.VK_N,KeyEvent.VK_O,KeyEvent.VK_P};
    public KeyBreakThread() {
        enabled = false;
    }
    public void run() {
        try {
            Robot robot = new Robot();
            while(true) {
                if (enabled) {
                    int randkey = keyIDs[(int)(Math.random()*((double)keyIDs.length-1))];
                    robot.keyPress(randkey);
                    robot.delay(5);
                    robot.keyRelease(randkey);
                }
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            return;
        }
    }
    public void enable() {
        enabled=true;
    }
    public void disable() {
        enabled=false;
    }
}
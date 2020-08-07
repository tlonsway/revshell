import java.awt.Robot;
public class MouseBreakThread implements Runnable {
    boolean enabled;
    public MouseBreakThread() {
        enabled = false;
    }
    public void run() {
        try {
            Robot robot = new Robot();
            while(true) {
                if (enabled) {
                    int x = (int)(Math.random()*1920);
                    int y = (int)(Math.random()*1080);
                    robot.mouseMove(x,y);
                }
                try {
                    Thread.sleep(1000);
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
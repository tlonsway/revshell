import cs1.*;
public class exec {
    public static void main(String[] args) {
        System.out.print("Enter command:");
        
        
        try {
            Runtime.getRuntime().exec("cmd /c echo hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
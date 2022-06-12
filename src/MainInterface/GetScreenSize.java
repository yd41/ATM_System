package MainInterface;

import java.awt.*;

public class GetScreenSize {
    public static int getScreeWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }  public static int getScreeHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}

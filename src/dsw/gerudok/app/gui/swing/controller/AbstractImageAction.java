package dsw.gerudok.app.gui.swing.controller;

import javax.swing.*;
import java.net.URL;

public abstract class AbstractImageAction extends AbstractAction {

    public Icon loadIcon(String filePath){

        URL iconURl = getClass().getResource(filePath);
        Icon icon = null;
        if(iconURl != null) {
            icon = new ImageIcon(iconURl);
        }else{

            System.out.println("Icon not found!");
        }
        return icon;
    }
}

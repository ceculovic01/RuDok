package dsw.gerudok.app.gui.swing.view.editView.controller;

import dsw.gerudok.app.gui.swing.view.editView.view.EditDialog;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.net.URL;

public class BoldButton extends StyledEditorKit.BoldAction {

    private EditDialog editDialog;

    public BoldButton(EditDialog editDialog){
        this.editDialog = editDialog;
        putValue(SHORT_DESCRIPTION, "Bold Text");
        putValue(SMALL_ICON, loadIcon("images/boldButton.png"));
        putValue(NAME, "");

    }

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

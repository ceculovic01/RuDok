package dsw.gerudok.app.gui.swing.view.editView.controller;

import dsw.gerudok.app.gui.swing.view.editView.view.EditDialog;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.net.URL;

public class UnderlineButton extends StyledEditorKit.UnderlineAction {

    private EditDialog editDialog;

    public UnderlineButton(EditDialog editDialog){
        this.editDialog = editDialog;
        putValue(SHORT_DESCRIPTION, "Underline Text");
        putValue(SMALL_ICON, loadIcon("images/underlineButton.png"));
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

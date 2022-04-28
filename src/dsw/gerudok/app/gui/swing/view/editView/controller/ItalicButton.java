package dsw.gerudok.app.gui.swing.view.editView.controller;

import dsw.gerudok.app.gui.swing.view.editView.view.EditDialog;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.net.URL;

public class ItalicButton extends StyledEditorKit.ItalicAction {

    private EditDialog editDialog;

    public ItalicButton(EditDialog editDialog){
        this.editDialog = editDialog;
        putValue(SHORT_DESCRIPTION, "Italic Text");
        putValue(SMALL_ICON, loadIcon("images/italicButton.png"));
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

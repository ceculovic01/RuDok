package dsw.gerudok.app.gui.swing.view.editView.controller;

import dsw.gerudok.app.gui.swing.view.editView.view.EditDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class ImageButton extends AbstractAction {

    private EditDialog editDialog;

    public ImageButton(EditDialog editDialog){
        this.editDialog = editDialog;
        putValue(SHORT_DESCRIPTION, "ImageButton");
        putValue(SMALL_ICON, loadIcon("images/imageButton.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
            editDialog.initialiseImageType();
            editDialog.getSlot().setFileType(1);
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

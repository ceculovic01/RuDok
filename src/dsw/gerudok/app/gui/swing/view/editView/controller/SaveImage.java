package dsw.gerudok.app.gui.swing.view.editView.controller;

import dsw.gerudok.app.gui.swing.view.editView.view.EditDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class SaveImage extends AbstractAction {

    private EditDialog editDialog;

    public SaveImage(EditDialog editDialog){
        this.editDialog = editDialog;
        putValue(SHORT_DESCRIPTION, "Save Image");
        putValue(SMALL_ICON, loadIcon("images/saveButton.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(editDialog.getImagePath() == null)
            return;
        if(editDialog.getImagePath().isEmpty()){
            return;
        }
            editDialog.getSlot().setSlotFilePath(editDialog.getImagePath());
            JOptionPane.showConfirmDialog(editDialog, "Uspešno sačuvana slika!", "Obaveštenje", JOptionPane.CLOSED_OPTION);

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

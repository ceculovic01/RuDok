package dsw.gerudok.app.gui.swing.view.editView.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.editView.view.EditDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;

public class SaveText extends AbstractAction {

    private EditDialog editDialog;

    public SaveText(EditDialog editDialog){
        this.editDialog = editDialog;
        putValue(SHORT_DESCRIPTION, "Save Text");
        putValue(SMALL_ICON, loadIcon("images/saveButton.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(editDialog.getEditorPane() != null && editDialog.getEditorPane().getText() != null){
            String newFileName = editDialog.getSlot().getName();
            newFileName = newFileName.replaceAll(" ", "");
            newFileName += ".txt";
            String projectName = editDialog.getProject().getName();
            projectName = projectName.replaceAll(" ", "");
            newFileName = projectName + newFileName;
            File file;
            if(!editDialog.getSlot().getSlotFilePath().isEmpty()){
                file = new File(editDialog.getSlot().getSlotFilePath());
            }else {
                 file = new File("slots/" + newFileName);
            }
            AppCore.getInstance().getiSerializer().saveFile(file, editDialog.getEditorPane().getText());
            editDialog.getSlot().setSlotFilePath(file.getAbsolutePath());
            JOptionPane.showConfirmDialog(editDialog, "Uspešno sačuvan tekst!", "Obaveštenje", JOptionPane.CLOSED_OPTION);
        }

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

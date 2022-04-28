package dsw.gerudok.app.gui.swing.view.editView.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.editView.view.EditDialog;
import dsw.gerudok.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class UploadImage extends AbstractAction {

    private EditDialog editDialog;

    public UploadImage(EditDialog editDialog){
        this.editDialog = editDialog;
        putValue(SHORT_DESCRIPTION, "UploadImage");
        putValue(SMALL_ICON, loadIcon("images/uploadButton.png"));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        FileFilter filterPng = new FileNameExtensionFilter("Image File PNG","png");
        FileFilter filterJpg = new FileNameExtensionFilter("Image File JPG", "jpg");
        jfc.addChoosableFileFilter(filterPng);
        jfc.addChoosableFileFilter(filterJpg);
        jfc.setFileSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION) {
            String name = jfc.getSelectedFile().getName();
            if(!name.contains(".jpg") && !name.contains(".png")){
                AppCore.getInstance().getiError().handleError("BadOpenedFile");
                return;
            }
            editDialog.setImagePath(jfc.getSelectedFile().getAbsolutePath());
            ImageIcon imageIcon = new ImageIcon(editDialog.getImagePath());
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(400, 150,  java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            editDialog.getImageLbl().setIcon(imageIcon);

        }else{
            return;
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

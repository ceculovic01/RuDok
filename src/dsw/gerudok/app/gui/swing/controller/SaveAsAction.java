package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class SaveAsAction extends AbstractImageAction{

    public SaveAsAction(){
        putValue(SMALL_ICON,loadIcon("images/saveAsAction.png"));
        putValue(SHORT_DESCRIPTION, "Save as");
        putValue(NAME,"Save as");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.SHIFT_MASK | ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("RuDok Project File","rpf");
        jfc.setFileFilter(filter);

        RuNode ruNode = MainFrame.getInstance().getTree().getSelectedRuNode();
        if(!(ruNode instanceof Project)){
            AppCore.getInstance().getiError().handleError("SaveProjectNotSelected");
            return;
        }
        Project project = (Project) ruNode;
        File projectFile = null;

        if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                projectFile=jfc.getSelectedFile();
                String newName = projectFile.getName() + ".rpf";
                projectFile = new File(projectFile.getParent(), newName);

        }else{
                return;
        }

        project.setProjectFilePath(projectFile.getPath());
        AppCore.getInstance().getiSerializer().saveProject(projectFile, project);
        project.setChanged(false);
        JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Uspešno sačuvan projekat!", "Obaveštenje!", JOptionPane.CLOSED_OPTION);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
    }
}

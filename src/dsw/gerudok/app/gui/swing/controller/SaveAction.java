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

public class SaveAction extends AbstractImageAction {

    public SaveAction(){
        putValue(SMALL_ICON,loadIcon("images/saveAction.png"));
        putValue(SHORT_DESCRIPTION, "Save");
        putValue(NAME,"Save");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
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

        if (!project.isChanged()){
            return;
        }

        if (project.getProjectFilePath().isEmpty()){
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                projectFile=jfc.getSelectedFile();
                String newName = projectFile.getName() + ".rpf";
                projectFile = new File(projectFile.getParent(),newName);

            }else{
                return;
            }

        }else {
            projectFile = new File(project.getProjectFilePath());
        }

        project.setProjectFilePath(projectFile.getPath());
        AppCore.getInstance().getiSerializer().saveProject(projectFile,project);
        project.setChanged(false);
        JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Uspešno sačuvan projekat!", "Obaveštenje!", JOptionPane.CLOSED_OPTION);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
    }
}

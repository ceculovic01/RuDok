package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.Workspace;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class WindowController extends WindowAdapter {

    public WindowController() {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Workspace workspace = (Workspace) MainFrame.getInstance().getTree().getRuTreeRoot();

        boolean skip = false;
        if(workspace.getChildren() == null){
            skip = true;
        }
        if(!skip) {
            skip = true;
            for (RuNode ruNode : workspace.getChildren()) {
                Project project = (Project) ruNode;
                if (!project.getProjectFilePath().isEmpty()) {
                    skip = false;
                }
            }
        }
            boolean saved = false;
        if(!skip) {
            if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li želite da sačuvate radni prostor RuDok-a?", "Čuvanje radnog prostora",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                if (!workspace.getWorkspaceFilePath().isEmpty()) {
                    String projectsPaths = "";
                    for (RuNode ruNode : workspace.getChildren()) {
                        Project project = (Project) ruNode;
                        if (!project.getProjectFilePath().isEmpty()) {
                            projectsPaths += project.getProjectFilePath();
                            projectsPaths += '\n';
                        }
                    }
                    AppCore.getInstance().getiSerializer().saveFile(new File(workspace.getWorkspaceFilePath()), projectsPaths);
                    saved = true;

                } else {


                    JFileChooser jfc = new JFileChooser();
                    FileFilter filter = new FileNameExtensionFilter("Text File", ".txt");
                    jfc.setFileFilter(filter);
                    File file = null;
                    if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                        File folder = jfc.getSelectedFile();
                        folder.mkdir();
                        String newName = jfc.getSelectedFile().getName() + ".txt";
                        file = new File(folder, newName);
                        String projectsPaths = "";
                        for (RuNode ruNode : workspace.getChildren()) {
                            Project project = (Project) ruNode;
                            if (!project.getProjectFilePath().isEmpty()) {
                                projectsPaths += project.getProjectFilePath();
                                projectsPaths += '\n';
                            }
                        }
                        AppCore.getInstance().getiSerializer().saveFile(file, projectsPaths);
                        workspace.setWorkspaceFilePath(file.getAbsolutePath());
                        saved = true;

                    }
                }

            }
        }
        if (saved) {
            List<Project> projectList = MainFrame.getInstance().getTree().getSavedProjects();
            File activeWorkspaceFile = new File("activeWorkspace.txt");
            AppCore.getInstance().getiSerializer().saveFile(activeWorkspaceFile, workspace.getWorkspaceFilePath());
            File file = new File(workspace.getWorkspaceFilePath());
            for(Project project: projectList){
                String newName = project.getName();
                newName = newName.replace(" ", "");
                File projectFolder = new File(file.getParent(), newName);
                boolean isCreated = projectFolder.mkdir();
                List<Slot> slotList = project.getProjectSlots();
                if(!slotList.isEmpty()){
                    for(Slot slot: slotList){

                        if(slot.getFileType() == 0){
                            if(!slot.getSlotFilePath().isEmpty()) {

                                    String slotName = slot.getName();
                                    slotName = slotName.replaceAll(" ", "");
                                    slotName += ".txt";
                                    File newSlotFile = new File(projectFolder.getAbsolutePath(), slotName);
                                    Path src = Paths.get(slot.getSlotFilePath());
                                    Path dest = Paths.get(newSlotFile.getAbsolutePath());
                                    AppCore.getInstance().getiSerializer().copyFile(src,dest);
                                    slot.setSlotFilePath(newSlotFile.getAbsolutePath());

                            }
                        }else if(slot.getFileType() == 1){
                                if(!slot.getSlotFilePath().isEmpty()){

                                        String slotName = slot.getName();
                                        slotName = slotName.replaceAll(" ", "");
                                        File file2 = new File(slot.getSlotFilePath());
                                        if(file2.getName().contains(".jpg")){
                                            slotName += ".jpg";
                                        }else {
                                            slotName += ".png";
                                        }
                                        File newSlotFile = new File(projectFolder.getAbsolutePath(), slotName);
                                        Path src = Paths.get(slot.getSlotFilePath());
                                        Path dest = Paths.get(newSlotFile.getAbsolutePath());
                                        AppCore.getInstance().getiSerializer().copyFile(src,dest);
                                        slot.setSlotFilePath(newSlotFile.getAbsolutePath());

                                }
                        }
                    }
                }
                AppCore.getInstance().getiSerializer().saveProject(new File(project.getProjectFilePath()),project);
            }
            JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Uspešno sačuvan radni prostor!", "Obaveštenje!", JOptionPane.CLOSED_OPTION);
        }
        if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni da želite da zatvorite RuDok?", "Zatvaranje RuDok-a",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                AppCore.getInstance().getiSerializer().removeAllFilesFromDirectory(new File("slots"));
                System.exit(0);
        }else {
            return;
        }
    }
}

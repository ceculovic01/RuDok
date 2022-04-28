package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.Workspace;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

public class SwitchAction extends AbstractImageAction{

    public SwitchAction(){
        putValue(SMALL_ICON, loadIcon("images/switchAction.png"));
        putValue(SHORT_DESCRIPTION, "Switch Workspace");
        putValue(NAME, "Switch Workspace");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Text File","txt");
        jfc.setFileFilter(filter);
        jfc.setFileSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION) {

            String name = jfc.getSelectedFile().getName();
            if (!name.contains(".txt")) {
                AppCore.getInstance().getiError().handleError("BadOpenedFile");
                return;
            }

                List<Project> projectList = AppCore.getInstance().getiSerializer().openWorkspace(jfc.getSelectedFile());
                Workspace workspace = MainFrame.getInstance().getDocumentRepository().getWorkspace();
                if(workspace.getChildren().size() > 0){
                    System.out.println("Before:" +workspace.getChildren().size());
                   for(int i = workspace.getChildren().size()-1; i>= 0; i--){
                       MainFrame.getInstance().getTree().removeRuNode(workspace.getChildren().get(i));
                   }
                    System.out.println("After:" + workspace.getChildren().size());
                }

                if(!projectList.isEmpty())
                for(Project p: projectList) {
                    MainFrame.getInstance().getTree().addOpenedProject(p);
                    MainFrame.getInstance().getMainPanel().addProjectView(p);
                    p.setChanged(false);
                }

                workspace.setWorkspaceFilePath(jfc.getSelectedFile().getPath());

        }
    }
}

package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.Workspace;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class OpenAction extends AbstractImageAction{

    public OpenAction(){
        putValue(SMALL_ICON,loadIcon("images/openAction.png"));
        putValue(SHORT_DESCRIPTION, "Open");
        putValue(NAME,"Open");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("RuDok Project File","rpf");
        jfc.setFileFilter(filter);
        jfc.setFileSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){

            String name = jfc.getSelectedFile().getName();
            if(!name.contains(".rpf")){
                AppCore.getInstance().getiError().handleError("BadOpenedFile");
                return;
            }
                Project p = AppCore.getInstance().getiSerializer().openProject(jfc.getSelectedFile());
                if(p == null){
                    return;
                }
                Workspace workspace = (Workspace) MainFrame.getInstance().getTree().getRuTreeRoot();
                if(!workspace.getChildren().isEmpty()){
                    for(RuNode ruNode: workspace.getChildren()){
                        if(ruNode.getName().equals(p.getName())){
                            AppCore.getInstance().getiError().handleError("SameNameProject");
                            return;
                        }
                    }
                }

                MainFrame.getInstance().getTree().addOpenedProject(p);
                MainFrame.getInstance().getMainPanel().addProjectView(p);
                p.setChanged(false);

        }
    }
}

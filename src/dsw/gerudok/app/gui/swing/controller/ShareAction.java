package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.repositoryView.model.ShareChecker;
import dsw.gerudok.app.gui.swing.view.ShareDialogView;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ShareAction extends AbstractImageAction {

    public ShareAction() {
        putValue(SMALL_ICON, loadIcon("images/share.png"));
        putValue(SHORT_DESCRIPTION, "Share document");
        putValue(NAME, "Share");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuNode ruNode = null;
        try{
            ruNode = MainFrame.getInstance().getTree().getSelectedRuNode();

        }catch (NullPointerException e1){
            AppCore.getInstance().getiError().handleError("DocumentNotSelected");
            return;
        }
        List<String> projectList = new ArrayList<>();
        if(ruNode instanceof Document){
            String projectName = ruNode.getParent().getName();
            for(RuNode rn: MainFrame.getInstance().getTree().getWorkspaceChildren()){
                if(!rn.getName().equals(projectName)){
                    if(!MainFrame.getInstance().getMainPanel().getShareCheckers().isEmpty()) {
                        boolean postoji = false;
                        for (ShareChecker shareChecker : MainFrame.getInstance().getMainPanel().getShareCheckers()) {
                            if (projectName.equals(shareChecker.getProject1().getName()) && ruNode.getName().equals(shareChecker.getDocument().getName()) && rn.getName().equals(shareChecker.getProject2().getName())) {
                                postoji = true;
                            }
                        }
                        if(!postoji)
                            projectList.add(rn.getName());
                    }else{
                        projectList.add(rn.getName());
                    }

                }
            }
            if(projectList.isEmpty()){
                AppCore.getInstance().getiError().handleError("CantBeShared");
                return;
            }
            ShareDialogView shareDialogView = new ShareDialogView(projectList);
            int n = shareDialogView.showDialog();
            if(n != 1){
                return;
            }
            String selected = (String) shareDialogView.getIzborProjektaCb().getSelectedItem();
            Project selectedProject = null;
            for(RuNode rn: MainFrame.getInstance().getTree().getWorkspaceChildren()){
                if(rn.getName().equals(selected)){
                    selectedProject = (Project) rn;
                }
            }
            Project project1 = (Project) ruNode.getParent();
            Document document = (Document) ruNode;
            ShareChecker shareChecker = new ShareChecker(project1,document,selectedProject);
            MainFrame.getInstance().getMainPanel().getShareCheckers().add(shareChecker);
            MainFrame.getInstance().getTree().addSharedDocument(selectedProject, document, project1);

        }else{
            AppCore.getInstance().getiError().handleError("DocumentNotSelected");
        }


    }
}

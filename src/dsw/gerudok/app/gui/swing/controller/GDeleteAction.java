package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.DocumentView;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GDeleteAction extends AbstractImageAction {

    public GDeleteAction(){
        putValue(SMALL_ICON,loadIcon("images/deleteSlot.png"));
        putValue(SHORT_DESCRIPTION, "Delete object");
        putValue(NAME,"Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = MainFrame.getInstance().getMainPanel().getActiveProjectView().getTabbedPane().getSelectedIndex();
        DocumentView documentView = (DocumentView) MainFrame.getInstance().getMainPanel().getActiveProjectView().getDocumentViewList().get(index);
        if(documentView != null){
            for(PageView pageView: documentView.getPageViewList()){
                pageView.startDeleteState();
            }
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
        }else {
            return;
        }
    }
}

package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.DocumentView;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;

import java.awt.event.ActionEvent;

public class GEditAction extends AbstractImageAction{

    public GEditAction(){
        putValue(SMALL_ICON,loadIcon("images/GEditAction.png"));
        putValue(SHORT_DESCRIPTION, "Edit object");
        putValue(NAME,"Edit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = MainFrame.getInstance().getMainPanel().getActiveProjectView().getTabbedPane().getSelectedIndex();
        DocumentView documentView = (DocumentView) MainFrame.getInstance().getMainPanel().getActiveProjectView().getDocumentViewList().get(index);
        if(documentView != null){
            for(PageView pageView: documentView.getPageViewList()){
                pageView.startEditState();
            }
        }else {
            return;
        }
    }
}
package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.DocumentView;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;

import java.awt.event.ActionEvent;

public class GRectangleAction extends AbstractImageAction {

    public GRectangleAction(){
        putValue(SMALL_ICON,loadIcon("images/RectangleImage.png"));
        putValue(SHORT_DESCRIPTION, "Rectangle");
        putValue(NAME,"Rectangle");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = MainFrame.getInstance().getMainPanel().getActiveProjectView().getTabbedPane().getSelectedIndex();
        DocumentView documentView = (DocumentView) MainFrame.getInstance().getMainPanel().getActiveProjectView().getDocumentViewList().get(index);
        if(documentView != null){
            for(PageView pageView: documentView.getPageViewList()){
                pageView.startRectangleState();
            }
        }else {
            return;
        }
    }
}

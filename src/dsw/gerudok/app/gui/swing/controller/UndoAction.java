package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.DocumentView;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractImageAction {

    public UndoAction(){
        putValue(SMALL_ICON, loadIcon("images/undoAction.png"));
        putValue(SHORT_DESCRIPTION, "Undo command");
        putValue(NAME, "Undo");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = MainFrame.getInstance().getMainPanel().getActiveProjectView().getTabbedPane().getSelectedIndex();
        DocumentView documentView = (DocumentView) MainFrame.getInstance().getMainPanel().getActiveProjectView().getDocumentViewList().get(index);
        if(documentView != null){
            for(PageView pageView: documentView.getPageViewList()){
                pageView.getPage().getSelection().removeAllFromList();
            }
            documentView.getCommandManager().undoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
        }else {
            return;
        }
    }
}

package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.DocumentView;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractImageAction{

    public RedoAction(){
        putValue(SMALL_ICON, loadIcon("images/redoAction.png"));
        putValue(SHORT_DESCRIPTION, "Redo command");
        putValue(NAME, "Redo");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                ActionEvent.SHIFT_MASK | ActionEvent.CTRL_MASK));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = MainFrame.getInstance().getMainPanel().getActiveProjectView().getTabbedPane().getSelectedIndex();
        DocumentView documentView = (DocumentView) MainFrame.getInstance().getMainPanel().getActiveProjectView().getDocumentViewList().get(index);
        if(documentView != null){
            for(PageView pageView: documentView.getPageViewList()){
                pageView.getPage().getSelection().removeAllFromList();
            }
            documentView.getCommandManager().doCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
        }else {
            return;
        }
    }
}

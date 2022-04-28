package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.Workspace;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RemoveNodeAction extends AbstractImageAction{

    public RemoveNodeAction() {
        putValue(SMALL_ICON, loadIcon("images/delete.png"));
        putValue(SHORT_DESCRIPTION, "Delete");
        putValue(NAME, "Delete");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuNode selectedNode = null;
        try{
            selectedNode = MainFrame.getInstance().getTree().getSelectedRuNode();
        }catch (Exception e1) {

            AppCore.getInstance().getiError().handleError("removeSelectedNodeNull");
            return;
        }

        if(selectedNode instanceof Workspace){
            AppCore.getInstance().getiError().handleError("removeWorkspace");
        }else if(selectedNode instanceof Slot){
            AppCore.getInstance().getiError().handleError("removeSlot");
        }else {
            MainFrame.getInstance().getTree().removeRuNode(selectedNode);

        }



    }
}

package dsw.gerudok.app.gui.swing.tree.controller;

import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class RuTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        RuTreeItem treeItemSelected = (RuTreeItem) path.getLastPathComponent();
        if(treeItemSelected == null){
            return;
        }
        System.out.println("Selektovan cvor:"+ treeItemSelected.getName());
        System.out.println("getPath: "+e.getPath());

    }

}

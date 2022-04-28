package dsw.gerudok.app.gui.swing.tree.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.RuView;
import dsw.gerudok.app.gui.swing.view.RuViewImplementation;
import dsw.gerudok.app.repository.*;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class RuTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn =null;
    private JTextField edit=null;
    private RuView ruView;
    public RuTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
        ruView = new RuViewImplementation();
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount() == 3) {
                return true;
            } else if(((MouseEvent)arg0).getClickCount() == 2){
                add();
                return false;

            }
        return false;
    }

    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof RuTreeItem))
            return;

        RuTreeItem clicked = (RuTreeItem) clickedOn;
        RuTreeItem parent = (RuTreeItem) clicked.getParent();

        if(e.getActionCommand().equals("")){
            AppCore.getInstance().getiError().handleError("emptyName");
            return;
        }
        if (clicked.getNodeModel() instanceof Workspace){
            clicked.setName(e.getActionCommand());
        }
        else if(clicked.getNodeModel() instanceof Project){
            for(RuNode rn: ((Workspace) parent.getNodeModel()).getChildren()){
                if(rn.getName().equals(e.getActionCommand())){
                    AppCore.getInstance().getiError().handleError("sameNameNode");
                    return;
                }
            }
            String name = clicked.getNodeModel().getName();
            clicked.setName(e.getActionCommand());

        }else if(clicked.getNodeModel() instanceof Document){
            for(RuNode rn: ((Project) parent.getNodeModel()).getChildren()){
                if(rn.getName().equals(e.getActionCommand())){
                    AppCore.getInstance().getiError().handleError("sameNameNode");
                    return;
                }
            }
            if(e.getActionCommand().contains("*")){
                AppCore.getInstance().getiError().handleError("*InName");
                return;
            }
            clicked.setName(e.getActionCommand());
        }else if(clicked.getNodeModel() instanceof Page){
            for(RuNode rn: ((Document) parent.getNodeModel()).getChildren()){
                if(rn.getName().equals(e.getActionCommand())){
                    AppCore.getInstance().getiError().handleError("sameNameNode");
                    return;
                }
            }
            clicked.setName(e.getActionCommand());
        }else if(clicked.getNodeModel() instanceof Slot){
            for(RuNode rn: ((Page) parent.getNodeModel()).getChildren()){
                if(rn.getName().equals(e.getActionCommand())){
                    AppCore.getInstance().getiError().handleError("sameNameNode");
                    return;
                }
            }
            clicked.setName(e.getActionCommand());
        }
    }

    public void add(){
        RuNode selectedItem = ruView.getMyMainFrame().getTree().getSelectedRuNode();
        if(selectedItem == null)
            return;
        if(selectedItem instanceof Project) {
            ruView.getMyMainFrame().getMainPanel().changePanel((Project) selectedItem);
        }
    }

}

package dsw.gerudok.app.gui.swing.view;

import javax.swing.*;

public class MainToolBar extends JToolBar {

    public MainToolBar(){
        super(HORIZONTAL);
        this.setFloatable(false);
        addElements();
    }

    private  void addElements(){
        this.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        this.add(MainFrame.getInstance().getActionManager().getRemoveNodeAction());
        this.add(MainFrame.getInstance().getActionManager().getShareAction());
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getSwitchAction());
        this.add(MainFrame.getInstance().getActionManager().getOpenAction());
        this.add(MainFrame.getInstance().getActionManager().getSaveAction());
        this.add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        this.addSeparator();
        this.add(MainFrame.getInstance().getActionManager().getUndoAction());
        this.add(MainFrame.getInstance().getActionManager().getRedoAction());
    }
}

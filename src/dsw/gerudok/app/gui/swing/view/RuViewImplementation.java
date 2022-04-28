package dsw.gerudok.app.gui.swing.view;

import javax.swing.*;

public class RuViewImplementation implements RuView {

    public RuViewImplementation() {
    }

    @Override
    public MainFrame getMyMainFrame() {
        return MainFrame.getInstance();
    }

    @Override
    public void updateTree() {
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
    }
}

package dsw.gerudok.app.gui.swing.view;

import javax.swing.*;

public class MainMenuBar extends JMenuBar {

    private JMenu file;
    private JMenu edit;
    private JMenu help;
    private JMenuItem aboutItem;


    public MainMenuBar() {

        this.initialise();
    }

    public void initialise(){
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        aboutItem = new JMenuItem("About");

        this.add(file);
        this.add(edit);
        this.add(help);

        help.add(MainFrame.getInstance().getActionManager().getAboutAction());

        file.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        file.add(MainFrame.getInstance().getActionManager().getRemoveNodeAction());
        file.add(MainFrame.getInstance().getActionManager().getShareAction());
        file.add(MainFrame.getInstance().getActionManager().getSwitchAction());
        file.add(MainFrame.getInstance().getActionManager().getOpenAction());
        file.add(MainFrame.getInstance().getActionManager().getSaveAction());
        file.add(MainFrame.getInstance().getActionManager().getSaveAsAction());

        edit.add(MainFrame.getInstance().getActionManager().getUndoAction());
        edit.add(MainFrame.getInstance().getActionManager().getRedoAction());


    }

    public JMenu getFile() {
        return file;
    }

    public JMenuItem getAboutItem() {
        return aboutItem;
    }

    public JMenu getHelp() {
        return help;
    }

}

package dsw.gerudok.app.gui.swing;

import dsw.gerudok.app.core.Gui;
import dsw.gerudok.app.core.Repository;
import dsw.gerudok.app.gui.swing.view.MainFrame;

public class SwingGui implements Gui {

    private MainFrame instance;
    private Repository documentRepository;

    public SwingGui(Repository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setDocumentRepository(documentRepository);
        instance.initialiseWorkspaceTree();
        instance.setVisible(true);
    }


    @Override
    public void update(String notification) {
        MainFrame.getInstance().showError(notification);
    }

    @Override
    public void updateCommand(String notification) {
        MainFrame.getInstance().setCommandCondition(notification);
    }
}

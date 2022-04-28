package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.core.Repository;
import dsw.gerudok.app.gui.swing.controller.ActionManager;
import dsw.gerudok.app.gui.swing.controller.WindowController;
import dsw.gerudok.app.gui.swing.tree.RuTree;
import dsw.gerudok.app.gui.swing.tree.view.RuTreeImplementation;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.MainPanel;
import dsw.gerudok.app.repository.Project;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    private ActionManager actionManager;
    private Repository documentRepository;
    private MainMenuBar mainMenuBar;
    private JToolBar toolBar;
    private RuTree tree;
    private JTree workspaceTree;
    private  MainPanel mainPanel;
    private PalleteToolBar palleteToolBar;


    private MainFrame(){

    }

    public static MainFrame getInstance(){
        if(instance==null){
            instance = new MainFrame();
            instance.initialise();

        }
        return instance;
    }

    private void initialise() {
        actionManager = new ActionManager();
        mainMenuBar = new MainMenuBar();
        toolBar = new MainToolBar();
        mainPanel = new MainPanel();
        palleteToolBar = new PalleteToolBar();


    }
    public void initialiseWorkspaceTree() {
        tree = new RuTreeImplementation();
        workspaceTree = tree.generateTree(documentRepository.getWorkspace());
        initialiseGUI();
    }

    private void initialiseGUI(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rukovalac dokumentima - RuDok");
        this.setVisible(true);

        this.setJMenuBar(mainMenuBar);

        this.add(toolBar, BorderLayout.NORTH);
        this.add(palleteToolBar, BorderLayout.EAST);

        JScrollPane scroll=new JScrollPane(workspaceTree);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,mainPanel);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(200);
        split.setOneTouchExpandable(true);
        this.addWindowListener(new WindowController());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        openLastWorkspace();
        setCommandCondition("undoDisabled");
        setCommandCondition("doDisabled");

    }

    public void setDocumentRepository(Repository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public MainMenuBar getMainMenuBar() {
        return mainMenuBar;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public RuTree getTree() {
        return tree;
    }

    public JTree getWorkspaceTree() {
        return workspaceTree;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void showError(String notification){
        JOptionPane.showMessageDialog(this, notification, "Greška!", JOptionPane.ERROR_MESSAGE);
    }

    public Repository getDocumentRepository() {
        return documentRepository;
    }

    public void setCommandCondition(String notification){
        if(notification == "undoEnabled")
            actionManager.getUndoAction().setEnabled(true);
        if(notification == "undoDisabled")
            actionManager.getUndoAction().setEnabled(false);
        if(notification == "doEnabled"){
            actionManager.getRedoAction().setEnabled(true);
        }
        if(notification == "doDisabled"){
            actionManager.getRedoAction().setEnabled(false);
        }
    }

    public void openLastWorkspace(){
        String workspacePath = AppCore.getInstance().getiSerializer().getTextFromFile("activeWorkspace.txt");
        List<Project> projectList = AppCore.getInstance().getiSerializer().openWorkspace(new File(workspacePath));
        if (!workspacePath.isEmpty() && !projectList.isEmpty()) {
            if (JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li želite da nastavite rad sa poslednjim radnim prostorom?", "Poslednji radni prostor",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    for (Project p : projectList) {
                        MainFrame.getInstance().getTree().addOpenedProject(p);
                        MainFrame.getInstance().getMainPanel().addProjectView(p);
                        p.setChanged(false);
                    }
                    documentRepository.getWorkspace().setWorkspaceFilePath(workspacePath);



            } else {
                return;
            }
        }
    }

}

package dsw.gerudok.app.gui.swing.view.repositoryView.view;

import dsw.gerudok.app.commands.CommandManager;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.ruNodeObserver.ProjectObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentView extends JPanel implements ProjectObserver {

    private Document document;
    private ImageIcon icon;
    private List<PageView> pageViewList;
    private ProjectView projectView;
    private CommandManager commandManager;

    public DocumentView(Document document) {
        this.document = document;
        this.icon = new ImageIcon(this.getClass().getResource("images/documentView.png"));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        pageViewList = new ArrayList<>();
        commandManager = new CommandManager();
        document.addObserver(this);
    }



    private int getRowCount(int number){
        int row ;
        if(number % 2 == 0){
            row = number / 2;
        }else{
            row = number / 2 + 1;
        }
        return row;
    }

    public Document getDocument() {
        return document;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void removeObservers(){
        if(!this.pageViewList.isEmpty()) {
            for (PageView pageView : this.pageViewList) {
                pageView.removeObservers();

            }
            this.pageViewList.clear();
        }
        this.getDocument().removeObserver(this);
    }

    @Override
    public void update(Object object, String notification) {
        if(notification == "Dodavanje stranice"){
            if(object instanceof Page){
                Page page = (Page) object;
                PageView pageView = new PageView(this,page);
                this.add(pageView);
                pageViewList.add(pageView);

            }
        }else if(notification == "Brisanje stranice"){
            if(object instanceof Page){
                Page page = (Page) object;

                for(PageView pageView: pageViewList){
                    if(pageView.getPage().getName().equals(page.getName())){
                        pageView.removeObservers();
                        this.remove(pageView);
                        break;
                    }
                }

            }
        }else if(notification == "Promena imena"){
            int i = projectView.getDocumentViewList().indexOf(this);
            projectView.getTabbedPane().setTitleAt(i, document.getName());

        }
        updateUI();
    }

    public void setProjectView(ProjectView projectView) {
        this.projectView = projectView;
    }

    public ProjectView getProjectView() {
        return projectView;
    }

    public List<PageView> getPageViewList() {
        return pageViewList;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}

package dsw.gerudok.app.gui.swing.view.repositoryView.view;

import dsw.gerudok.app.gui.swing.view.repositoryView.model.ShareChecker;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.ruNodeObserver.ProjectObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectView extends JPanel implements ProjectObserver {

    private Project project;
    private JTabbedPane tabbedPane;
    private JLabel naslov;
    private List<DocumentView> documentViewList;
    private MainPanel mainPanel;

    public ProjectView(Project project, MainPanel mainPanel) {
        this.setLayout(new BorderLayout());
        this.mainPanel = mainPanel;
        project.addObserver(this);
        documentViewList = new ArrayList<>();
        naslov = new JLabel(project.getName(),SwingConstants.CENTER);
        this.project = project;
        this.tabbedPane = new JTabbedPane();
        this.add(naslov,BorderLayout.NORTH);
        this.add(tabbedPane,BorderLayout.CENTER);
    }

    public void removeObservers(){
        if(!this.getDocumentViewList().isEmpty()){
            for(DocumentView documentView: documentViewList){
                documentView.removeObservers();
            }
            this.getDocumentViewList().clear();
        }
        this.getProject().removeObserver(this);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectView that = (ProjectView) o;
        return project.equals(that.project);
    }

    public DocumentView getDocumentView(Document document){
        if(documentViewList.isEmpty()){
            return null;
        }
        for(DocumentView documentView: documentViewList){
            if(documentView.getDocument().getName().equals(document.getName())){
                return documentView;
            }
        }
        return null;

    }

    public Project getProject() {
        return project;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JLabel getNaslov() {
        return naslov;
    }

    public List<DocumentView> getDocumentViewList() {
        return documentViewList;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void update(Object object,String notification) {
        if(notification == "Dodavanje dokumenta"){
            if(object instanceof Document){
                Document document = (Document) object;
                DocumentView documentView = new DocumentView(document);
                documentView.setProjectView(this);
                tabbedPane.addTab(documentView.getDocument().getName(), documentView.getIcon(), new JScrollPane(documentView));
                documentViewList.add(documentView);
                if(!document.getChildren().isEmpty()){
                    for(RuNode page: document.getChildren()){
                        documentView.update(page,"Dodavanje stranice");
                    }
                    for(PageView pageView: documentView.getPageViewList()){
                        if(!pageView.getPage().getChildren().isEmpty()){
                            for(RuNode slot: pageView.getPage().getChildren()){

                                Slot slot1 = (Slot) slot;
                                pageView.update(slot1, "Dodavanje slota");
                            }
                        }
                    }
                }

            }
        }else if(notification == "Brisanje dokumenta"){
            if(object instanceof Document){
                Document document = (Document) object;
                for(DocumentView documentView: documentViewList){
                    if(documentView.getDocument().getName().equals(document.getName())){
                        documentView.removeObservers();
                        int index =  this.documentViewList.indexOf(documentView);
                        tabbedPane.removeTabAt(index);
                        this.documentViewList.remove(documentView);
                        break;
                    }
                }
                for(ProjectView projectView: mainPanel.getProjectViewList()){
                    if(!projectView.getDocumentViewList().isEmpty()) {
                        for (DocumentView documentView : projectView.getDocumentViewList()) {
                            if (documentView.getDocument().getName().equals(document.getName())) {
                                documentView.removeObservers();
                                int index = projectView.getDocumentViewList().indexOf(documentView);
                                projectView.getTabbedPane().removeTabAt(index);
                                projectView.getDocumentViewList().remove(documentView);
                                break;
                            }
                        }
                    }
                }
                if(!mainPanel.getShareCheckers().isEmpty()){
                    List<ShareChecker> scList = mainPanel.findShareCheckerByDocument(document);
                    if(scList != null){
                        for(ShareChecker sc: scList){
                            mainPanel.getShareCheckers().remove(sc);
                        }
                    }
                }
            }
        }else if(notification == "Promena imena"){
            this.naslov.setText(this.project.getName());
        }

    }

}

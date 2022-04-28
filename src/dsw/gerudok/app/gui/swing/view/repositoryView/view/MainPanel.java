package dsw.gerudok.app.gui.swing.view.repositoryView.view;

import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.repositoryView.model.ShareChecker;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.ruNodeObserver.ProjectObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainPanel extends JPanel implements ProjectObserver {

    private List<ProjectView> projectViewList;
    private ProjectView activeProjectView;
    private List<ShareChecker> shareCheckers;

    public MainPanel() {
        this.setLayout(new BorderLayout());
        projectViewList = new ArrayList<>();
        activeProjectView = null;
        shareCheckers = new ArrayList<>();
    }

    public  void  addProjectView(Project project){
        MainFrame.getInstance().getTree().getRuTreeRoot().addObserver(this);
        ProjectView projectView = new ProjectView(project,this);
        this.projectViewList.add(projectView);
        if(!project.getChildren().isEmpty()){
            Iterator iterator = project.getChildren().iterator();
            while(iterator.hasNext()){
                Document document = (Document) iterator.next();
                projectView.update(document, "Dodavanje dokumenta");
            }

        }
        this.updateUI();
    }

    public void changePanel(Project project){
        ProjectView projectView = null;
        for(ProjectView projectViews: projectViewList){
            if(projectViews.getProject().getName().equals(project.getName())){
                projectView = projectViews;
                break;
            }
        }
        if(activeProjectView == null){
            activeProjectView = projectView;
            this.add(activeProjectView);
        }else{
            if(activeProjectView.getProject().getName().equals(projectView.getProject().getName())){
                return;
            }else{
                this.remove(activeProjectView);
                activeProjectView = projectView;
                this.add(activeProjectView);
            }

        }
        updateUI();

    }

    public ProjectView getProjectView(Project p){
        if(projectViewList.isEmpty()){
            return null;
        }
        for(ProjectView projectView: projectViewList){
            if(projectView.getProject().getName().equals(p.getName())){
                return projectView;
            }
        }
        return null;
    }
    public List<ShareChecker> findShareCheckerByDocument(Document document){
        if(shareCheckers.isEmpty())
            return  null;
        List<ShareChecker> shareCheckerArrayList = new ArrayList<>();
        for(ShareChecker shareChecker: shareCheckers){
            if(shareChecker.getDocument().equals(document)){
                shareCheckerArrayList.add(shareChecker);
            }
        }
        if(shareCheckerArrayList.isEmpty())
            return  null;
        return  shareCheckerArrayList;
    }
    public List<ProjectView> getProjectViewList() {
        return projectViewList;
    }

    public ProjectView getActiveProjectView() {
        return activeProjectView;
    }

    public void setActiveProjectView(ProjectView activeProjectView) {
        this.activeProjectView = activeProjectView;
    }

    public List<ShareChecker> getShareCheckers() {
        return shareCheckers;
    }


    @Override
    public void update(Object object, String notification) {
        if(notification == "Brisanje projekta"){
            if(object instanceof Project) {
                Project project = (Project) object;
                ProjectView projectView = getProjectView(project);
                projectViewList.remove(projectView);
                if(!shareCheckers.isEmpty()){
                    List<Document> documents = new ArrayList<>();
                    for(int i = 0; i < shareCheckers.size(); i++){
                        if(shareCheckers.get(i).getProject1().getName().equals(project.getName())){
                            if(!documents.contains(shareCheckers.get(i).getDocument())) {
                                documents.add(shareCheckers.get(i).getDocument());
                            }
                        }
                        if(shareCheckers.get(i).getProject2().getName().equals(project.getName())){
                            if(!documents.contains(shareCheckers.get(i).getDocument())) {
                                documents.add(shareCheckers.get(i).getDocument());
                            }
                        }
                    }
                    if(!documents.isEmpty()) {
                        for (ProjectView projectView1 : projectViewList) {
                            if (!projectView1.getProject().equals(project)) {
                                for (Document document : documents) {
                                    if(projectView1.getDocumentView(document) != null){
                                        DocumentView documentView = projectView1.getDocumentView(document);
                                        int index = projectView1.getDocumentViewList().indexOf(documentView);
                                        projectView1.getTabbedPane().removeTabAt(index);
                                        projectView1.getDocumentViewList().remove(documentView);
                                    }
                                }
                            }
                        }
                        for(Document document: documents){
                            List<ShareChecker> shareCheckerList = findShareCheckerByDocument(document);
                            if(shareCheckerList == null){
                                continue;
                            }else {
                                for(ShareChecker sc: shareCheckerList){
                                    int index = this.shareCheckers.indexOf(sc);
                                    this.shareCheckers.remove(index);
                                }
                            }
                        }

                    }


                }
                projectView.removeObservers();
                if(activeProjectView != null && activeProjectView.getProject().getName().equals(project.getName())){
                    this.remove(activeProjectView);
                    activeProjectView = null;
                }
            }
            this.updateUI();
        }
    }
}

package dsw.gerudok.app.gui.swing.tree.view;

import dsw.gerudok.app.gui.swing.tree.RuTree;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.Workspace;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuTreeImplementation implements RuTree {

    private RuTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public JTree generateTree(Workspace workspace) {
        RuTreeItem root = new RuTreeItem(workspace);
        workspace.addRuTreeItemObserver(root);
        treeModel = new DefaultTreeModel(root);
        treeView = new RuTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addProject(Project project) {
        RuNode nodeModel = ((RuTreeItem)treeModel.getRoot()).getNodeModel();
        RuTreeItem projectRTI = new RuTreeItem(project);
        ((RuTreeItem)treeModel.getRoot()).add(projectRTI);
        ((Workspace) nodeModel).addChild(project);
        projectRTI.addObservers();
        project.setChanged(true);
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void addDocument(Document document) {
        RuNode nodeModel = getSelectedRuNode();
        RuTreeItem documentRTI = new RuTreeItem(document);
        getSelectedNode().add(documentRTI);
        ((Project) nodeModel).addChild(document);
        documentRTI.addObservers();
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void addPage(Page page) {
        RuNode nodeModel = getSelectedRuNode();
        RuTreeItem pageRTI = new RuTreeItem(page);
        getSelectedNode().add(pageRTI);
        if(getSelectedNode().getName().contains("*")){
            page.addRuTreeItemObserver(pageRTI);
        }
        ((Document) nodeModel).addChild(page);
        page.addRuTreeItemObserver(pageRTI);
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeRuNode(RuNode ruNode) {
        RuTreeItem parent = null;
        if(this.getSelectedNode() != null) {
             parent = (RuTreeItem) this.getSelectedNode().getParent();
        }
        else {
            if(ruNode instanceof  Project){
                parent = (RuTreeItem) treeModel.getRoot();
            }
        }
        if(parent == null){
            return;
        }


        if(parent.getNodeModel() instanceof Workspace){
            Workspace parentNM = (Workspace) parent.getNodeModel();
            parentNM.removeChild(ruNode);
        }else if(parent.getNodeModel() instanceof Project){
            Project parentNM = (Project) parent.getNodeModel();
            parentNM.removeChild(ruNode);
        }else if(parent.getNodeModel() instanceof Document){
            Document parentNM = (Document) parent.getNodeModel();
            parentNM.removeChild(ruNode);
        }else if(parent.getNodeModel() instanceof Page){
            Page parentNM = (Page) parent.getNodeModel();
            parentNM.removeChild(ruNode);
        }
        if( parent.findByName(ruNode.getName()) != null){
            parent.remove(parent.findByName(ruNode.getName()));
        }
        SwingUtilities.updateComponentTreeUI(treeView);
        setRuTreeSelectionPath(null);
    }




    @Override
    public RuTreeItem getSelectedNode() {
        RuTreeItem ruTreeItem = (RuTreeItem) treeView.getLastSelectedPathComponent();
        return ruTreeItem;

    }

    @Override
    public RuNode getSelectedRuNode() {
        return getSelectedNode().getNodeModel();
    }

    @Override
    public RuNode getRuTreeRoot() {
        return (RuNode) ((RuTreeItem) treeView.getModel().getRoot()).getNodeModel();
    }

    @Override
    public void setRuTreeSelectionPath(TreePath treePath) {
        treeView.setSelectionPath(treePath);
    }


    @Override
    public List<RuNode> getWorkspaceChildren() {
        RuNodeComposite workspace = (RuNodeComposite) getRuTreeRoot();
        List<RuNode> children = workspace.getChildren();
        return children;
    }

    @Override
    public void addSharedDocument(Project project,Document document, Project projectMain) {
        RuTreeItem documentRTI = new RuTreeItem(document);
        RuTreeItem workspace = (RuTreeItem) treeModel.getRoot();
        RuTreeItem projectRTI = workspace.findByName(project.getName());

        RuTreeItem projectMainRTI = workspace.findByName(projectMain.getName());
        RuTreeItem documentMainRTI = projectMainRTI.findByName(document.getName());


        projectRTI.add(documentRTI);
        documentRTI.setRTIName(document.getName() + "*");
        getSelectedNode().setRTIName(document.getName() + "*");

        documentRTI.addMissedChildren();
        documentRTI.addObservers();
        documentMainRTI.addObservers();



        project.addChild(document);

        SwingUtilities.updateComponentTreeUI(treeView);


    }

    @Override
    public void addOpenedProject(Project project) {
        RuNode nodeModel = ((RuTreeItem)treeModel.getRoot()).getNodeModel();
        RuTreeItem projectRTI = new RuTreeItem(project);
        ((RuTreeItem)treeModel.getRoot()).add(projectRTI);
        ((Workspace) nodeModel).addChild(project);
        projectRTI.addObservers();
        project.setChanged(true);
        if(!project.getChildren().isEmpty()){
            Iterator iterator = project.getChildren().iterator();
            while(iterator.hasNext()){
                Document document = (Document) iterator.next();
                RuTreeItem documentRTI = new RuTreeItem(document);
                projectRTI.add(documentRTI);
                if(!document.getChildren().isEmpty()){
                    Iterator iterator2 = document.getChildren().iterator();
                    while(iterator2.hasNext()){
                        Page page = (Page) iterator2.next();
                        RuTreeItem pageRTI = new RuTreeItem(page);
                        documentRTI.add(pageRTI);
                        if(!page.getChildren().isEmpty()){
                            Iterator iterator3 = page.getChildren().iterator();
                            while(iterator3.hasNext()){
                                Slot slot = (Slot) iterator3.next();
                                RuTreeItem slotRTI = new RuTreeItem(slot);
                                pageRTI.add(slotRTI);
                            }
                        }

                    }
                }
                documentRTI.addObservers();
            }
        }
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public List<Project> getSavedProjects() {
        List<Project> projects = new ArrayList<>();
        RuTreeItem workspaceRTI = (RuTreeItem) treeModel.getRoot();
        if(workspaceRTI.getChildCount() > 0){
            Iterator iterator = workspaceRTI.children().asIterator();
            while(iterator.hasNext()){
                RuTreeItem projectRTI = (RuTreeItem) iterator.next();
                if(!projectRTI.getName().contains("*")){
                    projects.add((Project) projectRTI.getNodeModel());
                }
            }
        }
        return projects;
    }


}

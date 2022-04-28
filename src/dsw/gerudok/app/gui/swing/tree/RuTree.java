package dsw.gerudok.app.gui.swing.tree;

import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.Workspace;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.List;

public interface RuTree {

    JTree generateTree(Workspace workspace);
    void addProject(Project project);
    void addDocument(Document document);
    void addPage(Page page);
    void removeRuNode(RuNode ruNode);
    RuTreeItem getSelectedNode();
    RuNode getSelectedRuNode();
    RuNode getRuTreeRoot();
    void setRuTreeSelectionPath(TreePath treePath);
    List<RuNode> getWorkspaceChildren();
    void addSharedDocument(Project project,Document document, Project projectMain);
    void addOpenedProject(Project project);
    List<Project> getSavedProjects();

}

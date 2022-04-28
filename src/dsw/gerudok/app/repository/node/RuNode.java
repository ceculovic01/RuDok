package dsw.gerudok.app.repository.node;

import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.ruNodeObserver.ProjectObserver;
import dsw.gerudok.app.ruNodeObserver.RuNodeObserver;
import dsw.gerudok.app.ruNodeObserver.RuTreeItemObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class RuNode implements RuNodeObserver, Serializable {

    private String name;
    private RuNode parent;
    transient private List<ProjectObserver> projectObserverList;
    transient private List<RuTreeItemObserver> ruTreeItemObserverList;

    public RuNode(String name, RuNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public RuNode() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notified(this, "Promena imena");
        notifiedRuTreeItems(this,"Promena imena - RuTreeItem");
    }

    public RuNode getParent() {
        return parent;
    }

    public void setParent(RuNode parent) {
        this.parent = parent;
    }

    @Override
    public void notified(Object ruNode, String notification) {
        if(projectObserverList == null)
            return;
        if(ruNode == null)
            return;
        if(ruNode instanceof Project){
            ((Project) ruNode).setChanged(true);
        }else if(ruNode instanceof Document){
            Project project = (Project) ((Document) ruNode).getParent();
            project.setChanged(true);
        }else if(ruNode instanceof Page){
            Project project = (Project) ((Page) ruNode).getParent().getParent();
            project.setChanged(true);
        }else if(ruNode instanceof Slot){
            Project project = (Project) ((Slot) ruNode).getParent().getParent().getParent();
            project.setChanged(true);
        }
        for(ProjectObserver projectObserver: projectObserverList){
            projectObserver.update(ruNode, notification);

        }

    }

    @Override
    public void notifiedRuTreeItems(Object ruNode, String notification) {

        if(ruTreeItemObserverList == null)
            return;
        if(ruNode == null)
            return;
        for(RuTreeItemObserver ruTreeItemObserver: ruTreeItemObserverList){
            ruTreeItemObserver.update(ruNode, notification);

        }

    }

    @Override
    public void addObserver(ProjectObserver observer) {
        if (observer == null)
            return;
        if (this.projectObserverList == null) {
            this.projectObserverList = new ArrayList<>();
        }
        if (this.projectObserverList.contains(observer)) {
            return;
        }
        projectObserverList.add(observer);
    }

    @Override
    public void addRuTreeItemObserver(RuTreeItemObserver observer) {
        if (observer == null)
            return;
        if (this.ruTreeItemObserverList == null) {
            this.ruTreeItemObserverList = new ArrayList<>();
        }

        if(!ruTreeItemObserverList.isEmpty()) {

            for (RuTreeItemObserver observers : ruTreeItemObserverList) {
                if (observer.getRuTreeItemParenName().equals(observers.getRuTreeItemParenName())) {
                    return;
                }
            }
        }

        ruTreeItemObserverList.add(observer);
    }

    @Override
    public void removeRuTreeItemObserver(RuTreeItemObserver observer) {
        if(observer == null || this.ruTreeItemObserverList == null || !this.ruTreeItemObserverList.contains(observer))
            return;
        ruTreeItemObserverList.remove(observer);
    }

    @Override
    public void removeObserver(ProjectObserver observer) {
        if(observer == null || this.projectObserverList == null || !this.projectObserverList.contains(observer))
            return;
        this.projectObserverList.remove(observer);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RuNode)) return false;
        RuNode ruNode = (RuNode) o;
        return getName().equals(ruNode.getName());
    }

    @Override
    public String toString() {
        return "RuNode{" +
                "name='" + name + '\'' +
                '}';
    }

    public List<ProjectObserver> getProjectObserverList() {
        return projectObserverList;
    }

    public List<RuTreeItemObserver> getRuTreeItemObserverList() {
        return ruTreeItemObserverList;
    }
}

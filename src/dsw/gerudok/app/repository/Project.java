package dsw.gerudok.app.repository;

import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;

import java.util.ArrayList;
import java.util.List;

public class Project extends RuNodeComposite {

    private String projectFilePath = "";
    transient private boolean isChanged;

    public Project(String name, RuNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(RuNode child) {
        if (child != null &&  child instanceof Document){
            Document document = (Document) child;
            if (!this.getChildren().contains(document)){
                this.getChildren().add(document);
                this.notified(document, "Dodavanje dokumenta" );
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child != null && child instanceof Document){
            this.getChildren().remove(child);
            this.notified(child, "Brisanje dokumenta");
            this.notifiedRuTreeItems(child, "Brisanje dokumenta - RuTreeItem");

        }
    }

    public String getProjectFilePath() {
        return projectFilePath;
    }

    public void setProjectFilePath(String projectFilePath) {
        this.projectFilePath = projectFilePath;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public List<Slot> getProjectSlots(){
        List<Slot> slotList = new ArrayList<>();
        for(RuNode ruNode: this.getChildren()){
            Document document = (Document) ruNode;
            for(RuNode ruNode2: document.getChildren()){
                Page page = (Page) ruNode2;
                for(RuNode ruNode3: page.getChildren()){
                    Slot slot = (Slot) ruNode3;
                    slotList.add(slot);
                }
            }
        }
        return  slotList;
    }

    public void setChanged(boolean changed) {
        if(changed){
            this.notifiedRuTreeItems(this,"PromenaTrue");
        }
        else {
            this.notifiedRuTreeItems(this,"PromenaFalse");
        }
        isChanged = changed;
    }
}

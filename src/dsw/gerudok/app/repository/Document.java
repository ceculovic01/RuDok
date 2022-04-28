package dsw.gerudok.app.repository;

import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;

public class Document extends RuNodeComposite {


    public Document(String name, RuNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(RuNode child) {
        if (child != null &&  child instanceof Page){
            Page page = (Page) child;
            if (!this.getChildren().contains(page)){
                this.getChildren().add(page);
                notified(child, "Dodavanje stranice");
                notifiedRuTreeItems(child, "Dodavanje stranice - RuTreeItem");
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child != null && child instanceof Page){
            this.getChildren().remove(child);
            notified(child, "Brisanje stranice");
            notifiedRuTreeItems(child, "Brisanje stranice - RuTreeItem");
        }
    }

}

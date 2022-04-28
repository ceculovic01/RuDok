package dsw.gerudok.app.repository;

import dsw.gerudok.app.repository.elements.Selection;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;

public class Page extends RuNodeComposite {

    private int countSlot;
    private Selection selection;

    public Page(String name, RuNode parent) {
        super(name, parent);
        countSlot = 0;
        selection = new Selection();
    }

    @Override
    public void addChild(RuNode child) {
        if (child != null &&  child instanceof Slot){
            Slot slot = (Slot) child;
            if (!this.getChildren().contains(slot)){
                this.getChildren().add(slot);
                this.notifiedRuTreeItems(child,"Dodavanje slota - RuTreeItem");
                this.notified(child,"Dodavanje slota");
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child != null && child instanceof Slot){
            this.getChildren().remove(child);
            if(!selection.getActiveSlotList().isEmpty() && selection.getActiveSlotList().contains(child))
                selection.getActiveSlotList().remove(child);
            this.notifiedRuTreeItems(child, "Brisanje slota - RuTreeItem");
            this.notified(child,"Brisanje slota");
        }
    }

    public int getCountSlot() {
        return countSlot;
    }

    public void incCountSlot(){
        this.countSlot++;
    }

    public void decCountSlot(){ this.countSlot--;}

    public Selection getSelection() {
        return selection;
    }
}

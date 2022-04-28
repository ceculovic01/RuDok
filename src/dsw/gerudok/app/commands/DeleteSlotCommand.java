package dsw.gerudok.app.commands;

import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.elements.Slot;

import java.util.List;

public class DeleteSlotCommand extends AbstractCommand{

    private Page page;
    private List<Slot> slotList;

    public DeleteSlotCommand(Page page,List<Slot> slotList) {
        this.page = page;
        this.slotList = slotList;
    }

    @Override
    public void doCommand() {
       for(Slot slot: slotList){
           page.removeChild(slot);
       }
    }

    @Override
    public void undoCommand() {
        for(Slot slot: slotList){
            page.addChild(slot);
        }
    }
}

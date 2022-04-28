package dsw.gerudok.app.commands;

import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.elements.slotFactory.SlotFactory;
import dsw.gerudok.app.repository.elements.Type;

public class AddSlotCommand extends AbstractCommand {

    private Page page;
    private Slot slot = null;
    private int posX, posY;
    private Type type;

    public AddSlotCommand(Page page, int posX, int posY, Type type) {
        this.page = page;
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    }

    @Override
    public void doCommand() {
        if(slot == null){
           slot = SlotFactory.makeSlot(type,posX,posY);
        }
        page.incCountSlot();
        slot.setParent(page);
        slot.setName("Slot " + (page.getCountSlot()));
        page.addChild(slot);

    }

    @Override
    public void undoCommand() {
        page.decCountSlot();
        page.removeChild(slot);
    }
}

package dsw.gerudok.app.repository.elements.slotFactory;

import dsw.gerudok.app.repository.elements.RectangleSlot;
import dsw.gerudok.app.repository.elements.Slot;

public class RectangleFactory extends SlotFactory{

    @Override
    public Slot createSlot() {
        Slot slot = new RectangleSlot();
        slot.setWidth(100);
        slot.setHeight(50);
        return slot;
    }
}

package dsw.gerudok.app.repository.elements.slotFactory;

import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.elements.TriangleSlot;

public class TriangleFactory extends SlotFactory{
    @Override
    public Slot createSlot() {
        Slot slot = new TriangleSlot();
        slot.setWidth(50);
        slot.setHeight(50);
        return slot;
    }
}

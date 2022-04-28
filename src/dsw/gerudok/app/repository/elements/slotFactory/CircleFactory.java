package dsw.gerudok.app.repository.elements.slotFactory;

import dsw.gerudok.app.repository.elements.CircleSlot;
import dsw.gerudok.app.repository.elements.Slot;

public class CircleFactory extends SlotFactory{
    @Override
    public Slot createSlot() {
        Slot slot = new CircleSlot();
        slot.setWidth(50);
        slot.setHeight(50);
        return slot;
    }
}

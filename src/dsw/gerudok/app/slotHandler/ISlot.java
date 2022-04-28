package dsw.gerudok.app.slotHandler;

import dsw.gerudok.app.repository.elements.Slot;

public interface ISlot {
    void changeSlot(Slot slot,String transform,int newPositionX,int newPositionY);
    boolean checkLimit(Slot slot,int positionX, int positionY);
    boolean checkAngle(Slot slot, double angle);
}

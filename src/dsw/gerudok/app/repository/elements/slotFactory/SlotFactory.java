package dsw.gerudok.app.repository.elements.slotFactory;

import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.elements.Type;

public abstract  class SlotFactory {


    public static Slot makeSlot(Type type, int posX, int posY){
        SlotFactory slotFactory;
        if(type == Type.Rectangle){
            slotFactory = new RectangleFactory();
        }else if(type == Type.Circle){
            slotFactory = new CircleFactory();
        }else{
            slotFactory = new TriangleFactory();
        }
        Slot s = slotFactory.createSlot();

        s.setPositionX(posX);
        s.setPositionY(posY);
        s.setType(type);
        s.setAngle(0);
        return s;
    }
    public abstract Slot createSlot();


}

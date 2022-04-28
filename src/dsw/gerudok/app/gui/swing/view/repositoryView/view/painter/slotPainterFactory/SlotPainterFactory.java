package dsw.gerudok.app.gui.swing.view.repositoryView.view.painter.slotPainterFactory;

import dsw.gerudok.app.gui.swing.view.repositoryView.view.painter.SlotPainter;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.elements.Type;

public abstract class SlotPainterFactory {

    public static SlotPainter makeSlotPainter(Slot slot){
        SlotPainterFactory slotPainterFactory;
        if(slot.getType() == Type.Rectangle){
            slotPainterFactory = new RectanglePainterFactory();
        }else if(slot.getType() == Type.Circle){
            slotPainterFactory = new CirclePainterFactory();
        }else{
            slotPainterFactory = new TrianglePainterFactory();
        }
        return slotPainterFactory.createSlotPainter(slot);
    }

    public abstract SlotPainter createSlotPainter(Slot slot);


}

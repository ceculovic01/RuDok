package dsw.gerudok.app.gui.swing.view.repositoryView.view.painter.slotPainterFactory;

import dsw.gerudok.app.gui.swing.view.repositoryView.view.painter.SlotPainter;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.painter.TrianglePainter;
import dsw.gerudok.app.repository.elements.Slot;

public class TrianglePainterFactory extends SlotPainterFactory{
    @Override
    public SlotPainter createSlotPainter(Slot slot) {
        return new TrianglePainter(slot);
    }
}

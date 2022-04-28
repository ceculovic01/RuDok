package dsw.gerudok.app.gui.swing.view.repositoryView.view.painter;

import dsw.gerudok.app.repository.elements.Slot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class RectanglePainter extends SlotPainter{

    public RectanglePainter(Slot slot) {
        super(slot);
        this.shape = createRectangle(shape);
        paint = Color.BLACK;
        stroke = new BasicStroke();
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        this.shape = createRectangle(shape);
        graphics2D.setPaint(paint);
        graphics2D.setStroke(stroke);
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.rotate(slot.getAngle(),slot.getPositionX(),slot.getPositionY());
        graphics2D.draw(shape);
        graphics2D.drawString(slot.getName(), slot.getPositionX() + 10, slot.getPositionY() + (slot.getHeight() / 2));
        graphics2D.setTransform(affineTransform);



    }

    public Shape createRectangle(Shape shape){

        shape = new GeneralPath();

        ((GeneralPath)shape).moveTo(this.getSlot().getPositionX(),this.getSlot().getPositionY());

        ((GeneralPath)shape).lineTo(this.getSlot().getPositionX()+this.getSlot().getWidth(),this.getSlot().getPositionY());

        ((GeneralPath)shape).lineTo(this.getSlot().getPositionX()+this.getSlot().getWidth(),this.getSlot().getPositionY()+this.getSlot().getHeight());

        ((GeneralPath)shape).lineTo(this.getSlot().getPositionX(),this.getSlot().getPositionY()+this.getSlot().getHeight());

        ((GeneralPath)shape).closePath();
        return  shape;
    }
}

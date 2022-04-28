package dsw.gerudok.app.gui.swing.view.repositoryView.view.painter;

import dsw.gerudok.app.repository.elements.Slot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class TrianglePainter extends SlotPainter{
    public TrianglePainter(Slot slot) {
        super(slot);
        this.shape = createTriangle(shape);
        paint = Color.BLACK;
        stroke = new BasicStroke();
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        this.shape = createTriangle(shape);
        graphics2D.setPaint(paint);
        graphics2D.setStroke(stroke);
        int x = slot.getPositionX() - (slot.getWidth()/2);
        int y = slot.getPositionY() + slot.getHeight();
        x = x + 10;
        y = y - 10;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.rotate(slot.getAngle(),slot.getPositionX(),slot.getPositionY());
        graphics2D.drawString(slot.getName(), x, y);
        graphics2D.draw(shape);
        graphics2D.setTransform(affineTransform);


    }

    public Shape createTriangle(Shape shape){
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(slot.getPositionX(), slot.getPositionY());

        ((GeneralPath)shape).lineTo(slot.getPositionX() + (slot.getWidth()/2),slot.getPositionY()+ slot.getHeight());

        ((GeneralPath)shape).lineTo(slot.getPositionX() - (slot.getWidth()/2), slot.getPositionY() + slot.getHeight());

        ((GeneralPath)shape).closePath();
        return  shape;
    }
}

package dsw.gerudok.app.gui.swing.view.repositoryView.view.painter;

import dsw.gerudok.app.repository.elements.Slot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class CirclePainter extends SlotPainter{
    public CirclePainter(Slot slot) {
        super(slot);
        this.shape = createCircle(shape);
        paint = Color.BLACK;
        stroke = new BasicStroke();
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        this.shape = createCircle(shape);
        graphics2D.setPaint(paint);
        graphics2D.setStroke(stroke);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform affineTransform = graphics2D.getTransform();
        graphics2D.rotate(slot.getAngle(),slot.getPositionX(),slot.getPositionY());
        graphics2D.draw(shape);
        graphics2D.drawString(slot.getName(), slot.getPositionX() + 10, slot.getPositionY() + (slot.getHeight() / 2));
        graphics2D.setTransform(affineTransform);


    }
    public Shape createCircle(Shape shape){
        shape = new GeneralPath();

        ((GeneralPath)shape).moveTo(this.getSlot().getPositionX()+this.getSlot().getWidth()/2,this.getSlot().getPositionY());

        ((GeneralPath)shape).quadTo(this.getSlot().getPositionX()+this.getSlot().getWidth(), this.getSlot().getPositionY(),
                this.getSlot().getPositionX()+this.getSlot().getWidth(), this.getSlot().getPositionY()+this.getSlot().getHeight()/2);

        ((GeneralPath)shape).quadTo(this.getSlot().getPositionX()+this.getSlot().getWidth(), this.getSlot().getPositionY()+this.getSlot().getHeight(),
                this.getSlot().getPositionX()+this.getSlot().getWidth()/2, this.getSlot().getPositionY()+this.getSlot().getHeight());

        ((GeneralPath)shape).quadTo(this.getSlot().getPositionX(), this.getSlot().getPositionY()+this.getSlot().getHeight(),
                this.getSlot().getPositionX(), this.getSlot().getPositionY()+this.getSlot().getHeight()/2);


        ((GeneralPath)shape).quadTo(this.getSlot().getPositionX(), this.getSlot().getPositionY(),
                this.getSlot().getPositionX()+this.getSlot().getWidth()/2,this.getSlot().getPositionY());
        return shape;
    }
}

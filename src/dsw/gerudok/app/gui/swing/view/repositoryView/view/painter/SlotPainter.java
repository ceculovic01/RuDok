package dsw.gerudok.app.gui.swing.view.repositoryView.view.painter;

import dsw.gerudok.app.repository.elements.Slot;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class SlotPainter {

    protected Slot slot;
    protected Shape shape;
    protected Paint paint;
    protected Stroke stroke;

    public SlotPainter(Slot slot) {
        this.slot = slot;
    }


    public  boolean isElementAt(int x, int y){

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(getSlot().getAngle(), getSlot().getPositionX(), getSlot().getPositionY());

        Shape shape2 = affineTransform.createTransformedShape(shape);
        if(shape2.contains(x,y)){
            return true;
        }
        return false;
    }
    public abstract void paint(Graphics2D graphics2D);

    public Slot getSlot() {
        return slot;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public Shape getShape() {
        return shape;
    }
}

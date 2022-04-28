package dsw.gerudok.app.gui.swing.view.repositoryView.state;

import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.painter.SlotPainter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class SelectState extends State {

    private PageView med;
    private int x1,y1;
    private int x2,y2;

    public SelectState(PageView med) {
        this.med = med;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        x1 = e.getX();
        y1 = e.getY();
        for(PageView pageView: med.getDocumentView().getPageViewList()){
            if(!pageView.getPage().getSelection().getActiveSlotList().isEmpty() && !pageView.getPage().equals(med.getPage())){

                pageView.getPage().getSelection().removeAllFromList();
                pageView.repaint();
            }
        }

        for(int i = 0; i < med.getSlotPainterList().size(); i++){
            if(med.getSlotPainterList().get(i).isElementAt(point.x, point.y)){
                if(med.getPage().getSelection().getActiveSlotList().contains(med.getSlotPainterList().get(i).getSlot())){
                    med.getPage().getSelection().getActiveSlotList().remove(med.getSlotPainterList().get(i).getSlot());
                    continue;
                }
                med.getPage().getSelection().getActiveSlotList().add(med.getSlotPainterList().get(i).getSlot());
            }else {
                if(med.getPage().getSelection().getActiveSlotList().contains(med.getSlotPainterList().get(i).getSlot())){
                    med.getPage().getSelection().getActiveSlotList().remove(med.getSlotPainterList().get(i).getSlot());
                }
            }
        }
        med.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        int width = Math.abs(x2-x1);
        int height = Math.abs(y2-y1);
        med.getPage().getSelection().setWidth(width);
        med.getPage().getSelection().setHeight(height);
        if(x2 <= x1 && y2 <= y1){
            med.getPage().getSelection().setX(x2);
            med.getPage().getSelection().setY(y2);
        }else if(x2 <= x1 && y2 > y1){
            med.getPage().getSelection().setX(x1-width);
            med.getPage().getSelection().setY(y1);
        }else if(x2 > x1 && y2 <= y1){
            med.getPage().getSelection().setX(x1);
            med.getPage().getSelection().setY(y1-height);
        }else if(x2 > x1 && y2 > y1){
            med.getPage().getSelection().setX(x1);
            med.getPage().getSelection().setY(y1);
        }
        med.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!med.getPage().getSelection().isActive()){
            return;
        }

        Shape shape = new Rectangle2D.Double(med.getPage().getSelection().getX(),med.getPage().getSelection().getY(),
                med.getPage().getSelection().getWidth(),med.getPage().getSelection().getHeight());
        for(SlotPainter slotPainter: med.getSlotPainterList()){

            AffineTransform affineTransform = new AffineTransform();
            affineTransform.rotate(slotPainter.getSlot().getAngle(),slotPainter.getSlot().getPositionX(),slotPainter.getSlot().getPositionY());
            Shape shape2 = affineTransform.createTransformedShape(slotPainter.getShape());
            Area area = new Area(shape);
            area.intersect(new Area(shape2));
            if(!area.isEmpty()){
                med.getPage().getSelection().getActiveSlotList().add(slotPainter.getSlot());
            }

        }

        med.getPage().getSelection().setX(null);
        med.getPage().getSelection().setY(null);
        med.getPage().getSelection().setWidth(null);
        med.getPage().getSelection().setHeight(null);
        med.repaint();
    }
}

package dsw.gerudok.app.gui.swing.view.repositoryView.state;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.commands.MoveSlotCommand;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;
import dsw.gerudok.app.repository.elements.Slot;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MoveState extends State{

    private PageView med;
    private boolean clicked;
    int x, y;
    private Slot checkSlot;
    private List<Integer> beforePositions;
    private List<Integer> afterPositions;
    public MoveState(PageView med) {
        this.med = med;
        clicked = false;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(med.getPage().getSelection().getActiveSlotList().isEmpty()){
            AppCore.getInstance().getiError().handleError("objectNotSelected");
            return;
        }
        beforePositions = new ArrayList<>();
        afterPositions = new ArrayList<>();
        for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
            if(med.findBySlot(slot).isElementAt(e.getX(),e.getY())){
                clicked = true;
                checkSlot = slot;
                break;
            }

        }
        if(clicked){
            for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
                beforePositions.add(slot.getPositionX());
                beforePositions.add(slot.getPositionY());
            }
        }
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(clicked) {
                int beforeX = checkSlot.getPositionX();
                int beforeY = checkSlot.getPositionY();
                 int width = Math.abs(x - checkSlot.getPositionX());
                    int height = Math.abs(y - checkSlot.getPositionY());
                    int newPosX;
                    int newPosy;
                    if (x >= checkSlot.getPositionX()) {
                        newPosX = e.getX() - width;
                    } else {
                        newPosX = e.getX() + width;
                    }
                    if (y >= checkSlot.getPositionY()) {
                        newPosy = e.getY() - height;
                    } else {
                        newPosy = e.getY() + height;
                    }

                    iSlot.changeSlot(checkSlot, "moveTransform", newPosX, newPosy);
                int W = checkSlot.getPositionX() - beforeX;
                int Y = checkSlot.getPositionY() - beforeY;
                boolean check = false;
                for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
                    if(slot != checkSlot){
                        if(iSlot.checkLimit(slot,slot.getPositionX() + W,slot.getPositionY() + Y)){
                            check = true;
                        }
                    }
                }
                if(check){
                    iSlot.changeSlot(checkSlot, "moveTransform", beforeX, beforeY);
                }else {
                    for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
                        if(slot != checkSlot){
                            slot.setPositionX(slot.getPositionX() + W);
                            slot.setPositionY(slot.getPositionY() + Y);


                        }
                    }
                }

                x = e.getX();
                y = e.getY();


        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(clicked){
            List<String> slotListNames = new ArrayList<>();
            for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
                afterPositions.add(slot.getPositionX());
                afterPositions.add(slot.getPositionY());
                slotListNames.add(slot.getName());
            }
            med.getDocumentView().getCommandManager().addCommand(new MoveSlotCommand(med.getPage(),slotListNames, beforePositions,afterPositions));
        }
        clicked = false;

    }
}
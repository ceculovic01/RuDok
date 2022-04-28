package dsw.gerudok.app.gui.swing.view.repositoryView.state;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.commands.ResizeSlotCommand;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.elements.TriangleSlot;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ResizeState extends State {

    private PageView med;
    private boolean clicked;
    private boolean A,B,C,D;
    private Slot slotCheck;
    private int newPosX;
    private int newPosY;
    private List<Integer> beforePositions;
    private List<Integer> afterPositions;
    private List<Integer> beforeDimensions;
    private List<Integer> afterDimensions;

    public ResizeState(PageView med) {
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
        beforeDimensions = new ArrayList<>();
        afterDimensions = new ArrayList<>();
        afterPositions = new ArrayList<>();
        for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
            if(med.findBySlot(slot).isElementAt(e.getX(),e.getY())){
                clicked = true;
                if(slot instanceof TriangleSlot){
                    chooseAngleForTriangle(slot,e.getX(),e.getY());
                    slotCheck = slot;
                }else {
                    chooseAngle(slot,e.getX(),e.getY());
                    slotCheck = slot;
                }
                break;
            }
        }
        if(clicked){
            for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
               beforePositions.add(slot.getPositionX());
               beforePositions.add(slot.getPositionY());
               beforeDimensions.add(slot.getWidth());
               beforeDimensions.add(slot.getHeight());
            }
        }


    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(clicked) {
            slotCheck = med.getPage().getSelection().findSlotInList(slotCheck);
            for (Slot slot : med.getPage().getSelection().getActiveSlotList()) {
                if(slot == slotCheck)
                    continue;
                findPositions(slotCheck,slot,e.getX(),e.getY());
                if (A) {
                    iSlot.changeSlot(slot, "resizeTransformA", newPosX, newPosY);

                } else if (B) {
                    iSlot.changeSlot(slot, "resizeTransformB", newPosX, newPosY);

                } else if (C) {
                    iSlot.changeSlot(slot, "resizeTransformC", newPosX, newPosY);

                } else if (D) {
                    iSlot.changeSlot(slot, "resizeTransformD", newPosX, newPosY);

                }
            }
            if (A) {
                iSlot.changeSlot(slotCheck, "resizeTransformA", e.getX(), e.getY());
            } else if (B) {
                iSlot.changeSlot(slotCheck, "resizeTransformB", e.getX(), e.getY());
            } else if (C) {
                iSlot.changeSlot(slotCheck, "resizeTransformC", e.getX(), e.getY());
            } else if (D) {
                iSlot.changeSlot(slotCheck, "resizeTransformD", e.getX(), e.getY());
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(clicked){
            List<String> slotListName = new ArrayList<>();
            for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
                afterPositions.add(slot.getPositionX());
                afterPositions.add(slot.getPositionY());
                afterDimensions.add(slot.getWidth());
                afterDimensions.add(slot.getHeight());
                slotListName.add(slot.getName());
            }
            med.getDocumentView().getCommandManager().addCommand(new ResizeSlotCommand(med.getPage(),slotListName,beforePositions
            , afterPositions, beforeDimensions, afterDimensions));
        }
        clicked = false;
        A = false;
        B = false;
        C = false;
        D = false;
    }

    public void findPositions(Slot slot1, Slot slot2, int posX, int posY){
        double Nx = slot1.getPositionX() + (posX - slot1.getPositionX()) * Math.cos(slot1.getAngle()*(-1)) - (posY - slot1.getPositionY()) * Math.sin(slot1.getAngle()*(-1));
        double Ny = slot1.getPositionY() + (posX - slot1.getPositionX()) * Math.sin(slot1.getAngle()*(-1)) + (posY - slot1.getPositionY()) * Math.cos(slot1.getAngle()*(-1));
        Nx = Math.round(Nx);
        Ny = Math.round(Ny);
        int width = 0;
        int height = 0;

        if(A){
            if(slot1 instanceof TriangleSlot){
                width = (int) (Nx - (slot1.getPositionX()-(slot1.getWidth()/2)));
                height = (int) (Ny - slot1.getPositionY());
            }else {
                width = (int) (Nx - slot1.getPositionX());
                height = (int) (Ny - slot1.getPositionY());
            }
            newPosX = slot2.getPositionX() + width;
            newPosY = slot2.getPositionY() + height;
        }else if(B){
            if(slot1 instanceof TriangleSlot){
                width = (int) (Nx - (slot1.getPositionX()+(slot1.getWidth()/2)));
                height = (int) (Ny - slot1.getPositionY());
            }else {
                width = (int) (Nx - (slot1.getPositionX() + slot1.getWidth()));
                height = (int) (Ny - slot1.getPositionY());
            }

            newPosX = slot2.getPositionX() + slot2.getWidth() + width;
            newPosY = slot2.getPositionY() + height;
        }else if(C){

            if(slot1 instanceof TriangleSlot){
                width = (int) (Nx - (slot1.getPositionX()+(slot1.getWidth()/2)));
                height = (int) (Ny - (slot1.getPositionY()+slot1.getHeight()));
            }else {
                width = (int) (Nx -(slot1.getPositionX()+slot1.getWidth()));
                height = (int) (Ny - (slot1.getPositionY()+slot1.getHeight()));
            }
            newPosX = slot2.getPositionX() + slot2.getWidth() + width;
            newPosY = slot2.getPositionY() + slot2.getHeight() + height;
        }else if(D){
            if(slot1 instanceof TriangleSlot){
                width = (int) (Nx - (slot1.getPositionX()-(slot1.getWidth()/2)));
                height = (int) (Ny - (slot1.getPositionY()+slot1.getHeight()));
            }else {
                width = (int) (Nx - slot1.getPositionX());
                height = (int) (Ny - (slot1.getPositionY()+slot1.getHeight()));
            }

            newPosX = slot2.getPositionX() + width;
            newPosY = slot2.getPositionY() + slot2.getHeight() + height;
        }
        double pX =  slot2.getPositionX() + (newPosX - slot2.getPositionX()) * Math.cos(slot2.getAngle()) - (newPosY - slot2.getPositionY()) * Math.sin(slot2.getAngle());
        double pY =  slot2.getPositionY() + (newPosX - slot2.getPositionX()) * Math.sin(slot2.getAngle()) + (newPosY - slot2.getPositionY()) * Math.cos(slot2.getAngle());
        newPosX = (int) Math.round(pX);
        newPosY = (int) Math.round(pY);
    }

    public void chooseAngle(Slot slot, int newPositionX, int newPositionY){
        double Bx =  slot.getPositionX() + (slot.getPositionX()+slot.getWidth()-slot.getPositionX())*Math.cos(slot.getAngle()) -
                (slot.getPositionY()-slot.getPositionY())*Math.sin(slot.getAngle());
        double By =  slot.getPositionY() + (slot.getPositionX()+slot.getWidth()-slot.getPositionX())*Math.sin(slot.getAngle()) +
                (slot.getPositionY()-slot.getPositionY())*Math.cos(slot.getAngle());

        double Dx =  slot.getPositionX() + (slot.getPositionX()-slot.getPositionX())*Math.cos(slot.getAngle()) -
                (slot.getPositionY()+slot.getHeight()-slot.getPositionY())*Math.sin(slot.getAngle());
        double Dy =  slot.getPositionY() + (slot.getPositionX()-slot.getPositionX())*Math.sin(slot.getAngle()) +
                (slot.getPositionY()+slot.getHeight()-slot.getPositionY())*Math.cos(slot.getAngle());

        double Cx =  slot.getPositionX() + (slot.getPositionX()+slot.getWidth()-slot.getPositionX())*Math.cos(slot.getAngle()) -
                (slot.getPositionY()+slot.getHeight()-slot.getPositionY())*Math.sin(slot.getAngle());
        double Cy =  slot.getPositionY() + (slot.getPositionX()+slot.getWidth()-slot.getPositionX())*Math.sin(slot.getAngle()) +
                (slot.getPositionY()+slot.getHeight()-slot.getPositionY())*Math.cos(slot.getAngle());

        int AT = Math.abs(newPositionX - slot.getPositionX()) + Math.abs(newPositionY - slot.getPositionY());
        int BT = (int) (Math.abs(newPositionX - Bx) + Math.abs(newPositionY - By));
        int CT = (int) (Math.abs(newPositionX - Cx) + Math.abs(newPositionY - Cy));
        int DT = (int) (Math.abs(newPositionX - Dx) + Math.abs(newPositionY - Dy));
        int min = Math.min(Math.min(AT,BT),Math.min(CT,DT));
        if(min == AT){
            A = true;
        }else if (min == BT) {
            B = true;
        }else if(min == CT){
            C = true;
        }else  if(min == DT){
            D = true;
        }
    }

    public void chooseAngleForTriangle(Slot slot, int newPositionX, int newPositionY){

        double Ax = slot.getPositionX() + (slot.getPositionX()-(slot.getWidth()/2)-slot.getPositionX())*Math.cos(slot.getAngle()) -
                (slot.getPositionY()-slot.getPositionY())*Math.sin(slot.getAngle());
        double Ay = slot.getPositionY() + (slot.getPositionX()-(slot.getWidth()/2)-slot.getPositionX())*Math.sin(slot.getAngle()) +
                (slot.getPositionY()-slot.getPositionY())*Math.cos(slot.getAngle());

        double Bx =  slot.getPositionX() + (slot.getPositionX()+(slot.getWidth()/2)-slot.getPositionX())*Math.cos(slot.getAngle()) -
                (slot.getPositionY()-slot.getPositionY())*Math.sin(slot.getAngle());
        double By =  slot.getPositionY() + (slot.getPositionX()+(slot.getWidth()/2)-slot.getPositionX())*Math.sin(slot.getAngle()) +
                (slot.getPositionY()-slot.getPositionY())*Math.cos(slot.getAngle());

        double Dx = slot.getPositionX() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(slot.getAngle());
        double Dy = slot.getPositionY() + (slot.getPositionX() - (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(slot.getAngle());

        double Cx = slot.getPositionX() + (slot.getPositionX() + (slot.getWidth()/2) - slot.getPositionX()) * Math.cos(slot.getAngle()) -
                (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.sin(slot.getAngle());
        double Cy = slot.getPositionY() + (slot.getPositionX() + (slot.getWidth()/2) - slot.getPositionX()) * Math.sin(slot.getAngle()) +
                (slot.getPositionY() + slot.getHeight() - slot.getPositionY()) * Math.cos(slot.getAngle());


        int AT = (int) (Math.abs(newPositionX - Ax) + Math.abs((newPositionY - Ay)));
        int BT = (int) (Math.abs(newPositionX - Bx) + Math.abs(newPositionY - By));
        int CT = (int) (Math.abs(newPositionX - Cx) + Math.abs(newPositionY - Cy));
        int DT = (int) (Math.abs(newPositionX - Dx) + Math.abs(newPositionY - Dy));
        int min = Math.min(Math.min(AT,BT),Math.min(CT,DT));
        if(min == AT){
            A = true;
        }else if (min == BT) {
            B = true;
        }else if(min == CT){
            C = true;
        }else  if(min == DT){
            D = true;
        }

    }



}

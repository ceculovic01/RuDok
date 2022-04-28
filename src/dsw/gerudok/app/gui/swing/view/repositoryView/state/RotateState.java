package dsw.gerudok.app.gui.swing.view.repositoryView.state;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.commands.RotateSlotCommand;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;
import dsw.gerudok.app.repository.elements.Slot;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RotateState extends State{
    private PageView med;
    private boolean clicked;
    private Slot slotCheck;
    private List<Double> beforeAngles;
    private List<Double> afterAngles;

    public RotateState(PageView med) {
        this.med = med;
        clicked = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(med.getPage().getSelection().getActiveSlotList().isEmpty()){
            AppCore.getInstance().getiError().handleError("objectNotSelected");
            return;
        }
        beforeAngles = new ArrayList<>();
        afterAngles = new ArrayList<>();

        for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
            if(med.findBySlot(slot).isElementAt(e.getX(),e.getY())){
                clicked = true;
                slotCheck = slot;
                break;
            }
        }
        if(clicked){
            for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
               beforeAngles.add(slot.getAngle());
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(clicked) {
            boolean isLimited = false;
            double startAngle = slotCheck.getAngle();
            iSlot.changeSlot(slotCheck, "rotationTransform", e.getX(),e.getY());
            double newAngle = slotCheck.getAngle() - startAngle;
            for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
                if(slot != slotCheck){
                    if(iSlot.checkAngle(slot,slot.getAngle()+ newAngle)){
                        isLimited = true;

                    }

                }
            }
            if(isLimited){
                slotCheck.setAngle(startAngle);
                slotCheck.changedParameters();
            }else {
                for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
                    if(slot != slotCheck){
                        slot.setAngle(slot.getAngle() + newAngle);
                        slot.changedParameters();
                    }
                }
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(clicked){
            List<String> slotListName = new ArrayList<>();
            for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
                afterAngles.add(slot.getAngle());
                slotListName.add(slot.getName());
            }
            med.getDocumentView().getCommandManager().addCommand(new RotateSlotCommand(med.getPage(),slotListName,beforeAngles,afterAngles));
        }
        clicked = false;
    }
}

package dsw.gerudok.app.commands;

import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;

import java.util.ArrayList;
import java.util.List;

public class RotateSlotCommand extends AbstractCommand{

    private Page page;
    private List<String> slotListName;
    private List<Double> beforeAngles;
    private List<Double> afterAngles;

    public RotateSlotCommand(Page page, List<String> slotListName, List<Double> beforeAngles, List<Double> afterAngles) {
        this.page = page;
        this.slotListName = slotListName;
        this.beforeAngles = beforeAngles;
        this.afterAngles = afterAngles;
    }

    @Override
    public void doCommand() {
        int i = 0;
        List<Slot> slotList = new ArrayList<>();
        for(RuNode ruNode: page.getChildren()){
            for(String string: slotListName){
                if(ruNode.getName().equals(string)){
                    slotList.add((Slot) ruNode);
                }
            }
        }
        for(Slot slot: slotList){
            slot.setAngle(afterAngles.get(i++));
            slot.changedParameters();
        }
    }

    @Override
    public void undoCommand() {
        int i = 0;
        List<Slot> slotList = new ArrayList<>();
        for(RuNode ruNode: page.getChildren()){
            for(String string: slotListName){
                if(ruNode.getName().equals(string)){
                    slotList.add((Slot) ruNode);
                }
            }
        }
        for(Slot slot: slotList){
            slot.setAngle(beforeAngles.get(i++));
            slot.changedParameters();
        }
    }
}

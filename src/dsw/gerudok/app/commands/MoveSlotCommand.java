package dsw.gerudok.app.commands;

import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;

import java.util.ArrayList;
import java.util.List;

public class MoveSlotCommand extends AbstractCommand{

    private Page page;
    private List<String> slotListNames;
    private List<Integer> beforePositions;
    private List<Integer> afterPositions;

    public MoveSlotCommand(Page page,List<String> slotListNames, List<Integer> beforePositions, List<Integer> afterPositions) {
        this.page = page;
        this.slotListNames = slotListNames;
        this.beforePositions = beforePositions;
        this.afterPositions = afterPositions;
    }

    @Override
    public void doCommand() {
        int i = 0;
        List<Slot> slotList = new ArrayList<>();
        for(RuNode ruNode: page.getChildren()){
            for(String string: slotListNames){
                if(ruNode.getName().equals(string)){
                    slotList.add((Slot) ruNode);
                }
            }
        }
        for(Slot slot: slotList){
            slot.setPositionX(afterPositions.get(i++));
            slot.setPositionY(afterPositions.get(i++));
            slot.changedParameters();
        }
    }

    @Override
    public void undoCommand() {

        int i = 0;
        List<Slot> slotList = new ArrayList<>();
        for(RuNode ruNode: page.getChildren()){
            for(String string: slotListNames){
                if(ruNode.getName().equals(string)){
                    slotList.add((Slot) ruNode);
                }
            }
        }
        for(Slot slot: slotList){
            slot.setPositionX(beforePositions.get(i++));
            slot.setPositionY(beforePositions.get(i++));
            slot.changedParameters();
        }
    }
}

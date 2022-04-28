package dsw.gerudok.app.gui.swing.view.repositoryView.state;

import dsw.gerudok.app.commands.DeleteSlotCommand;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;
import dsw.gerudok.app.repository.elements.Slot;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteState extends State {

    private PageView med;

    public DeleteState(PageView med) {
        this.med = med;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(med.getSlotPainterList().isEmpty()){
            return;
        }else{
            List<Slot> list = new ArrayList<>();
            boolean isActive = false;
            for(int i = 0; i < med.getSlotPainterList().size(); i++){
                if(med.getSlotPainterList().get(i).isElementAt(e.getX(),e.getY())){
                    if(med.getPage().getSelection().getActiveSlotList().contains(med.getSlotPainterList().get(i).getSlot())){
                        for(Slot slot: med.getPage().getSelection().getActiveSlotList()){
                            list.add(slot);

                        }
                        isActive = true;
                    }else {
                        list.add(med.getSlotPainterList().get(i).getSlot());
                    }

                }
            }
            if(!list.isEmpty()){
               for(Slot slot: list){
                   med.getPage().removeChild(slot);
               }
               med.getDocumentView().getCommandManager().addCommand(new DeleteSlotCommand(med.getPage(),list));
               if(isActive){
                   med.getPage().getSelection().getActiveSlotList().clear();
               }
               ruView.updateTree();
            }
        }

    }
}

package dsw.gerudok.app.gui.swing.view.repositoryView.state;

import dsw.gerudok.app.gui.swing.view.editView.view.EditDialog;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;

import java.awt.event.MouseEvent;

public class EditState extends State{

    private PageView med;

    public EditState(PageView med) {
        this.med = med;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(med.getSlotPainterList() == null || med.getSlotPainterList().isEmpty())
            return;

        for(int i = 0; i < med.getSlotPainterList().size(); i++){
            if(med.getSlotPainterList().get(i).isElementAt(e.getX(),e.getY())){
                EditDialog editDialog = new EditDialog(MainFrame.getInstance(), true,med.getDocumentView().getProjectView().getProject(),med.getSlotPainterList().get(i).getSlot());
                break;
            }
        }
    }
}

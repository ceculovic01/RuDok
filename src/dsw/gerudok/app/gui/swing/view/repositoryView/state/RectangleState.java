package dsw.gerudok.app.gui.swing.view.repositoryView.state;

import dsw.gerudok.app.commands.AddSlotCommand;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;
import dsw.gerudok.app.repository.elements.Type;

import java.awt.event.MouseEvent;

public class RectangleState extends State{

    private PageView med;

    public RectangleState(PageView med) {
        this.med = med;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1){
            med.getDocumentView().getCommandManager().addCommand(new AddSlotCommand(med.getPage(),x,y, Type.Rectangle));
            ruView.updateTree();
        }
    }
}

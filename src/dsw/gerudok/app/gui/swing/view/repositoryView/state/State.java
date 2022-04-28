package dsw.gerudok.app.gui.swing.view.repositoryView.state;

import dsw.gerudok.app.slotHandler.ISlot;
import dsw.gerudok.app.slotHandler.SlotHandler;
import dsw.gerudok.app.gui.swing.view.RuView;
import dsw.gerudok.app.gui.swing.view.RuViewImplementation;

import java.awt.event.MouseEvent;

public class State {

    protected ISlot iSlot = new SlotHandler();
    protected RuView ruView = new RuViewImplementation();
    public void mousePressed(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
}

package dsw.gerudok.app.gui.swing.view;

import javax.swing.*;

public class PalleteToolBar extends JToolBar {
    public PalleteToolBar(){
        super(JToolBar.VERTICAL);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(MainFrame.getInstance().getActionManager().getHandCursorAction());
        add(MainFrame.getInstance().getActionManager().getRectangleAction());
        add(MainFrame.getInstance().getActionManager().getCircleAction());
        add(MainFrame.getInstance().getActionManager().getTriangleAction());
        add(MainFrame.getInstance().getActionManager().getgMoveAction());
        add(MainFrame.getInstance().getActionManager().getgResizeAction());
        add(MainFrame.getInstance().getActionManager().getGrotateAction());
        add(MainFrame.getInstance().getActionManager().getGdeleteAction());
        add(MainFrame.getInstance().getActionManager().getgEditAction());


    }
}

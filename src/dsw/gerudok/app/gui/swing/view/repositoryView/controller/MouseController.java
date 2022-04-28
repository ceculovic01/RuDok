package dsw.gerudok.app.gui.swing.view.repositoryView.controller;

import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseController extends MouseAdapter implements MouseMotionListener {

    private PageView pageView;

    public MouseController(PageView pageView){
        this.pageView = pageView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pageView.getStateManager().getCurrentState().mousePressed(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        pageView.getStateManager().getCurrentState().mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pageView.getStateManager().getCurrentState().mouseReleased(e);
    }
}

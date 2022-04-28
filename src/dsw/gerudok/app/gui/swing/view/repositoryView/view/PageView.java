package dsw.gerudok.app.gui.swing.view.repositoryView.view;

import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.repositoryView.controller.MouseController;
import dsw.gerudok.app.gui.swing.view.repositoryView.state.StateManager;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.painter.SlotPainter;
import dsw.gerudok.app.gui.swing.view.repositoryView.view.painter.slotPainterFactory.SlotPainterFactory;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.ruNodeObserver.ProjectObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PageView extends JPanel implements ProjectObserver {

    private Page page;
    private StateManager stateManager;
    private List<SlotPainter> slotPainterList;
    private DocumentView documentView;

    public PageView(DocumentView documentView,Page page) {
        page.addObserver(this);
        this.page = page;
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        this.addMouseListener(new MouseController(this));
        this.addMouseMotionListener(new MouseController(this));
        this.stateManager = new StateManager(this);
        this.setMaximumSize(this.getPreferredSize());
        slotPainterList = new ArrayList<>();
        this.documentView = documentView;


    }

    @Override
    public void update(Object object, String notification) {

        if(object instanceof Slot){
            Slot slot = (Slot) object;
            if(notification == "Dodavanje slota") {
                SlotPainter slotPainter = SlotPainterFactory.makeSlotPainter(slot);
                this.slotPainterList.add(slotPainter);
                slotPainter.getSlot().addObserver(this);
            }else if(notification == "Brisanje slota"){
                for(SlotPainter slotPainter: slotPainterList){
                    if(slotPainter.getSlot().getName().equals(slot.getName())){
                        this.slotPainterList.remove(slotPainter);
                        break;
                    }
                }
            }
        }
            if(notification == "Promena parametara"){
            this.getDocumentView().getProjectView().getProject().setChanged(true);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());
        }

        repaint();
    }

    public void removeObservers(){

        this.getPage().removeObserver(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setFont(new Font("Arial", Font.BOLD,12));
        graphics2D.drawString(this.page.getName(),(this.getWidth()/2)-(page.getName().length()/2),15);
        for(SlotPainter slotPainter: slotPainterList){
            if(page.getSelection().findSlotInList(slotPainter.getSlot()) != null){
                slotPainter.setPaint(Color.CYAN);
            }else {
                slotPainter.setPaint(Color.BLACK);
            }
            slotPainter.paint(graphics2D);
        }
        if(this.getPage().getSelection().isActive()){
            graphics2D.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
            graphics2D.drawRect(this.getPage().getSelection().getX(),this.getPage().getSelection().getY(),
                    this.getPage().getSelection().getWidth(), this.getPage().getSelection().getHeight());
        }

    }

    public SlotPainter findBySlot(Slot slot){
        if(!slotPainterList.isEmpty()){
            for(SlotPainter slotPainter: slotPainterList){
                if(slotPainter.getSlot().getName().equals(slot.getName())){
                    return  slotPainter;
                }
            }
        }
        return null;
    }


    public void startSelectState() {
        stateManager.setSelectState();
    }
    public void startRectangleState(){
        stateManager.setRectangleState();
    }
    public void startCircleState(){ stateManager.setCircleState();}
    public void startTriangleState(){ stateManager.setTriangleState();}
    public void startMoveState() {
        stateManager.setMoveState();
    }
    public void startResizeState(){
        stateManager.setResizeState();
    }
    public void startRotateState(){ stateManager.setRotateState();}
    public void startDeleteState(){ stateManager.setDeleteState();}
    public void startEditState(){ stateManager.setEditState();}

    public StateManager getStateManager() {
        return stateManager;
    }

    public Page getPage() {
        return page;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    public List<SlotPainter> getSlotPainterList() {
        return slotPainterList;
    }

    public DocumentView getDocumentView() {
        return documentView;
    }
}

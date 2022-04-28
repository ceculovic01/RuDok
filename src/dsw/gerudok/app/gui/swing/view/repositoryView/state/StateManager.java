package dsw.gerudok.app.gui.swing.view.repositoryView.state;

import dsw.gerudok.app.gui.swing.view.repositoryView.view.PageView;

public class StateManager {
    private State currentState;
    private SelectState selectState;
    private RectangleState rectangleState;
    private CircleState circleState;
    private TriangleState triangleState;
    private MoveState moveState;
    private ResizeState resizeState;
    private RotateState rotateState;
    private DeleteState deleteState;
    private EditState editState;

    public StateManager(PageView med)
    {
        selectState=new SelectState(med);
        rectangleState=new RectangleState(med);
        circleState = new CircleState(med);
        triangleState = new TriangleState(med);
        moveState = new MoveState(med);
        resizeState = new ResizeState(med);
        rotateState = new RotateState(med);
        deleteState = new DeleteState(med);
        editState = new EditState(med);
        currentState = selectState;
    }

    public void setCircleState() { currentState = circleState; }
    public void setRectangleState(){ currentState = rectangleState; }
    public void setSelectState(){ currentState = selectState; }
    public void setTriangleState(){ currentState = triangleState; }
    public void setMoveState(){ currentState = moveState; }
    public void setResizeState(){ currentState = resizeState; }
    public void setRotateState(){ currentState = rotateState; }
    public void setDeleteState(){ currentState = deleteState; }
    public void setEditState(){ currentState = editState; }
    public State getCurrentState() {
        return currentState;
    }
}

package dsw.gerudok.app.gui.swing.controller;

public class ActionManager {
    private NewProjectAction newProjectAction;
    private AboutAction aboutAction;
    private RemoveNodeAction removeNodeAction;
    private ShareAction shareAction;
    private GSelectedAction handCursorAction;
    private GRectangleAction rectangleAction;
    private GCircleAction circleAction;
    private GTriangleAction triangleAction;
    private GMoveAction gMoveAction;
    private GResizeAction gResizeAction;
    private GRotateAction GrotateAction;
    private GDeleteAction GdeleteAction;
    private GEditAction gEditAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private OpenAction openAction;
    private SaveAction saveAction;
    private SaveAsAction saveAsAction;
    private SwitchAction switchAction;

    public ActionManager() {
        initialise();
    }

    private void initialise(){
        newProjectAction = new NewProjectAction();
        aboutAction = new AboutAction();
        removeNodeAction = new RemoveNodeAction();
        shareAction = new ShareAction();
        handCursorAction = new GSelectedAction();
        rectangleAction = new GRectangleAction();
        circleAction = new GCircleAction();
        triangleAction = new GTriangleAction();
        gMoveAction = new GMoveAction();
        gResizeAction = new GResizeAction();
        GrotateAction = new GRotateAction();
        GdeleteAction = new GDeleteAction();
        gEditAction = new GEditAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        openAction = new OpenAction();
        saveAction = new SaveAction();
        saveAsAction = new SaveAsAction();
        switchAction = new SwitchAction();
    }

    public RemoveNodeAction getRemoveNodeAction() {
        return removeNodeAction;
    }

    public NewProjectAction getNewProjectAction() {
        return newProjectAction;
    }

    public void setNewProjectAction(NewProjectAction newProjectAction) {
        this.newProjectAction = newProjectAction;
    }

    public AboutAction getAboutAction() {
        return aboutAction;
    }

    public ShareAction getShareAction() {
        return shareAction;
    }

    public GSelectedAction getHandCursorAction() {
        return handCursorAction;
    }

    public GRectangleAction getRectangleAction() {
        return rectangleAction;
    }

    public GCircleAction getCircleAction() {
        return circleAction;
    }

    public GTriangleAction getTriangleAction() {
        return triangleAction;
    }

    public GRotateAction getGrotateAction() {
        return GrotateAction;
    }

    public GDeleteAction getGdeleteAction() {
        return GdeleteAction;
    }

    public GMoveAction getgMoveAction() {
        return gMoveAction;
    }

    public GResizeAction getgResizeAction() {
        return gResizeAction;
    }

    public GEditAction getgEditAction() {
        return gEditAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public OpenAction getOpenAction() {
        return openAction;
    }

    public SaveAction getSaveAction() {
        return saveAction;
    }

    public SaveAsAction getSaveAsAction() {
        return saveAsAction;
    }

    public SwitchAction getSwitchAction() {
        return switchAction;
    }
}

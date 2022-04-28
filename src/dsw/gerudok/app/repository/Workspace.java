package dsw.gerudok.app.repository;

import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;

public class Workspace extends RuNodeComposite {

    private String workspaceFilePath = "";

    public Workspace(String name) {
        super(name, null);

    }


    @Override
    public void addChild(RuNode child) {
        if (child != null &&  child instanceof Project){
            Project project = (Project) child;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child != null && child instanceof Project){
            this.notified(child, "Brisanje projekta");
            this.notifiedRuTreeItems(child,"Brisanje projekta - RuTreeItem");
            this.getChildren().remove(child);

        }
    }

    public String getWorkspaceFilePath() {
        return workspaceFilePath;
    }

    public void setWorkspaceFilePath(String workspaceFilePath) {
        this.workspaceFilePath = workspaceFilePath;
    }
}

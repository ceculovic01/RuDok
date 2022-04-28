package dsw.gerudok.app.repository;

import dsw.gerudok.app.core.Repository;

public class RepositoryImpl implements Repository {

    private Workspace root;

    public RepositoryImpl() {
        root = new Workspace("Workspace");
    }

    @Override
    public Workspace getWorkspace() {
        return root;
    }

    public Workspace getRoot() {
        return root;
    }

    public void setRoot(Workspace root) {
        this.root = root;
    }
}

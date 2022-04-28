package dsw.gerudok.app.gui.swing.view.repositoryView.model;

import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Project;

public class ShareChecker {
    Project project1;
    Document document;
    Project project2;

    public ShareChecker(Project project1, Document document, Project project2) {
        this.project1 = project1;
        this.document = document;
        this.project2 = project2;
    }

    public Project getProject1() {
        return project1;
    }

    public void setProject1(Project project1) {
        this.project1 = project1;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Project getProject2() {
        return project2;
    }

    public void setProject2(Project project2) {
        this.project2 = project2;
    }
}

package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.*;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractImageAction{

    int projectlbl = 0;
    int documentlbl = 0;
    int pageLbl = 0;

    public NewProjectAction(){

        putValue(SMALL_ICON,loadIcon("images/addbutton.png"));
        putValue(SHORT_DESCRIPTION, "Add");
        putValue(NAME,"Add");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        RuNode selectedRuNode = null;

        try{
            selectedRuNode = MainFrame.getInstance().getTree().getSelectedRuNode();

        }catch (NullPointerException e1){
            projectlbl++;
            Project p = new Project("Project " + projectlbl,  MainFrame.getInstance().getTree().getRuTreeRoot());
            Workspace workspace = (Workspace) MainFrame.getInstance().getTree().getRuTreeRoot();
            if(workspace.getChildren() != null){
                while(workspace.getChildren().contains(p)){
                    projectlbl++;
                    p.setName("Project " + projectlbl);
                }
            }
            MainFrame.getInstance().getTree().addProject(p);
            MainFrame.getInstance().getMainPanel().addProjectView(p);
        }

        if(selectedRuNode instanceof Workspace) {
            projectlbl++;
            Project p = new Project("Project " + projectlbl, selectedRuNode);
            if(((Workspace) selectedRuNode).getChildren() != null){
                while(((Workspace) selectedRuNode).getChildren().contains(p)){
                    projectlbl++;
                    p.setName("Project " + projectlbl);
                }

            }
            MainFrame.getInstance().getTree().addProject(p);
            MainFrame.getInstance().getMainPanel().addProjectView(p);
        }else if(selectedRuNode instanceof Project){
            documentlbl++;
            Document  d = new  Document("Document " + documentlbl, selectedRuNode);
            if(((Project) selectedRuNode).getChildren() != null){
                while(((Project) selectedRuNode).getChildren().contains(d)){
                    documentlbl++;
                    d.setName("Document " + documentlbl);
                }

            }
            MainFrame.getInstance().getTree().addDocument(d);
        }else if(selectedRuNode instanceof Document){
            pageLbl++;
            Page p = new Page("Page " + pageLbl, selectedRuNode);
            if(((Document) selectedRuNode).getChildren() != null){
                while(((Document) selectedRuNode).getChildren().contains(p)){
                    pageLbl++;
                    p.setName("Page " + pageLbl);
                }

            }
            MainFrame.getInstance().getTree().addPage(p);

        }else if(selectedRuNode instanceof Page){
            AppCore.getInstance().getiError().handleError("addToPage");
        }else if(selectedRuNode instanceof Slot){
            AppCore.getInstance().getiError().handleError("addToSlot");
        }
    }
}

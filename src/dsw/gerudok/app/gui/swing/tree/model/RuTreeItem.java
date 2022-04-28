package dsw.gerudok.app.gui.swing.tree.model;

import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.elements.Slot;
import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;
import dsw.gerudok.app.ruNodeObserver.RuTreeItemObserver;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuTreeItem extends DefaultMutableTreeNode implements RuTreeItemObserver {

    private String name;
    private RuNode nodeModel;

    public RuTreeItem(RuNode nodeModel) {
        this.nodeModel = nodeModel;
        this.name = nodeModel.getName();
    }

    public RuTreeItem findByName(String name){

        if(children == null){
            return  null;
        }

        Iterator iterator = children.iterator();
        while(iterator.hasNext()){
            RuTreeItem current = (RuTreeItem) iterator.next();
            if(current.getNodeModel().getName().equals(name)){
                return  current;
            }

        }
        return  null;


    }
    @Override
    public boolean getAllowsChildren() {
        if(nodeModel instanceof RuNodeComposite)
            return true;
        return false;
    }

    @Override
    public boolean isLeaf() {
        if(nodeModel instanceof RuNodeComposite)
            return false;
        return true;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof RuTreeItem) {
            RuTreeItem otherObj = (RuTreeItem) obj;
            return this.nodeModel.equals(otherObj.nodeModel);
        }
        return false;
    }

    private int findIndexByChild(RuTreeItem node){
        if(nodeModel instanceof RuNodeComposite){

            Iterator childrenIterator = children.iterator();
            TreeNode current;
            int i = 0;

            while (childrenIterator.hasNext()){
                current = (TreeNode) childrenIterator.next();
                if (current.equals(node))
                    return  i;
                i++;
            }
        }

        return -1;

    }
    @Override
    public void removeAllObservers(){
        if(this.getChildCount() > 0) {
            if (this.getNodeModel() instanceof Page) {
                Iterator iterator = children.iterator();
                while (iterator.hasNext()) {
                    RuTreeItem child = (RuTreeItem) iterator.next();
                    child.getNodeModel().removeRuTreeItemObserver(child);
                }
            }else if(this.getNodeModel() instanceof  Document){
                Iterator iterator = children.iterator();
                while(iterator.hasNext()){
                    RuTreeItem child = (RuTreeItem) iterator.next();
                    if(child.getChildCount() > 0){
                        Iterator iterator2 = child.children.iterator();
                        while(iterator2.hasNext()){
                            RuTreeItem child2 = (RuTreeItem) iterator2.next();
                            child2.getNodeModel().removeRuTreeItemObserver(child2);
                        }
                    }
                    child.getNodeModel().removeRuTreeItemObserver(child);
                }
            }else if(this.getNodeModel() instanceof  Project){
                Iterator iterator = children.iterator();
                while(iterator.hasNext()){
                    RuTreeItem child = (RuTreeItem) iterator.next();
                    if(child.getChildCount() > 0){
                        Iterator iterator2 = child.children.iterator();
                        while(iterator2.hasNext()){
                            RuTreeItem child2 = (RuTreeItem) iterator2.next();
                            if(child2.getChildCount() > 0){
                                Iterator iterator3 = child2.children.iterator();
                                while (iterator3.hasNext()){
                                    RuTreeItem child3 = (RuTreeItem) iterator3.next();
                                    child3.getNodeModel().removeRuTreeItemObserver(child3);
                                }
                            }

                            child2.getNodeModel().removeRuTreeItemObserver(child2);
                        }
                    }
                    child.getNodeModel().removeRuTreeItemObserver(child);
                }
            }
        }
        return;
    }
    @Override
    public boolean containsObservers(){
        if(this.getChildCount() > 0) {
            Iterator iterator = children.iterator();
            while (iterator.hasNext()) {
                RuTreeItem child = (RuTreeItem) iterator.next();
                if (child.getName().contains("*")) {
                    return true;
                }
            }
        }
        return  false;
    }
    @Override
    public void addObservers(){
        if(nodeModel instanceof  Project){
            this.getNodeModel().addRuTreeItemObserver(this);

        }else if(nodeModel instanceof Document){
            Document document = (Document) nodeModel;
            if(!document.getChildren().isEmpty()){
                Iterator iterator = this.children.iterator();
                while (iterator.hasNext()){
                    RuTreeItem pageRTI = (RuTreeItem) iterator.next();
                    if(pageRTI.children != null){
                        Iterator iterator1 = pageRTI.children.iterator();
                        while(iterator1.hasNext()){
                            RuTreeItem slotRTI = (RuTreeItem) iterator1.next();
                            slotRTI.getNodeModel().addRuTreeItemObserver(slotRTI);
                        }
                    }
                    pageRTI.getNodeModel().addRuTreeItemObserver(pageRTI);
                }
            }
            this.getNodeModel().addRuTreeItemObserver(this);
        }else  if(nodeModel instanceof  Page){
            Page page = (Page) nodeModel;
            if(!page.getChildren().isEmpty()){
                Iterator iterator = this.children.iterator();
                while(iterator.hasNext()){
                    RuTreeItem slotRTI = (RuTreeItem) iterator.next();
                    slotRTI.getNodeModel().addRuTreeItemObserver(slotRTI);
                }
            }
            this.getNodeModel().addRuTreeItemObserver(this);
        }else if(nodeModel instanceof  Slot){
            this.getNodeModel().addRuTreeItemObserver(this);
        }
    }

    public void addMissedChildren(){
        if(nodeModel instanceof  Document){
            Document document = (Document) nodeModel;
            if(!document.getChildren().isEmpty()){
                for(RuNode page: document.getChildren()){
                    Page pageCmp = (Page) page;
                    RuTreeItem pageRTI = new RuTreeItem(pageCmp);
                    this.add(pageRTI);
                    if(!pageCmp.getChildren().isEmpty()){
                        for(RuNode slot: pageCmp.getChildren()){
                            RuTreeItem slotRTI = new RuTreeItem(slot);
                            pageRTI.add(slotRTI);
                        }
                    }

                }
            }
        }
        return;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.nodeModel.setName(name);
    }

    public void setRTIName(String name){
        this.name = name;
    }

    public RuNode getNodeModel() {
        return nodeModel;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(Object object, String notification) {
        if(object instanceof Page) {
            if (notification == "Dodavanje stranice - RuTreeItem") {
                Page page = (Page) object;
                if (this.findByName(page.getName()) == null) {
                    RuTreeItem pageRTI = new RuTreeItem(page);
                    this.add(pageRTI);
                    page.addRuTreeItemObserver(pageRTI);

                } else {
                    return;
                }


            }
            if(notification == "Brisanje stranice - RuTreeItem"){
                Page page = (Page) object;
                RuTreeItem pageRTI = findByName(page.getName());
                if(pageRTI == null){
                    return;
                }else{
                    pageRTI.removeAllObservers();
                    pageRTI.getNodeModel().removeRuTreeItemObserver(pageRTI);
                    int index = this.findIndexByChild(pageRTI);
                    if(index != -1)
                        this.remove(index);
                }
            }
            if(notification == "Promena imena - RuTreeItem"){
                this.setRTIName(this.getNodeModel().getName());
            }
        }
        if(object instanceof Document){
            if(notification == "Brisanje dokumenta - RuTreeItem"){
                Document document = (Document) object;
                RuTreeItem workspace = (RuTreeItem) this.getParent();
                Iterator iterator = workspace.children.iterator();
                while(iterator.hasNext()){
                    RuTreeItem projectRTI = (RuTreeItem) iterator.next();
                    if(projectRTI.getName().equals(this.getName()) || projectRTI.children == null){
                        continue;
                    }
                    if(!projectRTI.children.isEmpty()){
                        Iterator iterator2 = projectRTI.children.iterator();
                        while(iterator2.hasNext()){
                            RuTreeItem documentRTI = (RuTreeItem) iterator2.next();
                            if(documentRTI.getName().equals(document.getName() + "*")){
                                documentRTI.removeAllObservers();
                                documentRTI.getNodeModel().removeRuTreeItemObserver(documentRTI);
                                int index = projectRTI.findIndexByChild(documentRTI);
                                projectRTI.remove(index);
                                if(((Project) projectRTI.getNodeModel()).getChildren().contains(document)){
                                    ((Project) projectRTI.getNodeModel()).getChildren().remove(document);
                                }
                                break;
                            }
                        }

                    }

                }


            }
            if(notification == "Promena imena - RuTreeItem"){
                this.setRTIName(this.getNodeModel().getName() + "*");
            }
        }
        if(object instanceof Slot){
            if(notification == "Dodavanje slota - RuTreeItem"){
                Slot slot = (Slot) object;
                if (this.findByName(slot.getName()) == null){
                    RuTreeItem slotRTI = new RuTreeItem(slot);
                    this.add(slotRTI);
                    slot.addRuTreeItemObserver(slotRTI);


                }else{
                    return;
                }
            }
            if(notification == "Brisanje slota - RuTreeItem"){
                Slot slot = (Slot) object;
                RuTreeItem slotRTI = findByName(slot.getName());
                if(slot == null || slotRTI == null){
                    return;
                }else{
                    slotRTI.getNodeModel().removeRuTreeItemObserver(slotRTI);

                    int index = this.findIndexByChild(slotRTI);
                    if(index != -1)
                        this.remove(index);
                }
            }
            if(notification == "Promena imena - RuTreeItem"){
                this.setRTIName(this.getNodeModel().getName());
            }

        }
        if(object instanceof Project){
            if(notification == "Brisanje projekta - RuTreeItem"){
                Project project = (Project) object;
                if(project.getChildren().isEmpty())
                    return;
                RuTreeItem projectRTI = this.findByName(project.getName());
                Iterator iterator = projectRTI.children.iterator();
                List<RuTreeItem> documentsRTI = new ArrayList<>();
                while(iterator.hasNext()){
                    RuTreeItem documentRTI = (RuTreeItem) iterator.next();
                    if(documentRTI.getName().contains("*")){
                        documentsRTI.add(documentRTI);
                    }
                }
                if(documentsRTI.isEmpty()){
                    return;
                }else {
                    Iterator iterator2 = this.children.iterator();
                    while(iterator2.hasNext()){
                        RuTreeItem project2RTI = (RuTreeItem) iterator2.next();
                        if(!project2RTI.equals(projectRTI)){
                            for(RuTreeItem documentListRTI: documentsRTI){
                                if(project2RTI.findByName(documentListRTI.getNodeModel().getName()) != null){
                                    RuTreeItem delete = project2RTI.findByName(documentListRTI.getNodeModel().getName());
                                    Project project2 = (Project) project2RTI.getNodeModel();
                                    project2.removeChild(documentListRTI.getNodeModel());
                                    project2RTI.remove(delete);

                                }
                            }
                        }
                    }
                }
            }
            if(notification == "PromenaTrue"){
                this.setRTIName("*" + this.getNodeModel().getName());
            }
            if(notification == "PromenaFalse"){
                this.setRTIName(this.getNodeModel().getName());
            }
        }


    }

    @Override
    public String getRuTreeItemParenName() {
        String str = "";
        if(this.getNodeModel() instanceof Project){
            str = this.getName();
        }
        if(this.getNodeModel() instanceof Document){
            str = this.getParent().toString();
        }
        if(this.getNodeModel() instanceof  Page){
            str =  this.getParent().getParent().toString();
        }
        if(this.getNodeModel() instanceof Slot){
            str =  this.getParent().getParent().getParent().toString();
        }
        return str;

    }


}

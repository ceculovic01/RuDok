package dsw.gerudok.app.repository.node;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode {
    private List<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();

    }

    public abstract void addChild(RuNode child);

    public abstract void removeChild(RuNode child);

    public List<RuNode> getChildren() {
        return children;
    }

    public void setChildren(List<RuNode> children) {
        this.children = children;
    }



}

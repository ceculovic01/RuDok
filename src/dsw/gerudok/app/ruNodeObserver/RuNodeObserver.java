package dsw.gerudok.app.ruNodeObserver;

public interface RuNodeObserver {
    void notified(Object ruNode, String notification);
    void addObserver(ProjectObserver observer);
    void removeObserver(ProjectObserver observer);
    void notifiedRuTreeItems(Object ruNode, String notification);
    void addRuTreeItemObserver(RuTreeItemObserver observer);
    void removeRuTreeItemObserver(RuTreeItemObserver observer);
}

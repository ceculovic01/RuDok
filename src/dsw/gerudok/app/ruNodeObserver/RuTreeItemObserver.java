package dsw.gerudok.app.ruNodeObserver;

public interface RuTreeItemObserver {
    void update(Object object, String notification);
    String getRuTreeItemParenName();
    void addObservers();
    void removeAllObservers();
    boolean containsObservers();
}

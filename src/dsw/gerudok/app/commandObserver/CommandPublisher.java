package dsw.gerudok.app.commandObserver;

public interface CommandPublisher {
    void notified(String notification);
    void addSubscriber(CommandSubscriber commandSubscriber);
    void removeSubscriber(CommandSubscriber commandSubscriber);
}

package dsw.gerudok.app.errorObserver;

public interface ErrorPublisher {
    void notified(String notification);
    void addSubscriber(ErrorSubscriber errorSubscriber);
    void removeSubscriber(ErrorSubscriber errorSubscriber);
}

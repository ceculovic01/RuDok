package dsw.gerudok.app.core;

import dsw.gerudok.app.errorObserver.ErrorSubscriber;
import dsw.gerudok.app.commandObserver.CommandSubscriber;

public interface Gui extends ErrorSubscriber, CommandSubscriber {
    void start();
}

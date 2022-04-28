package dsw.gerudok.app.core;

import dsw.gerudok.app.commandObserver.CommandPublisher;

public interface ICommand extends CommandPublisher {
    void setCommandCondition(String notification);
}

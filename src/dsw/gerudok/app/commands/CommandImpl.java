package dsw.gerudok.app.commands;

import dsw.gerudok.app.commandObserver.CommandSubscriber;
import dsw.gerudok.app.core.ICommand;

public class CommandImpl implements ICommand {

    private CommandSubscriber commandSubscriber;

    public CommandImpl() {
    }

    @Override
    public void notified(String notification) {
        commandSubscriber.updateCommand(notification);
    }

    @Override
    public void addSubscriber(CommandSubscriber commandSubscriber) {
        this.commandSubscriber = commandSubscriber;
    }

    @Override
    public void removeSubscriber(CommandSubscriber commandSubscriber) {

    }

    @Override
    public void setCommandCondition(String notification) {
        notified(notification);
    }
}

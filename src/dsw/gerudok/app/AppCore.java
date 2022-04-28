package dsw.gerudok.app;

import dsw.gerudok.app.serializer.Serializer;
import dsw.gerudok.app.commands.CommandImpl;
import dsw.gerudok.app.core.*;
import dsw.gerudok.app.errorHandler.ErrorHandler;
import dsw.gerudok.app.gui.swing.SwingGui;
import dsw.gerudok.app.repository.RepositoryImpl;

public class AppCore extends ApplicationFramework {


    private static AppCore instance;

    @Override
    public void run() {
        this.gui.start();
    }

    private AppCore() {

    }

    public static AppCore getInstance() {
        if (instance == null) {
            instance = new AppCore();
        }
        return instance;
    }

    public static void main(String[] args) {
        Repository repository = new RepositoryImpl();
        Gui gui = new SwingGui(repository);
        IError iError = new ErrorHandler();
        ICommand iCommand = new CommandImpl();
        ISerializer iSerializer = new Serializer();
        ApplicationFramework appCore = AppCore.getInstance();
        appCore.initialise(gui, repository,iError, iCommand, iSerializer);
        appCore.run();
    }
}

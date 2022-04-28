package dsw.gerudok.app.core;

public abstract class ApplicationFramework {

    protected Gui gui;
    protected Repository repository;
    protected IError iError;
    protected ICommand iCommand;
    protected ISerializer iSerializer;

    public abstract void run();
    public void initialise(Gui gui, Repository repository,IError iError,ICommand iCommand, ISerializer iSerializer){
        this.setGui(gui);
        this.setRepository(repository);
        this.setiError(iError);
        this.iError.addSubscriber(gui);
        this.setiCommand(iCommand);
        this.iCommand.addSubscriber(gui);
        this.setiSerializer(iSerializer);
    }

    public ApplicationFramework() {
    }

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public IError getiError() {
        return iError;
    }

    public void setiError(IError iError) {
        this.iError = iError;
    }

    public ICommand getiCommand() {
        return iCommand;
    }

    public void setiCommand(ICommand iCommand) {
        this.iCommand = iCommand;
    }

    public ISerializer getiSerializer() {
        return iSerializer;
    }

    public void setiSerializer(ISerializer iSerializer) {
        this.iSerializer = iSerializer;
    }
}
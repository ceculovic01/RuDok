package dsw.gerudok.app.commands;

import dsw.gerudok.app.AppCore;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<AbstractCommand> commands = new ArrayList<AbstractCommand>();
    private int currentCommand = 0;


    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }


    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            AppCore.getInstance().getiCommand().setCommandCondition("undoEnabled");
        }
        if(currentCommand==commands.size()){
            AppCore.getInstance().getiCommand().setCommandCondition("doDisabled");
        }
    }


    public void undoCommand(){
        if(currentCommand > 0){
            AppCore.getInstance().getiCommand().setCommandCondition("doEnabled");
            commands.get(--currentCommand).undoCommand();
        }
        if(currentCommand==0){
            AppCore.getInstance().getiCommand().setCommandCondition("undoDisabled");

        }
    }
}

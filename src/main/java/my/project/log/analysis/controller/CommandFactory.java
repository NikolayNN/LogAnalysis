package my.project.log.analysis.controller;

import my.project.log.analysis.controller.commands.*;
import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public class CommandFactory {

    private View view;
    private static Command[] availableCommands;

    public CommandFactory(View view, LogAnalyser logAnalyser) {
        this.view = view;
        availableCommands = new Command[]{
                new LogGroupCommand(view, logAnalyser),
                new SetOutFileCommand(view, logAnalyser),
                new SetInDirectoryCommand(view),
                new SetCountThreadCommand(view),
                new HelpCommand(view)
        };
    }

    public Command createCommand(String source) {
        final int COMMAND_NAME_POSITION = 0;

        String commandName = source.split(" ")[COMMAND_NAME_POSITION];
        for (Command command : availableCommands) {
            if (command.getName().equalsIgnoreCase(commandName)) {
                command.setup(source);
                return command;
            }
        }
        return new WrongCommand(view);
    }

    public static String printAvailableCommands() {
        StringBuilder result = new StringBuilder();
        for (Command command : availableCommands) {
            result.append(command.getName());
            result.append("\n");
            result.append("\t");
            result.append(command.getDescription());
            result.append("\n");
        }
        return result.toString();
    }

}

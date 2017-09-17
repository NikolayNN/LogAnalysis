package my.project.log.analysis.controller;

import my.project.log.analysis.controller.commands.Command;
import my.project.log.analysis.controller.commands.LogGroupCommand;
import my.project.log.analysis.controller.commands.WrongCommand;
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
            new LogGroupCommand(view, logAnalyser)
        };
    }

    public Command createCommand(String source) {
        String commandStr = source.split(" ")[0];
        for (Command command : availableCommands) {
            if (command.getName().equalsIgnoreCase(commandStr)) {
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

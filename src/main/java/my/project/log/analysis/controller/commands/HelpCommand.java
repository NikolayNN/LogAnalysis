package my.project.log.analysis.controller.commands;

import my.project.log.analysis.controller.CommandFactory;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public class HelpCommand extends Command {

    public HelpCommand(View view) {
        super(view);
    }

    @Override
    public String getName() {
        return Utils.getProperty("command.help.name");
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void execute() {
        view.write(CommandFactory.printAvailableCommands());
    }
}

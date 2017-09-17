package my.project.log.analysis.controller.commands;

import my.project.log.analysis.controller.CommandFactory;
import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public class HelpCommand extends Command {

    private final String NAME = COMMAND_HELP;

    public HelpCommand(View view) {
        super(view);
    }

    @Override
    public String getName() {
        return NAME;
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

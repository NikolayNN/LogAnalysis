package my.project.log.analysis.controller.commands;

import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public class WrongCommand extends Command {
    public WrongCommand(View view) {
        super(view);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute() {
        view.write("wrong command");
    }
}

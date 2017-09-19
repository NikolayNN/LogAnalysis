package my.project.log.analysis.controller.commands;

import my.project.log.analysis.exception.WrongCommandFormatException;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public class SetCountThreadCommand extends Command{

    private final int EXPECTED_PARAMETER_COUNT = 2;

    public SetCountThreadCommand(View view) {
        super(view);
    }

    @Override
    public void execute() {
        threadCount = getCountThread();
        view.write(String.format("count of thread set %s", threadCount));
    }

    private int getCountThread(){
        final int VALUE_POSITION_IN_COMMAND = 1;
        checkNumberParameters(EXPECTED_PARAMETER_COUNT);
        try {
            return Integer.parseInt(getParameterByPosition(VALUE_POSITION_IN_COMMAND));
        }catch (NumberFormatException ex){
            throw new WrongCommandFormatException(ex.getMessage(), ex);
        }
    }

    @Override
    public String getName() {
        return Utils.getProperty("command.set-count-thread.name");
    }

    @Override
    public String getDescription() {
        return Utils.getProperty("command.set-count-thread.description");
    }
}

package my.project.log.analysis.controller.commands;

import my.project.log.analysis.exception.WrongCommandFormatException;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public class SetCountThreadCommand extends Command{

    private final int COUNT_PARAMETER = 2;

    public SetCountThreadCommand(View view) {
        super(view);
    }

    @Override
    public void execute() {
        threadCount = getCountThread();
        view.write(String.format("count of thread set %s", threadCount));
    }

    private int getCountThread(){
        final int POSITION_IN_COMMAND = 1;
        checkNumberParameters(COUNT_PARAMETER);
        try {
            return Integer.parseInt(getParameterByPosition(POSITION_IN_COMMAND));
        }catch (NumberFormatException ex){
            throw new WrongCommandFormatException(ex.getMessage(), ex);
        }
    }

    @Override
    public String getName() {
        return COMMAND_SET_COUNT_THREAD;
    }

    @Override
    public String getDescription() {
        return Utils.getProperty("command.set-count-thread.description");
    }
}

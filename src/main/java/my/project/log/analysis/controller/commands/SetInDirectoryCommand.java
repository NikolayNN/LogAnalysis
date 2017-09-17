package my.project.log.analysis.controller.commands;

import my.project.log.analysis.exception.WrongCommandFormatException;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nikolay Horushko
 */
public class SetInDirectoryCommand extends Command {

    private final Pattern pathToFilePattern = Pattern.compile("(\\w{1}:\\/([A-z]|[0-9]|\\s|\\/)+)");
    private final String DESCRIPTION = Utils.getProperty("command.set-in-dir.description");

    public SetInDirectoryCommand(View view) {
        super(view);
    }

    @Override
    public void execute() {
        pathToInputDirectory = getPathToFile();
        view.write(String.format("set new path for input directory %s", pathToInputDirectory));
    }

    private String getPathToFile() {
        final int EXPECTED_NUMBER_PARAMETERS = 2;
        final int POSITION_SECOND_PARAMETER = 1;
        final String PARAMETERS_SEPARATOR = " ";

        String[] splittedCommand = command.split(PARAMETERS_SEPARATOR);
        if (splittedCommand.length != EXPECTED_NUMBER_PARAMETERS) {
            throw new WrongCommandFormatException(String.format("ERROR. Command contains %s parameters, " +
                    "but expect 2", splittedCommand.length));
        }
        String pathToFile = splittedCommand[POSITION_SECOND_PARAMETER];
        Matcher matcher = pathToFilePattern.matcher(pathToFile);
        if (matcher.matches()) {
            return pathToFile;
        } else {
            throw new WrongCommandFormatException(String.format("ERROR. You entered incorrect path to file"));
        }
    }

    @Override
    public String getName() {
        return COMMAND_SET_IN_DIR;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}

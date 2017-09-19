package my.project.log.analysis.controller.commands;

import my.project.log.analysis.exception.WrongCommandFormatException;
import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nikolay Horushko
 */
public class SetOutFileCommand extends Command {

    final int EXPECTED_PARAMETER_COUNT = 2;
    private final Pattern pathToFilePattern = Pattern.compile("(\\w{1}:\\/([A-z]|[0-9]|\\s|\\/)+\\.\\w+)");

    public SetOutFileCommand(View view, LogAnalyser logAnalyser) {
        super(view, logAnalyser);
    }

    @Override
    public void execute() {
        pathToOutputFile = getPathToFile();
        logAnalyser.getLogFileWriter().setPathToOutputFile(pathToOutputFile);
        view.write(String.format("set new path for output file %s", pathToOutputFile));
    }

    private String getPathToFile() {

        final int VALUE_POSITION_IN_COMMAND = 1;

        checkNumberParameters(EXPECTED_PARAMETER_COUNT);
        String pathToFile = getParameterByPosition(VALUE_POSITION_IN_COMMAND);
        Matcher matcher = pathToFilePattern.matcher(pathToFile);
        if (matcher.matches()) {
            return pathToFile;
        } else {
            throw new WrongCommandFormatException(String.format("ERROR. You entered incorrect path to file"));
        }
    }

    @Override
    public String getName() {
        return Utils.getProperty("command.set-out-file.name");
    }

    @Override
    public String getDescription() {
        return Utils.getProperty("command.set-out-file.description");
    }
}

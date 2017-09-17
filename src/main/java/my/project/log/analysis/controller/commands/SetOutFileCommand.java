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

    private final String COMMAND_DESCRIPTION = Utils.getProperty("command.set-out-file.description");
    private final Pattern pathToFilePattern = Pattern.compile("(\\w{1}:\\/([A-z]|[0-9]|\\s|\\/)+\\.\\w+)");

    public SetOutFileCommand(View view, LogAnalyser logAnalyser) {
        super(view, logAnalyser);
    }


    @Override
    public void execute() {
        pathToOutputFile = getPathToFile();
        logAnalyser.getLogFileWriter().setPathToOutputFile(pathToOutputFile);
        view.write(String.format("set new path for input file %s", pathToOutputFile));
    }

    private String getPathToFile() {
        String[] splittedCommand = command.split(" ");
        if (splittedCommand.length != 2) {
            throw new WrongCommandFormatException(String.format("ERROR. Command contains %s parameters, but expect 2", splittedCommand.length));
        }
        String pathToFile = splittedCommand[1];
        Matcher matcher = pathToFilePattern.matcher(pathToFile);
        if (matcher.matches()) {
            return pathToFile;
        } else {
            throw new WrongCommandFormatException(String.format("ERROR. You entered incorrect path to file"));
        }
    }

    @Override
    public String getName() {
        return COMMAND_SET_OUT_FILE;
    }

    @Override
    public String getDescription() {
        return COMMAND_DESCRIPTION;
    }
}

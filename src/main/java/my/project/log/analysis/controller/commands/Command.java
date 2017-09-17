package my.project.log.analysis.controller.commands;

import my.project.log.analysis.exception.WrongCommandFormatException;
import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.View;

import java.util.regex.Matcher;

/**
 * @author Nikolay Horushko
 */
public abstract class Command {

    protected static String pathToOutputFile;
    protected static String pathToInputDirectory;
    protected static int threadCount;
    protected final String COMMAND_HELP = Utils.getProperty("command.help.name");
    protected final String COMMAND_SET_OUT_FILE = Utils.getProperty("command.set-out-file.name");
    protected final String COMMAND_SET_IN_DIR = Utils.getProperty("command.set-in-dir.name");
    protected final String COMMAND_LOG_ANALYSIS = Utils.getProperty("command.analyse.name");
    protected final String COMMAND_SET_COUNT_THREAD = Utils.getProperty("command.set-count-thread.name");
    protected final String PARAMETERS_SEPARATOR = " ";

    protected View view;
    protected LogAnalyser logAnalyser;
    protected String command;

    static {
        pathToOutputFile = Utils.getProperty("default.out.file");
        pathToInputDirectory = Utils.getProperty("default.in.dir");
        threadCount = Integer.parseInt(Utils.getProperty("default.log-analysis.count.thread"));
    }


    public Command(View view) {
        this.view = view;
    }

    public Command(View view, LogAnalyser logAnalyser) {
        this.view = view;
        this.logAnalyser = logAnalyser;
    }

    protected void checkNumberParameters(int expectedNumberParameters) {
        String[] splittedCommand = command.split(PARAMETERS_SEPARATOR);
        if (splittedCommand.length != expectedNumberParameters) {
            throw new WrongCommandFormatException(String.format("ERROR. Command contains %s parameters, " +
                    "but expect %s", splittedCommand.length, expectedNumberParameters));
        }
    }

    protected String getParameterByPosition(int position){
        String[] splittedCommand = command.split(PARAMETERS_SEPARATOR);
        return splittedCommand[position];
    }

    public void setup(String command) {
        this.command = command;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute();
}

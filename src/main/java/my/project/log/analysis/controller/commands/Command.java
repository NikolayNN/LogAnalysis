package my.project.log.analysis.controller.commands;

import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public abstract class Command {

    protected static String pathToOutputFile;
    protected static String pathToInputDirectory;
    protected final String COMMAND_HELP = Utils.getProperty("command.help.name");
    protected final String COMMAND_SET_OUT_FILE = Utils.getProperty("command.set-out-file.name");
    protected final String COMMAND_SET_IN_DIR = Utils.getProperty("command.set-in-dir.name");
    protected final String COMMAND_LOG_ANALYSIS = Utils.getProperty("command.analyse.name");

    protected View view;
    protected LogAnalyser logAnalyser;
    protected String command;

    static {
        pathToOutputFile = Utils.getProperty("default.out.file");
        pathToInputDirectory = Utils.getProperty("default.in.dir");
    }


    public Command(View view){
        this.view = view;
    }

    public Command(View view, LogAnalyser logAnalyser) {
        this.view = view;
        this.logAnalyser = logAnalyser;
    }

    public void setup(String command){
        this.command = command;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute();
}

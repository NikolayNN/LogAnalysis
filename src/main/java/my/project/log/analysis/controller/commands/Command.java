package my.project.log.analysis.controller.commands;

import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public abstract class Command {
    protected final String COMMAND_HELP = Utils.getProperty("command.help");
    protected final String COMMAND_SET_OUT_DIR = Utils.getProperty("command.set-out-dir");
    protected final String COMMAND_SET_IN_DIR = Utils.getProperty("command.set-in-dir");
    protected final String COMMAND_LOG_ANALYSIS = Utils.getProperty("command.analyse");
    protected String PATH_OUT_DIR = Utils.getProperty("default.out.dir");

    protected View view;
    protected LogAnalyser logAnalyser;
    protected String command;


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

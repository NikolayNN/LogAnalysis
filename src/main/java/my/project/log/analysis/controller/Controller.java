package my.project.log.analysis.controller;

import my.project.log.analysis.controller.commands.Command;
import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.service.filewriter.LogFileWriter;
import my.project.log.analysis.service.filewriter.LogFileWriterSimply;
import my.project.log.analysis.service.message.parcer.LogMessageParser;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.Console;
import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public class Controller {

    public void start(){
        View view = new Console();
        LogMessageParser logMessageParser = new LogMessageParser();
        LogFileWriter logFileWriter = new LogFileWriterSimply(Utils.getProperty("default.out.file"));
        LogAnalyser logAnalyser = new LogAnalyser(logMessageParser, logFileWriter);
        CommandFactory commandFactory = new CommandFactory(view, logAnalyser);
        view.write("Hello!");
        view.write("Welcome to the best Log Analyzer :)");
        view.write("Input 'help' for help.");
        while (true){
            try{
                view.write("Input your command..");
                String input = view.read();
                Command command = commandFactory.createCommand(input);
                command.execute();
            }catch (Exception e){
                view.write(e.getMessage());
                e.printStackTrace();
            }
        }
    }



}

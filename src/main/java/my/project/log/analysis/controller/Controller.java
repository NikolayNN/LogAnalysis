package my.project.log.analysis.controller;

import my.project.log.analysis.controller.commands.Command;
import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.service.filewriter.ConcurrentLogFileWriter;
import my.project.log.analysis.service.filewriter.LogFileWriter;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.Console;
import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public class Controller {

    public void start(){
        View view = new Console();
        LogFileWriter logFileWriter = new ConcurrentLogFileWriter(Utils.getProperty("default.out.file"));
        LogAnalyser logAnalyser = new LogAnalyser(logFileWriter);
        CommandFactory commandFactory = new CommandFactory(view, logAnalyser);

        view.write("Hello!\n Welcome to the best Log Analyzer :)\n Input 'help' for help.\n");
        while (true){
            try{
                view.write("Input your command..");
                String input = view.read();
                Command command = commandFactory.createCommand(input);
                command.execute();
            }catch (Exception e){
                view.write(e.getMessage());
            }
        }
    }



}

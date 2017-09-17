package my.project.log.analysis.controller;

import my.project.log.analysis.controller.commands.Command;
import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.service.filewriter.FileWriter;
import my.project.log.analysis.service.filewriter.FileWriterImpl;
import my.project.log.analysis.service.message.parcer.LogMessageParcer;
import my.project.log.analysis.service.pathreader.PathReader;
import my.project.log.analysis.service.pathreader.impl.PathReaderImpl;
import my.project.log.analysis.view.Console;
import my.project.log.analysis.view.View;

/**
 * @author Nikolay Horushko
 */
public class Controller {

    public void start(){
        View view = new Console();
        PathReader pathReader = new PathReaderImpl();
        LogMessageParcer logMessageParcer = new LogMessageParcer();
        FileWriter fileWriter = new FileWriterImpl();
        LogAnalyser logAnalyser = new LogAnalyser(pathReader, logMessageParcer, fileWriter);
        CommandFactory commandFactory = new CommandFactory(view, logAnalyser);
        view.write("Hello!");
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

package my.project.log.analysis.controller.commands;

import my.project.log.analysis.exception.WrongCommandFormatException;
import my.project.log.analysis.model.GroupBy;
import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.service.filters.FilterChainExecutorFactory;
import my.project.log.analysis.service.loggroup.LogBuncherFactory;
import my.project.log.analysis.utils.Utils;
import my.project.log.analysis.view.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public class LogGroupCommand extends Command {

    private final String USERNAME_FILTER_TOKEN = Utils.getProperty("command.analyse.token.username.filter");
    private final String START_PERIOD_FILTER_TOKEN = Utils.getProperty("command.analyse.token.startperiod.filter");
    private final String FINISH_PERIOD_FILTER_TOKEN = Utils.getProperty("command.analyse.token.finishperiod.filter");
    private final String CUSTOM_MESSAGE_PATTERN_TOKEN = Utils.getProperty("command.analyse.token.messagepattern.filter");
    private final String GROUP_BY_TOKEN = Utils.getProperty("command.analyse.token.groupby");
    private final String COMMAND_DESCRIPTION = Utils.getProperty("command.analyse.description");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utils.getProperty("command.analyse.time.format"));
    private String[] commandParameters;

    public LogGroupCommand(View view, LogAnalyser logAnalyser) {
        super(view, logAnalyser);
    }

    @Override
    public void execute() {
        commandParameters = command.split("(?=-[usfpg])");
        Set<String> userNames = searchParametersByToken(USERNAME_FILTER_TOKEN);
        Set<String> customMessagePattern = searchParametersByToken(CUSTOM_MESSAGE_PATTERN_TOKEN);
        LocalDateTime startPeriod = searchDate(START_PERIOD_FILTER_TOKEN, LocalDateTime.MIN);
        LocalDateTime finishPeriod = searchDate(FINISH_PERIOD_FILTER_TOKEN, LocalDateTime.MAX);

        if (userNames.size() == 0 && customMessagePattern.size() == 0 &&
                startPeriod.isEqual(LocalDateTime.MIN) && finishPeriod.isEqual(LocalDateTime.MAX)) {
            throw new WrongCommandFormatException("you should specify at least one filter parameter.");
        }

        GroupBy group = GroupBy.getEnum(searchParameterByToken(GROUP_BY_TOKEN).toLowerCase());
        if (group == null) {
            throw new WrongCommandFormatException("you should specify group parameter");
        }

        logAnalyser.setLogFilterChain(new FilterChainExecutorFactory().createFilterChain(userNames, customMessagePattern,
                startPeriod, finishPeriod));
        logAnalyser.setLogGroup(new LogBuncherFactory().createLogGroup(group));

        logAnalyser.runAnalysis(pathToInputDirectory, threadCount);
        view.write("Ok. Result saved in the file " + pathToOutputFile);
    }

    private LocalDateTime searchDate(String token, LocalDateTime defaultValue) {
        String dateInString = searchParameterByToken(token);

        if (dateInString == null || dateInString.equals("")) {
            return defaultValue;
        }
        try {
            return LocalDateTime.parse(dateInString, formatter);
        } catch (Exception ex) {
            throw new WrongCommandFormatException(String.format("Invalid date format '%s'", dateInString));
        }

    }

    private Set<String> searchParametersByToken(String token) {
        final int PARAMETER_POSITION = 1;
        Set<String> result = new HashSet<>();
        for (String commandParameter : commandParameters) {
            if (commandParameter.contains(token)) {
                result.add(commandParameter.split(PARAMETERS_SEPARATOR)[PARAMETER_POSITION]);
            }
        }
        return result;
    }

    private String searchParameterByToken(String token) {
        final int PARAMETER_POSITION = 1;
        for (String commandParameter : commandParameters) {
            if (commandParameter.contains(token)) {
                return commandParameter.split(PARAMETERS_SEPARATOR)[PARAMETER_POSITION];
            }
        }
        return "";
    }

    @Override
    public String getName() {
        return Utils.getProperty("command.analyse.name");
    }

    @Override
    public String getDescription() {
        return COMMAND_DESCRIPTION;
    }
}

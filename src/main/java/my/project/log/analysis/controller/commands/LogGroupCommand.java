package my.project.log.analysis.controller.commands;

import my.project.log.analysis.exception.WrongCommandFormatException;
import my.project.log.analysis.model.GroupBy;
import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.service.filters.impl.FilterChainFactory;
import my.project.log.analysis.service.logcounter.LogGroupFactory;
import my.project.log.analysis.view.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public class LogGroupCommand extends Command {
    private final String USERNAME_FILTER = "-user";
    private final String START_PERIOD_FILTER = "-start";
    private final String FINISH_PERIOD_FILTER = "-finish";
    private final String CUSTOM_MESSAGE_PATTERN_PATTERN = "-pattern";
    private final String GROUP_BY = "-groupby";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");

    private String NAME = COMMAND_LOG_ANALYSIS;
    private String DESCRIPTION = "Run log analysis with parameters you need to set at least one " +
            "filter parameter and group parameter.";
    private String[] commandParameters;

    public LogGroupCommand(View view, LogAnalyser logAnalyser) {
        super(view, logAnalyser);
    }

    @Override
    public void execute() {
        commandParameters = command.split("(?=-[usfpg])");
        Set<String> userNames = searchParametersByToken(USERNAME_FILTER);
        Set<String> customMessagePattern = searchParametersByToken(CUSTOM_MESSAGE_PATTERN_PATTERN);
        LocalDateTime startPeriod = getDate(START_PERIOD_FILTER, LocalDateTime.MIN);
        LocalDateTime finishPeriod = getDate(FINISH_PERIOD_FILTER, LocalDateTime.MAX);

        if (userNames.size() == 0 && customMessagePattern.size() == 0 &&
                startPeriod.isEqual(LocalDateTime.MIN) && finishPeriod.isEqual(LocalDateTime.MAX)) {
            throw new WrongCommandFormatException("you should specify at least one filter parameter.");
        }

        GroupBy group = GroupBy.getEnum(searchParameterByToken(GROUP_BY).toLowerCase());
        if (group == null) {
            throw new WrongCommandFormatException("you should specify group parameter");
        }

        FilterChainFactory filterChainFactory = new FilterChainFactory();
        LogGroupFactory logGroupFactory = new LogGroupFactory();

        logAnalyser.setFilterChain(filterChainFactory.createFilterChain(userNames, customMessagePattern,
                startPeriod, finishPeriod));
        logAnalyser.setLogGroup(logGroupFactory.createLogGroup(group));

        logAnalyser.runAnalysis("D://log.txt");
    }

    private LocalDateTime getDate(String token, LocalDateTime defaultValue) {
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
        Set<String> result = new HashSet<>();
        for (String commandParameter : commandParameters) {
            if (commandParameter.contains(token)) {
                result.add(commandParameter.split(" ")[1]);
            }
        }
        return result;
    }

    private String searchParameterByToken(String token) {
        String result = "";
        for (String commandParameter : commandParameters) {
            if (commandParameter.contains(token)) {
                return commandParameter.split(" ")[1];
            }
        }
        return "";
    }


    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}

package my.project.log.analysis.controller.commands;

import my.project.log.analysis.exception.WrongCommandFormatException;
import my.project.log.analysis.model.GroupBy;
import my.project.log.analysis.service.LogAnalyser;
import my.project.log.analysis.service.filters.FilterChainExecutorFactory;
import my.project.log.analysis.service.loggroup.LogGroupFactory;
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
        Set<String> userNames = searchTokenValueByToken(USERNAME_FILTER_TOKEN);
        Set<String> customMessagePattern = searchTokenValueByToken(CUSTOM_MESSAGE_PATTERN_TOKEN);
        LocalDateTime startPeriod = searchDate(START_PERIOD_FILTER_TOKEN, LocalDateTime.MIN);
        LocalDateTime finishPeriod = searchDate(FINISH_PERIOD_FILTER_TOKEN, LocalDateTime.MAX);

        if (userNames.size() == 0 && customMessagePattern.size() == 0 &&
                startPeriod.isEqual(LocalDateTime.MIN) && finishPeriod.isEqual(LocalDateTime.MAX)) {
            throw new WrongCommandFormatException("you should specify at least one filter parameter.");
        }

        Set<GroupBy> groups = convertStringsToEnumGroupBy(searchTokenValueByToken(GROUP_BY_TOKEN));
        if (groups.size() == 0) {
            throw new WrongCommandFormatException("You should specify at least one group parameter");
        } else {
            if (groups.size() > 2) {
                throw new WrongCommandFormatException("You can't use more than 2 group parameters");
            }
        }

        logAnalyser.setLogFilterChainExecutor(new FilterChainExecutorFactory().createFilterChain(userNames, customMessagePattern,
                startPeriod, finishPeriod));
        logAnalyser.setLogGroup(new LogGroupFactory().createLogGroup(groups));

        logAnalyser.runAnalysis(pathToInputDirectory, threadCount);
        view.write("Ok. Result saved in the file " + pathToOutputFile);
    }

    private Set<GroupBy> convertStringsToEnumGroupBy(Set<String> strings) {
        Set<GroupBy> result = new HashSet<>();
        for (String str : strings) {
            GroupBy group = GroupBy.getEnum(str.toLowerCase());
            if (group == null) {
                throw new WrongCommandFormatException(String.format("wrong group specify parameter '%s'", str));
            }
            result.add(group);
        }
        return result;
    }

    private LocalDateTime searchDate(String token, LocalDateTime defaultValue) {
        String dateInString = searchTokenValueForToken(token);

        if (dateInString == null || dateInString.equals("")) {
            return defaultValue;
        }
        try {
            return LocalDateTime.parse(dateInString, formatter);
        } catch (Exception ex) {
            throw new WrongCommandFormatException(String.format("Invalid date format '%s'", dateInString));
        }

    }

    private Set<String> searchTokenValueByToken(String token) {
        final int TOKEN_VALUE_POSITION = 1;
        Set<String> result = new HashSet<>();
        for (String commandParameter : commandParameters) {
            if (commandParameter.contains(token)) {
                checkTokenValueCount(commandParameter);
                result.add(commandParameter.split(PARAMETERS_SEPARATOR)[TOKEN_VALUE_POSITION]);
            }
        }
        return result;
    }

    private String searchTokenValueForToken(String token) {
        final int TOKEN_VALUE_POSITION = 1;
        for (String commandParameter : commandParameters) {
            if (commandParameter.contains(token)) {
                checkTokenValueCount(commandParameter);
                return commandParameter.split(PARAMETERS_SEPARATOR)[TOKEN_VALUE_POSITION];
            }
        }
        return "";
    }

    private void checkTokenValueCount(String commandParameter) {
        final int EXPECTED_COUNT = 2;
        String[] params = commandParameter.split(PARAMETERS_SEPARATOR);
        if (params.length > EXPECTED_COUNT){
            throw new WrongCommandFormatException(String.format("You can't use more then one parameter for token %s", params[0]));
        }
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

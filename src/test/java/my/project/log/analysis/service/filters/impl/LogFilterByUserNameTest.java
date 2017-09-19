package my.project.log.analysis.service.filters.impl;

import my.project.log.analysis.exception.LogFilterInterruptingException;
import my.project.log.analysis.model.LogMessage;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public class LogFilterByUserNameTest {

    @Test
    public void successfulFilter(){
        //given
        Set<String> userNames = new HashSet<>(Arrays.asList(new String[]{"userName1", "userName2"}));
        LogFilterByUserName filter = new LogFilterByUserName(userNames);
        LogMessage logMessage = new LogMessage(LocalDateTime.now(),"userName1",
                "customMessage", "logMessage");
        //when
        boolean isExceptionThrown = false;

        try {
            filter.doFilter(logMessage);
        } catch (LogFilterInterruptingException e){
            isExceptionThrown = true;
        }

        //then
        assert(!isExceptionThrown);
    }

    @Test
    public void successfulFilter2(){
        //given
        Set<String> userNames = new HashSet<>(Arrays.asList(new String[]{"userName1", "userName2"}));
        LogFilterByUserName filter = new LogFilterByUserName(userNames);
        LogMessage logMessage = new LogMessage(LocalDateTime.now(),"userName2",
                "customMessage", "logMessage");
        //when
        boolean isExceptionThrown = false;

        try {
            filter.doFilter(logMessage);
        } catch (LogFilterInterruptingException e){
            isExceptionThrown = true;
        }

        //then
        assert(!isExceptionThrown);
    }

    @Test
    public void unSuccessfulFilter1(){
        //given
        Set<String> userNames = new HashSet<>(Arrays.asList(new String[]{"userName1", "userName2"}));
        LogFilterByUserName filter = new LogFilterByUserName(userNames);
        LogMessage logMessage = new LogMessage(LocalDateTime.now(),"otherUserName1",
                "customMessage", "logMessage");
        //when
        boolean isExceptionThrown = false;

        try {
            filter.doFilter(logMessage);
        } catch (LogFilterInterruptingException e){
            isExceptionThrown = true;
        }

        //then
        assert(isExceptionThrown);
    }
}
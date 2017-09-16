package my.project.log.analysis.service.filters.impl.impl;

import my.project.log.analysis.exception.LogFilterInteruptingException;
import my.project.log.analysis.model.LogMessage;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Nikolay Horushko
 */
public class LogFilterByTimeTest {

        @Test
        public void successfulFilter(){
            //given
            List<String> userNames = Arrays.asList(new String[]{"userName1", "userName2"});

            LocalDateTime startDate = LocalDateTime.of(2017, Month.JUNE, 1, 00, 00, 00);
            LocalDateTime finishDate = LocalDateTime.of(2017, Month.AUGUST, 1, 00, 00, 00);
            LogFilterByTime filter = new LogFilterByTime(startDate, finishDate);

            LogMessage logMessage = new LogMessage(LocalDateTime.of(2017, Month.JULY, 9, 11, 6, 22),"userName1", "customMessage");
            //when
            boolean isExceptionThrown = false;

            try {
                filter.doFilter(logMessage);
            } catch (LogFilterInteruptingException e){
                isExceptionThrown = true;
            }

            //then
            assertEquals(false, isExceptionThrown);
        }

    @Test
    public void successfulFilterStartDateEqualsCurrent(){
        //given
        List<String> userNames = Arrays.asList(new String[]{"userName1", "userName2"});

        LocalDateTime startDate = LocalDateTime.of(2017, Month.JULY, 9, 11, 6, 22);
        LocalDateTime finishDate = LocalDateTime.of(2017, Month.AUGUST, 1, 00, 00, 00);
        LogFilterByTime filter = new LogFilterByTime(startDate, finishDate);

        LogMessage logMessage = new LogMessage(LocalDateTime.of(2017, Month.JULY, 9, 11, 6, 22),"userName1", "customMessage");
        //when
        boolean isExceptionThrown = false;

        try {
            filter.doFilter(logMessage);
        } catch (LogFilterInteruptingException e){
            isExceptionThrown = true;
        }

        //then
        assertEquals(false, isExceptionThrown);
    }

    @Test
    public void successfulFilterFinishDateEqualsCurrent(){
        //given
        List<String> userNames = Arrays.asList(new String[]{"userName1", "userName2"});

        LocalDateTime startDate = LocalDateTime.of(2017, Month.JUNE, 1, 00, 00, 00);
        LocalDateTime finishDate = LocalDateTime.of(2017, Month.JULY, 9, 11, 6, 22);
        LogFilterByTime filter = new LogFilterByTime(startDate, finishDate);

        LogMessage logMessage = new LogMessage(LocalDateTime.of(2017, Month.JULY, 9, 11, 6, 22),"userName1", "customMessage");
        //when
        boolean isExceptionThrown = false;

        try {
            filter.doFilter(logMessage);
        } catch (LogFilterInteruptingException e){
            isExceptionThrown = true;
        }

        //then
        assertEquals(false, isExceptionThrown);
    }

    @Test
    public void unSuccessfulFilter(){
        //given
        List<String> userNames = Arrays.asList(new String[]{"userName1", "userName2"});

        LocalDateTime startDate = LocalDateTime.of(2017, Month.JUNE, 1, 00, 00, 00);
        LocalDateTime finishDate = LocalDateTime.of(2017, Month.AUGUST, 1, 00, 00, 00);
        LogFilterByTime filter = new LogFilterByTime(startDate, finishDate);

        LogMessage logMessage = new LogMessage(LocalDateTime.of(2016, Month.JULY, 9, 11, 6, 22),"userName1", "customMessage");
        //when
        boolean isExceptionThrown = false;

        try {
            filter.doFilter(logMessage);
        } catch (LogFilterInteruptingException e){
            isExceptionThrown = true;
        }

        //then
        assertEquals(true, isExceptionThrown);
    }

    @Test
    public void unSuccessfulFilter2(){
        //given
        List<String> userNames = Arrays.asList(new String[]{"userName1", "userName2"});

        LocalDateTime startDate = LocalDateTime.of(2017, Month.JUNE, 1, 00, 00, 00);
        LocalDateTime finishDate = LocalDateTime.of(2017, Month.AUGUST, 1, 00, 00, 00);
        LogFilterByTime filter = new LogFilterByTime(startDate, finishDate);

        LogMessage logMessage = new LogMessage(LocalDateTime.of(2017, Month.SEPTEMBER, 9, 11, 6, 22),"userName1", "customMessage");
        //when
        boolean isExceptionThrown = false;

        try {
            filter.doFilter(logMessage);
        } catch (LogFilterInteruptingException e){
            isExceptionThrown = true;
        }

        //then
        assertEquals(true, isExceptionThrown);
    }


}
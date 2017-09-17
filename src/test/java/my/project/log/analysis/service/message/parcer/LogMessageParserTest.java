package my.project.log.analysis.service.message.parcer;

import my.project.log.analysis.model.LogMessage;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * @author Nikolay Horushko
 */
public class LogMessageParserTest {

    LogMessageParser logMessageParser;

    @Before
    public void setup(){
        logMessageParser = new LogMessageParser();
    }

    @Test
    public void test(){
        //given
        String logMessage = "2017-09-16 12:11:06 | UserName1 | CustomMessage";
        //when
        LogMessage result = logMessageParser.parce(logMessage);
        //then
        assertEquals("2017-09-16T12:11:06", result.getDate().toString());
        assertEquals("UserName1", result.getUserName());
        assertEquals("CustomMessage", result.getCustomMessage());
    }

}
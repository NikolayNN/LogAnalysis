package my.project.log.analysis.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

/**
 * @author Nikolay Horushko
 */
@AllArgsConstructor
@Getter
public class LogMessage {
    private Date date;
    private String userName;
    private String customMessage;
}

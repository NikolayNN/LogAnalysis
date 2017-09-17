package my.project.log.analysis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author Nikolay Horushko
 */
@AllArgsConstructor
@Getter
public class LogMessage {
    private LocalDateTime date;
    private String userName;
    private String customMessage;
    private String sourceLogMessage;
}

package my.project.log.analysis.view;

/**
 * @author Nikolay Horushko
 */
public interface View {
    void write(String message);
    String read();
}

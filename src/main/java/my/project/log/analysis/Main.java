package my.project.log.analysis;

import my.project.log.analysis.controller.Controller;

/**
 * @author Nikolay Horushko
 */
public class Main {
    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.start();
    }
}

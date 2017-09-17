package my.project.log.analysis.model;

/**
 * @author Nikolay Horushko
 */
public enum GroupBy {
    
    HOUR("hour"), DAY("day"), MONTH("month"), YEAR("year"), USERNAME("username");

    private String code;

    GroupBy(String code) {
        this.code = code;
    }

    public static GroupBy getEnum(String code) {

        switch (code) {
            case "hour":
                return HOUR;
            case "day":
                return DAY;
            case "month":
                return MONTH;
            case "year":
                return YEAR;
            case "username":
                return USERNAME;
            default:
                return null;
        }
    }
}

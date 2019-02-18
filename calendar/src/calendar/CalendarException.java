package calendar;

public class CalendarException extends Exception {
    private String message;
    private int id;
    //1 date format; 2 invalid date; 3 time format; 4 start time later than end time; 5 null object
    // 6 alarm time after item start time
    //7 时间冲突
    //8 父子类型冲突

    public CalendarException(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

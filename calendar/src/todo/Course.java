package todo;

import calendar.CalendarException;

import java.util.ArrayList;

/**
 * Created by cjy970910 on 2018/5/22.
 */
public class Course extends ToDo {
    private final int type = 6;
    private String name;
    private String place;
    private int numberOfWeek;
    private String teacher;
    private int repeatedDay;
    private String comment;


    protected Course(ArrayList<String> messageList) throws CalendarException {
        super(messageList.get(0), messageList.get(1));
        this.name = messageList.get(2);
        this.content = messageList.get(3);
        this.place = messageList.get(4);
        this.numberOfWeek = Integer.parseInt(messageList.get(5));
        this.teacher = messageList.get(6);
        this.comment = messageList.get(7);
        this.repeatedDay = Integer.parseInt(messageList.get(8));
    }

    @Override
    public String print() {
        return "";
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String getAlarmMessage() {
        return "";
    }
}

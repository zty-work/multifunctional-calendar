package todo;

import calendar.CalendarException;

import java.util.ArrayList;

/**
 * Created by cjy970910 on 2018/5/22.
 */
public class Journey extends ToDo {
    private final int type = 3;
    private String means;
    private String place;
    private String number;

    protected Journey(ArrayList<String> messageList) throws CalendarException {
        super(messageList.get(0), messageList.get(1));
        this.means = messageList.get(2);
        this.place = messageList.get(3);
        this.number = messageList.get(4);
        this.content = messageList.get(5);
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

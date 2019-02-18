package todo;


import calendar.CalendarException;

import java.util.ArrayList;

/**
 * Created by cjy970910 on 2018/5/22.
 */
public class Interview extends ToDo  {
    private final int type = 5;
    private String place;
    private String company;
    private String position;

    protected Interview(ArrayList<String> messageList)throws CalendarException {
        super(messageList.get(0),messageList.get(1));
        this.place = messageList.get(2);
        this.company = messageList.get(3);
        this.position = messageList.get(4);
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
    public String getAlarmMessage(){
        return "";
    }
}

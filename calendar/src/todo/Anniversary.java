package todo;

import calendar.CalendarException;

import java.util.ArrayList;

/**
 * Created by cjy970910 on 2018/5/22.
 */
public class Anniversary extends ToDo {
    private final int type = 4;
    private String kind;
    private String name;

    protected Anniversary(ArrayList<String> messageList) throws CalendarException {
        super(messageList.get(0), messageList.get(1));
        this.kind = messageList.get(2);
        this.name = messageList.get(3);
        this.content = messageList.get(4);
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

package todo;

import calendar.CalendarException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Meeting extends ToDo {
    private String place;
    private String topic;
    private final int type = 2;

    protected Meeting(ArrayList<String> messageList) throws CalendarException {
        super(messageList.get(0), messageList.get(1));
        this.place = messageList.get(2);
        this.topic = messageList.get(3);
        this.content = messageList.get(4);
    }


    @Override
    public String print() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String startDateString = format.format(startDate);
        String endDateString = format.format(endDate);
        return "开始时间：" + startDateString + "\n结束时间：" + endDateString + "\n主题：" + topic + "\n地点：" + place + "\n内容：\n" + content;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String startDateString = format.format(startDate);
        String endDateString = format.format(endDate);
        return "M " + startDateString + " " + endDateString + " " + replaceSpace(place) + " " + replaceSpace(topic) + " " + replaceSpace(content);
    }

    @Override
    public String getAlarmMessage() {
        return "";
    }
}

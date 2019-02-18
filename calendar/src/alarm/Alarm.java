package alarm;

import calendar.CalendarException;
import todo.ToDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 一个ToDo如果需要提醒，则有一个Alarm
 * Alarm构造时，会生成一个消息列表，为每次提醒的文字内容
 */
public class Alarm {
    private Date startTime;
    private int intervalTime;
    private int type;
    private ArrayList<String> messageList;
    private ArrayList<Date> alarmTimeList;

    /**
     * @param startTimeString 最早提醒日期，格式为yyyy-MM-dd HH:mm
     * @param intervalTime    间隔时间，以分钟为单位
     * @param type            1 显示框提醒；2 弹框提醒；3两者都有
     * @param toDo            需要提醒的事项对象
     */
    public Alarm(String startTimeString, int intervalTime, int type, ToDo toDo) throws CalendarException {
        if (!startTimeString.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}")) {
            throw new CalendarException("格式错误！", 3);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            startTime = format.parse(startTimeString);
        } catch (ParseException e) {
            throw new CalendarException("格式错误！", 3);
        }
        if (startTime.after(toDo.getStartDate())) {
            throw new CalendarException("提醒时间晚于事项开始时间！", 6);
        }
        this.intervalTime = intervalTime;
        this.type = type;
        setAlarmTimeList(toDo);
        setMessageList(toDo);
    }

    /**
     * @return 返回最近一次的提醒信息。（比如1点和2点都需要提醒，则返回1点的那次提醒信息）
     * 每次提醒完，该条消息将从列表中消失。
     */
    public String getMessage() {
        String message = messageList.get(0);
        messageList.remove(0);
        return message;
    }

    private void setMessageList(ToDo toDo) {
        int gap = (int) (toDo.getStartDate().getTime() - startTime.getTime()) / 60000;
        int times = gap / intervalTime;
        for (int i = 0; i < times; i++) {
            int minutes = gap - times;
            int hour = 0;
            int day = 0;
            if (minutes > 60) {
                hour = minutes / 60;
            }
            if (hour > 24) {
                day = hour / 24;
            }
            String message;
            if (day != 0) {
                message = toDo.getAlarmMessage() + "还有" + day + "天开始。";
            } else if (hour != 0) {
                message = toDo.getAlarmMessage() + "还有" + hour + "小时开始。";
            } else {
                message = toDo.getAlarmMessage() + "还有" + minutes + "分钟开始。";
            }
            messageList.add(message);
        }
    }

    private void setAlarmTimeList(ToDo toDo) {
        int gap = (int) (toDo.getStartDate().getTime() - startTime.getTime()) / 60000;
        int times = gap / intervalTime;
        for (int i = 0; i < times; i++) {
            int minutes = gap - times;
            int hour = 0;
            int day = 0;
            if (minutes > 60) {
                hour = minutes / 60;
            }
            if (hour > 24) {
                day = hour / 24;
            }
            String message;
            if (day != 0) {
                message = toDo.getAlarmMessage() + "还有" + day + "天开始。";
            } else if (hour != 0) {
                message = toDo.getAlarmMessage() + "还有" + hour + "小时开始。";
            } else {
                message = toDo.getAlarmMessage() + "还有" + minutes + "分钟开始。";
            }
            messageList.add(message);
        }
    }
}

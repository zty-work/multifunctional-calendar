package todo;

import calendar.CalendarException;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dating extends ToDo {
    private String place;
    private String people;
    private final int type = 1;

    protected Dating(ArrayList<String> messageList) throws CalendarException {
        super(messageList.get(0), messageList.get(1));
        this.place = messageList.get(2);
        this.people = messageList.get(3);
        this.content = messageList.get(4);
    }


    @Override
    public String print() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String startDateString = format.format(startDate);
        String endDateString = format.format(endDate);
        return "开始时间：" + startDateString +
                "\n结束时间：" + endDateString +
                "\n对象：" + people +
                "\n地点：" + place +
                "\n内容：\n" + content;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String startDateString = format.format(startDate);
        String endDateString = format.format(endDate);
        Map<String, String> map1 = new HashMap<>();
        map1.put("type", "1");
        map1.put("startDate", startDateString);
        map1.put("endDate", endDateString);
        map1.put("place", place);
        map1.put("people", people);
        map1.put("content", content);
        JSONObject dating = JSONObject.fromObject(map1);
        return dating.toString();
    }


    @Override
    public String getAlarmMessage() {
        return "与" + people + "在" + place + "的约会";
    }

}

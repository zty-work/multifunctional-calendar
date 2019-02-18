package todo;

import calendar.CalendarException;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cjy970910 on 2018/5/22.
 */
public class UserDefined extends ToDo {
    private final int type = 0;

    protected UserDefined(ArrayList<String> messageList) throws CalendarException {
        super(messageList.get(0), messageList.get(1));
        this.content = messageList.get(2);
    }

    @Override
    public String print() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String startDateString = format.format(startDate);
        String endDateString = format.format(endDate);
        return "开始时间：" + startDateString + "\n结束时间：" + endDateString + "\n内容：\n" + content;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String startDateString = format.format(startDate);
        String endDateString = format.format(endDate);
        Map<String, String> map1 = new HashMap<>();
        map1.put("type", "0");
        map1.put("startDate", startDateString);
        map1.put("endDate", endDateString);
        map1.put("content", content);
        JSONObject dating = JSONObject.fromObject(map1);
        return dating.toString();
    }

    @Override
    public String getAlarmMessage() {
        return "";
    }
}

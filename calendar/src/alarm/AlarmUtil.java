package alarm;

import java.util.ArrayList;
import java.util.Date;

public class AlarmUtil {
    private static ArrayList<Alarm> alarmList = read();

    private static ArrayList<Alarm> read() {
        return new ArrayList<>();
    }


    /**
     *
     * @param currentTime 传入当前时间，为Date类型，格式为yyyy-MM-dd HH:mm
     * @return 返回Alarm列表，得到显示信息的方法为，遍历该列表，对每个Alarm进行getMessage。
     */
    public static ArrayList<Alarm> getAlarmList(Date currentTime) {
        return new ArrayList<Alarm>();
    }


    public static ArrayList<Alarm> getLatestAlarmList() {
        return new ArrayList<Alarm>();
    }


    /**
     * 生成ToDo时如果需要提醒，则调用该方法添加一个提醒。
     * @param alarm 新的提醒
     */
    public static void addAlarm(Alarm alarm) {

    }

    /**
     * 停止一个提醒
     * @param alarm 需要删除的提醒
     */
    public static void stopAlarm(Alarm alarm) {

    }
}

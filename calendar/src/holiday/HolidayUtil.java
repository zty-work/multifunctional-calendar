package holiday;

import calendar.CalendarDate;
import calendar.CalendarException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;


public class HolidayUtil {
    private static ArrayList<Holiday> holidayList = readHoliday();
    private static ArrayList<CalendarDate> workdayList = readWorkday();

    /**
     * 返回假日列表
     */
    private static ArrayList<Holiday> readHoliday() {
        ArrayList<Holiday> holidays = new ArrayList<>();
        File file = new File("src/holiday/holiday.json");
        String content = readFile();
        JSONObject obj = JSONObject.fromObject(content);
        JSONArray workdayArray = obj.getJSONArray("holiday");
        for (int i = 0; i < workdayArray.size(); i++) {
            try {
                JSONObject holidayJson = workdayArray.getJSONObject(i);
                String holidayName = holidayJson.getString("zh_name");
                CalendarDate holidayTime = new CalendarDate(holidayJson.getString("holiday_time"));
                CalendarDate startTime = new CalendarDate(holidayJson.getString("start_time"));
                CalendarDate endTime = new CalendarDate(holidayJson.getString("end_time"));
                Holiday holiday = new Holiday(holidayName, holidayTime, startTime, endTime);
                holidays.add(holiday);
            } catch (CalendarException e) {
                Logger.getAnonymousLogger("Json file error.");
            }
        }
        return holidays;
    }


    /**
     * 返回调休后的工作日列表（即需要在日期上标注“班”的日期列表）
     */
    private static ArrayList<CalendarDate> readWorkday() {
        ArrayList<CalendarDate> workdays = new ArrayList<>();
        String content = readFile();
        JSONObject obj = JSONObject.fromObject(content);
        JSONArray workdayArray = obj.getJSONArray("workday");
        for (int i = 0; i < workdayArray.size(); i++) {
            try {
                workdays.add(new CalendarDate((String) workdayArray.get(i)));
            } catch (CalendarException e) {
                e.printStackTrace();
            }
        }
        return workdays;
    }

    private static String readFile() {
        String content = "";
        File file = new File("src/holiday/holiday.json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                content = content.concat(line);
            }
        } catch (FileNotFoundException e) {
            Logger.getAnonymousLogger("Json File Missed");
        } catch (IOException e2) {
            Logger.getAnonymousLogger("IO error");
        }
        return content;
    }

    /**
     * 传入CalendarDate，返回该日期的假日名称，如不是节日返回空字符串
     */
    public static String getName(CalendarDate date) {
        if (date == null) {
            return null;
        }
        for (Holiday holiday : holidayList) {
            if (holiday.getHolidayDate().equals(date)) {
                return holiday.getName();
            }
        }
        return "";
    }

    /**
     * 传入CalendarDate，返回该日期是否是调休日期（是否需要打“休”）
     */
    public static boolean isHoliday(CalendarDate date) {
        if (date == null) {
            return false;
        }
        for (Holiday holiday : holidayList) {
            if (date.isBetween(holiday.getStartDate(), holiday.getEndDate())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 传入CalendarDate，返回该日期是否是调休后需要上班的日期（是否需要打“班”）
     */
    public static boolean isWorkday(CalendarDate date) {
        if (date == null) {
            return false;
        }
        for (CalendarDate workday : workdayList) {
            if (workday.equals(date)) {
                return true;
            }
        }
        return false;
    }
}

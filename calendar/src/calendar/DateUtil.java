package calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

public class DateUtil {
    /**
     * get a calendar.CalendarDate instance point to today
     *
     * @return a calendar.CalendarDate object
     */
    public static CalendarDate getToday() {
        Calendar calendar = Calendar.getInstance();
            return new CalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * get all dates in the same month with given center
     *
     * @param date the given center
     * @return a list of days in a whole month
     */
    public static List<CalendarDate> getDaysInMonth(CalendarDate date) {
        if (date == null ) {
            return null;
        }
        int[] numberOfDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(date.getYear())) {
            numberOfDays[1] = 29;
        }
        List<CalendarDate> dayList = new ArrayList<>();
        try {
            for (int i = 1; i <= numberOfDays[date.getMonth() - 1]; i++) {
                dayList.add(new CalendarDate(date.getYear() + "-" + date.getMonth() + "-" + i));
            }
        } catch (CalendarException e) {
            Logger.getAnonymousLogger().info(e.getMessage());
        }
        return dayList;
    }

    /**
     * Judge whether the input center is valid. For example, 2018-2-31 is not valid
     *
     * @param dateString the input center
     * @return true if the center is valid, false if the center is not valid.
     */
    public static boolean isValid(String dateString) {
        if (dateString == null) {
            return false;
        }
        String[] date = dateString.split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        if (year < 1 || month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && (day > 30)) {
            return false;
        }
        if (month == 2 && isLeapYear(year) && day > 29) {
            return false;
        }
        if (month == 2 && !isLeapYear(year) && day > 28) {
            return false;
        }
        return true;
    }

    /**
     * Judge whether the input is formatted.
     * For example, 2018/2/1 is not valid and 2018-2-1 is valid.
     *
     * @param dateString dateString
     * @return true if the input is formatted, false if the input is not formatted.
     */
    public static boolean isFormatted(String dateString) {
        return dateString != null && dateString.matches("\\d{1,4}-\\d{1,2}-\\d{1,2}");
    }

    /**
     * Judge whether the input year is a leap year or not.
     * For example, year 2000 is a leap year, and 1900 is not.
     *
     * @param year the year
     * @return true if the input year is a leap year, false if the input is not.
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

}

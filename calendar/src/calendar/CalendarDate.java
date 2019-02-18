package calendar;

import java.util.logging.Logger;

public class CalendarDate {
    private int year;
    private int month;
    private int day;

    public CalendarDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public CalendarDate(String dateString) throws CalendarException {
        if (!DateUtil.isFormatted(dateString)) {
            throw new CalendarException("日期格式错误！", 1);
        }
        if (!DateUtil.isValid(dateString)) {
            throw new CalendarException("日期非法！", 2);
        }
        String[] date = dateString.split("-");
        this.year = Integer.parseInt(date[0]);
        this.month = Integer.parseInt(date[1]);
        this.day = Integer.parseInt(date[2]);
    }


    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    /**
     * Get index of the day in a week for one center.
     * <p>
     * Don't use the existing implement like Calendar.setTime(),
     * try to implement your own algorithm.
     *
     * @return 1-7, 1 stands for Monday and 7 stands for Sunday
     */
    public int getDayOfWeek() {
        int y = year % 100;
        int c = year / 100;
        int m = month;
        int d = day;
        if (month == 1 || month == 2) {
            m += 12;
            y--;
        }
        int week = y + y / 4 + c / 4 - 2 * c + 13 * (m + 1) / 5 + d - 1;
        if (week % 7 == 0) {
            return 7;
        } else if (week % 7 < 0) {
            return (week % 7 + 7) % 7;
        } else {
            return week % 7;
        }
    }

    public int getWeekOfMonth() {
        CalendarDate firstDayOfMonth = new CalendarDate(year, month, 1);
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek() % 7;
        return (day + firstDayOfWeek - 1) / 7;
    }

    public boolean isEarlier(CalendarDate date) {
        if (date == null) {
            return false;
        }
        if (year < date.getYear()) {
            return true;
        } else if (year > date.getYear()) {
            return false;
        } else {
            if (month < date.getMonth()) {
                return true;
            } else if (month > date.getMonth()) {
                return false;
            } else {
                return day < date.getDay();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        int year = ((CalendarDate) obj).getYear();
        int month = ((CalendarDate) obj).getMonth();
        int day = ((CalendarDate) obj).getDay();
        return this.getYear() == year && this.getMonth() == month && this.getDay() == day;
    }

    //包括相等
    public boolean isBetween(CalendarDate date1, CalendarDate date2) {
        return (date1.isEarlier(this) || date1.equals(this)) && (isEarlier(date2) || equals(date2));
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }


}

package holiday;
import calendar.CalendarDate;

public class Holiday {
    private String name;
    private CalendarDate holidayDate;
    private CalendarDate startDate;
    private CalendarDate endDate;

    Holiday(String name, CalendarDate holidayDate, CalendarDate startDate, CalendarDate endDate) {
        this.name = name;
        this.holidayDate = holidayDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CalendarDate getHolidayDate() {
        return holidayDate;
    }

    public CalendarDate getStartDate() {
        return startDate;
    }

    public CalendarDate getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }
}

package todo;

import alarm.Alarm;
import calendar.CalendarException;
import java.util.ArrayList;

public class ToDoFactory {
    public static final int USERDEFINED= 0;
    public static final int DATING= 1;
    public static final int MEETING= 2;
    public static final int JOURNEY= 3;
    public static final int ANNIVERSARY= 4;
    public static final int INTERVIEW= 5;
    public static final int COURSE= 6;


/**
 * 构造TODO的工厂
 * @param type:类型
 * @param messageList: 构造待办事项的信息，顺序为：开始时间、结束时间（若不设时间这两个为Null）
 *                    后面的信息列表依次为
 *                   自定义：描述
 *                   约会：地点、人物、内容
 *                   会议：地点、主题、内容
 *                   旅程：交通方式、地点、车次\航班号、备注
 *                   纪念日：类型（结婚纪念日、生日、节日）、名字、描述
 *                   面试：地点、公司、岗位、备注
 *                   课程：课程名、课程内容、上课地点、持续时间、老师、备注，课程重复周天（用字符串的1-7表示周一到周日）。
 */

    public static ToDo createToDo(int type, ArrayList<String> messageList) throws CalendarException{
        switch (type) {
            case 0:
                return new UserDefined(messageList);
            case 1:
                return new Dating( messageList);
            case 2:
                return new Meeting( messageList);
            case 3:
                return new Journey(messageList);
            case 4:
                return new Anniversary( messageList);
            case 5:
                return new Interview( messageList);
            case 6:
                return new Course(messageList);
            default:
                return new UserDefined(messageList);
        }
    }



}

package todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import alarm.Alarm;
import calendar.*;

public abstract class ToDo {
    protected Date startDate;
    protected Date endDate;
    protected String content;
    protected boolean isImportant = false;
    protected boolean isUrgent = false;
    protected int status = 0; //0 未开始; 1 进行中; 2已完成; 3 过期
    protected ArrayList<ToDo> subToDo;
    protected boolean isSub = false;
    protected int type;

    public ToDo(String startDateString, String endDateString)throws CalendarException {
        if (!startDateString.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}") || !endDateString.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}")) {
            throw new CalendarException("格式错误！", 3);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate;
        Date endDate;
        try {
            startDate = format.parse(startDateString);
            endDate = format.parse(endDateString);
        } catch (ParseException e) {
            throw new CalendarException("格式错误！", 3);
        }
        if (endDate.before(startDate)) {
            throw new CalendarException("开始时间晚于结束时间！", 4);
        }
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    public int getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public ArrayList<ToDo> getSubToDo() {
        return subToDo;
    }

    private void setSub(boolean sub) {
        isSub = sub;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }

    protected void setStatus(int status) {
        this.status = status;
    }

    //以上为get/set方法

    /**
     * 检查子事项时间是否在父事项时间内，或子事项间是否冲突
     * @param toDo
     * @return
     */
    private boolean isTimeConflict(ToDo toDo) {
        return false;
    }

    /**
     * 检查子事项类型与父事项是否冲突
     * @param toDo
     * @return
     */
    private boolean isTypeConflict(ToDo toDo) {
        return false;
    }

    public void addSubToDo(ToDo toDo) throws CalendarException {
        if (isSub) {

        }
        if (isTimeConflict(toDo)) {
            throw new CalendarException("balabala", 7);
        }
        if (isTypeConflict(toDo)) {
            throw new CalendarException("balabala", 8);
        }
        subToDo.add(toDo);
        toDo.setSub(true);
    }

    public boolean finish() {
        if (status == 1) {
            status = 2;
            if (!isSub && !subToDo.isEmpty()) {
                for (ToDo todo : subToDo) {
                    todo.setStatus(2);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 工具方法
     *
     * @param string
     * @return
     */
    String replaceSpace(String string) {
        return string.replace(" ", "|^-^|");
    }

    /**
     * 用于检测是否其全部子事项都完成了
     */
    public boolean check() {
        if (subToDo.isEmpty()) {
            return false;
        }
        if (isSub) {
            return false;
        }
        for (ToDo todo : subToDo) {
            if (todo.getStatus() != 2) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回在界面上打印的信息
     */
    public abstract String print();

    /**
     * 返回在存储在文本中的格式
     */
    public abstract String toString();

    /**
     * 返回提醒信息
     */
    public abstract String getAlarmMessage();


}

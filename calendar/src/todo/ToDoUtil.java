package todo;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Logger;

import calendar.CalendarDate;
import calendar.CalendarException;

public class ToDoUtil {
    private static ArrayList<ToDo> todoList = read();

    private static ArrayList<ToDo> read() {
        ArrayList<ToDo> todoList = new ArrayList<>();
        File file = new File("src/todo/todolist.txt");
        String line;
        return todoList;
    }

    private static String replaceToPlace(String string) {
        return string.replace("|^-^|", " ");
    }

    public static boolean insert(ToDo toDo) {
        if (ifConflict(toDo)) {
            return false;
        }
        if (!repeatToDo(toDo)) {
            return false;
        }
        if (toDo == null) {
            return false;
        }
        todoList.add(toDo);
        File file = new File("src/todo/todolist.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(toDo.toString());
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean delete(ToDo toDo) {
        if (todoList.remove(toDo)) {
            File file = new File("src/todo/todolist.txt");
            if (file.exists()) {
                file.delete();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    Logger.getAnonymousLogger("IO Exception");
                }
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (ToDo todo : todoList) {
                    writer.write(todo.toString());
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                Logger.getAnonymousLogger("IO Exception");
            }
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<ToDo> search(String startDateString, String endDateString) throws CalendarException {
        if (startDateString == null || endDateString == null) {
            throw new CalendarException("对象为空！", 5);
        }
        if (!startDateString.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}") || !endDateString.matches("\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}")) {
            throw new CalendarException("格式错误！", 3);
        }
        ArrayList<ToDo> todoListTemp = new ArrayList<>();
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
        for (ToDo todo : todoList) {
            if (startDate.before(todo.getEndDate()) && endDate.after(todo.getStartDate())) {
                todoListTemp.add(todo);
            }
        }
        return todoListTemp;
    }

    public static ArrayList<ToDo> searchByDay(CalendarDate date) {
        if (date == null) {
            return null;
        }
        String start = date.toString() + " " + "00:00";
        String end = date.toString() + " " + "23:59";
        try {
            return search(start, end);
        } catch (CalendarException e) {//Impossible
            return null;
        }
    }

    /**
     * 判断某天是否有事件（判断是否需要高亮）
     */
    public static boolean hasToDo(CalendarDate date) {
        if (date == null) {
            return false;
        }
        ArrayList<ToDo> todoList = searchByDay(date);
        return !todoList.isEmpty();
    }

    /**
     * 插入每年每周重复的待办事项
     * @param toDo 年每周重复的待办事项
     * @return 如果全部插入成功返回true，否则返回false
     */
    private static boolean repeatToDo(ToDo toDo) {
        return false;
    }

    private static boolean ifConflict(ToDo toDo) {
        return false;
    }

    public static void changeStatus(Date currentTime) {
        for (ToDo todo : todoList) {
            if (!currentTime.after(todo.getStartDate()) && !todo.getEndDate().after(currentTime) && todo.getStatus() == 0) {
                todo.setStatus(1);
                for (ToDo subTodo : todo.getSubToDo()) {
                    if (!currentTime.after(subTodo.getStartDate()) && !subTodo.getEndDate().after(currentTime) && subTodo.getStatus() == 0) {
                        subTodo.setStatus(1);
                        continue;
                    }
                    if (currentTime.after(subTodo.getEndDate()) && (subTodo.getStatus() == 1 || subTodo.getStatus() == 0)) {
                        subTodo.setStatus(3);
                    }
                }
                continue;
            }
            if (currentTime.after(todo.getEndDate()) && (todo.getStatus() == 1 || todo.getStatus() == 0)) {
                todo.setStatus(3);
                for (ToDo subTodo : todo.getSubToDo()) {
                    subTodo.setStatus(3);
                }
            }
        }
    }

}

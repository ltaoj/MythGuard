package cn.ltaoj.mythguard.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ltaoj on 2018/3/29 20:52.
 */

public class DateUtil {

    /**
     * 将2017.10.09的时间转换为2017-10-09
     * @param strDate
     * @return
     */
    public static Date parseDate(String strDate) {
        String[] hms = strDate.split("\\.");// '.'为正则里的关键字，需要转义
        if (hms.length >= 2) {
            String result = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                result = String.join("-", hms);
            } else {
                result = join("-", hms);
            }
            result = (hms.length == 2) ? result + "-01" : result;
            return Date.valueOf(result);
        }
        return Date.valueOf("1900-01-01");
    }

    private static String join(String s, String[] hms) {
        String result = "";
        for (String str : hms) {
            result += (str + s);
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length() - s.length());
        }
        return result;
    }

    /**
     * 返回当前年份
     * @return
     */
    public static String getYear() {
        return (new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime())).toString();
    }

    public static String getMonth() {
        return (new SimpleDateFormat("MM").format(Calendar.getInstance().getTime())).toString();
    }

    public static String getDate() {
        return (new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())).toString();
    }

    public static Date getYearStart() {
        return Date.valueOf(getYear() + "-01-01");
    }

    public static Date getYearEnd()  {
        return Date.valueOf(getYear() +  "-12-31");
    }

//    private static String formatNumber(int number) {
//        if (String.valueOf(number).length() == 1) {
//            return "0" + number;
//        } else {
//            return String.valueOf(number);
//        }
//    }

    /**
     * 返回本月第一天和最后一天
     * Date[0] = firstDay Date[1] = lastDay
     * @return
     */
    public static Date[] getFirstLastDayThisMonth(){
        Date[] dateList = new Date[2];

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay = new Date(calendar.getTimeInMillis());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date lastDay = new Date(calendar.getTimeInMillis());

        dateList[0] = firstDay;
        dateList[1] = lastDay;
        return dateList;
    }

    /**
     * 返回上月第一天和最后一天
     * Date[0] = firstDay Date[1] = lastDay
     * @return
     */
    public static Date[] getFirstLastDayLastMonth(){
        Date[] dateList = new Date[2];

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);
        Date lastDay = new Date(cal.getTimeInMillis());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay = new Date(cal.getTimeInMillis());

        dateList[0] = firstDay;
        dateList[1] = lastDay;
        return dateList;
    }

    /**
     * 返回某个日期上一个月第一天和最后一天
     * @param date 某个日期
     * @return Date[0] = firstDay Date[1] = lastDay
     */
    public static Date[] getFirstLastDayBefore(Date date){
        Date[] dateList = new Date[2];

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);
        Date lastDay = new Date(cal.getTimeInMillis());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay = new Date(cal.getTimeInMillis());

        dateList[0] = firstDay;
        dateList[1] = lastDay;
        return dateList;
    }

    /**
     * 格式化日期：年月日
     * @param date
     * @param split
     * @return
     */
    public static String formatDate(Date date, String split) {
        if (split == null || split.equals(""))
            split = ".";
        String[] times = {String.valueOf(date.getYear() + 1990), String.valueOf(date.getMonth() + 1), String.valueOf(date.getDate())};
        String result = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            result = String.join(split, times);
        } else {
            result = join(split, times);
        }
        return result;
    }

    public static void main(String[] args) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        System.out.println(getYear());
    }
}


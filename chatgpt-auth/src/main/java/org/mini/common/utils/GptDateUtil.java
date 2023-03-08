package org.mini.common.utils;


import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date Util
 */
@Slf4j
public class GptDateUtil {

    private GptDateUtil() {}

    /**
     * current time
     * format: yyyy-MM-dd HH:mm:ss
     */
    public static String currentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    /**
     * current day start timestamp
     */
    public static Long currentDayStartTimestamp() {
        Calendar instance = Calendar.getInstance();
        StringBuffer buffer = new StringBuffer();
        buffer.append(instance.get(Calendar.YEAR)).append("-")
                .append(instance.get(Calendar.MONTH) + 1).append("-")
                .append(instance.get(Calendar.DAY_OF_MONTH))
                .append(" 00:00:00");
        return convertStringToLong(buffer.toString());
    }

    /**
     * current system time
     * @return
     */
    public static String currentSystemTimeAsString() {
        return String.valueOf(currentSystemTimeAsLong());
    }

    /**
     * current system time
     * @return
     */
    public static Long currentSystemTimeAsLong() {
        return System.currentTimeMillis();
    }

    public static Integer calculateSecondGap(Long opt,Long target) {
        return (int)((target - opt) / 1000);
    }

    /**
     * current date
     * @return
     */
    public static String currentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * Compare the date size
     * @param prevDatetime      The first time
     * @param nextDatetimen      The second time
     * @return Integer 2，1：The first date is large，0：The two dates are the same，-1：The second date is large
     */
    public static Integer compareDateTime(String prevDatetime, String nextDatetimen) {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(prevDatetime);
            Date dt2 = df.parse(nextDatetimen);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }

        } catch (Exception e) {
            log.error(e.getMessage() + "compare datetime failed");
        }
        return 2;
    }

    /**
     * get current year and month
     */
    public static String currentYearAndMonth() {
        Calendar instance = Calendar.getInstance();
        StringBuffer buffer = new StringBuffer();
        buffer.append(instance.get(Calendar.YEAR));
        buffer.append(instance.get(Calendar.MONTH) + 1);
        return buffer.toString();
    }

    /**
     * Days a few days after the current date
     */
    public static String dateAfterCurrentDate(int num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * Days a few days before the current date
     */
    public static String dateBeforeCurrentDate(int num) {
        return dateAfterCurrentDate(-1 * num);
    }

    /**
     * convert string time to timestamp
     */
    public static Long convertStringToLong(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date) sdf.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return -1L;
    }

    public static int getCurrentMonthDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.roll(Calendar.DATE,-1);
        return calendar.get(Calendar.DATE);
    }
}

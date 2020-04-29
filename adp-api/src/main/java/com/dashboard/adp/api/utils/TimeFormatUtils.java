package com.dashboard.adp.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Copyright © 2016 ICST. All rights reserved.
 *
 * @Description: TODO
 * @Author: CosmicSouls
 * @CreateDate: 2018/3/31 14:17
 * @Remark: The remark content
 * @Version: V1.0
 * Copyright: Copyright (c) 2018
 */
public class TimeFormatUtils {

    public static final String DATE2Y = "yy-MM-dd";
    public static final String DATE2Y_TIME = "yy-MM-dd HH:mm";
    public static final String DATE_MONTH_TIME = "MM-dd HH:mm";
    public static final String TIEM_HOUR_MINUTE = "HH:mm";
    public static final String DATE4Y = "yyyy-MM-dd";
    public static final String DATE4Y_TIME = "yyyy-MM-dd HH:mm";
    public static final String DATE4Y_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE2_MMddhhmmss="MM-dd hh:mm:ss";
    public static final String DATE2_MMddHHmmss="MM-dd HH:mm:ss";

    /**
     * 得到 yy-MM-dd 格式的日期时间字符串
     * <p>Author ：曾杰(Zeng Jie) on 2016/6/28
     * @param
     * @return
     */
    public static String getDate2Y(long time) {
        SimpleDateFormat format = new SimpleDateFormat(DATE2Y);
        return format.format(new Date(time));
    }
    /**
     * 得到 yy-MM-dd HH:mm 格式的日期时间字符串
     * <p>Author ：曾杰(Zeng Jie) on 2016/6/28
     * @param
     * @return
     */
    public static String getDate2YTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat(DATE2Y_TIME);
        return format.format(new Date(time));
    }

    /**
     * 得到 yyyy-MM-dd HH:mm:ss 格式的日期时间字符串
     * <p>Author ：曾杰(Zeng Jie) on 2016/6/28
     * @param
     * @return
     */
    public static String getDate4YTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat(DATE4Y_TIME_SECOND);
        return format.format(new Date(time));
    }

    /**
     * 得到 HH:mm 格式的时间字符串
     * <p>Author ：曾杰(Zeng Jie) on 2016/6/28
     * @param
     * @return
     */
    public static String getHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat(TIEM_HOUR_MINUTE);
        return format.format(new Date(time));
    }

    /**
     * Get the time to chat
     */
    public static String getChatTime(long timesamp) {
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp);
        int temp = Integer.parseInt(sdf.format(today)) - Integer.parseInt(sdf.format(otherDay));

        switch (temp) {
            case 0:
                result = "今天 " + getHourAndMin(timesamp);
                break;
            case 1:
                result = "昨天 " + getHourAndMin(timesamp);
                break;
            case 2:
                result = "前天 " + getHourAndMin(timesamp);
                break;

            default:
                // result = temp + "天前 ";
                result = getDate2YTime(timesamp);
                break;
        }
        return result;
    }

    public static String getCustomizedTime(String standardTime) {
        Date now = new Date();
        Date chatTime = StringToDate(standardTime);
        long minuties = (now.getTime() - chatTime.getTime()) / 60000; // 当前时间和发帖时间的间隔分钟

        if (minuties < 60) {
            return minuties + "分钟";
        } else if (minuties < 1440 && minuties >= 60) {
            return minuties / 60 + "小时";
        } else if (minuties > 1439 && minuties < 1440 * 2) {
            return "昨天";
        } else if (minuties < 1440 * 7 && minuties > 1440 * 2) {
            int day = (int) (minuties / 1440);
            return day + "天前";
        } else if (minuties >= 1440 * 7) {
            return DateToYMDString(chatTime);
        } else {
            return standardTime;
        }
    }

    public static String DateToMonthString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_MONTH_TIME);
        return sdf.format(date);
    }

    public static String DateToHourString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIEM_HOUR_MINUTE);
        return sdf.format(date);
    }

    public static Date StringToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat(DATE4Y_TIME);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * 日期转换成日期格式为 yyyy-MM-dd 的字符串
     * @param date
     * @return
     */
    public static String DateToYMDString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE4Y);
        return sdf.format(date);
    }

    public static int getDateWeek(String strDate) {
        // String subTime = strDate.substring(0, 10);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE4Y,
                Locale.CHINA);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
        }
        if (date == null) {
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // calendar.get
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return week;
    }

    public static String removeTail(String time) {
        if (time == null) {
            return null;
        }
        time = time.substring(0, time.indexOf('T'));
        return time;
    }

    /**
     * 替换掉时间格式为  2013-03-26T10:00:00  中的 T 字符
     * <p>Author ：曾杰(Zeng Jie) on 2016/6/28
     * @param
     * @return
     */
    public static String removeT(String time) {
        if (time == null) {
            return null;
        }
        time = time.replace('T', ' ');
        return time;
    }

    /**
     * 得到系统当前时间，时间格式为  yyyy-MM-dd HH:mm:ss
     * <p>Author ：曾杰(Zeng Jie) on 2016/6/28
     * @param
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE4Y_TIME_SECOND, Locale.CHINA);
        String time = sdf.format(System.currentTimeMillis());
//        String time = sdf.format(new Date());
        return time;
    }
    public static String replacePoint(String timeStr)
    {
        return timeStr.replace(".0", "");
    }
    public static String[] getYMD(String timeStr){
        String[] startAndEndTime = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat(DATE4Y, Locale.CHINA);
        if("".equalsIgnoreCase(timeStr)){
            String time = sdf.format(new Date());

            startAndEndTime[0] = "1970-01-01";
            startAndEndTime[1] = time;
        }else if("Today".equalsIgnoreCase(timeStr)){
            String time = sdf.format(new Date());

            startAndEndTime[0] = time;
            startAndEndTime[1] = time;
        }else if("Week".equalsIgnoreCase(timeStr)){
            Calendar calendar = Calendar.getInstance();
            int dayOfWeek = getMondayPlus();

            calendar.add(Calendar.DATE, dayOfWeek);
            String startTime = sdf.format(calendar.getTime());
            startAndEndTime[0] = startTime;

            calendar.add(Calendar.DATE, 6);
            String endTime = sdf.format(calendar.getTime());
            startAndEndTime[1] =endTime;

//			Calendar calendar = Calendar.getInstance();
//			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//			String startTime = sdf.format(calendar.getTime());
//			startAndEndTime[0] = startTime;
//
//			calendar.add(Calendar.DATE, 6);
//			String endTime = sdf.format(calendar.getTime());
//			startAndEndTime[1] =endTime;

        }else if("ProvWeek".equalsIgnoreCase(timeStr)){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String startTime = sdf.format(calendar.getTime());
            startAndEndTime[0] = startTime;

            calendar.add(Calendar.DATE, 6);
            String endTime = sdf.format(calendar.getTime());
            startAndEndTime[1] =endTime;

        }else if("TwoWeeksAgo".equalsIgnoreCase(timeStr)){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR, -2);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//			String startTime = sdf.format(calendar.getTime());
            startAndEndTime[0] = "1970-01-01";

            calendar.add(Calendar.DATE, 6);
            String endTime = sdf.format(calendar.getTime());
            startAndEndTime[1] =endTime;

        }else if("Month".equalsIgnoreCase(timeStr)){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            String startTime = sdf.format(calendar.getTime());
            startAndEndTime[0] = startTime;

            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            String endTime = sdf.format(calendar.getTime());
            startAndEndTime[1] =endTime;

        }else if("ProvMonth".equalsIgnoreCase(timeStr)){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            String startTime = sdf.format(calendar.getTime());
            startAndEndTime[0] = startTime;

            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            String endTime = sdf.format(calendar.getTime());
            startAndEndTime[1] =endTime;

        }
        return startAndEndTime;
    }

    /**
     *  获得当前日期与本周一相差的天�?	 */
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二�?.....
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * funcdesc
     * <p>Author ：曾杰(Zeng Jie) on 2016/6/28
     * @param
     * @return
     */
    public static String formatChinaString(String timeStr){
        String format =null;
        if("".equalsIgnoreCase(timeStr)){
            format = "最初";
        }else if("Today".equalsIgnoreCase(timeStr)){
            format = "今天";
        }else if("Week".equalsIgnoreCase(timeStr)){
            format = "本周";
        }else if("ProvWeek".equalsIgnoreCase(timeStr)){
            format = "上周";
        }else if("TwoWeeksAgo".equalsIgnoreCase(timeStr)){
            format = "两周以前";
        }else if("Month".equalsIgnoreCase(timeStr)){
            format = "本月";
        }else if("ProvMonth".equalsIgnoreCase(timeStr)){
            format = "上月";
        }

        return format;
    }

    /**
     * funcdesc
     * <p>Author ：曾杰(Zeng Jie) on 2016/6/28
     * @param
     * @return
     */
    public static String getMyAlbumTime(String time) {
        String ret = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long create = sdf.parse(time).getTime();
            Calendar now = Calendar.getInstance();

            long ms  = 1000*(now.get(Calendar.HOUR_OF_DAY)*3600+now.get(Calendar.MINUTE)*60+now.get(Calendar.SECOND));//毫秒�?	            long ms_now = now.getTimeInMillis();
            long ms_now = now.getTimeInMillis();
            if(ms_now-create<ms){
                ret = "今天";
            }else if(ms_now-create<(ms+24*3600*1000)){
                ret = "昨天";
            }
            return ret;
        } catch (Exception e) {
        }
        return null;

    }

    public static String getChatTime1(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timesamp;
        try {
            timesamp = sdf1.parse(time).getTime();


            String result = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            Date today = new Date(System.currentTimeMillis());
            Date otherDay = new Date(timesamp);
            int temp = Integer.parseInt(sdf.format(today)) - Integer.parseInt(sdf.format(otherDay));

            switch (temp) {
                case 0:
                    result = "今天 ";
                    break;
                case 1:
                    result = "昨天 ";
                    break;
                case 2:
                    result = time;
                    break;

                default:
                    // result = temp + "天前 ";
//				result = getDate2YTime(timesamp);
                    break;
            }
            return result;

        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 得到系统当前时间，时间格式为  MM-dd hh:mm:ss
     * <p>Author ：曾杰(Zeng Jie) on 2016/7/5
     * @param
     * @return
     */
    public static String getTimeMMddhhmmss() {
        SimpleDateFormat formatBuilder = new SimpleDateFormat(DATE2_MMddhhmmss);
        return formatBuilder.format(new Date());
    }
    /**
     * 得到系统当前时间，时间格式为  MM-dd HH:mm:ss
     * <p>Author ：曾杰(Zeng Jie) on 2016/7/5
     * @param
     * @return
     */
    public static String getTimeMMddHHmmss() {
        SimpleDateFormat formatBuilder = new SimpleDateFormat(DATE2_MMddHHmmss);
        return formatBuilder.format(new Date());
    }
    /**获取制定时间格式的时间戳*/
    public static long getTimeLong(String _time)
    {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time_long=0;
        Date date;
        try {
            date = sDateFormat.parse(_time);
            time_long=date.getTime();
        } catch (ParseException e) {
        }
        return time_long;
    }

    // 字符串类型日期转化成date类型
    public static Date strToDate(String style, String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(style);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static String dateToStr(String style, Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(style);
        return formatter.format(date);
    }
    public static long getTimeMillis2yyyyMMdd()
    {
        Date date=new Date();
        Date retDate=new Date(date.getYear(),date.getMonth(),date.getDate());
        long timeMilis=retDate.getTime();
        date=null;
        retDate=null;
        return timeMilis;
    }
    public static int getAge(Date birthDate) {

        if (birthDate == null)
            throw new
                    RuntimeException("出生日期不能为null");

        int age = 0;

        Date now = new Date();

        SimpleDateFormat format_y = new
                SimpleDateFormat("yyyy");
        SimpleDateFormat format_M = new
                SimpleDateFormat("MM");

        String birth_year =
                format_y.format(birthDate);
        String this_year =
                format_y.format(now);

        String birth_month =
                format_M.format(birthDate);
        String this_month =
                format_M.format(now);

        // 初步，估算
        age =
                Integer.parseInt(this_year) - Integer.parseInt(birth_year);

        // 如果未到出生月份，则age - 1
        if
                (this_month.compareTo(birth_month) < 0)
            age -=
                    1;
        if (age <
                0)
            age =
                    0;
        return age;
    }

    /**
     * 获取前后日期 :n为正数向后推迟n天，负数时向前提前n天
     * @param n
     * @param format
     * @return
     */
    public static String getNdate(int n, String format)
    {
        Date date = null;
        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.DATE, n);
        date = cd.getTime();
        SimpleDateFormat dformat = new SimpleDateFormat(format);
        return dformat.format(date);
    }
    /**
     * 添加或减少多少天
     * @param dateTime 待处理的日期
     * @param n 加减天数
     * @return
     */
    public static Date addOrSubtractDays(Date dateTime,int n){

        //日期格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Calendar calstart = java.util.Calendar.getInstance();
        calstart.setTime(dateTime);

        calstart.add(java.util.Calendar.DAY_OF_WEEK, n);

        System.out.println(df.format(calstart.getTime()));
        return calstart.getTime();
    }
    public static String addOrSubtractDaysToString(Date dateTime,int n){

        //日期格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Calendar calstart = java.util.Calendar.getInstance();
        calstart.setTime(dateTime);

        calstart.add(java.util.Calendar.DAY_OF_WEEK, n);


        SimpleDateFormat sdf = new SimpleDateFormat(DATE4Y_TIME_SECOND, Locale.CHINA);
        String time = sdf.format(calstart.getTime().getTime());

        return time;
    }
}

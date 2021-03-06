package com.xiaochao.mycfms.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public final class DateUtil {
    private DateUtil() {
    }

    public final static String MYSQL_CURRENT_DATE_FORMAT_14 = "%Y%m%d%H%i%s";
    public final static String MYSQL_CURRENT_DATE_FORMAT_8 = "%Y%m%d";

    /**
     * @Title: format8
     * @Description: 转换成8位的日期
     * @param date 日期
     * @return
     * @author 
     */
    public static String format8(LocalDate localDate) {
        if (Optional.ofNullable(localDate).isPresent()) {
            return localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        }
        return null;
    }

    /**
     * @Title: format14
     * @Description: 转换成14位的日期
     * @param date 日期
     * @return
     * @author 
     */
    public static String format14(LocalDateTime dateTime) {
        if (Optional.ofNullable(dateTime).isPresent()) {
            return dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        }
        return null;
    }

    /**
     * @Title: parse
     * @Description: 字符串类型的日期转换成Date类型
     * @param datetime 字符串类型的日期
     * @return
     * @author 
     */
    public static LocalDate parseDate(String date) {
        if (Optional.ofNullable(date).isPresent() && 8 == date.length()) {
            try {
                return LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
            } catch (IllegalArgumentException | DateTimeParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Title: parse
     * @Description: 字符串类型的日期转换成Date类型
     * @param datetime 字符串类型的日期
     * @return
     * @author zhangweirong
     */
    public static LocalDateTime parseDateTime(String datetime) {
        if (Optional.ofNullable(datetime).isPresent() && 14 == datetime.length()) {
            try {
                return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            } catch (IllegalArgumentException | DateTimeParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Title: isFirstDayOfMonth
     * @Description: 判断是否是本月第一天
     * @return
     * @author zhangweirong
     */
    public static boolean isFirstDayOfMonth() {
        return LocalDate.now().getDayOfMonth() == 1;
    }

    /**
     * @Title: isFirstDayOfYear
     * @Description: 判断是否是本年第一天
     * @return
     * @author zhangweirong
     */
    public static boolean isFirstDayOfYear() {
        return LocalDate.now().getDayOfYear() == 1;
    }

    /**
     * @Title: addDays
     * @Description: 增加天数
     * @param datetime 字符串类型日期
     * @param days 增加或退后的天数
     * @return
     * @author zhangweirong
     */
    public static String addDays(String datetime, int days) {
        if (Optional.ofNullable(datetime).isPresent()) {
            if (0 == days) {
                return datetime;
            } else {
                LocalDate basic = parseDate(datetime.substring(0, 8));
                basic = basic.plusDays(days);
                if (8 == datetime.length()) {
                    return basic.format(DateTimeFormatter.BASIC_ISO_DATE);
                } else {
                    return basic.format(DateTimeFormatter.BASIC_ISO_DATE) + datetime.substring(8);
                }
            }
        }
        return null;
    }

    /**
     * @Title: getCurDate
     * @Description: 获取计算机系统的当前日期并格式化成8位
     * @return
     * @author zhangweirong
     */
    public static String getCurDate() {
        return LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    /**
     * @Title: getCurDateTime
     * @Description: 获取计算机系统的当前日期并格式化成14位
     * @return
     * @author zhangweirong
     */
    public static String getCurDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    /**
     * @Title: getCurDateTime
     * @Description: 获取计算机系统的当前日期并格式化成14位
     * @return
     * @author zhangweirong
     */
    public static String getCurDateTime(String parten) {
        if (null == parten || "".equals(parten)) {
            parten = "yyyy-MM-dd HH:mm:ss.SSS";
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(parten));
    }
    /**
     * @Title: getFutureDateTime
     * @Description: 获取当前时间以后的第N分钟的14位时间
     * @param minutes 分钟偏移量
     * @return
     * @author zhangweirong
     */
    public static String getFutureDateTime(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    /**
     * @Title: parseYear
     * @Description: 根据日期获取年份
     * @param date 字符串类型日期
     * @return
     * @author zhangweirong
     */
    public static String parseYear(String date) {
        if (Optional.ofNullable(date).isPresent() && (date.length() == 8 || date.length() == 14)) {
            return date.substring(0, 4);
        } else {
            return null;
        }
    }

    /**
     * @Title: parseMonth
     * @Description: 根据日期获取月份
     * @param date 字符串类型日期
     * @return
     * @author zhangweirong
     */
    public static String parseMonth(String date) {
        if (Optional.ofNullable(date).isPresent() && date.length() >= 8) {
            return date.substring(4, 6);
        } else {
            return null;
        }
    }

    /**
     * 两日期之间的天数
     * 
     * @param lastTxnDate 最后交易时间
     * @param txnDate 当前交易时间
     * @return
     */
    public static int getDayGap(String lastTxnDate, String txnDate) {
        LocalDate d1 = parseDate(lastTxnDate);
        LocalDate d2 = parseDate(txnDate);
        Period period = Period.between(d1, d2);
        return period.getDays();
    }

    /**
     * @Title: getCurDateT
     * @Description: 获取计算机系统的当前日期并格式化成8位 例如yyyy-MM-dd
     * @return
     * @author liyanjiao
     */
    public static String getCurDateT() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 返回2个日期之间的日期集合 包含起止日期
     * 
     * @param beginDate
     * @param endDate
     * @return
     * @throws TransactionException
     */
    public static List<String> getDateList(String beginDate, String endDate) {
        try {
            List<String> list = new ArrayList<String>();
            LocalDate date1 = parseDate(beginDate);
            LocalDate date2 = parseDate(endDate);
            Period period = Period.between(date1, date2);
            for (int i = 0; i <= period.getDays(); i++) {
                list.add(addDays(beginDate, i));
            }
            return list;
        } catch (Exception e) {
            return null;
            // throw new TransactionException(BCModules.ACCOUNT_SYS.getCode(), LedgersMsgId.BC11000006E.toString(),
            // LedgersMsgId.BC11000006E.getDesc());
        }

    }

    public static void main(String[] args) {
        // System.out.println("parseDate:" + (parseDate("20150101").getDayOfMonth() == 1));
        // System.out.println("parseDate:" + (parseDate("20150101").getDayOfYear() == 1));
        // System.out.println("isFirstDayOfMonth:" + isFirstDayOfMonth());
        // System.out.println("isFirstDayOfYear:" + isFirstDayOfYear());
        // System.out.println("addDays:" + addDays("20150101", 1));
        // System.out.println("getCurDate:" + getCurDate());
        // System.out.println("getCurDateTime:" + getCurDateTime());
        // System.out.println("getFutureDateTime:" + getFutureDateTime(300));
        // System.out.println("parseYear:" + parseYear("20150202"));
        // System.out.println("parseMonth:" + parseMonth("20150302"));
        // System.out.println("getDateList:" + getDateList("20151024", "20151026"));
        // System.out.println("getDayGap:" + getDayGap("20151024", "20151026"));

    }
}

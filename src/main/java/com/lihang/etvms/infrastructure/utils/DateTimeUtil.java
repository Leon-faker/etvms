package com.lihang.etvms.infrastructure.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类
 *
 * @date 2022/12/30
 **/
public class DateTimeUtil {

    /**
     * 常数 60 进制
     */
    public static final int timeScale = 60;
    /**
     * 日期-时间 格式。
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期 格式。
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YY_MM_DD = "yy-MM-dd";
    /**
     * 日期 格式。
     */
    public static final String YYYYMMDD = "yyyyMMdd";
    /**
     * 日期-时间 格式。
     */
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 日期-时间 格式。
     */
    public static final String HHMMSS = "HH:mm:ss";
    /**
     * 日期-时间 格式。
     */
    public static final String YYYYMMDDBLANKHHMMSS = "yyyyMMdd HH:mm:ss";
    private DateTimeUtil() {

    }

    /**
     * @return 字符串类型时间 -> LocalDate 类型时间。
     */
    public static LocalDate toLocalDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, formatter);
    }

    /**
     * @return 字符串类型时间 -> LocalTime 类型时间。
     */
    public static LocalTime toLocalTime(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalTime.parse(date, formatter);
    }

    /**
     * @return 字符串类型时间 -> LocalDateTime 类型时间。
     */
    public static LocalDateTime toLocalDateTime(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Date 类时间 -> 字符串格式时间。
     *
     * @param date    Date 类时间。
     * @param pattern 时间格式。
     * @return 字符串。
     */
    public synchronized static String toString(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * Date 类时间 -> 字符串格式时间。
     *
     * @param date Date 类时间。
     * @return 字符串。
     */
    public static String toString(Date date) {
        return toString(date, YYYY_MM_DD);
    }

    /**
     * LocalDateTime -> String.
     *
     * @param time    LocalDateTime
     * @param pattern 格式。
     * @return time string。
     */
    public static String toString(LocalDateTime time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(time);
    }

    /**
     * LocalDate -> String.
     *
     * @param time    LocalDate
     * @param pattern 格式。
     * @return time string。
     */
    public static String toString(LocalDate time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(time);
    }

    /**
     * LocalTime -> String.
     *
     * @param time    LocalDate
     * @param pattern 格式。
     * @return time string。
     */
    public static String toString(LocalTime time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(time);
    }
}

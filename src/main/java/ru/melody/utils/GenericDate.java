package ru.melody.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenericDate {


    // GET DATE & TIME IN ANY FORMAT

    private static final String DATE_FORMAT_NOW = "dd.MM.yyyy HH:mm:ss";
    private static final String TIME_FORMAT_HOUR_NOW = "HH";
    private static final String DATE_FORMAT_WITHOUT_TIME = "dd.MM.yyyy";
    private static final Calendar cal = Calendar.getInstance();
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    private static final SimpleDateFormat timeFormatWithoutDate = new SimpleDateFormat(TIME_FORMAT_HOUR_NOW);
    private static final SimpleDateFormat dateFormatWithoutTime = new SimpleDateFormat(DATE_FORMAT_WITHOUT_TIME);

    /**
     * Метод создания даты равной сейчас (Текущая дата)
     */
    public static String nowDate() {
        return sdf.format(cal.getTime());
    }

    /**
     * Метод создания даты равной сейчас (Текущее время в формате HH)
     */
    public static String nowHourTime() {
        return timeFormatWithoutDate.format(cal.getTime());
    }

    /**
     * Метод создания даты равной сегодня (Текущее дата в формате dd.MM.yyyy)
     */
    public static String nowDateWithoutTime() {
        return dateFormatWithoutTime.format(cal.getTime());
    }

    /**
     * Метод создания даты равной сегодня + кол-во календарных дней (Текущее дата в формате dd.MM.yyyy)
     * @param quantityDaysForShifting кол-во календарных дней которое нужно прибавить к текущей дате
     */
    public static String getDateWithDaysShifting(Integer quantityDaysForShifting) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, quantityDaysForShifting);
        return dateFormatWithoutTime.format(calendar.getTime());
    }


    /**
     * Метод создания даты равной завтра
     */
    public static String tomorrowDate() {
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(cal.getTime());
    }

    /**
     * Метод создания даты равной вчера
     */
    public static String yesterdayDate() {
        cal.add(Calendar.DAY_OF_MONTH, -2);
        return dateFormatWithoutTime.format(cal.getTime());
    }

    /**
     * Метод создания даты равной завтра в формате без указания времени - dd.MM.yyyy
     */

    public static  String tomorrowDateWithoutTime() {
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return dateFormatWithoutTime.format(cal.getTime());
    }


}

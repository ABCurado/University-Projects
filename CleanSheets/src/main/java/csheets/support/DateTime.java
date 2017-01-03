package csheets.support;

/**
 *
 * @author Paulo Gandra Sousa
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DateTime {

    private static final int DAYS_TILL_END_OF_WEEK = 6;

    /**
     * checks if the date in both calendar instances are the same. time is not
     * compared!
     *
     * @param a date to be checked.
     * @param b date to be checked against
     * @return True if the dates are equal, false otherwise.
     */
    public static boolean isSameDate(final Calendar a, final Calendar b) {
        return (a.get(Calendar.YEAR) == b.get(Calendar.YEAR)) && (a.
                get(Calendar.MONTH) == b.get(Calendar.MONTH)) && (a.
                get(Calendar.DAY_OF_MONTH) == b.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Checks if a date is previous to another. Time is not compared!
     *
     * @param a date to be checked
     * @param b date to be checked against
     * @return True if the first date occurred before the second date.
     */
    public static boolean isPreviousDate(final Calendar a, final Calendar b) {
        Calendar dateA = new GregorianCalendar(a.get(Calendar.YEAR), a.
                get(Calendar.MONTH), a.
                get(Calendar.DAY_OF_MONTH));
        Calendar dateB = new GregorianCalendar(b.get(Calendar.YEAR), b.
                get(Calendar.MONTH), b.
                get(Calendar.DAY_OF_MONTH));
        return (dateA.before(dateB));
    }

    /**
     * Checks if a date is after another. Time is not compared!
     *
     * @param a date to be checked
     * @param b date to be checked against
     * @return True if the first date occurred after the second date.
     */
    public static boolean isFutureDate(final Calendar a, final Calendar b) {
        Calendar dateA = new GregorianCalendar(a.get(Calendar.YEAR), a.
                get(Calendar.MONTH), a.
                get(Calendar.DAY_OF_MONTH));
        Calendar dateB = new GregorianCalendar(b.get(Calendar.YEAR), b.
                get(Calendar.MONTH), b.
                get(Calendar.DAY_OF_MONTH));
        return (dateA.after(dateB));
    }

    /**
     * checks if the two calendar instances represent dates of the same year.
     *
     * @param a date to be checked
     * @param b date to be checked against
     * @return True if the dates are in the same year.
     */
    public static boolean isSameYear(final Calendar a, final Calendar b) {
        return a.get(Calendar.YEAR) == b.get(Calendar.YEAR);
    }

    /**
     * checks if the two calendar instances represent dates of the same month
     * (regardless of the year).
     *
     * @param a date to be checked
     * @param b date to be checked against
     * @return True if the dates are in the same month.
     */
    public static boolean isSameMonth(final Calendar a, final Calendar b) {
        return a.get(Calendar.MONTH) == b.get(Calendar.MONTH);
    }

    /**
     * returns the current date of the system
     *
     * @return The current calendar.
     */
    public static Calendar now() {
        return new GregorianCalendar();
    }

    /**
     * returns the number of the week in the year given a certain date
     *
     * @param date Calendar date.
     * @return The week number of the given date.
     */
    public static int weekNumber(final Calendar date) {
        return date.get(Calendar.WEEK_OF_YEAR);
    }

    public static Calendar dateToCalendar(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static int currentWeekNumber() {
        return weekNumber(now());
    }

    public static Calendar dateCurrentWeekDay(int dayWeek) {
        int year = DateTime.year(DateTime.now());
        int week = DateTime.currentWeekNumber();
        Calendar date = DateTime.beginningOfWeek(year, week);
        date.set(Calendar.DAY_OF_WEEK, dayWeek);
        return date;
    }

    /**
     * returns the date of the first day of a week
     *
     * @param year Year.
     * @param week Week number.
     * @return The date of the beginning of the week of the given year.
     */
    public static Calendar beginningOfWeek(final int year, final int week) {
        final Calendar date = new GregorianCalendar();
        date.clear();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.WEEK_OF_YEAR, week);
        return date;
    }

    /**
     * returns the date of the last day of a week
     *
     * @param year Year.
     * @param week Week number.
     * @return The date of the end of the week of the given year.
     */
    public static Calendar endOfWeek(final int year, final int week) {
        final Calendar date = beginningOfWeek(year, week);
        date.add(Calendar.DATE, DAYS_TILL_END_OF_WEEK);
        return date;
    }

    /**
     * returns the date of the last day of the current month
     *
     * @return The end of the current month.
     */
    public static Calendar endOfCurrentMonth() {
        return endOfMonth(now());
    }

    /**
     * returns the date of the last day of a certain month
     *
     * @param reference a date to be used as reference month
     * @return The end of the given date month.
     */
    public static Calendar endOfMonth(final Calendar reference) {
        final Calendar lastDay = new GregorianCalendar();
        lastDay.setTime(reference.getTime());
        final int last = lastDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        lastDay.set(Calendar.DAY_OF_MONTH, last);
        return lastDay;
    }

    public static Calendar endOfMonth(final int year, final int month) {
        final Calendar date = newCalendar(year, month, year);
        return endOfMonth(date);
    }

    public static int currentYear() {
        return now().get(Calendar.YEAR);
    }

    public static int day(Calendar date) {
        return date.get(Calendar.DAY_OF_MONTH);
    }

    public static int month(Calendar date) {
        return date.get(Calendar.MONTH) + 1;
    }

    public static int year(Calendar date) {
        return date.get(Calendar.YEAR);
    }

    public static int hour(Calendar date) {
        return date.get(Calendar.HOUR_OF_DAY);
    }

    public static int min(Calendar date) {
        return date.get(Calendar.MINUTE);
    }

    public static boolean overlappingDates(Calendar start1, Calendar end1,
            Calendar start2, Calendar end2) {
        return (DateTime.isSameDate(start1, end2) || DateTime.
                isPreviousDate(start1, end2)) && (DateTime.isSameDate(start2, end1) || DateTime.
                isPreviousDate(start2, end1));
    }

    /**
     * returns the current month of the year
     *
     * @return current month (1 - 12) of the year
     */
    public static int currentMonth() {
        return now().get(Calendar.MONTH) + 1;
    }

    /**
     * Creates a new Calendar object set to a specific date
     *
     * @param year the year
     * @param month the month (1 - 12)
     * @param day the day of the month
     * @return a newly create Calendar object
     */
    public static Calendar newCalendar(final int year, final int month,
            final int day) {
        return new GregorianCalendar(year, month - 1, day);
    }

    /**
     * Creates a new Calendar object set to a specific date and time
     *
     * @param year the year
     * @param month the month (1 - 12)
     * @param day the day of the month
     * @param hour hour
     * @param minute minute
     * @param second second
     * @return a newly create Calendar object
     */
    public static Calendar newCalendar(final int year, final int month,
            final int day, final int hour,
            final int minute, final int second) {
        return new GregorianCalendar(year, month - 1, day, hour, minute, second);
    }

    public static String format(final Calendar ocurrs) {
        return format(ocurrs, "YYYY/MM/dd");
    }

    public static String format(final Calendar ocurrs, String dateFormat) {
        final SimpleDateFormat formater = new SimpleDateFormat(dateFormat);
        return formater.format(ocurrs.getTime());
    }

    private DateTime() {
        // to make sure this is an utility class
    }

    public static boolean isBetweenDates(Calendar start, Calendar end,
            Calendar date) {

        return (DateTime.isSameDate(date, start) || DateTime.
                isSameDate(date, end))
                || (DateTime.isFutureDate(date, start) && DateTime.
                isPreviousDate(date, end));
    }

    /**
     * Compares the hour element and returns true if hour from date1 is before
     * or the same as hour from date 2
     *
     * @param date1 First date.
     * @param date2 Second date.
     * @return True if the hour of the first date is less or equal to the second date hour.
     */
    public static boolean isTimeBeforeOrEqual(Calendar date1, Calendar date2) {

        int hour1 = date1.get(Calendar.HOUR_OF_DAY);
        int hour2 = date2.get(Calendar.HOUR_OF_DAY);
        return (hour1 <= hour2);
    }
}

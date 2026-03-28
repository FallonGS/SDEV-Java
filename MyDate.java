import java.util.GregorianCalendar;

public class MyDate {
    private int year;
    private int month;
    private int day;

    // no arg constructor
    public MyDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // constructor with elapsed time in milliseconds since January 1 1970
    public MyDate(long elapsedTime) {
        setDate(elapsedTime);
    }

    // constructor with year, month and  day
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // three getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // set date using elapsed time
    public void setDate(long elapsedTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);

        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }
}

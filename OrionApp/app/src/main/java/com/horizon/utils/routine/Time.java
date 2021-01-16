package com.horizon.utils.routine;



import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A class that defines the time format and utils for the routines.
 */
public class Time {
    private final int h;
    private final int min;

    public Time(int h, int min) {
        this.h = h;
        this.min = min;
    }

    /**
     * @param timeFormatArr - [hh, mm]
     */
    public Time(String[] timeFormatArr) {
        this(Integer.parseInt(timeFormatArr[0]), Integer.parseInt(timeFormatArr[1]));
    }

    public int getHour() {
        return this.h;
    }

    public int getMinute() {
        return this.min;
    }

    /**
     * @return A formatted string {+hh:mm / -hh:mm} that contains the time 
     * offset relative to GMT.
     */
    public static String getTimeZoneParam() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
                Locale.getDefault());
        Date currentLocalTime = calendar.getTime();
        DateFormat date = new SimpleDateFormat("Z");
        return date.format(currentLocalTime);
    }

    @Override
    public String toString() {
        return String.format("%2d:%2d", this.h, this.min);
    }
}

/*
 * TimeDiff.java
 * $Revision$
 * 
 * Copyright 2008 Patrick Mairif.
 * The program is distributed under the terms of the Apache License (ALv2).
 *
 * charset=utf-8, tabstop=4
 */
package net.sf.jcprogress.utils;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * represents a period of time
 *
 * @author pmairif
 */
public final class TimeDiffUtils {

    private float seconds;
    private long minutes;
    private long hours;
    private long days;

    private int secondsFractionDigits = 3;

    public TimeDiffUtils(final long millis) {
        this.days = TimeUnit.MILLISECONDS.toDays(millis);
        this.hours = TimeUnit.MILLISECONDS.toHours(millis) % 24;
        this.minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        this.seconds = (TimeUnit.MILLISECONDS.toSeconds(millis) % 60) + ((millis % 1000) / 1000f);
    }

    /**
     * @return the days
     */
    public long getDays() {
        return days;
    }

    /**
     * @return the hours
     */
    public long getHours() {
        return hours;
    }

    /**
     * @return the minutes
     */
    public long getMinutes() {
        return minutes;
    }

    /**
     * @return the seconds
     */
    public float getSeconds() {
        return seconds;
    }

    public int getSecondsFractionDigits() {
        return this.secondsFractionDigits;
    }

    public void setSecondsFractionDigits(int secondsFractionDigits) {
        this.secondsFractionDigits = secondsFractionDigits;
    }

    @Override
    public String toString() {
        return toString(Locale.getDefault());
    }

    public String toString(Locale locale) {
        StringBuilder b = new StringBuilder();
        NumberFormat nf = NumberFormat.getInstance(locale);
        nf.setMaximumFractionDigits(secondsFractionDigits);

        if (0 == days && 0 == hours && 0 == minutes && 0f == seconds)
            b.append("0s"); //$NON-NLS-1$
        else {
            boolean ws = false;
            if (days > 0) {
                b.append(days).append("d"); //$NON-NLS-1$
                ws = true;
            }
            if (hours > 0) {
                if (ws) b.append(" "); //$NON-NLS-1$
                b.append(hours).append("h"); //$NON-NLS-1$
                ws = true;
            }
            if (minutes > 0) {
                if (ws) b.append(" "); //$NON-NLS-1$
                b.append(minutes).append("min"); //$NON-NLS-1$
                ws = true;
            }
            if (seconds > 0) {
                if (ws) b.append(" "); //$NON-NLS-1$
                b.append(nf.format(seconds)).append("s"); //$NON-NLS-1$
            }
        }

        return b.toString();
    }
}

/*
 * ConsoleProgressBarThread.java
 * 
 * Copyright 2008-2009 Patrick Mairif.
 * The program is distributed under the terms of the Apache License (ALv2).
 * 
 * charset=utf-8, tabstob=4
 */
package net.sf.jcprogress;

import net.sf.jcprogress.abs.ConsoleProgressThreadBase;

import java.io.PrintStream;
import java.util.Locale;

/**
 * @author pmairif
 */
public final class ConsoleProgressBarThread extends ConsoleProgressThreadBase {

    public ConsoleProgressBarThread(PrintStream out, ProgressStatusProvider statusTextProvider) {
        this(out, statusTextProvider, Locale.getDefault());
    }

    /**
     * create new progress bar and set the counter to be reached.
     */
    public ConsoleProgressBarThread(PrintStream out, ProgressStatusProvider statusTextProvider, Locale locale) {
        super(out, statusTextProvider, locale);

        this.barSize = 40;
    }

    /**
     * progress bar on console.
     */
    @Override
    protected void show() {
        if (statusProvider != null) {
            printLocation.print("\r");                //go to begin of line //$NON-NLS-1$
            showProgressBar();
            showPercentage();
            showCounter();
            showStatusText();

            if (hasData()) {
                if (isStalled()) {
                    showStalled();
                } else {
                    showEndDate();
                    showRemainingTime();
                }
            }
        }
    }

    protected void showProgressBar() {
        float percentage = getCurrentPercentage();
        if (percentage > 1)
            percentage = 1;
        int chars = (int) (percentage * barSize);    //Anzahl Zeichen auf Balken

        printLocation.print("["); //$NON-NLS-1$
        for (int i = 0; i < chars; i++) {
            printLocation.print("#");             //$NON-NLS-1$
        }

        if (chars < barSize)
            printLocation.print(charIndicator.getNextChar());

        for (int i = chars + 1; i < barSize; i++) {
            printLocation.print(".");             //$NON-NLS-1$
        }
        printLocation.print("]"); //$NON-NLS-1$
    }

    /**
     * resize the bar length
     *
     * @param b number of characters for the bar
     */
    public void setSize(int b) {
        barSize = b;
    }

    private int barSize;
}

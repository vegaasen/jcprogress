/*
 * Checkpoint.java (jcprogress)
 *
 * Copyright 2009 Patrick Mairif.
 * The program is distributed under the terms of the Apache License (ALv2).
 *
 * tabstop=4, charset=UTF-8
 */
package net.sf.jcprogress;

/**
 * checkpoint
 *
 * @author pmairif
 */
public final class Checkpoint {

    private final long startElapsedMs;

    private final int startProgessCount;

    private final ProgressCalculator calculator;

    public Checkpoint(int progressWhole, int progressCurrent, long elapsedMs, int stalledTimeWindowMs) {
        this(progressWhole, progressCurrent, elapsedMs, stalledTimeWindowMs, System.currentTimeMillis());
    }

    public Checkpoint(int progressWhole, int progressCurrent, long elapsedMs, int stalledTimeWindowMs, long now) {
        int progressRemaining = progressWhole - progressCurrent;

        this.startElapsedMs = elapsedMs;
        this.startProgessCount = progressCurrent;
        this.calculator = new ProgressCalculator(now, progressRemaining, stalledTimeWindowMs);
    }

    public void setProgressCount(int currentCount) {
        calculator.setProgressCount(currentCount - startProgessCount);
    }

    public void setProgressWhole(int wholeCount) {
        calculator.setProgressWhole(wholeCount - startProgessCount);
    }

    public long estimateWholeTime() {
        return calculator.estimateWholeTime() + startElapsedMs;
    }

    public long estimateEndTime() {
        return this.calculator.estimateEndTime();
    }

    public ProgressCalculator getCalculator() {
        return this.calculator;
    }
}

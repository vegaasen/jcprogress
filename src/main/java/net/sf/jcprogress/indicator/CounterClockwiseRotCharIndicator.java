/*
 * RotCharIndicator.java
 * $Revision$
 * 
 * Copyright 2008 Patrick Mairif.
 * The program is distributed under the terms of the Apache License (ALv2).
 * 
 * tabstop=4, charset=UTF-8
 */
package net.sf.jcprogress.indicator;

import net.sf.jcprogress.abs.CharIndicator;

/**
 * @author pmairif
 */
public final class CounterClockwiseRotCharIndicator extends CharIndicator {

    private static final String rotChars = "\\-/|"; //$NON-NLS-1$

    public CounterClockwiseRotCharIndicator() {
        super();
    }

    @Override
    public char getNextChar() {
        return getNextChar(rotChars);
    }

}

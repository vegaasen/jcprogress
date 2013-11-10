/*
 * BubbleCharIndicator.java
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
public final class BubbleCharIndicator extends CharIndicator {

	private static final String rotChars = ".oOo"; //$NON-NLS-1$

	public BubbleCharIndicator() {
		super();
	}

	@Override
	public char getNextChar() {
		return getNextChar(rotChars);
	}
}

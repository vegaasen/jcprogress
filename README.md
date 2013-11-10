Java Console Progress Indicator
==============

#Information

This is a pure java library for easily displaying different progress indicators on the console.

# Getting started

You can simply use the demo located at the following package-level:

    net.sf.jcprogress.demo.ConsoleDemo

# Usage

## Console Progressbar

    ConsoleProgressThreadBase progress = new ConsoleProgressBarThread(SomeRandom<PrintStream>);
    progress.start();
    //your logic that should be measured goes here.
    progress.waitToStop();
package net.sf.jcprogress.abs;

import net.sf.jcprogress.ConsoleProgressBarThread;
import net.sf.jcprogress.ProgressStatusProvider;

import java.io.PrintStream;
import java.util.logging.Logger;

/**
 * @author <a href="vegaasen@gmail.com">vegardaasen</a>
 */
public abstract class AbstractProvider implements ProgressStatusProvider {

    private static final Logger LOG = Logger.getLogger(AbstractProvider.class.getName());

    public String name = EMPTY;
    public int curr = 0;
    public int noOfElements = 0;
    private PrintStream stream;
    private ConsoleProgressThreadBase threadBase;

    public AbstractProvider() {
    }

    public AbstractProvider(final String name) {
        this.name = String.format("'%s'", name);
    }

    public void initializeDefault() {
        LOG.info("Using defaults. Setting stream source to System.out, and configuring the threadbase with ConsoleProgressBarThread.");
        stream = new PrintStream(System.out);
        threadBase = new ConsoleProgressBarThread(stream, this);
    }

    @Override
    public String getProgressStatusText() {
        return name;
    }

    @Override
    public int getCurrentProgressCount() {
        return curr;
    }

    @Override
    public int getWholeProcessCount() {
        return noOfElements;
    }

    public void increment() {
        curr++;
    }

    public void decrement() {
        curr--;
    }

    /**
     * Mimics the same as getWholeProcessCount(). This is mainly there to avoid confusion regarding conventions.
     * "setExpectedNumber(n)" => "getWholeProcessCount()". Makes no sense.
     *
     * @return number of elements to walk through
     */
    public int getExpectedNumber() {
        return getWholeProcessCount();
    }

    public void setExpectedNumber(final int num) {
        this.noOfElements = num;
    }

    public void setStream(final PrintStream stream) {
        this.stream = stream;
    }

    public void setThreadBase(final ConsoleProgressThreadBase threadBase) {
        this.threadBase = threadBase;
    }

    public abstract void task() throws Exception;

    public void run() {
        if (threadBase == null) {
            LOG.warning("Run has been called without initializing. Using defaults instead (this.initializeDefault()).");
            initializeDefault();
        }
        threadBase.start();
        try {
            task();
        } catch (final Exception e) {
            LOG.severe(String.format("Unable to perfom the task. Reason:\n%s", e.getMessage()));
        }
        threadBase.waitToStop();
    }

}

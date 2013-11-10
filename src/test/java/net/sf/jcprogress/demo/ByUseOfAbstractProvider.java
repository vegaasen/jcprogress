package net.sf.jcprogress.demo;

import net.sf.jcprogress.ConsoleProgressBarThread;
import net.sf.jcprogress.abs.AbstractProvider;

/**
 * @author <a href="vegaasen@gmail.com">vegardaasen</a>
 */
public final class ByUseOfAbstractProvider {

    public static class Defaults extends AbstractProvider {

        private static final long SLEEP_TIME = 100L;

        public static void main(final String... args) {
            final Defaults provider = new Defaults();
            provider.run();
        }

        @Override
        public void task() throws Exception {
            setExpectedNumber(50); // <---- IMPORTANT
            for (; curr < getExpectedNumber(); increment()) { // <---- IMPORTANT
                myPointlessAndMeasuredLogic();
            }
        }

        private void myPointlessAndMeasuredLogic() throws InterruptedException {
            Thread.sleep(SLEEP_TIME);
        }

    }

    public static class DefaultsWithName extends AbstractProvider {

        public DefaultsWithName(String name) {
            super(name);
        }

        public static void main(String... args) {
            DefaultsWithName provider = new DefaultsWithName("LOL WTF");
            provider.run();
        }

        @Override
        public void task() {
            setExpectedNumber(50);
            for (curr = 0; curr < noOfElements; curr++) {
                myPointlessAndMeasuredLogic();
            }
        }

        private void myPointlessAndMeasuredLogic() {
            int wtf = 1 + curr;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class SpecifiallySettingOutSource extends AbstractProvider {

        public static void main(String... args) {
            Defaults provider = new Defaults();
            provider.setThreadBase(new ConsoleProgressBarThread(System.err, provider));
            provider.run();
        }

        @Override
        public void task() {
            setExpectedNumber(50);
            for (curr = 0; curr < noOfElements; curr++) {
                myPointlessAndMeasuredLogic();
            }
        }

        private void myPointlessAndMeasuredLogic() {
            int wtf = 1 + curr;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

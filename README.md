Java Console Progress Indicator
==============

#Information

This is a pure java library for easily displaying different progress indicators on the console.

# Getting started

You can simply use the demo located at the following package-level:

    net.sf.jcprogress.demo.ConsoleDemo

# Usage

## Console Progressbar

The simplest way to do this is by extending the class named

    net.sf.jcprogress.abs.AbstractProvider

Simplest example (yet) possible:

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

### Important things

There are two things to notice from the example (---^). The first thing is that we're setting the countAll property to the actual size of the
measured element. If this is not done, there is nothing to measure - so this is the first requirement. The second thing to notice
is the incrementing of the count-attribute. This tracks how long in the process it has been traveling.
So, this basically cooks down to two things:

* Always initialize the with an expected number (the number you're working towards - measurable) using setExpectedNumber(num)
* Always increment the current ongoing number of elements with calling increment(). Decrement() is also supported, if needed..


# Acknowledgements

* Patrick Mairif (guy that created this awsome stuff)
* Vegard Aasen (guy that just made it a bit easier to use + removing all stuff that could be removed :P)

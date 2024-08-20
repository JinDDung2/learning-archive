package thread.start.test;

import util.MyLogger;

import static util.MyLogger.*;

public class StartTest1Main {

    public static void main(String[] args) throws InterruptedException {

        CounterThread thread = new CounterThread();
        thread.start();

    }

    static class CounterThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                log("run()" + " value:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

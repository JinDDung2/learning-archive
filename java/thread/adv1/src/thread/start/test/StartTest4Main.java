package thread.start.test;

import static util.MyLogger.log;

public class StartTest4Main {

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(new CounterRunnable(
                "A",
                1000
        ), "Thread-A");

        Thread threadB = new Thread(new CounterRunnable(
                "B",
                500
        ), "Thread-B");

        threadA.start();
        threadB.start();

    }

    static class CounterRunnable implements Runnable{

        private final String name;
        private final int time;

        public CounterRunnable(String name, int time) {
            this.name = name;
            this.time = time;
        }

        @Override
        public void run() {
            while (true) {
                log(name);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

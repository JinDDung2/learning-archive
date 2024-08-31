package thread.yield;

import thread.start.HelloRunnable;

import static util.ThreadUtils.sleep;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        /**
         * Empty: 특정 스레드가 쭉~ 수행된 다음 다른 스레드가 실행
         * sleep(): RUNNABLE -> TIMED_WATTING == 스레드가 CPU 자원을 사용하지 않고, 실행 스케줄리에서 잠시 제외 됨.
         * 다시 TIMED-WATTING -> RUNNABLE 상태가 되면 실행 스케줄링에 포함
         * yield(): RUNNABLE 상태일 때, RUNNING과 READY가 있는데 yield하면 RUNNING -> READY가 됨. 자바에서 두 상태를 구분할 수는 없음.
         */

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // 1. empty
//                sleep(1); // 2. sleep
                Thread.yield(); // 3. yield
            }
        }
    }

}

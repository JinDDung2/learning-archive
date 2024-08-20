package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV4 {

    public static void main(String[] args) throws InterruptedException {
        log("start");
        SumTask sumTask1 = new SumTask(0, 50);
        Thread thread1 = new Thread(sumTask1, "Thread-1");

        thread1.start();

        // 스레드 종료될 때 까지 대기
        log("join() - main 스레드가 thread-1 종료까지 1초 대기");
        thread1.join(1000);

        log("task1 = " + sumTask1.result);

        log("end");
    }

    static class SumTask implements Runnable {

        int start;
        int end;
        int result;

        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            result = sum;
            log("작업 종료 result = " + result);
        }
    }

}

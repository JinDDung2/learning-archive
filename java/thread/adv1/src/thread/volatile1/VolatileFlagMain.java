package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        thread.start();

        sleep(1000);
        log("runFlag true에서 false로 변경 시도");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);

    }

    static class MyTask implements Runnable {

        // boolean runFlag = true;
        // 캐시메모리가 바뀌는 시점이 컨택스트 스위칭인데 항상은 아님.
        volatile boolean runFlag = true; // 캐시 메모리 뭇시하고 메인 메모리를 직접 읽음. 단 성능이 느려지는 단점

        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
                // runFlag가 false로 변하면 탈출
            }
            log("task 종료");
        }
    }

}

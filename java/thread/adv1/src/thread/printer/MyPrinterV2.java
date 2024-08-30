package thread.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV2 {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료 (q): ");;
            String input = userInput.nextLine();
            if (input.equals("q")) {
                printer.work = false; // 이거 필요 없어보이는데?
                printerThread.interrupt(); // interrupt -> sleep() 상태에서 빠져나온다.
                break;
            }

            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {

        volatile boolean work = true;
        Queue<String> jonQueue = new ConcurrentLinkedDeque<>();

        @Override
        public void run() {
            while (work) {
                if (jonQueue.isEmpty()) {
                    continue;
                }

                try {
                    String job = jonQueue.poll();
                    log("출력 시작: " + job + ", 대기 문서: " + jonQueue);
                    Thread.sleep(3000);
                    log("출력 완료");
                } catch (InterruptedException e) {
                    log("인터럽트");
                    break;
                }
            }

            log("프린트 완료");
        }

        public void addJob(String input) {
            jonQueue.offer(input);
        }
    }
}

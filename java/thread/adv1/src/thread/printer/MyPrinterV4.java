package thread.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

import static util.MyLogger.log;

public class MyPrinterV4 {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료 (q): ");;
            String input = userInput.nextLine();
            if (input.equals("q")) {
                printerThread.interrupt(); // interrupt -> sleep() 상태에서 빠져나온다.
                break;
            }

            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {

        Queue<String> jonQueue = new ConcurrentLinkedDeque<>();

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                if (jonQueue.isEmpty()) {
                    Thread.yield(); // 추가
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

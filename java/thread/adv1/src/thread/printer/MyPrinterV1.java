package thread.printer;

import util.MyLogger;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

import static util.MyLogger.*;
import static util.ThreadUtils.sleep;

public class MyPrinterV1 {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료 (q): ");;
            String input = userInput.nextLine();
            if (input.equals("q")) {
                printer.work = false;
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

                String job = jonQueue.poll();
                log("출력 시작: " + job + ", 대기 문서: " + jonQueue);
                // 이 부분때메 'q'를 입력해도 최대 3초까지 기달려야함. -> 반응이 느림
                sleep(3000);
                log("출력 완료");
            }

            log("프린트 완료");
        }

        public void addJob(String input) {
            jonQueue.offer(input);
        }
    }
}

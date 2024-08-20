package thread.start;

import util.MyLogger;

import static util.MyLogger.*;

public class MainThreadMainV1 {

    public static void main(String[] args) {
        log("main() start");

        HelloRunnable helloRunnable = new HelloRunnable();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(helloRunnable);
            thread.start();
        }

        log("main() end");
    }

}

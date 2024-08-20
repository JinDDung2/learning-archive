package thread.start;

public class HelloThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThreadV1 = new HelloThread();
        HelloThread helloThreadV2 = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        helloThreadV1.start();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        helloThreadV2.start();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

}

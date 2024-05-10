package sbu.cs;

abstract class person {
    abstract void eat();
}

public class test extends Thread {


    public void run() {
        for (int i = 1; i < 5; i++) {
            System.out.println(i +Thread.currentThread().getName());
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        test t1 = new test();
        test t2 = new test();
        t1.setPriority(1);
        t2.setPriority(10);
        t1.start();
        t2.start();






    }
}
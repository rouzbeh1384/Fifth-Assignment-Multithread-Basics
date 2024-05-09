package sbu.cs;

import java.util.*;

public class TaskScheduler
{
    public static class Task implements Runnable
    {
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */
        String taskName;
        int processingTime;

        public Task(String taskName, int processingTime) {
            this.taskName = taskName;
            this.processingTime = processingTime;
        }
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */

        @Override
        public void run() {
            /*
            TODO
                Simulate utilizing CPU by sleeping the thread for the specified processingTime
             */
            try {
                Thread.sleep(processingTime);
            }catch (Exception e){

            }
        }
    }

    public static ArrayList<String> doTasks(ArrayList<Task> tasks) {
        ArrayList<String> finishedTasks = new ArrayList<>();
        int i, j;
        int n = tasks.size();
        for (i = 0; i < n - 1; i++) {

            for (j = 0; j < n - i - 1; j++) {
                if (tasks.get(j).processingTime < tasks.get(j + 1).processingTime) {
                    Task t;
                    t = tasks.get(j);
                    tasks.set(j, tasks.get(j + 1));
                    tasks.set(j + 1, t);

                }
            }
        }


            for (Task task : tasks) {
                Thread thread = new Thread(task);
                thread.start();
                try {
                    thread.join();
                    finishedTasks.add(task.taskName);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }





        /*
        TODO
            Create a thread for each given task, And then start them based on which task has the highest priority
            (highest priority belongs to the tasks that take more time to be completed).
            You have to wait for each task to get done and then start the next task.
            Don't forget to add each task's name to the finishedTasks after it's completely finished.
         */

            return finishedTasks;

    }
//    public static void main(String[] args) {
//        // Test your code here
//    }
//    public static class SleepThread extends Thread {
////        public void run() {
////            try {
////                Thread.sleep(10000);
////            } catch (InterruptedException e) {
////                System.out.println("Thread was interrupted!");
////            } finally {
////                System.out.println("Thread will be finished here!!!");
////            }
////        }
////    }
////
////    public static void main(String[] args) {
////        SleepThread thread = new SleepThread();
////        thread.start();
////        thread.interrupt();
////    }
//public static class DirectRunnable implements Runnable {
//    public void run() {
//        System.out.println("Running in: " + Thread.currentThread().getName());
//    }
//}
//
//
//        public static void main(String[] args) {
//            DirectRunnable runnable = new DirectRunnable();
//            runnable.run();
//        }

    public static class JoinThread extends Thread {
        public void run() {
            System.out.println("Running in: " + Thread.currentThread().getName());
        }
    }


        public static void main(String[] args) {
            JoinThread thread = new JoinThread();
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Back to: " + Thread.currentThread().getName());
        }

}


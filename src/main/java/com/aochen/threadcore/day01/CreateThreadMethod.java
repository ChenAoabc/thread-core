package com.aochen.threadcore.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 * description create thead method
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public class CreateThreadMethod {
    // main
    public static void main(String[] args) {
        testCreateThreadByExtend();
        // testCreateThreadByRunnable();
        // testCreateThreadByCallable();
        // testThreadPoolSpread();
    }

    /******************* method-1: by extends Thread class*****************************/
    static class TestThread extends Thread {
        @Override
        public void run() {
            System.out.println(10+15);
        }
    }
    private static void testCreateThreadByExtend() {
        new TestThread().start();
    }

    /******************* method-2: by imp Runnable interface and New Thread()*****************************/
    private static void testCreateThreadByRunnable() {
        Runnable runnable = () -> System.out.println(10+15);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /******************* method-3: by impl Callable interface*****************************/
    private static void testCreateThreadByCallable() {
        // 使用Callable创建线程需要借助FutureTask进行封装
        Callable<Integer> callable = () -> 10+15;
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    /******************* method-4: by Thread Pool get thread *****************************/
    private static void testThreadPoolSpread() {
        // not use thread:20063
        // use thread:4057
        // 自定义线程
        ExecutorService poolExecutor = new ThreadPoolExecutor(5, 10, 500,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        // 使用SingleThread池--使用一个工作线程执行任务、可以确保任务执行顺序
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        long before = System.currentTimeMillis();
        try {
            testThread(singleThreadExecutor);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 不使用线程执行任务
//        for (int i = 0; i < 10; i++) {
//            task();
//        }
        long after = System.currentTimeMillis();
        System.out.println("time:"+ (after - before));
//        Date after = new Date();
//        System.out.println("after"+dateFormat.format(after));
    }

    private static void testThread(ExecutorService poolExecutor) throws ExecutionException, InterruptedException {

        List<Future<?>> result=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<?> submit = poolExecutor.submit(CreateThreadMethod::task);
            result.add(submit);
        }
        for (Future<?> future : result) {
            future.get();
        }
        poolExecutor.shutdown();

    }

    static Integer task(){
        System.out.println("执行任务");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

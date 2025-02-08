package com.aochen.threadcore.day03.code;

/**
 * <p>
 * description xxx
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public class ThreadLifeExample {
    public static void main(String[] args) {
        test();


    }

    private static void test() {
        // 此时线程属于：创建状态
        Thread example = new XThread();
        System.out.println(example.getState());
        example.start();
        System.out.println(example.getState());
    }
}

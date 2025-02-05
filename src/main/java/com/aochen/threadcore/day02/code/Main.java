package com.aochen.threadcore.day02.code;

import java.util.LinkedList;

/**
 * <p>
 * description xxx
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public class Main {
    public static void main(String[] args) {
        test01();

    }

    private static void test01() {
        // 创建缓冲区
        BufferResource resource = new BufferResource(new LinkedList<>());

        // 分别创建10个消费者和生产者进行生产和消费
        for (int i = 0; i < 10; i++) {
            new Producter(resource).start();
        }

        for (int i = 0; i < 10; i++) {
            new Consumer(resource).start();
        }
    }
}

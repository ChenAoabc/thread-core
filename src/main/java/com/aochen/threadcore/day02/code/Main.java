package com.aochen.threadcore.day02.code;

import com.aochen.threadcore.day02.code.buffer.*;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <p>
 * description 主方法：用于测试使用
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public class Main {
    public static void main(String[] args) {
        test01();

    }

    private static void test01() {
        // 创建Object方式的缓冲区
         BufferResource resource = new ObjectBufferResource(new LinkedList<>());
        // 创建Lock方式的缓冲区
        // BufferResource resource = new LockBufferResource(new LinkedList<>());
        // 创建blockQueue方法的缓冲区
        // BufferResource resource = new BlockingQueueBufferResource(new LinkedBlockingDeque<>(BaseBufferResource.MAX_CAPACITY));

        // 分别创建10个消费者和生产者进行生产和消费
        for (int i = 0; i < 10; i++) {
            new Producter(resource).start();
        }

        for (int i = 0; i < 10; i++) {
            new Consumer(resource).start();
        }
    }
}

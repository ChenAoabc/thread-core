package com.aochen.threadcore.day02.code;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * description 缓冲资源类
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public class BufferResource {
    /**
     * 缓冲区的最大容量capacity
     */
    private static final int MAX_CAPACITY = 10;
    /**
     * 缓冲区域
     */
    private  Queue<Integer> queue;

    /**
     * 创建对象时，必须指定队列
     *
     * @param queue 队列
     */
    public BufferResource(Queue<Integer> queue) {
        this.queue =queue;
    }

    /**
     * <p>
     * 消费者方法
     * </p>
     *
     * @author ao.chen02@hand-china.com 2025/2/5 17:05
     */
    public synchronized void consume() {
        // 如果缓冲区中没有数据则等待
        while (queue.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + "当前缓冲区为空，等待生产。。。");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 消费数据
        Integer poll = queue.remove();
        System.out.println(Thread.currentThread().getName() + "消费成功，消费内容：" + poll+",当前缓冲区大小："+queue.size());
        notifyAll();
    }

    public synchronized void product(Integer value) {
        while (queue.size() == MAX_CAPACITY) {
            System.out.println(Thread.currentThread().getName() + "当前缓冲区已满，等待消费。。。。");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 生产数据
        queue.add(value);
        // 生产的时候增加点延迟。。模拟生产会影响到消费
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "生产成功，生产数据：" + value+",当前缓冲区大小："+queue.size());
        notifyAll();
    }

}

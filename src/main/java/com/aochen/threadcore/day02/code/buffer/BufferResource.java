package com.aochen.threadcore.day02.code.buffer;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * <p>
 * description 缓冲抽象资源类
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public abstract class BufferResource {
    /**
     * 缓冲区的最大容量capacity
     */
    public static final int MAX_CAPACITY = 10;
    /**
     * 简单数据缓冲区域
     */
    private Queue<Integer> simpleBuffer;

    /**
     * 阻塞队列缓冲区域
     */
    private BlockingQueue<Integer> blockingBuffer;

    /**
     * <p>
     *  消费者方法（由子类进行实现）
     * </p>
     *
     * @author ao.chen02@hand-china.com 2025/2/6 16:37
     */
    public abstract void consume();

    /**
     * <p>
     *   生产者方法(由子类进行实现)
     * </p>
     *
     * @param value
     * @author ao.chen02@hand-china.com 2025/2/6 16:40
     */
    public abstract void  product(Integer value);

    // getter/setter方法
    public Queue<Integer> getSimpleBuffer() {
        return simpleBuffer;
    }

    public void setSimpleBuffer(Queue<Integer> simpleBuffer) {
        this.simpleBuffer = simpleBuffer;
    }

    public BlockingQueue<Integer> getBlockingBuffer() {
        return blockingBuffer;
    }

    public void setBlockingBuffer(BlockingQueue<Integer> blockingBuffer) {
        this.blockingBuffer = blockingBuffer;
    }
}

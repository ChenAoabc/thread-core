package com.aochen.threadcore.day02.code;

import java.nio.Buffer;

/**
 * <p>
 * description 消费者线程
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public class Consumer extends Thread {
    private BufferResource buffer;

    /**
     * 创建消费者对象时，必须指定缓冲区
     *
     * @param buffer  缓冲区
     */
    public Consumer(BufferResource buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        this.buffer.consume();
    }
}

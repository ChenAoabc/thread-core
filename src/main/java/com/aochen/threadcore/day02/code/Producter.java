package com.aochen.threadcore.day02.code;

import java.util.Random;

/**
 * <p>
 * description 生产者
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public class Producter extends Thread {
    private BufferResource buffer;
    Random rand = new Random();

    /**
     * 创建生产者时，必须指定缓冲区
     *
     * @param buffer 缓冲区
     */
    public Producter(BufferResource buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        this.buffer.product(rand.nextInt(100));
    }
}

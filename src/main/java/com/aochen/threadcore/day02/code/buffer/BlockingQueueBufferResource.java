package com.aochen.threadcore.day02.code.buffer;

import java.util.concurrent.BlockingQueue;

/**
 * <p>
 * description 缓冲资源类：使用BlockingQueue
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public class BlockingQueueBufferResource extends BufferResource {


    /**
     * 构造方法-必须设置阻塞队列缓冲区
     *
     * @param buffer 缓冲区
     */
    public BlockingQueueBufferResource(BlockingQueue<Integer> buffer) {
        super.setBlockingBuffer(buffer);
    }

    @Override
    public void consume() {
        try{
            Integer take = super.getBlockingBuffer().take();
            System.out.println(Thread.currentThread().getName() + " 消费成功：" + take + " 当前缓冲区size = " +  super.getBlockingBuffer().size());
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void product(Integer value) {
        try{
            super.getBlockingBuffer().put(value);
            System.out.println(Thread.currentThread().getName() + " 生产成功：" + value + " 当前缓冲区size = " +  super.getBlockingBuffer().size());
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}

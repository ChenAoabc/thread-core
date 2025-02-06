package com.aochen.threadcore.day02.code.buffer;

import java.util.Queue;

/**
 * <p>
 * description 缓冲资源类；采用synchronized+wait()+notifyAll()
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public class ObjectBufferResource extends BufferResource {


    /**
     * 创建对象时，必须指定队列
     *
     * @param queue 队列
     */
    public ObjectBufferResource(Queue<Integer> queue) {
        super.setSimpleBuffer(queue);
    }

    /**
     * <p>
     * 消费者方法
     * </p>
     *
     * @author ao.chen02@hand-china.com 2025/2/5 17:05
     */
    @Override
    public void consume() {
        synchronized(this){
            // 如果缓冲区中没有数据则等待
            while (super.getSimpleBuffer().isEmpty()) {
                System.out.println(Thread.currentThread().getName() + "当前缓冲区为空，等待生产。。。");
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // 消费数据
            Integer poll = super.getSimpleBuffer().remove();
            System.out.println(Thread.currentThread().getName() + "消费成功，消费内容：" + poll+",当前缓冲区大小："+super.getSimpleBuffer().size());
            notifyAll();
        }
    }

    @Override
    public void product(Integer value) {
        synchronized(this){
            while (super.getSimpleBuffer().size() == MAX_CAPACITY) {
                System.out.println(Thread.currentThread().getName() + "当前缓冲区已满，等待消费。。。。");
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // 生产数据
            super.getSimpleBuffer().add(value);
            // 生产的时候增加点延迟。。模拟生产会影响到消费
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "生产成功，生产数据：" + value+",当前缓冲区大小："+super.getSimpleBuffer().size());
            notifyAll();
        }
    }
}

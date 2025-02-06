package com.aochen.threadcore.day02.code.buffer;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * description 资源类：采用lock+condition
 * </p>
 *
 * @author ao.chen02@hand-china.com
 */
public class LockBufferResource extends BufferResource {

    // 创建锁
    private final Lock lock = new ReentrantLock();

    //生产者对应的Condition
    private final Condition producerCondition = lock.newCondition();

    // 消费者对应的Condition
    private final Condition consumerCondition = lock.newCondition();

    /**
     * 创建对象时，必须指定队列
     *
     * @param queue 队列
     */
    public LockBufferResource(Queue<Integer> queue) {
        super.setSimpleBuffer(queue);
    }

    // 重写父类的生产和消费方法
    @Override
    public void consume() {
        // 获取锁
        lock.lock();
        try {
            while (super.getSimpleBuffer().isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " 当前缓冲区为空，等待生产中...");
                consumerCondition.await();

            }
            // 进行消费
            Integer value = super.getSimpleBuffer().remove();
            System.out.println(Thread.currentThread().getName() + " 消费成功：" + value.toString() +
                    " 当前缓冲区size = " + super.getSimpleBuffer().size());
            // 消费完后只唤醒生产者
            producerCondition.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    @Override
    public void product(Integer value) {
        lock.lock();
        try {
            // 判断队列是否已满
            while (super.getSimpleBuffer().size() == MAX_CAPACITY) {
                System.out.println(Thread.currentThread().getName() + "当前缓冲区已满，等待消费...");
                producerCondition.await();
            }

            // 生产数据
            super.getSimpleBuffer().add(value);
            System.out.println(Thread.currentThread().getName()+"生产成功："+value.toString()
                    +" 当前缓冲区size = " + super.getSimpleBuffer().size());
            // 生产完毕，只唤醒消费者
            consumerCondition.signalAll();
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            // 释放锁资源
            lock.unlock();
        }
    }
}

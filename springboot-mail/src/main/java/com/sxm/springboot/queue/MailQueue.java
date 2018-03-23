package com.sxm.springboot.queue;

import com.sxm.springboot.model.Email;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 引入了任务队列linkedblockingqueue来实现对邮件发送进行流量削锋、间隔发送以及重复内容检测。
 * LinkedBlockingQueue作为一个阻塞队列是线程安全的，同时具有先进先出等特性，是作为生产者消费者的首选。
 * LinkedBlockingQueue可以指定容量，也可以不指定，不指定的话，默认最大是Integer.MAX_VALUE。
 * put方法在队列满的时候会阻塞直到有队列成员被消费，take方法在队列空的时候会阻塞，直到有队列成员被放进来
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/3/22 0022 上午 10:17
 * @since 0.1
 */
public class MailQueue {

    //队列大小
    private static final int QUEUE_MAX_SIZE = 1000;

    private static BlockingQueue<Email> blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

    /**
     * 私有的默认构造子，保证外界无法直接实例化
     */
    private MailQueue() {
    }

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static MailQueue queue = new MailQueue();
    }

    //单例队列
    public static MailQueue getMailQueue() {
        return SingletonHolder.queue;
    }

    //生产入队
    public void produce(Email mail) throws InterruptedException {
        blockingQueue.put(mail);
    }

    //消费出队
    public Email consume() throws InterruptedException {
        return blockingQueue.take();
    }

    // 获取队列大小
    public int size() {
        return blockingQueue.size();
    }
}

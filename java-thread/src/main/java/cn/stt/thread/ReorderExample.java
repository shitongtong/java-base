package cn.stt.thread;

import org.junit.Test;

/**
 * 重排序对多线程的影响
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/4/10.
 */
public class ReorderExample {
    int a = 0;
    volatile boolean flag = false;

    public void writer() {
        a = 1;        // 1
        flag = true;    // 2
    }

    public void reader() {
        if (flag) {    // 3
            int i = a * a; // 4
            System.out.println("i==" + i);
        }
        System.out.println("a==" + a);
    }

    /**
     * 期望结果
     * reader=1
     * reader=-1
     * reader=0 //1、2重排序后可能导致的结果（没测出来）
     */
    @Test
    public void test() {
        for (int i = 0; i < 1000; i++) {
            ReorderExample reorderExample = new ReorderExample();
            new Thread(() -> {
                reorderExample.writer();
            }).start();
            new Thread(() -> {
                reorderExample.reader();
            }).start();
        }
    }
}

package cn.stt.exception;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM Args: -XX:MaxDirectMemorySize=10M
 * 直接内存oom异常
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/4/9.
 */
public class NativeHeapOomMock {
    // 每次内存分配大小
    private static int _1M = 1024 * 1024;
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field unsafeFiled = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeFiled.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeFiled.get(Unsafe.class);
        while (true) {
            unsafe.allocateMemory(_1M);
        }
    }
}

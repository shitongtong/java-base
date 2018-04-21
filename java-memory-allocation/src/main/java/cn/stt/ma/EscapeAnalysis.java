package cn.stt.ma;

/**
 * 逃逸分析
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/4/19.
 */
public class EscapeAnalysis {
    /**
     * JVM参数设置：-verbose:gc
     * 开启逃逸分析设置 -XX:+DoEscapeAnalysis
     * 注意：Java在Java SE 6u23以及以后的版本中支持并默认开启了逃逸分析的选项，所以想观察到未开启情况，设置：-XX:-DoEscapeAnalysis
     *
     * 执行结果：
     * 未开启：
     * [GC (Allocation Failure)  65536K->752K(251392K), 0.0009194 secs]
     [GC (Allocation Failure)  66288K->720K(251392K), 0.0008254 secs]
     Time cost is 56596480

     开启：
     Time cost is 6840183

     时间相差9倍
     * @param args
     */
    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 0; i < 1000 * 1000 * 10; ++i) {
            Foo foo = new Foo();
        }
        long end = System.nanoTime();
        System.out.println("Time cost is " + (end - start));
    }

    private static class Foo {
        private int x;
        private static int counter;

        public Foo() {
            x = (++counter);
        }
    }

}

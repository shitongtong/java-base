package cn.stt.ma;

/**
 * JVM大对象分配内存的特殊处理
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/4/18.
 */
public class MaMain {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728
     * jdk8及以上需要指定垃圾回收器 -XX:+UseParNewGC 因为PretenureSizeThreshold参数只对ParNew有效，而jdk8默认的不是这个
     */
    public static void main(String[] args) {
//        byte[] allocation = new byte[9 * _1MB];
//        byte[][] allocation;
//        allocation = new byte[11][_1MB]; // 直接分配在老年代中

        byte[] allocation = new byte[4 * _1MB];
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

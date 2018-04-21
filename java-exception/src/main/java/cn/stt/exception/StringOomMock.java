package cn.stt.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * JVM参数配置
 * -XX:MaxPermSize=10m
 * -XX:PermSize=10m
 * -Xms100m
 * -Xmx100m
 * -XX:-UseGCOverheadLimit
 * <p>
 * <p>
 * OOM异常：OutOfMemoryError
 * <p>
 * 命令执行方法：
 * javac -encoding UTF-8 StringOomMock.java
 * ps:javac StringOomMock.java会报错： 编码GBK的不可映射字符
 * java -XX:MaxPermSize=10m -XX:PermSize=10m -Xms100m -Xmx100m -XX:-UseGCOverheadLimit StringOomMock
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/4/9.
 */
public class StringOomMock {
    /**
     * jdk7:
     * -XX:MaxPermSize=10m -XX:PermSize=10m -Xms100m -Xmx100m -XX:-UseGCOverheadLimit
     * java.lang.OutOfMemoryError: Java heap space
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            List<String> list = new ArrayList<String>();
            for (int i = 0; ; i++) {
                System.out.println(i);
                list.add(String.valueOf("String" + i++).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

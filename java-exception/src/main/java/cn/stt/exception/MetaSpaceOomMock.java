package cn.stt.exception;

import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Dispatcher;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;

/**
 * jvm参数配置
 * -XX:MetaspaceSize=8m
 * -XX:MaxMetaspaceSize=50m
 * <p>
 * 借助cglib框架生成新类
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2018/4/9.
 */
public class MetaSpaceOomMock {
    public static void main(String[] args) {
        ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MetaSpaceOomMock.class);
            enhancer.setCallbackTypes(new Class[]{Dispatcher.class, MethodInterceptor.class});
            enhancer.setCallbackFilter(new CallbackFilter() {
                @Override
                public int accept(Method method) {
                    return 1;
                }

                @Override
                public boolean equals(Object obj) {
                    return super.equals(obj);
                }
            });

            Class clazz = enhancer.createClass();
            System.out.println(clazz.getName());
            //显示数量信息（共加载过的类型数目，当前还有效的类型数目，已经被卸载的类型数目）
            System.out.println("total: " + loadingBean.getTotalLoadedClassCount());
            System.out.println("active: " + loadingBean.getLoadedClassCount());
            System.out.println("unloaded: " + loadingBean.getUnloadedClassCount());
        }
    }
}

/**
 * 输出结果：
 * cn.stt.exception.MetaSpaceOomMock$$EnhancerByCGLIB$$2cbbd631
 * total: 6313
 * active: 6313
 * unloaded: 0
 * cn.stt.exception.MetaSpaceOomMock$$EnhancerByCGLIB$$ac5033c
 * total: 6314
 * active: 6314
 * unloaded: 0
 * Exception in thread "main" java.lang.IllegalStateException: Unable to load cache item
 * at net.sf.cglib.core.internal.LoadingCache.createEntry(LoadingCache.java:79)
 * at net.sf.cglib.core.internal.LoadingCache.get(LoadingCache.java:34)
 * at net.sf.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:116)
 * at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:291)
 * at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:480)
 * at net.sf.cglib.proxy.Enhancer.createClass(Enhancer.java:337)
 * at cn.stt.exception.MetaSpaceOomMock.main(MetaSpaceOomMock.java:42)
 * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 * at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 * at java.lang.reflect.Method.invoke(Method.java:498)
 * at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 * Caused by: java.lang.OutOfMemoryError: Metaspace
 * at net.sf.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:345)
 * at net.sf.cglib.proxy.Enhancer.generate(Enhancer.java:492)
 * at net.sf.cglib.core.AbstractClassGenerator$ClassLoaderData$3.apply(AbstractClassGenerator.java:93)
 * at net.sf.cglib.core.AbstractClassGenerator$ClassLoaderData$3.apply(AbstractClassGenerator.java:91)
 * at net.sf.cglib.core.internal.LoadingCache$2.call(LoadingCache.java:54)
 * at java.util.concurrent.FutureTask.run(FutureTask.java:266)
 * at net.sf.cglib.core.internal.LoadingCache.createEntry(LoadingCache.java:61)
 * ... 11 more
 */

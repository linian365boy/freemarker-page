package com.lm.base;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 用于反射service类
 * 这里由分布式Service转化而来，代码无实际意义
 * @author jnbzwm
 *
 */
@SuppressWarnings("unchecked")
public abstract class CallService {

    public static Map process(String target, Map mapIn) throws Exception {
        return proxy(target, mapIn);
    }

    private static Map proxy(String target, Map mapIn) throws Exception {
        String className = StringUtils.substringBeforeLast(target, ".");
        String methodName = StringUtils.substringAfterLast(target, ".");
        Class serviceClass = loadClass(className);
        Method method = getMethod(serviceClass, methodName, Map.class);
        method.setAccessible(true);
        return (Map) method.invoke(serviceClass.newInstance(), mapIn);
    }

    private static Method getMethod(Class<?> cls, String name, Class<?>... parameterTypes) {
        return MethodUtils.getAccessibleMethod(cls, name, parameterTypes);
    }

    /**
     * Load a class given its name. BL: We wan't to use a known
     * ClassLoader--hopefully the heirarchy is set correctly.
     * 
     * @param className
     *            A class name
     * @return The class pointed to by <code>className</code>
     * @exception ClassNotFoundException
     *                If a loading error occurs
     */
    private static <T> Class<T> loadClass(String className) {
        try {
            return (Class<T>) getClassLoader().loadClass(className);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return the context classloader. BL: if this is command line operation,
     * the classloading issues are more sane. During servlet execution, we
     * explicitly set the ClassLoader.
     * 
     * @return The context classloader.
     */
    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
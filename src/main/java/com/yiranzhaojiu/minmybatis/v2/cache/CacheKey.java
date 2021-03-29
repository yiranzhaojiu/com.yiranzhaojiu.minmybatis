package com.yiranzhaojiu.minmybatis.v2.cache;


import java.util.Arrays;

/*
* 计算key对应的HashCode值
* */
public class CacheKey {
    private static final int DEFAULT_MULTIPLIER = 37;
    private static final int DEFAULT_HASHCODE = 17;

    private int count;
    private int hashcode;
    private final int multiplier;
    public CacheKey() {
        this.hashcode = DEFAULT_HASHCODE;
        this.multiplier = DEFAULT_MULTIPLIER;
    }
    public void update(Object object) {
        int baseHashCode = object == null ? 1 : calcuteHashCode(object);
        count++;
        baseHashCode *= count;
        hashcode = multiplier * hashcode + baseHashCode;
    }

    public int hashCode() {
        return hashcode;
    }

    public static int calcuteHashCode(Object obj) {
        if (obj == null) {
            // for consistency with Arrays#hashCode() and Objects#hashCode()
            return 0;
        }
        final Class<?> clazz = obj.getClass();
        if (!clazz.isArray()) {
            return obj.hashCode();
        }
        final Class<?> componentType = clazz.getComponentType();
        if (long.class.equals(componentType)) {
            return Arrays.hashCode((long[]) obj);
        } else if (int.class.equals(componentType)) {
            return Arrays.hashCode((int[]) obj);
        } else if (short.class.equals(componentType)) {
            return Arrays.hashCode((short[]) obj);
        } else if (char.class.equals(componentType)) {
            return Arrays.hashCode((char[]) obj);
        } else if (byte.class.equals(componentType)) {
            return Arrays.hashCode((byte[]) obj);
        } else if (boolean.class.equals(componentType)) {
            return Arrays.hashCode((boolean[]) obj);
        } else if (float.class.equals(componentType)) {
            return Arrays.hashCode((float[]) obj);
        } else if (double.class.equals(componentType)) {
            return Arrays.hashCode((double[]) obj);
        } else {
            return Arrays.hashCode((Object[]) obj);
        }
    }
}

package com.ifmo.lesson19.refletion;

import com.ifmo.lesson6.ArrayList;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtils implements Serializable {

    static String toString(Class obj) {
        ArrayList result = new ArrayList();
        while (obj != null) {
            for (Method method : obj.getDeclaredMethods()) {
                int modifiers = method.getModifiers();
                if (Modifier.isPrivate(modifiers)) {
                    result.add(method);
                    System.out.println(method);
                }
            }
            obj = obj.getSuperclass();
        }
        return result.iterator().toString();
    }

    static <T> T deepClone(T obj) throws CloneNotSupportedException, IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(obj);
        oos.close();
        return (T) obj.getClass();
    }

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        toString(BufferedInputStream.class);
        deepClone(BufferedInputStream.class);
    }
}

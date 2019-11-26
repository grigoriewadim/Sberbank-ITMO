package com.ifmo.lesson19.refletion;

import com.ifmo.lesson6.ArrayList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    private static ArrayList listFields = new ArrayList();
    private static ArrayList listMethods = new ArrayList();
    ReflectionUtils(Object obj) {
        for (Field fields : obj.getClass().getFields()) {
                listFields.add(fields.getName());
        }
        Method[] methods = obj.getClass().getMethods();
        for (Method met: methods) {
            listMethods.add(met.getName());
        }
    }

    public static void main(String[] args) {
        new ReflectionUtils(new java.io.ByteArrayOutputStream());
        for (Object fields: listFields) {
            System.out.println(fields);
        }

        for (Object methods: listMethods) {
            System.out.println(methods);
        }
    }
}

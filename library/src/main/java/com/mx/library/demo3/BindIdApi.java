package com.mx.library.demo3;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: lhe
 * @date: 2019/02/27
 * @desctiption: xxxx
 */
public class BindIdApi {

    public static void bindId(Activity obj) {

        Class<?> cls = obj.getClass();

        //使用反射调用setContentView
        if (cls.isAnnotationPresent(BindId.class)) {
            BindId mId = cls.getAnnotation(BindId.class);
            int id = mId.value();

            try {
                Method method = cls.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(obj, id);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        //使用反射调用findViewById
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindId.class)) {
                BindId mId = field.getAnnotation(BindId.class);
                int id = mId.value();

                // 使用反射调用findViewById，并为字段设置值
                try {
                    Method method = cls.getMethod("findViewById", int.class);
                    method.setAccessible(true);
                    Object view = method.invoke(obj, id);
                    field.setAccessible(true);
                    field.set(obj, view);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void bindId2(Activity obj) {
        Class<?> cls = obj.getClass();
        if (cls.isAnnotationPresent(BindId.class)) {
            // 得到这个类的BindId注解
            BindId mId = (BindId) cls.getAnnotation(BindId.class);
            // 得到注解的值
            int id = mId.value();
            obj.setContentView(id);
        }
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindId.class)) {
                BindId mId = field.getAnnotation(BindId.class);
                int id = mId.value();
                View view=obj.findViewById(id);
                try {
                    field.setAccessible(true);
                    field.set(obj, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

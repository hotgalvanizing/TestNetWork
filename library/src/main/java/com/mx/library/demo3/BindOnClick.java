package com.mx.library.demo3;

import android.app.Activity;

import java.lang.reflect.Method;

/**
 * @author: lhe
 * @date: 2019/02/27
 * @desctiption: xxxx
 */
public class BindOnClick {

    public static void bindOnCLick(final Activity obj){

        Class<?> cls = obj.getClass();
        Method methods[] = cls.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            final Method method = methods[i];
            if (method.isAnnotationPresent(OnClick.class)){
                OnClick mOnClick = method.getAnnotation(OnClick.class);
                int[] id = mOnClick.value();
                for (int j = 0; j < id.length; j++) {

                }
            }
        }

    }



}

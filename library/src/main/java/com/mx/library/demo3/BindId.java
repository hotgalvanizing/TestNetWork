package com.mx.library.demo3;

import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: lhe
 * @date: 2019/02/27
 * @desctiption: xxxx
 */
@Retention(RUNTIME)
@Target({FIELD, TYPE})
public @interface BindId {

    /**
     * 需要绑定的view或者layout的id
     * @return
     */
    int value() default View.NO_ID;

}

package com.mx.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NetStateReceiver extends BroadcastReceiver {

    private NetType netType;
    //    private NetChangeObserver listener;
    private Map<Object, List<MethodManager>> netWorkList;

    public NetStateReceiver() {
        netType = NetType.NONE;
        netWorkList = new HashMap();
    }

//    public void setListener(NetChangeObserver listener) {
//        this.listener = listener;
//    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            Log.d(Constants.TAG, "");
        }

        if (intent.getAction().equalsIgnoreCase(Constants.ANDROID_NET_CHANGE_ACTION)) {
            Log.d(Constants.TAG, "");
            netType = NetWorkUtils.getNetType();
            if (NetWorkUtils.isNetWorkAvailable()) {
//                listener.onConnect(netType);
                Log.d(Constants.TAG,"有网");
            } else {
//                listener.onDisConnect();
                Log.d(Constants.TAG,"无网");
            }
            post(netType);
        }
    }

    private void post(NetType netType) {
        Set<Object> set = netWorkList.keySet();
        for (Object o : set) {
            List<MethodManager> methodManagers = netWorkList.get(o);
            if (methodManagers != null) {
                for (MethodManager methodManager : methodManagers) {
                    if (methodManager.getType().isAssignableFrom(netType.getClass())) {
                        switch (methodManager.getNetType()) {
                            case AUTO:
                                invoke(methodManager, o, netType);
                                break;
                            case CMWAP:
                                if (netType == NetType.WIFI || netType == NetType.NONE) {
                                    invoke(methodManager, o, netType);
                                }
                                break;
                            case WIFI:
                                if (netType == NetType.WIFI || netType == NetType.NONE) {
                                    invoke(methodManager, o, netType);
                                }
                                break;
                            case CMNET:
                                if (netType == NetType.WIFI || netType == NetType.NONE) {
                                    invoke(methodManager, o, netType);
                                }

                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    private void invoke(MethodManager methodManager, Object o, NetType netType) {
        Method method = methodManager.getMethod();
        try {
            method.invoke(o,netType);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void registerObserver(Object o) {
        //獲取o中所有的带有注解的方法
        List<MethodManager> methodManagers = netWorkList.get(o);
        if (methodManagers == null) {
            //不为空，表示注册过
            methodManagers = findAnnotationMedthod(o);
            netWorkList.put(o, methodManagers);
        }
    }

    private List<MethodManager> findAnnotationMedthod(Object o) {
        List<MethodManager> list = new ArrayList<>();
        Class<?> cl = o.getClass();
        Method[] methods = cl.getMethods();
        for (Method method : methods) {
            //获取方法注解
            NetWork netWork = method.getAnnotation(NetWork.class);
            if (netWork == null) {
                continue;
            }
            //返回值校验
            Type type = method.getGenericReturnType();
            if (!"void".equals(type.toString())) {
                throw new RuntimeException("");
            }
            //参数校验
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new RuntimeException("");
            }
            MethodManager methodManager = new MethodManager(parameterTypes[0], netWork.netType(), method);
            list.add(methodManager);
        }

        return list;
    }

    public void unRegisterObserver(Object o) {
        if (!netWorkList.isEmpty()) {
            netWorkList.remove(o);
        }
    }
}

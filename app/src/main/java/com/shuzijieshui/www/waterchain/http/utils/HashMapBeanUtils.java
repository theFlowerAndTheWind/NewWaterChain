package com.shuzijieshui.www.waterchain.http.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by WanghongHe on 2018/12/12 14:49.
 * hashMap 和 实体bean 直接转换工具
 */

public class HashMapBeanUtils {
    /**
     * HashMap转换成JavaBean
     *
     * @author hailan
     * @time 下午05:57:16
     * @param map
     * @param cls
     * @return
     */
    public static Object hashMapToJavaBean(HashMap<?,?> map, Class<?> cls) {
        Object obj = null;
        try {
            obj = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 取出bean里的所有方法
        Method[] methods = cls.getMethods();
        for (int i = 0; i < methods.length; i++) {
            // 取方法名
            String method = methods[i].getName();
            // 取出方法的类型
            Class<?>[] cc = methods[i].getParameterTypes();
            if (cc.length != 1)
                continue;

            // 如果方法名没有以set开头的则退出本次for
            if (!method.startsWith("set") )
                continue;
            // 类型
            String type = cc[0].getSimpleName();

            try {
                //
                Object value = method.substring(3,4).toLowerCase().concat(method.substring(4));
                // 如果map里有该key
                if (map.containsKey(value)) {
                    // 调用其底层方法
                    setValue(type, map.get(value), i, methods, obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /**
     * 调用底层方法设置值
     *
     * @author hailan
     * @time 下午06:01:56
     * @param type
     * @param value
     * @param i
     * @param method
     * @param bean
     * @throws Exception
     */
    private static void setValue(String type, Object value, int i, Method[] method,
                                 Object bean) throws Exception {
        if (value != null && !value.equals("")) {
            try {
                if (type.equals("String")) {
                    // 第一个参数:从中调用基础方法的对象 第二个参数:用于方法调用的参数
                    method[i].invoke(bean, new Object[] { value });
                } else if (type.equals("int") || type.equals("Integer")) {
                    method[i].invoke(bean, new Object[] { new Integer(""
                            + value) });
                } else if (type.equals("BigDecimal")) {
                    method[i].invoke(bean, new Object[] { new BigDecimal((String)value) });
                } else if (type.equals("long") || type.equals("Long")) {
                    method[i].invoke(bean,
                            new Object[] { new Long("" + value) });
                } else if (type.equals("boolean") || type.equals("Boolean")) {
                    method[i].invoke(bean, new Object[] { Boolean.valueOf(""
                            + value) });
                } else if (type.equals("Date")) {
                    Date date = null;
                    if (value.getClass().getName().equals("java.util.Date")) {
                        date = (Date) value;
                    } else {
                        //根据文件内的格式不同修改，时间格式太多在此不做通用格式处理。
                        if (value.toString().length() > 10){
                            String format = "yyyy-MM-dd HHmmss";
                            date = parseDateTime("" + value, format);
                        } else if (value.toString().length() == 10){
                            String format = "yyyy-MM-dd";
                            date = parseDateTime("" + value, format);
                        } else  if (value.toString().length() == 8){
                            String format = "yyyyMMdd";
                            date = parseDateTime("" + value, format);
                        } else  if (value.toString().length() == 14){
                            String format = "yyyyMMddHHmmss";
                            date = parseDateTime("" + value, format);
                        }else  if (value.toString().length() == 6){
                            String format = "HHmmss";
                            date = parseDateTime("" + value, format);
                        }
                    }
                    if (date != null) {
                        method[i].invoke(bean, new Object[] { date });
                    }
                } else if (type.equals("byte[]")) {
                    method[i].invoke(bean,
                            new Object[] { new String(value + "").getBytes() });
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    /**
     * 日期格式转换
     *
     * @author hailan
     * @time 下午06:02:59
     * @param dateValue
     * @param format
     * @return
     */
    private static Date parseDateTime(String dateValue, String format) {
        SimpleDateFormat obj = new SimpleDateFormat(format);
        try {
            return obj.parse(dateValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * bean实体类转换map
     * @param ojt 实例bean
     * @return map
     */
    public static HashMap<String, Object> hashJavaBeanToMap(Object ojt) {
        if (ojt == null){
            return null;
        }
        Class<?> cls = ojt.getClass();
        Field[] declaredFields = cls.getDeclaredFields();

        HashMap<String, Object> mapbean = new HashMap<String, Object>();
        for(Field field : declaredFields){
            field.setAccessible(true);
            try {
                mapbean.put(field.getName(), field.get(ojt));
            } catch (IllegalArgumentException|IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mapbean;
    }
}

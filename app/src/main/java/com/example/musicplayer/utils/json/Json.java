package com.example.musicplayer.utils.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Json {

    private Class[] baseClasses = {Integer.class, Byte.class, Long.class,
            Double.class, Float.class, Character.class, Short.class, Boolean.class,
            int.class, byte.class, long.class, double.class, float.class,
            char.class, short.class, boolean.class, String.class};


    public <T> T fromJson(String json,Class<T> aClass) throws JSONException {
        if(json.equals("") || json==null || aClass==null){
            throw new NullPointerException();
        }
        return toBean(new JSONObject(json),aClass);
    }

    public <T> List fromJson(String json,List<T> list) throws JSONException {
        return toList(new JSONArray(json));
    }

    private <T> T toBean(JSONObject jsonObject, Class<T> aClass) {
        Field[] fields = aClass.getFields();
        T result = null;
        try {
            result = aClass.newInstance();
            for (Field field: fields) {
                String setterName="set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
                Method setter=aClass.getMethod(setterName,field.getType());
                Object value=null;
                if(field.getType()==List.class){
                    value=toList(jsonObject.getJSONArray(field.getName()));
                }else if (isBaseClass(field.getType())){
                    value=jsonObject.get(field.getName());
                }else{
                    field.getType();
                    value=toBean(new JSONObject(jsonObject.get(field.getName())+""),field.getType());
                }
                setter.invoke(result,value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    private  <T> List toList(JSONArray jsonArray) throws JSONException {
        List<Object> result=new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            Object object=jsonArray.get(i);
            if(object instanceof  JSONArray){
                result.add(toList((JSONArray)object));
            }else if (object instanceof  JSONObject){
                result.add((JSONObject)object);
            }else{
                result.add(object);
            }
        }
        return result;
    }


    public String toJson(Object object) {
        if (object instanceof List) {
            return fromList((List) object).toString();
        } else {
            return fromBean(object).toString();
        }
    }


    private JSONObject fromBean(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
        Class aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        JSONObject result = new JSONObject();
        for (Field field : fields) {
            String getterName = "get" + field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
            Method getter = null;
            try {
                getter = aClass.getDeclaredMethod(getterName);
                if (field.getType() == List.class) {
                    result.put(field.getName(), fromList((List) getter.invoke(object)));
                } else if (isBaseClass(field.getType())) {
                    result.put(field.getName(), getter.invoke(object));
                } else {
                    result.put(field.getName(), fromBean(getter.invoke(object)));
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    private JSONArray fromList(List<Object> list) {
        if (list == null) {
            throw new NullPointerException();
        }
        JSONArray jsonArray = new JSONArray();
        for (Object object : list) {
            if (object == null) {
                throw new NullPointerException();
            }
            if (object instanceof List) {
                jsonArray.put(fromList((List) object));
            } else if (isBaseClass(object.getClass())) {
                jsonArray.put(object);
            } else {
                jsonArray.put(fromBean(object));
            }
        }
        return jsonArray;
    }

    /**
     * 判断是否为基本类
     *
     * @param aClass
     * @return
     */
    private boolean isBaseClass(Class aClass) {
        for (Class i : baseClasses) {
            if (aClass == i) {
                return true;
            }
        }
        return false;
    }
}

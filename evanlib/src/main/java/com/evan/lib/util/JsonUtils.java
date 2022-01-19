package com.evan.lib.util;

import android.text.TextUtils;
import com.evan.lib.util.json.Result;
import com.evan.lib.util.json.TypeBuilder;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gson相关的工具类
 *
 * @author Evan
 * @date 2021-12-15 09:18:00
 */
public class JsonUtils {


    private static Gson gson = null;

    static {
        gson = new Gson();
    }

    public static String toJson(Object obj) {


        if (obj == null) {
            return "";
        } else {
            return getGson().toJson(obj);
        }
    }

    /**
     * 将实体类转换成json字符串对象            注意此方法需要第三方gson  jar包
     *
     * @param obj 对象
     * @return map
     */
    private String toJson(Object obj, int method) {
        if (method == 1) {
            //字段是首字母小写，其余单词首字母大写
            Gson gson = new Gson();
            String obj2 = gson.toJson(obj);
            return obj2;
        } else if (method == 2) {
            // FieldNamingPolicy.LOWER_CASE_WITH_DASHES    全部转换为小写，并用空格或者下划线分隔
            //FieldNamingPolicy.UPPER_CAMEL_CASE    所以单词首字母大写
            Gson gson2 = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            String obj2 = gson2.toJson(obj);
            return obj2;
        }
        return "";
    }

    /**
     * 将实体类转换成请求参数,json字符串形式返回
     *
     * @return
     */
    public String getJsonParams() {
        String jsonStr = new Gson().toJson(this);
        if (TextUtils.isEmpty(jsonStr)) {
            jsonStr = "";
        }

        return jsonStr;
    }

    /**
     * 将实体类转换成请求参数,以map<k,v>形式返回
     *
     * @return
     */
    public Map<String, String> getMapParams() {
        Class<? extends JsonUtils> clazz = this.getClass();
        Class<? extends Object> superclass = clazz.getSuperclass();

        Field[] fields = clazz.getDeclaredFields();
        Field[] superFields = superclass.getDeclaredFields();

        if (fields == null || fields.length == 0) {
            return Collections.emptyMap();
        }

        Map<String, String> params = new HashMap<String, String>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                params.put(field.getName(), String.valueOf(field.get(this)));
            }

            for (Field superField : superFields) {
                superField.setAccessible(true);
                params.put(superField.getName(), String.valueOf(superField.get(this)));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return params;
    }

    private static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().serializeNulls().create();
        }
        return gson;
    }

    private static Gson getGsonSerializer() {
        return new Gson();
    }

    private static Gson getGsonDeserializer() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        return gson;
    }


    public static List<Map<String, Object>> parseGsonMapList(String reader) {
        Type parent = TypeBuilder
                .newInstance(Map.class)
                .addTypeParam(String.class)
                .beginSubType(Object.class) //开始Object 部分
                .endSubType()
                .build();
        Type type = TypeBuilder
                .newInstance(List.class)
                .addTypeParam(parent)
                .build();
        return getGsonDeserializer().fromJson(reader, type);
    }


    public static <T> T fromJson(String reader, Class<T> clazz) {
        return parseGsonObject(reader, clazz);
    }

    public static <T> T fromJson(Object reader, Class<T> clazz) {
        return parseGsonObject(toJson(reader), clazz);
    }

    public static <T> T fromJson(String reader, Type type) {
        return getGsonDeserializer().fromJson(reader, type);
    }

    public static <T> T parseGsonObject(String reader, Class<T> clazz) {
        Type type = TypeBuilder
                .newInstance(clazz)
                .build();
        return getGsonDeserializer().fromJson(reader, type);
    }

    public static Map<String, Object> parseGsonMap(String reader) {
        Type type = TypeBuilder
                .newInstance(Map.class)
                .addTypeParam(String.class)
                .beginSubType(Object.class) //开始Object 部分
                .endSubType()
                .build();
        return getGsonDeserializer().fromJson(reader, type);
    }

    public static <T> List<T> parseArray(String reader, Class<T> clazz) {
        Type type = TypeBuilder
                .newInstance(List.class)
                .addTypeParam(clazz)
                .build();
        return new Gson().fromJson(reader, type);
    }

    public static <T> Result<List<T>> parseResult(String reader, Class<T> clazz) {
        Type type = TypeBuilder
                .newInstance(Result.class)
                .beginSubType(List.class)
                .addTypeParam(clazz)
                .endSubType()
                .build();
        return new Gson().fromJson(reader, type);
    }
    /**
     * 创建一个新的JSONObject对象
     */
    public static JSONObject createJSONObject(String json) {

        try {
            if (json == null) {
                return new JSONObject("{}");
            }
            if (json != null && json.startsWith("\ufeff")) {
                json = json.substring(1);
            }
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

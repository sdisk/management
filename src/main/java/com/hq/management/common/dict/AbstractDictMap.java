package com.hq.management.common.dict;


import java.util.HashMap;

/**
 * @program: management
 * @description: 字典映射抽象类
 * @author: Mr.Huang
 * @create: 2018-10-29 14:36
 **/
public abstract class AbstractDictMap {

    private HashMap<String, String> dictory = new HashMap<>();
    private HashMap<String, String> fieldWarpperDictory = new HashMap<>();
    // init initBeWrapped

    public AbstractDictMap() {
        put("id", "主键Id");
        init();
        initBeWrapped();
    }
    public abstract void init();
    public abstract void initBeWrapped();

    public String get(String key) {
        return this.dictory.get(key);
    }

    public void put(String key, String value) {
        this.dictory.put(key, value);
    }

    public String getFieldWarpperDictory(String key) {
        return this.fieldWarpperDictory.get(key);
    }

    public void putFieldWarpperDictory(String key, String methodName) {
        this.fieldWarpperDictory.put(key, methodName);
    }
}

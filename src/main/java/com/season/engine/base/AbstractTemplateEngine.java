package com.season.engine.base;


import java.util.HashMap;
import java.util.Map;

public class AbstractTemplateEngine {

    private boolean ret;

    private String msg;

    private Object data;

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

}


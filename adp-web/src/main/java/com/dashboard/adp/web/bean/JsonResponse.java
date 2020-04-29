package com.dashboard.adp.web.bean;

public class JsonResponse {

    private int code;
    
    private String result;
    
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static JsonResponse success() {
        JsonResponse json = new JsonResponse();
        json.setCode(1);
        json.setResult("success");
        return json;
    }
    
    public static JsonResponse failed() {
        JsonResponse json = new JsonResponse();
        json.setCode(0);
        json.setResult("failed");
        return json;
    }
}

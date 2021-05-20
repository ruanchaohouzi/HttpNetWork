package com.shuwen.network.bean;

public class ResponseResult<T> {

    private boolean success;
    private String code;
    private String msg;
    private String requestId;
    private T data;
    public void setSuccess(boolean success) {
         this.success = success;
     }
     public boolean getSuccess() {
         return success;
     }

    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

    public void setRequestId(String requestId) {
         this.requestId = requestId;
     }
     public String getRequestId() {
         return requestId;
     }

    public void setData(T data) {
         this.data = data;
     }
     public T getData() {
         return data;
     }

}
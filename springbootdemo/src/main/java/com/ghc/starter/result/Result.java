package com.ghc.starter.result;

public class Result<T> {
    private String msg;
    private int code;
    private T data;
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    public static <T> Result<T> error(){
        Result result = new Result<T>(null);
        result.code = CodeMsg.ERROR.getCode();
        result.msg = CodeMsg.ERROR.getMsg();
        return result;
    }
    private  Result(T data){
        this.data = data;
        this.msg = CodeMsg.SUCCESS.getMsg();
        this.code = CodeMsg.SUCCESS.getCode();
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}

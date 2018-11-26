package com.ghc.starter.result;

public enum CodeMsg implements  BaseCodeMsg{
    SUCCESS("SUCCESS",200),ERROR("ERROR",500);
    private final String msg;
    private final int code;
    CodeMsg(String msg,int code){
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}

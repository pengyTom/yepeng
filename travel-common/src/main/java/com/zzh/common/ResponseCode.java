package com.zzh.common;

/**
 * 响应码的实现
 */
public enum  ResponseCode {

    SUCCESS(200,"SUCCESS"),
    ERROR(0,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(1,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;


    /**
     * 枚举对象   通过构造器进行赋值
     * @param code
     * @param desc
     */
    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }


}

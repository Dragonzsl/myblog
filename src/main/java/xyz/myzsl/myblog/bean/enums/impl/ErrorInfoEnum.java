package xyz.myzsl.myblog.bean.enums.impl;

import xyz.myzsl.myblog.bean.enums.IErrorInfo;

/**
 * @author shilin
 * @email g1335026358@gmail.com
 * @date 2021-06-04 22:28:07
 */
public enum ErrorInfoEnum implements IErrorInfo {
    SUCCESS(0, "操作成功"),
    MISSING_PARAMETERS(4004, "参数缺失"),
    UNKNOWN_ERROR(5000, "出现未知错误"),
    RESOURCES_NOT_FOUND(4003, "找不到相应资源"),
    INVALID_ID(4008, "你的id不合法");

    private int code;
    private String msg;

    @Override
    public String getMsg() {
        return msg;
    }

    ErrorInfoEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }
}

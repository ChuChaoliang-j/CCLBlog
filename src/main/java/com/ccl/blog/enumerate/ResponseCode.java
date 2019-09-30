package com.ccl.blog.enumerate;

/**
 * @author CCL
 * @date 2019/9/14 22:33
 */
public enum ResponseCode {
    /**
     * SUCCESS 请求成功
     * ERROR 请求错误
     */
    SUCCESS(0, "请求成功"),
    ERROR(-1, "网络异常，请稍后重试！");

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}

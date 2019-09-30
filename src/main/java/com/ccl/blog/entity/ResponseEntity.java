package com.ccl.blog.entity;

import com.ccl.blog.enumerate.ResponseCode;
import lombok.Data;

/**
 * @author CCL
 * @date 2019/9/14 22:34
 */
@Data
public class ResponseEntity {
    private int code;
    private String msg;
    private Object data;

    public ResponseEntity(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public ResponseEntity(ResponseCode responseCode, Object data) {
        this(responseCode);
        this.data = data;
    }
}

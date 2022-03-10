package com.padapp.pad.e;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @Description: -TODO
 * @Author: ZhangWei
 * @Since: 2021/6/30 16:31
 * @Version: V1.0
 */
public class ResultDto {


    private int code;

    private String message;

    private boolean success;

    private Object data;




    public static ResultDto success(){
        ResultDto result = new ResultDto();
        result.setCode(10000);
        result.setMessage("操作成功");
        result.setSuccess(true);
        return result;
    }

    public static ResultDto success(Object data){
        ResultDto result = success();
        result.setData(data);
        return result;
    }


    public static ResultDto fail(String errMsg , Integer code){
        ResultDto result = new ResultDto();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(errMsg);
        return result;
    }


    public static ResultDto fail(Exception e){
        return fail("系统异常",9999);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

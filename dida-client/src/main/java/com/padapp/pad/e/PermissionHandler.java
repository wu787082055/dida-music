package com.padapp.pad.e;

import com.padapp.pad.exception.WebException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.util.List;

@RestControllerAdvice
public class PermissionHandler {

    @ExceptionHandler(value = { WebException.class })
    public ResultDto authorizationException(WebException e){

        return ResultDto.fail(e.getMessage(),1008);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResultDto validException(MethodArgumentNotValidException exception){
        //验证出错结果集
        BindingResult bindingResult = exception.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        /*fieldErrors.forEach(p -> {
            sb.append(p.getDefaultMessage()).append(",");
        });*/

        for (FieldError fieldError : fieldErrors) {
            sb.append(fieldError.getDefaultMessage()).append(",");
        }

        String errMsg = sb.deleteCharAt(sb.length() - 1).toString();

        //LOGGER.error(errMsg);
        //return ResultDto.fail("字段不合法",11000);
        return ResultDto.fail(errMsg,11000);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResultDto validException(Exception exception){
        System.err.println(exception.getClass());
        return ResultDto.fail("服务器异常",9999);
    }

}

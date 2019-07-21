package com.ley.crazyblitz.advice;

import com.ley.crazyblitz.constants.NetConstants;
import com.ley.crazyblitz.exception.BusinessException;
import com.ley.crazyblitz.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ApiResult businessErrorHandler(BusinessException e) {
        ApiResult result = new ApiResult();
        result.setCode(e.getCode());
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public ApiResult accessDeniedErrorHandler(AccessDeniedException e) {
        ApiResult result = new ApiResult();
        result.setCode(NetConstants.RTN_TOKEN_EXPIRE);
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiResult<String> validatorErrorHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ApiResult<String> result = new ApiResult<>();
        result.setMessage("参数错误！");
        result.setCode(NetConstants.RTN_PARAMS_ERROR);
        if (bindingResult.hasErrors()) {
            ObjectError error = bindingResult.getAllErrors().get(0);
            result.setMessage("参数错误：" + error.getDefaultMessage());
        }
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult<String> notExpectedErrorHandler(Exception e) {
        ApiResult<String> result = new ApiResult<>();
        result.setCode(NetConstants.RTN_SYS_ERROR);
        result.setMessage("系统错误！");
        log.error("系统错误", e);
        result.setData(e.getMessage());
        return result;
    }
}
package cn.addenda.piparbac.config;

import cn.addenda.se.result.ControllerResult;
import cn.addenda.se.result.ServiceException;
import cn.addenda.se.result.StatusConst;
import cn.addenda.se.result.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "cn.addenda.piparbac.controller")
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Object handleException(ServiceException ex, HttpServletRequest request) {
        log.error("请求url:{}，异常信息:{}，异常栈： {} ", request.getRequestURI(), ex.printMessage(), ex);

        ControllerResult<String> objectControllerResult = new ControllerResult<>();
        objectControllerResult.setReqStatus(StatusConst.SUCCESS);
        objectControllerResult.setBizStatus(StatusConst.FAILED);
        objectControllerResult.setBizFailedCode(ex.getFailedCode());
        objectControllerResult.setBizFailedMsg(ex.printMessage());
        objectControllerResult.setResult(null);
        return objectControllerResult;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Object handleException(IllegalArgumentException ex, HttpServletRequest request) {
        log.error("请求url:{}，异常信息:{}，异常栈： {} ", request.getRequestURI(), ex.getMessage(), ex);

        ControllerResult<String> objectControllerResult = new ControllerResult<>();
        objectControllerResult.setReqStatus(StatusConst.SUCCESS);
        objectControllerResult.setBizStatus(StatusConst.FAILED);
        objectControllerResult.setBizFailedMsg(ex.getMessage());
        objectControllerResult.setResult(null);
        return objectControllerResult;
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception ex, HttpServletRequest request) {
        log.error("请求url:{}，异常信息:{}，异常栈： {} ", request.getRequestURI(), ex.getMessage(), ex);

        ControllerResult<String> objectControllerResult = new ControllerResult<>();
        objectControllerResult.setReqStatus(StatusConst.ERROR);
        objectControllerResult.setReqErrorMsg("系统异常，请联系IT处理！");
        objectControllerResult.setBizStatus(null);
        objectControllerResult.setResult(null);
        return objectControllerResult;
    }

    @ExceptionHandler(SystemException.class)
    public Object handleException(SystemException ex, HttpServletRequest request) {
        log.error("请求url:{}，异常信息:{}，异常栈： {} ", request.getRequestURI(), ex.getMessage(), ex);

        ControllerResult<String> objectControllerResult = new ControllerResult<>();
        objectControllerResult.setReqStatus(StatusConst.ERROR);
        objectControllerResult.setReqErrorMsg("系统异常，请联系IT处理！");
        objectControllerResult.setBizStatus(null);
        objectControllerResult.setResult(null);
        return objectControllerResult;
    }

}


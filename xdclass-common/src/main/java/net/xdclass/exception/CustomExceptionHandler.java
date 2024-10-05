package net.xdclass.exception;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.util.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 程序狗没有春天
 * @version 1.00
 * @createTime 2024/10/5 20:49
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData Handle(Exception e) {
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            log.info("[业务异常]{}", e);
            return JsonData.buildCodeAndMsg( bizException.getCode(), bizException.getMsg());
        } else {
            log.info("[系统异常]{}", e);
            return JsonData.buildError("全局异常，未知错误");
        }
    }
}

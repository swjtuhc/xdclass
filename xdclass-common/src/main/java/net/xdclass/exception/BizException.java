package net.xdclass.exception;

import lombok.Data;
import net.xdclass.enums.BizCodeEnum;

/**
 * @author 程序狗没有春天
 * @version 1.00
 * @createTime 2024/10/5 20:23
 * 运行时候抛出的异常
 */
@Data
public class BizException extends RuntimeException{
    private int code;
    private String msg;
    public BizException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public BizException(BizCodeEnum bizCodeEnum){
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }
}

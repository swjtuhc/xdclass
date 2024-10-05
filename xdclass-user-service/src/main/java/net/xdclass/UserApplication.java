package net.xdclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 程序狗没有春天
 * @version 1.00
 * @createTime 2024/10/5 15:08
 */
@SpringBootApplication
@MapperScan("net.xdclass.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
//        注意在这里自己建立了启动类的话,需要在resources里面写配置类
    }
}

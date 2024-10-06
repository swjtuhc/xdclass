package net.xdclass.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 程序狗没有春天
 * @version 1.00
 * @createTime 2024/10/6 18:22
 */
@Api(tags = "用户通知模块")
@RestController
@RequestMapping("/api/user/v1")
@Slf4j
public class NotifyController {
    @Autowired
    private Producer captchaProducer;

    @GetMapping("captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        String captchaText = captchaProducer.createText();
        log.info("图形验证码:{}", captchaText);

        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);
        ServletOutputStream outputStream = null;
        try{
            outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("获取图形化验证码异常:{}", e);
        }
    }
}

package com.gg.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码
 * @author: GG
 * @date: 2021/2/27 12:28 下午
 */
@RestController
public class CaptchaController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captcha",produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        // 定义 response 输出类型为 image/jpeg 类型
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control", "post-check=0,pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        // 生成验证码图片
        String text = defaultKaptcha.createText(); // 获取验证码文本内容
        System.out.println("验证码文本内容：" + text);
        request.getSession().setAttribute("captcha", text);
        BufferedImage image = defaultKaptcha.createImage(text); // 根据文本内容创建图形验证码
        // 生成流返回
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpeg", outputStream); // 输出流输出图片，格式为jpg
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

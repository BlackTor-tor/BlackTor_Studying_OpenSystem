package com.study.controller;

import com.google.code.kaptcha.Producer;
import com.study.config.json.JsonResult;
import com.study.config.json.ResultTool;
import com.study.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 验证码控制层
 *
 * @author Cyanogen
 * @date 2022-04-14 21:05:33
 */
@RestController
@ApiOperation("验证码操作")
public class AuthController {

    @Resource
    Producer producer;

    @Resource
    RedisUtil redisUtil;

    /**
     * 生成验证码
     *
     * @return 验证码id以及验证码图片
     * @throws IOException 异常
     */
    @GetMapping("/captcha")
    @ApiOperation("生成验证码")
    public JsonResult captcha() throws IOException {

        //key用于找到真实验证码
        String key = UUID.randomUUID().toString();
        //真实验证码
        String code = producer.createText();

        // 为了测试
        //key = "aaaaa";
        //code = "11111";

        //生成验证码图片
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        BASE64Encoder encoder = new BASE64Encoder();
        //编码前缀
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encode(outputStream.toByteArray());

        redisUtil.hset("captcha", key, code, 120);

        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("captchaImg", base64Img);
        return ResultTool.success(map);
    }
}


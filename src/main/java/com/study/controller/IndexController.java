package com.study.controller;

import com.study.config.json.JsonResult;
import com.study.config.json.ResultCode;
import com.study.config.json.ResultTool;
import com.study.entity.User;
import com.study.service.CheckService;
import com.study.service.IndexService;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * 通用操作
 *
 * @author Cyanogen
 * @date 2022-04-15 10:41
 */
@Validated
@RestController
@ApiOperation("通用操作")
public class IndexController {

    @Resource
    private IndexService indexService;

    @Resource
    private CheckService checkService;

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 是否注册成功
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public JsonResult register(@Validated @RequestBody User user, @NotBlank(message = "邀请码不应为空") String inviteCode) {
        System.out.println("user = " + user);
        try {
            Boolean registered = indexService.register(user, inviteCode);
            if (registered) {
                return ResultTool.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultTool.fail("注册失败");
    }

    /**
     * 帐号校验
     *
     * @param userAccount 用户账号
     * @return 是否已经注册
     */
    @GetMapping("/checkAccount")
    @ApiOperation("账号校验")
    public JsonResult checkAccount(@NotBlank(message = "用户账号不应为空") String userAccount) {
        Boolean isExist = checkService.checkAccount(userAccount);
        if (isExist) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        } else {
            return ResultTool.success();
        }
    }

    /**
     * 邀请码校验
     *
     * @param inviteCode 邀请码
     * @return 邀请码是否有效
     */
    @GetMapping("/checkInviteCode")
    @ApiOperation("邀请码校验")
    public JsonResult checkInviteCode(@NotBlank(message = "邀请码不应为空") String inviteCode) {
        Boolean isValid = checkService.checkInviteCode(inviteCode);
        if (isValid) {
            return ResultTool.success();
        } else {
            return ResultTool.fail(ResultCode.INVALID_INVITE_CODE);
        }
    }

    /**
     * 上传图片
     *
     * @param req   后端使用的,前端无需考虑
     * @param image 文件
     * @return 返回值为图片的地址
     */
    @PostMapping("/uploadImg")
    @ApiOperation("上传图片")
    public JsonResult uploadImg(HttpServletRequest req, @RequestBody MultipartFile image) {
        StringBuilder url = new StringBuilder();
        String per = ".";
        String filePath = "/pic";
        //创建File类
        File imgFolder = new File(per + filePath);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        //配置url,用于markdown编辑器的回显
        url.append(req.getScheme())
                .append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + Objects.requireNonNull(image.getOriginalFilename()).replaceAll(" ", "");

        try {
            //文件复制
            FileCopyUtils.copy(image.getBytes(), new File(imgFolder, imgName));
            url.append("/").append(imgName);
            return ResultTool.success(url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultTool.fail(ResultCode.UPLOAD_ERROR);
    }
}

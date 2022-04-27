package com.study.service;


import com.study.entity.User;

/**
 * 用户注册/登录
 *
 * @author Cyanogen
 * @since 2022-04-13 21:05:34
 */
public interface IndexService {

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @param inviteCode 邀请码
     * @return 是否注册成功
     */
    Boolean register(User user, String inviteCode);


    /**
     * 验证密保答案
     *
     * @param answer   密保答案
     * @param username 用户名
     * @return 答案是否正确
     */
    Boolean verifyAnswer(String username, String answer);

    /**
     * 更改密码
     *
     * @param password 新密码
     * @param username 用户名
     * @return 是否更改成功
     */
    Boolean changePassword(String username, String password);
}

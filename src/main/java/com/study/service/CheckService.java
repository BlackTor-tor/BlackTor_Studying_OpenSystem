package com.study.service;

/**
 * 检查服务类
 *
 * @author Cyanogen
 * @date 2022-04-16 11:19
 */
public interface CheckService {
    /**
     * 邀请码校验
     *
     * @param inviteCode 邀请码
     * @return 邀请码是否存在
     */
    Boolean checkInviteCode(String inviteCode);

    /**
     * 帐号校验
     *
     * @param username 用户账号
     * @return 账号是否已经注册
     */
    Boolean checkAccount(String username);
}

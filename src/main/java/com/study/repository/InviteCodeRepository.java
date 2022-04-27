package com.study.repository;

import com.study.entity.InviteCode;

/**
 * 邀请码仓库类
 *
 * @author Cyanogen
 * @date 2022-04-16 11:28
 */
public interface InviteCodeRepository {
    /**
     * 查找邀请码
     *
     * @param inviteCode 邀请码
     * @return 是否存在该邀请码
     */
    InviteCode find(String inviteCode);


    /**
     * 生成邀请码
     *
     * @param bsosId 用户id
     * @return 邀请码
     */
    String save(String bsosId);


    /**
     * 邀请码变化
     *
     * @param inviteCode 邀请码
     * @return 邀请人id
     */
    String change(String inviteCode);


    /**
     * 生成邀请码记录
     *
     * @param bsosId    邀请人id
     * @param invitedId 被邀请人id
     * @return 是否成功生成邀请码记录
     */
    Integer generateRecord(String bsosId, String invitedId);
}

package com.study.mapper;

import com.study.entity.InviteCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 邀请码表(InviteCode)表数据库访问层
 *
 * @author Cyanogen
 * @since 2022-04-18 21:19:09
 */
@Mapper
public interface InviteCodeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param inviteCode 主键
     * @return 实例对象
     */
    InviteCode queryByInviteCode(String inviteCode);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param inviteCode 实例对象
     * @return 对象列表
     */
    List<InviteCode> queryAll(InviteCode inviteCode);

    /**
     * 新增数据
     *
     * @param inviteCode 实例对象
     * @return 影响行数
     */
    int insert(InviteCode inviteCode);

    /**
     * 修改数据
     *
     * @param inviteCode 实例对象
     * @return 影响行数
     */
    int update(InviteCode inviteCode);

    /**
     * 通过主键删除数据
     *
     * @param inviteCode 主键
     * @return 影响行数
     */
    int deleteById(String inviteCode);

    /**
     * 校验邀请码是否存在
     *
     * @param inviteCode 邀请码
     * @return 邀请码是否存在
     */
    boolean checkInviteCode(String inviteCode);

    /**
     * 查询/判断邀请码权限
     *
     * @param inviteCode 邀请码
     * @return 邀请码对象
     */
    InviteCode checkInviteCodeRole(String inviteCode);

}

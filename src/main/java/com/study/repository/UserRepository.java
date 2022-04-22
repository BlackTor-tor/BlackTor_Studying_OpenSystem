package com.study.repository;

import com.study.entity.Role;
import com.study.entity.User;

import java.util.List;

/**
 * 用户仓库类
 *
 * @author Cyanogen
 * @date 2022-04-16 11:13
 */
public interface UserRepository {

    /**
     * 保存用户
     *
     * @param user 用户实体
     * @return 印象行数
     */
    Integer save(User user);


    /**
     * 通过用户名查询用户
     *
     * @param userAccount 用户账号
     * @return 用户实体
     */
    User findByUserAccount(String userAccount);


    /**
     * 通过用户id删除用户
     *
     * @param bsosId 用户id
     * @return 影响行数
     */
    Integer deleteById(String bsosId);

    /**
     * 校验用户账号
     *
     * @param userAccount 用户账号
     * @return 用户账号是否重名 true:不重名 false:重名
     */
    Boolean checkAccount(String userAccount);

    /**
     * 更新用户
     *
     * @param user 实例对象
     * @return 影响行数
     */
    Integer updateUser(User user);

    /**
     * 根据用户账号查询角色列表
     *
     * @param userAccount 用户账号
     * @return 影响行数
     */
    List<Role> findUserRolesByAccount(String userAccount);

}

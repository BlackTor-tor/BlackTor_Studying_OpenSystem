package com.study.repository;

import com.study.entity.Role;

import java.util.List;

/**
 * 角色仓库类
 *
 * @author Cyanogen
 * @date 2022-04-18 22:23
 */
public interface RoleRepository {

    /**
     * 获取用户所拥有的权限
     *
     * @param userAccount 用户账号
     * @return 用户权限集合
     */
    List<Role> findUserRole(String userAccount);
}

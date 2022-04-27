package com.study.mapper;

import com.study.entity.Role;
import com.study.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户基本信息表(User)表数据库访问层
 *
 * @author Cyanogen
 * @since 2022-04-18 21:19:09
 */
@Mapper
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param bsosId 主键
     * @return 实例对象
     */
    User queryById(String bsosId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param bsosId 主键
     * @return 影响行数
     */
    int deleteById(String bsosId);

    /**
     * 校验用户账号是否存在
     *
     * @param userAccount 用户账号
     * @return 用户账号是否存在:true 已存在, false 不存在
     */
    boolean checkAccount(String userAccount);

    /**
     * 通过用户id查询用户角色
     *
     * @param bsosId 用户id
     * @return 角色列表
     */
    List<String> queryUserRoleById(String bsosId);

    /**
     * 通过用户账号查询用户角色
     *
     * @param userAccount 用户账号
     * @return 角色列表
     */
    List<Role> queryUserRoleByUserAccount(String userAccount);

}

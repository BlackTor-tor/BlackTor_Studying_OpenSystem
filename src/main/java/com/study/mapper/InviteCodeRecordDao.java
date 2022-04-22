package com.study.mapper;

import com.study.entity.InviteCodeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (InviteCodeRecord)表数据库访问层
 *
 * @author Cyanogen
 * @since 2022-04-18 21:19:09
 */
@Mapper
public interface InviteCodeRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param recordId 主键
     * @return 实例对象
     */
    InviteCodeRecord queryById(Long recordId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<InviteCodeRecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param inviteCodeRecord 实例对象
     * @return 对象列表
     */
    List<InviteCodeRecord> queryAll(InviteCodeRecord inviteCodeRecord);

    /**
     * 新增数据
     *
     * @param inviteCodeRecord 实例对象
     * @return 影响行数
     */
    int insert(InviteCodeRecord inviteCodeRecord);

    /**
     * 修改数据
     *
     * @param inviteCodeRecord 实例对象
     * @return 影响行数
     */
    int update(InviteCodeRecord inviteCodeRecord);

    /**
     * 通过主键删除数据
     *
     * @param recordId 主键
     * @return 影响行数
     */
    int deleteById(Long recordId);

}

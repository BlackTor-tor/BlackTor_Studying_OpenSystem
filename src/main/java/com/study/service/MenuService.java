package com.study.service;

import com.study.entity.Menu;

import java.util.List;

/**
 * 记录访问的url(Menu)表服务接口
 *
 * @author Cyanogen
 * @since 2022-04-30 17:27:37
 */
public interface MenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    Menu queryById(Long menuId);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Long menuId);

    /**
     * 获取所有url
     * (每个url都包括所需的权限)
     *
     * @return url集合
     */
    List<Menu> getAllMenus();
}

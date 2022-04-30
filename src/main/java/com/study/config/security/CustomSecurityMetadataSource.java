package com.study.config.security;

import com.study.entity.Menu;
import com.study.entity.Role;
import com.study.service.MenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 配置每个url所需的权限
 *
 * @author Cyanogen
 * @date 2022-04-30 19:26
 */
@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 用于匹配url
     */
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Resource
    private MenuService menuService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获得当前请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //获取所有的url相关信息
        List<Menu> allMenus = menuService.getAllMenus();

        //url匹配
        for (Menu menu : allMenus) {
            //将查询到的所有url与当前请求的url进行匹配,如果匹配成功,返回所需要对应的角色
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                String[] roleArr = new String[roles.size()];
                for (int i = 0; i < roleArr.length; i++) {
                    roleArr[i] = roles.get(i).getRoleName();
                }
                return SecurityConfig.createList(roleArr);
            }
        }
        //配置文件中没有,则登入后即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}

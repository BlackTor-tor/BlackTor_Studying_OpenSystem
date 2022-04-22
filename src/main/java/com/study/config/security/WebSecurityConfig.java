package com.study.config.security;


import com.alibaba.fastjson.JSON;
import com.study.config.json.JsonResult;
import com.study.config.json.ResultCode;
import com.study.config.json.ResultTool;
import com.study.entity.User;
import com.study.service.UserService;
import com.study.service.impl.IndexServiceImpl;
import com.study.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * SpringSecurity配置类
 *
 * @author Cyanogen
 * @date 2022-04-13 21:05:33
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private IndexServiceImpl loginService;

    @Resource
    private CaptchaFilter captchaFilter;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private UserService userService;

    /**
     * 登录失败处理逻辑
     */
    @Resource
    LoginFailureHandler authenticationFailureHandler;


    /**
     * 自定义登录过滤器
     *
     * @return 过滤器类
     * @throws Exception 抛出异常
     */
    @Bean
    LoginFilter loginFilter() throws Exception {

        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setUsernameParameter("userAccount");
        loginFilter.setPasswordParameter("password");
        //登录处理的url
        loginFilter.setFilterProcessesUrl("/login");
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        //登录成功处理
        loginFilter.setAuthenticationSuccessHandler((req, res, auth) -> {

            // 生成jwt，并放置到请求头中
            String jwt = jwtUtil.generateToken(auth.getName());
            res.setHeader(jwtUtil.getHeader(), jwt);
            System.out.println("登录成功");

            //更新用户登录时间
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user.setRecentLoginTime(new Date());
            userService.update(user);

            //返回json数据
            JsonResult result = ResultTool.success();
            //处理编码方式，防止中文乱码的情况
            res.setContentType("text/json;charset=utf-8");
            //塞到HttpServletResponse中返回给前台
            res.getWriter().write(JSON.toJSONString(result));
        });
        //登录失败处理
        loginFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        return loginFilter;
    }

    /**
     * 自定义jwt过滤器
     *
     * @return 过滤器类
     * @throws Exception 抛出异常
     */
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    /**
     * 把数据库查出的用户信息交给SpringSecurity处理
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //把自定义的userDetailsService注入到配置文件中
        auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //登录验证
        http.authorizeRequests()
                //访问限制
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated();

        //异常处理
        http.exceptionHandling()
                //取消自动重定向到SpringSecurity自带的登录页
                .authenticationEntryPoint(((req, res, authException) -> {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
                    res.setContentType("text/json;charset=utf-8");
                    res.getWriter().write(JSON.toJSONString(result));
                }))
                //权限拒绝处理逻辑
                .accessDeniedHandler((req, res, e) -> {
                    res.setContentType("application/json;charset=UTF-8");
                    res.setStatus(HttpServletResponse.SC_FORBIDDEN);

                    JsonResult result = ResultTool.fail(e.getMessage());

                    res.getWriter().write(JSON.toJSONString(result));
                });

        //登出
        http.logout()
                //允许所有用户
                .permitAll()
                //登出成功处理逻辑
                .logoutSuccessHandler((request, response, authentication) -> {
                    if (authentication != null) {
                        new SecurityContextLogoutHandler().logout(request, response, authentication);
                    }

                    JsonResult result = ResultTool.success();
                    response.setContentType("application/json;charset=UTF-8");
                    response.setHeader(jwtUtil.getHeader(), "");
                    response.getWriter().write(JSON.toJSONString(result));
                });

        // 禁用session
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //配置自定义过滤器
        http.addFilter(jwtAuthenticationFilter())
                .addFilterAfter(loginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);

        //解决跨域
        http.cors().and().csrf().disable().headers().frameOptions().disable();
    }
}

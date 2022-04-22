package com.study.config.security;

import com.study.common.Const;
import com.study.exception.CaptchaException;
import com.study.utils.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 * (每次只调用一次)
 *
 * @author Cyanogen
 * @date 2022-04-15 18:05:37
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Resource
    RedisUtil redisUtil;

    @Resource
    LoginFailureHandler authenticationFailureHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String url = httpServletRequest.getRequestURI();

        if ("/login".equals(url) && "POST".equals(httpServletRequest.getMethod())) {

            try {
                // 校验验证码
                validate(httpServletRequest);
            } catch (CaptchaException e) {
                // 交给认证失败处理器
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    /**
     * 校验验证码逻辑
     *
     * @param httpServletRequest req
     */
    private void validate(HttpServletRequest httpServletRequest) {

        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("key");

        if (code == null || key == null || "".equals(code.trim()) || "".equals(key.trim())) {
            throw new CaptchaException("验证码错误");
        }

        if (!code.equals(redisUtil.hget(Const.CAPTCHA, key))) {
            throw new CaptchaException("验证码错误");
        }

        //验证后删除
        redisUtil.hdel(Const.CAPTCHA, key);
    }
}

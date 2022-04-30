package com.study.config.security;


import com.study.service.IndexService;
import com.study.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt过滤器
 *
 * @author Cyanogen
 * @date 2022-04-15 18:05:37
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private IndexService indexService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwt = request.getHeader(jwtUtil.getHeader());
        if (jwt == null || "".equals(jwt.trim())) {
            chain.doFilter(request, response);
            return;
        }

        Claims claim = jwtUtil.getClaimByToken(jwt);
        if (claim == null) {
            throw new JwtException("token 异常");
        }
        if (jwtUtil.isTokenExpired(claim)) {
            throw new JwtException("token已过期");
        }

        //从claim中获取用户账号
        String userAccount = claim.getSubject();

        //获取用户的权限等信息
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(userAccount, null, indexService.getUserAuthorityInfo(userAccount));

        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }
}

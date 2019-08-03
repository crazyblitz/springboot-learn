package com.ley.crazyblitz.jwt;

import com.ley.crazyblitz.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhiyuan
 **/
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtTokenUtils jwtTokenUtils;

    private static final String FILTER_APPLIED = "__spring_security_JwtFilter_filterApplied";

    /**
     * jwt token
     **/
    @Value("${jwt.token}")
    private String tokenHeader;

    /**
     * 私钥
     **/
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 过期时间
     **/
    @Value("${jwt.expiration}")
    private String expiration;

    public static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        log.info("tokenHeader: {},secret: {},expiration: {}", tokenHeader, secret, expiration);
        if (request.getAttribute(FILTER_APPLIED) != null) {
            chain.doFilter(request, response);
            return;
        }

        String tokenHeader = request.getHeader(this.tokenHeader);
        if (tokenHeader != null && tokenHeader.startsWith(TOKEN_PREFIX)) {
            String authToken = tokenHeader.substring(7);
            String username = jwtTokenUtils.getUsernameFromToken(authToken, secret);
            if (username != null && jwtTokenUtils.validateToken(authToken, username, secret)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.debug("username:{}, 数据库获取userName失败:{}", username, tokenHeader);
                    SecurityContextHolder.getContext().setAuthentication(null);
                }
            } else {
                if (username != null) {
                    log.debug("username:{}, token过期:{}", username, tokenHeader);
                }
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }


        // 防止filter在容器内多执行一遍
        request.setAttribute(FILTER_APPLIED, true);
        chain.doFilter(request, response);
    }
}
package com.ley.crazyblitz.business.controller;

import com.ley.crazyblitz.business.entity.User;
import com.ley.crazyblitz.utils.ApiResult;
import com.ley.crazyblitz.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.ley.crazyblitz.jwt.JwtAuthenticationTokenFilter.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/business/users")
@Slf4j
public class LoginController {

    @Resource
    private JwtTokenUtils jwtTokenUtils;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Resource
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody User user, HttpServletResponse response) {
        ApiResult<String> apiResult = new ApiResult<>();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUserName(), user.getUserPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Reload password post-security so we can generate token
            String token = TOKEN_PREFIX + jwtTokenUtils.generateToken(user.getUserName(), secret, expiration);
            response.addCookie(new Cookie("userToken", token));
            apiResult.success(token);
        } catch (BadCredentialsException exception) {
            log.error("验证出错: {}", exception.getMessage());
            apiResult.fail("密码不正确或者用户不存在.");
        }
        return apiResult;
    }

    @GetMapping("/index")
    public String index() {
        UserDetails userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "欢迎光临: " + userDetails.getUsername() + "," + userDetails.getPassword();
    }
}

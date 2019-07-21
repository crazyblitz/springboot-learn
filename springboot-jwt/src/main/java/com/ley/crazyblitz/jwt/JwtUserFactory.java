package com.ley.crazyblitz.jwt;

import com.ley.crazyblitz.business.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return JwtUser.builder().id(user.getUserId())
                .password(user.getUserPassword())
                .username(user.getUserName())
                .authorities(mapToGrantedAuthorities()).build();
    }

    /**
     * 获取权限
     **/
    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}

package com.crazyblitz.springboot.shiro.config;


import com.crazyblitz.springboot.shiro.utils.security.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro configuration
 *
 * @author Aliue
 */
@Configuration
@AutoConfigureAfter({ShiroHelperConfig.class})
@Slf4j
public class ShiroConfig {


    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") UserAuthorizingRealm authRealm,
                                           @Qualifier("cookieRememberMeManager") CookieRememberMeManager cookieRememberMeManager) {
        if (log.isInfoEnabled()) {
            log.info("securityManager()");
        }

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();


        // 设置rememberMe管理器
        securityManager.setRememberMeManager(cookieRememberMeManager);

        // 设置realm,解决doGetAuthorizationInfo方法没有调用问题
        securityManager.setRealm(authRealm);

        return securityManager;
    }

    /**
     * realm
     *
     * @return
     */
    @Bean(name = "authRealm")
    public UserAuthorizingRealm userAuthorizingRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        if (log.isInfoEnabled()) {
            log.info("userAuthorizingRealm bean");
        }
        UserAuthorizingRealm myAuthorizingRealm = new UserAuthorizingRealm();
        // 设置密码凭证匹配器
        myAuthorizingRealm.setCredentialsMatcher(matcher);
        return myAuthorizingRealm;
    }


    /**
     * cookie对象;
     *
     * @return
     */
    private SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称,对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //记住我cookie生效时间30天(259200)单位秒;
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * 记住我管理器 cookie管理对象;
     */
    @Bean(name = "cookieRememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    /**
     * 密码匹配凭证管理器
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordUtils.ALGORITHM_NAME);
        // 散列的次数
        hashedCredentialsMatcher.setHashIterations(PasswordUtils.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }

    /**
     * 开启shiro aop注解支持.
     * <p>
     * 使用代理方式;所以需要开启代码支持;
     * Controller才能使用@RequiresPermissions
     *
     * @param securityManager
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }
}
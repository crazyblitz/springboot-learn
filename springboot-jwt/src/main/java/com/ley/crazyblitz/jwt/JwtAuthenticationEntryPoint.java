package com.ley.crazyblitz.jwt;

import com.alibaba.fastjson.JSONObject;
import com.ley.crazyblitz.constants.NetConstants;
import com.ley.crazyblitz.utils.ApiResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截请求没有走凭证
 *
 * @author zhiyuan
 **/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        // 这个异常在未授权用户访问安全资源时会触发,我们会发出一个50未授权的response
        ApiResult<String> result = new ApiResult<>();
        result.setCode(NetConstants.RTN_TOKEN_EXPIRE);
        result.setMessage("Unauthorized: " + e.getMessage());
        httpServletResponse.getWriter().print(JSONObject.toJSONString(result));
    }
}

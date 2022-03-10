package com.padapp.pad.filter;

import com.alibaba.druid.util.StringUtils;
import com.padapp.pad.config.JwtConfig;
import com.padapp.pad.exception.TokenException;
import com.sun.javafx.geom.transform.SingularMatrixException;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JwtConfig jwtConfig ;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {

        //过滤预请求
        String method = request.getMethod().toUpperCase();
        if (method.equals(RequestMethod.OPTIONS.toString())){
            return true ;
        }


        /** Token 验证 */

        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token)){
            token = request.getParameter("Authorization");
        }
        if(StringUtils.isEmpty(token)){

            throw new TokenException(jwtConfig.getHeader()+ "不能为空");
        }

        Claims claims = null;
        try{
            claims = jwtConfig.getTokenClaim(token);
            if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
                throw new TokenException(jwtConfig.getHeader() + "失效，请重新登录。");
            }
        }catch (Exception e){
            throw new TokenException(jwtConfig.getHeader() + "失效，请重新登录。");
        }

        /** 设置 identityId 用户身份ID */
        //request.setAttribute("identityId", claims.getSubject());
        return true;
    }
}

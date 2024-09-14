package com.kuril.logindemo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.kuril.logindemo.pojo.Result;
import com.kuril.logindemo.utils.JWTUtitls;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截到了");
        String jwt = request.getHeader("token");
        if(jwt == null || jwt.equals("")) {
            log.info("无token");
            String notLogin = JSONObject.toJSONString(Result.error("NOT_LOGIN"));
            response.getWriter().write(notLogin);
            return false;
        }
//      解析失败返回未登录
        try{
            JWTUtitls.verifyToken(jwt);
        }catch (Exception e){
            log.info("token解析失败");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        log.info("jwt校验无问题，放行");
        return true;
    }
}

package com.demo.handler;

import com.demo.util.JWTuuid;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("========================请求处理前(1)=====================");
        String path = request.getRequestURL().toString();
        System.out.println("-----path----------------" + path);
        //http://localhost:8080/21
        //http://124.222.2.28:8080/24
        path=path.substring(24);//把请求的方法截取出来
        System.out.println("当前的path；"+path);
        //String authorization = request.getHeader("Authorization"); // 从请求头中获取Authorization字段
        //System.out.println("authorization---------" + authorization);
        String token="";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(path.startsWith("/admin"))
                {
                    if(cookie.getName().equals("admintoken"))
                    {
                        System.out.println("使用管理员token");
                        token=cookie.getValue();
                        // TODO: 使用 admintoken 进行管理员身份验证
                    }
                }
                else{
                if (cookie.getName().equals("token")) {
                    System.out.println("使用用户token");
                    token = cookie.getValue();
                    // TODO: 使用 token 进行用户身份验证
                   }
                }
            }
        }
        System.out.println("token----------"+token);
        if(token!=null){
            //验证token
            Claims claims = JWTuuid.testJwt(token);
            if(claims!=null){
                return true;
            }
            else{
                response.sendRedirect("/index"); // 跳转到登录页面
                return false;
            }
        }
        response.sendRedirect("/index"); // 跳转到登录页面
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("========================请求处理后，生成试图之前(2)=====================");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("========================结束时调用(3)=====================");
    }
}

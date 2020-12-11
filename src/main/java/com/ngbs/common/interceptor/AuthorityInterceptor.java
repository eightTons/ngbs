package com.ngbs.common.interceptor;

import com.ngbs.common.Const;
import com.ngbs.common.ServerResponse;
import com.ngbs.controller.UserController;
import com.ngbs.pojo.User;
import com.ngbs.util.CookieUtil;
import com.ngbs.util.JsonUtil;
import com.ngbs.util.RedisPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(("preHandle"));

        //判断登陆
        String loginToken = CookieUtil.readLoginToken(request);
        User user;
        if (loginToken != null) {
            String userJsonStr = RedisPoolUtil.get(loginToken);
            user  = JsonUtil.string2Obj(userJsonStr, User.class);
        }
        if (StringUtils.isEmpty(loginToken) || loginToken == null) {
            //未登录，返回false，不会调用controller里面的方法
            //response必须重置重置，否则会宝getWritter() has already been called for this response
            //这里我们手动接管了SpringMVC原生的返回，而是托管到拦截器中返回
            response.reset();
            //这是返回编码，否者会乱码
            response.setCharacterEncoding("UTF-8");
            //设置返回值类型
            response.setContentType("application/json;chartset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print("用户未登录,请先登录");
            out.flush();
            out.close();
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(("postCompletion"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info(("afterCompletion"));
    }
}

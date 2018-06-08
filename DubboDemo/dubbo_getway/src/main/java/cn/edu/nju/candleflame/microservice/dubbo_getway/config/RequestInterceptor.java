package cn.edu.nju.candleflame.microservice.dubbo_getway.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {
    /**
     * 在请求处理之前执行，主要用于权限验证、参数过滤等。
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token= httpServletRequest.getParameter("token");
        // 当token为1的请求才可以访问
        if (token!=null&&token.equals("1")){
            return true;
        }
        httpServletResponse.getWriter().write("token error");
        return false;
    }

    /**
     * 当前请求进行处理之后执行，主要用于日志记录、权限检查、性能监控、通用行为等。
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 当前对应的Interceptor 的preHandle返回值为true的时候执行，主要用于资源清理工作
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

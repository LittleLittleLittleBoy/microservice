package cn.edu.nju.candleflame.microservice.cloud_getway.configuration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class RequestFilter extends ZuulFilter {
    /**
     * 返回一个字符串，标识该过滤器在何时调用
     * pre：在请求被路由之前调用。
     * routing：在路由请求时候被调用
     * error：处理请求时发生错误时被调用
     * post：在routing和error过滤器之后被调用。
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 标识该过滤器在过滤器链里面执行的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个Boolean值，标识该过滤器是否开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体的过滤器逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx=RequestContext.getCurrentContext();
        HttpServletRequest request=ctx.getRequest();
        String token=request.getParameter("token");
        if (token==null||!token.equals("1")){
            ctx.setResponseBody("token error");
            //设置不对该请求进行路由操作
            ctx.setSendZuulResponse(false);
        }
        return null;
    }
}

package com.xiongfk.elasticSearch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 功能描述 TODO
 *
 * @Author xiongfk
 * @Date 2020/8/14
 * @Version 1.0
 **/
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("--------------执行过滤操作------------");
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        filterChain.doFilter(requestWrapper, servletResponse);
        try {
        } catch (Exception e) {
//            servletResponse.setContentType("application/json;charset=utf-8");//设置编码格式，以防前端页面出现中文乱
//            PrintWriter writer = servletResponse.getWriter();
//            Map<String,Object> map = new HashMap<>();
//            map.put("code", ResponseEnums.BAD_REQUEST.getCode());
//            map.put("msg", ResponseEnums.BAD_REQUEST.getMsg());
//            map.put("data", "");
//            ObjectMapper mapper = new ObjectMapper();
//            String string = mapper.writeValueAsString(map);
//            writer.append(string);
//            servletRequest.getRequestDispatcher("/error/exthrow").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
}

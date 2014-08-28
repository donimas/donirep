package kz.bee.doni.angulartest;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        String accessControlRequestHeaders = httpServletRequest.getHeader("Access-Control-Request-Headers");
        String accessControlRequestMethod = httpServletRequest.getHeader("Access-Control-Request-Method");
        if ((accessControlRequestMethod == null) || accessControlRequestMethod.trim().isEmpty()) {
            accessControlRequestMethod = "POST,GET,OPTIONS";
        } else {
            accessControlRequestMethod = accessControlRequestMethod + ",POST,GET,OPTIONS";
        }
        
        String origin = httpServletRequest.getHeader("origin");
        
        httpServletResponse.addHeader("Access-Control-Allow-Origin", origin);
        httpServletResponse.addHeader("Access-Control-Allow-Methods", accessControlRequestMethod);
        httpServletResponse.addHeader("Access-Control-Allow-Headers", accessControlRequestHeaders);
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        
        //"Access-Control-Allow-Headers", "Accept,Accept-Encoding,Accept-Language,Cache-Control,Connection,Content-Length,Content-Type,Cookie,Host,Pragma,Referer,RemoteQueueID,User-Agent"
        
        if (httpServletResponse.getHeader("Access-Control-Allow-Origin") == null) {
            httpServletResponse.addHeader("Access-Control-Allow-Origin", origin);
        }
        if (httpServletResponse.getHeader("Access-Control-Allow-Methods") == null) {
            httpServletResponse.addHeader("Access-Control-Allow-Methods", accessControlRequestMethod);
        }
        if (httpServletResponse.getHeader("Access-Control-Allow-Headers") == null) {
            httpServletResponse.addHeader("Access-Control-Allow-Headers", accessControlRequestHeaders);
        }
        if (httpServletResponse.getHeader("Access-Control-Allow-Credentials") == null) {
            httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        }

        try{
            if(request != null){
                System.out.println("request not null");
            } else {
                System.out.println("request is null");
            }
            chain.doFilter(request, response);
            System.out.println("origin: "+origin);
            System.out.println("Access-Control-Allow-Methods"+accessControlRequestMethod);
            System.out.println("Access-Control-Allow-Headers"+accessControlRequestHeaders);
        } catch(Exception e){
            System.out.println("FILTER EXCEPTION: "+e.getMessage());
        }
        
//        System.out.println(httpServletRequest.getContextPath() + " headers (cors):");
//        for (String key: httpServletResponse.getHeaderNames()) {
//            System.out.println("  " + key + ": " + httpServletResponse.getHeader(key));
//        }
    }

    @Override
    public void destroy() {}
}

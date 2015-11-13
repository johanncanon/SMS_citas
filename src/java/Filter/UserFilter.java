/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Desarrollo_Planit
 */
//@WebFilter("*.xhtml")
public class UserFilter implements Filter {
    
    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
    }

    private boolean noProteger(String urlStr) {

        if (urlStr.endsWith("/login.xhtml")) {
            return true;
        }
        
        if (urlStr.indexOf("/javax.faces.resource/") != -1) {
            return true;
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        
        //UserBean sesion = (UserBean) req.getSession().getAttribute("sesion");
        
        String url = req.getRequestURL().toString();
        boolean noProteger = noProteger(url);

        if (noProteger(url)) {            
            chain.doFilter(request, response);
            return;
        }
        
        Object sesion = req.getSession().getAttribute("Sesion");
        
        if(req.getSession().getAttribute("Sesion")==null){            
            resp.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
        }

        chain.doFilter(request, response);

    }
    

    @Override
    public void destroy() {
        this.filterConfig = null;

    }
    
}

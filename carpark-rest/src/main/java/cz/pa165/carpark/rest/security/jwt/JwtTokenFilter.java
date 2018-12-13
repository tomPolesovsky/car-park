package cz.pa165.carpark.rest.security.jwt;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT filter for security implements {@link Filter}
 *
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
public class JwtTokenFilter implements Filter {

    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        jwtTokenProvider = (JwtTokenProvider) WebApplicationContextUtils.
                getRequiredWebApplicationContext(filterConfig.getServletContext()).
                getBean("jwtTokenProvider");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (this.isLoginPage(request)) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {
            String token = jwtTokenProvider.resolveToken(request);

            if (token != null && jwtTokenProvider.validateToken(token)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

    private boolean isLoginPage(HttpServletRequest request) {
        return request.getRequestURI().contains("/login");
    }

    @Override
    public void destroy() {
        System.out.println("JwtTokenFilter destroyed");
    }

}
package com.loginsignup.security;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
public class customJwtFilter extends OncePerRequestFilter {
    private static final Logger logger =LoggerFactory.getLogger(customJwtFilter.class);
    private customJwtTokenProvider customJwtTokenProvider;
    private customUserDetailService customUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest arg0, HttpServletResponse arg1, FilterChain arg2)
            throws ServletException, IOException {
        // TODO Auto-generated method stubtr
        try{
            String jwtrequest =getJWTRequest(arg0);
            if(StringUtils.hasText(jwtrequest) && customJwtTokenProvider.validateToken(jwtrequest)){
                Long id =customJwtTokenProvider.getUserIdFromJwt(jwtrequest);
                UserDetails userDetails =customUserDetailService.loadUserById(id);
                UsernamePasswordAuthenticationToken auth =new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(arg0));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch(Exception e){
            logger.error("Could Not set User Authentication.", e);
        }
        arg2.doFilter(arg0, arg1);
    }
    private String getJWTRequest(HttpServletRequest arg0) {
        String tokenBearer = arg0.getHeader("Authorization");
        if (StringUtils.hasText(tokenBearer) && tokenBearer.startsWith("Bearer")) {
            return tokenBearer.substring(8, tokenBearer.length());
        }
        return null;
    }
}
package com.gcsi.listflix.identity.security;

import com.gcsi.listflix.identity.security.jwt.JwtTokenProvider;
import com.gcsi.listflix.identity.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author Gary Cheng
 */
@Component
public class AuthenticationFilter implements WebFilter {
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = WebUtils.resolveToken(exchange.getRequest());
        if (StringUtils.hasText(token) && this.jwtTokenProvider.validateToken(token)) {
            Authentication authentication = this.jwtTokenProvider.getAuthentication(token);
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }
        return chain.filter(exchange);
    }
}

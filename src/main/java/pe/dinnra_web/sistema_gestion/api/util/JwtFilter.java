package pe.dinnra_web.sistema_gestion.api.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.dinnra_web.sistema_gestion.api.model.entity.User;
import pe.dinnra_web.sistema_gestion.api.repository.UserRepository;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Cookie[] cookies = request.getCookies();
        String authHead = request.getHeader("Authorization");
        String token = null;
        String subject = null;
        String position = null;

        /*if (cookies != null){
            String cookieToken = jwtUtil.extractTokenFromCookies(cookies);
        }*/

        if (authHead != null && authHead.startsWith("Bearer")){
            token = authHead.substring(7);
            subject = jwtUtil.extractIdUser(token);
            position = jwtUtil.extractPosition(token);
        }

        if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null){

            User user = userRepository.findByIdUser(Long.valueOf(subject));

            if (jwtUtil.validateToken(token, user)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority(position)));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,response);
    }

}

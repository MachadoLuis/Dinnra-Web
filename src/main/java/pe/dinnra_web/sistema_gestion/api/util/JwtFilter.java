package pe.dinnra_web.sistema_gestion.api.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
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
        String authHead = request.getHeader("Authorization");
        try{
            if (authHead != null && authHead.startsWith("Bearer")){
                String token = authHead.substring(7);
                String subject = jwtUtil.extractIdUser(token);
                String position = jwtUtil.extractPosition(token);
                User user = userRepository.findByIdUser(Long.valueOf(subject));
                if (jwtUtil.validateToken(token, user)){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority(position)));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }catch (Exception e){
            SecurityContextHolder.clearContext();

            HttpSession httpSession = request.getSession(false);
            if (httpSession != null){
                httpSession.invalidate();
            }
        }

        filterChain.doFilter(request,response);
    }

}

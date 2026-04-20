package com.example.Ex7_Restful.API_Spring.service.auth;

import com.example.Ex7_Restful.API_Spring.entity.User;
import com.example.Ex7_Restful.API_Spring.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;

 /* HttpServletRequest ==>
     - Chứa thông tin về request từ client gửi lên
     - Có thể lấy header, query param, body, method, URI, ...
     - Ví dụ:
        request.getHeader("Authorization");  // header
        request.getParameter("id");           // query param
        request.getMethod();                  // GET, POST
        request.getRequestURI();              // /api/products*/

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Không có token → cho đi tiếp
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String username = jwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (io.jsonwebtoken.ExpiredJwtException e) { // token hết hạn
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"code\":401,\"message\":\"Token expired\"}");
            return; // không cho dđi vào controller

        } catch (io.jsonwebtoken.JwtException e) { // sai chũ ký, token bị sửa, ...//
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"code\":401,\"message\":\"Invalid token\"}");
            return;
        }

        // Token hợp lệ → đi tiếp
        filterChain.doFilter(request, response);
    }
}
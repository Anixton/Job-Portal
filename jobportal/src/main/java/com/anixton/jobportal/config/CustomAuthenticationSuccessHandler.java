package com.anixton.jobportal.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler  {

    /**
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @param authentication the Authentication object
     * @throws IOException if an input or output error occurs
     * @throws ServletException if a servlet error occurs
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        @NotNull Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        System.out.println("The username " + username + " is logged in.");

        boolean hasJobSeekerRole = authentication.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("Job Seeker"));
        boolean hasRecruiterRole = authentication.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("Recruiter"));

        if (hasRecruiterRole || hasJobSeekerRole) {
            response.sendRedirect("/dashboard/");
        }
    }
}

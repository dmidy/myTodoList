package com.example.myTodoList.components;

import com.example.myTodoList.model.User;
import com.example.myTodoList.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String remoteUser = request.getRemoteUser();
        if (remoteUser != null) {
            User user = userService.findByUsername(remoteUser); // Retrieving a user by ID
            if (user != null) {
                request.setAttribute("currentUser", user); // Add a user to the model for all requests
            }
        }
        return true;
    }
}

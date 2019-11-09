package com.mytlx.controller;

import com.mytlx.domain.User;
import com.mytlx.service.ILogin;
import com.mytlx.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author TLX
 * @date 2019.11.8
 * @time 18:07
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username + " " + password);

        ILogin userService = new UserServiceImpl();
        User login = userService.login(new User(username, password));

        System.out.println(login);

        if (null != login) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", login);
            System.out.println(request.getSession().getAttribute("user"));
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            // 登录失败
            request.setAttribute("msg", "登录失败");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

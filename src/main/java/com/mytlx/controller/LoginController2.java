package com.mytlx.controller;

import com.mytlx.domain.User;
import com.mytlx.service.ILogin;
import com.mytlx.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author TLX
 * @date 2019.11.8
 * @time 18:07
 */
@WebServlet(name = "LoginController2", urlPatterns = {"/LoginController2"})
public class LoginController2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/json; charset=utf-8");
        PrintWriter out = response.getWriter();


        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username + " " + password);

        ILogin userService = new UserServiceImpl();
        User login = userService.login(new User(username, password));

        System.out.println(login);

        JSONObject jsonObject = new JSONObject();

        if (null != login) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", login);
            jsonObject.put("username", login.getUsername());
            jsonObject.put("password", login.getPassword());

            out.print(jsonObject);
            out.flush();
        } else {
            out.print(jsonObject);
            out.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

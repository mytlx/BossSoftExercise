<%--
  Created by IntelliJ IDEA.
  User: TLX
  Date: 2019.11.8
  Time: 17:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>${requestScope.msg}</h3>

<form action="LoginController" method="post">
    <label for="username"> 用户名：</label>
    <input type="text" id="username" name="username">
    <br>
    <label for="password">密码：</label>
    <input type="password" id="password" name="password">
    <br>
    <input type="submit" value="登录">
</form>

</body>
</html>

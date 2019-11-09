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
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
</head>
<body>
<h3>${requestScope.msg}</h3>

<div>
    <label for="username"> 用户名：</label>
    <input type="text" id="username" name="username">
    <br>
    <label for="password">密码：</label>
    <input type="password" id="password" name="password">
    <br>
    <button id="login">登录</button>&nbsp;&nbsp;
    <label for="rememberme">
        <input type="checkbox" id="rememberme" name="rememberme" value="1">记住密码
    </label>
</div>

<script>
    $("#login").click(function () {
        login();
    });

    function setCookie() {
        var username = $("#username").val();
        var password = $("#password").val();
        var checked = $("#rememberme").is(':checked');

        console.log("setCookie方法是否记住密码：" + checked);

        if (checked) {
            // 设置cookie过期时间
            var date = new Date();
            date.setTime(date.getTime() + 60 * 60 * 1000);  // 只能这么写，60表示60秒钟

            console.log("添加cookie中");

            // 调用jquery.cookie.js中的方法设置cookie中的用户名    
            $.cookie("username", username, {expires: date, path: '/'});
            // 调用jquery.cookie.js中的方法设置cookie中的登陆密码，并使用base64（jquery.base64.js）进行加密
            $.cookie("password", password, {expires: date, path: '/'});
        } else {
            $.cookie("username", null);
            $.cookie("password", null);
        }
    }

    //清除所有cookie函数
    function clearAllCookie() {
        var date = new Date();
        date.setTime(date.getTime() - 10000);
        var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
        console.log("需要删除的cookie名字：" + keys);
        if (keys) {
            for (var i = keys.length; i--;)
                document.cookie = keys[i] + "=0; expire=" + date.toGMTString() + "; path=/";
        }
    }

    $(document).ready(function() {
        //任何需要执行的js特效
        $("table tr:nth-child(even)").addClass("even");
        getCookie();
    });

    // 获取cookie    
    function getCookie() {
        var username = $.cookie("username");
        var password = $.cookie("password");
        console.log("获取cookie:账户：" + username);
        console.log("获取cookie:密码：" + password);
        if (!username || username === "0") {
            console.log("账户：" + !username);
            //alert("请输出内容！");
        } else {
            $("#username").val(username);
        }
        if (!password || password === "0") {
            console.log("密码：" + !password);
        } else {
            //密码存在的话把密码填充到密码文本框    
            //console.log("密码解密后："+$.base64.decode(pwd));
            $("#password").val(password);
            //密码存在的话把“记住用户名和密码”复选框勾选住    
            $("#rememberme").attr("checked", "true");
        }

    }

    function login() {
        var username = $('#username').val();
        console.log("用户名：" + username);
        if (username === '') {
            alert("请输入用户名。");
            return;
        }
        var password = $('#password').val();
        console.log("密码：" + password);
        if (password === '') {
            alert("请输入密码。");
            return;
        }
        //判断是否选中复选框，如果选中，添加cookie  
        var rememberme = $("#rememberme").is(':checked');
        console.log("是否记住密码：" + rememberme);
        if (rememberme) {
            // 添加cookie    
            setCookie();
        }
        else {
            clearAllCookie();
        }
        loginRequest(username, password);//联网上传账号密码
    }

    function loginRequest(username, password) {
        $.ajax({
            type: "post",
            url: "LoginController2",
            dataType: "json",
            data: {
                username: username,
                password: password
            },
            success: function (res) {
                var ret = JSON.stringify(res);
                console.log("返回数据：" + ret);
                console.log(typeof(ret));
                if (ret === '{}') {
                    alert("无此账户");
                } else {
                    window.location.href = 'index.jsp';
                }
            },
            error: function () {
                alert("用户名密码错误");
            }
        });
        return false;
    }

</script>
</body>
</html>

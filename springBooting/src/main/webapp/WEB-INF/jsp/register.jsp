<%--
  Created by IntelliJ IDEA.
  User: gaosh
  Date: 2019/9/6
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册界面</title>
</head>
<body>
<form name="" action="/user/register/" method="post">
    <h4 align="center">用户注册界面</h4>
    <table align="center">
        <tr>
            <td>
                <input type="text" name="userName" placeholder="请输入用户名:"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="password" name="password" placeholder="请输入密码:"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="register" value="注册"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

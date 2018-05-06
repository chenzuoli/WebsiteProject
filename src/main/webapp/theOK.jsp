<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%-- 這句很重要 不能漏--%>
<%@ page import="top.wetech.czl.model.User" %>
<%
    String uid = request.getParameter("uid");
    String password = request.getParameter("password");
    if (uid != null && uid.trim().length() > 0 && password != null && password.trim().length() > 0) {
        User.dao.saveUser(uid, password);
    } else {
        response.sendRedirect("signup.html");
    }
%>
<html>
<head>
    <title>Result Page</title>
    <link rel="icon" href="images/favicon-16x16.png" type="image/x-icon"/>
</head>
<body>
<h2>注册成功!</h2>
<a href="login.html">点击登陆</a>
</body>
</html>
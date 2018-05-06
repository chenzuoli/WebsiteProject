<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%-- 這句很重要 不能漏--%>

<html>
<head>
    <title>百草园</title>
    <link rel="icon" href="images/favicon-16x16.png" type="image/x-icon"/>
</head>
<style>
    .div {
        width: auto;
        height: 100px;
        background-color: red;
    }
</style>
<body>
<div class="div">
    <h1>百草园</h1>
    <%
        Object name = session.getAttribute("uid");
        Object password = session.getAttribute("password");
        if (name != null && password != null) {
    %>
    <a href="javascript:void0">欢迎<%=name %>
    </a>
    <%
    } else {
    %>
    <a href="/login.jsp">登陆</a>
    <a href="/register.jsp">注册</a>
    <%
        }
    %>
    <a href="publish.jsp">发表文章</a>
</div>
<h2>Welcome!</h2>
</body>
</html>
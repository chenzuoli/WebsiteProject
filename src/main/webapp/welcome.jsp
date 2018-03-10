<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%-- 這句很重要 不能漏--%>
<%@ page import="java.util.*,java.io.*,top.wetech.czl.model.User"%>
<%
    String uid = request.getParameter("uid");
    String password = request.getParameter("password");
    List<User> listUser = User.dao.findByNameAndPassword(uid, password);
    if(listUser.size() <= 0) {
        response.sendRedirect("login.html");
    } else {
        session.setAttribute("uid", uid);
        session.setAttribute("password", password);
    }
%>
<html>
    <head>
        <title>Home Page</title>
        <link rel="icon" href="images/favicon-16x16.png" type="image/x-icon" />
    </head>
    <body>
        <h1>Welcome!<%=uid%></h1>
        <a href="publish.jsp">发表文章</a>
    </body>
</html>
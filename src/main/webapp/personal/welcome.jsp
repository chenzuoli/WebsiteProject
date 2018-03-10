<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%-- 這句很重要 不能漏--%>
<%@ page import="java.util.*,java.io.*,top.wetech.czl.model.Article"%>
<%
    String content = request.getParameter("content");
    String uid = session.getAttribute("uid").toString();
    Article.dao.saveArticle(content, "大数据", uid, "spark");
%>
<html>
    <head>
        <title>个人中心</title>
        <link rel="icon" href="../images/favicon-16x16.png" type="image/x-icon" />
    </head>
    <body>
        <p>Publish Success!</p>
    </body>
</html>
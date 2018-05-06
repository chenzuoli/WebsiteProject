<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%-- 這句很重要 不能漏--%>
<%@ page import="top.wetech.czl.model.Article" %>
<%
    String title = request.getParameter("title");
    String tag = request.getParameter("tag");
    String content = request.getParameter("content");
    String uid = session.getAttribute("uid").toString();
    Article.dao.saveArticle(title, tag, uid, content);
%>
<html>
<head>
    <title>个人中心</title>
    <link rel="icon" href="../images/favicon-16x16.png" type="image/x-icon"/>
</head>
<body>
<p>Publish Success!</p>
</body>
</html>
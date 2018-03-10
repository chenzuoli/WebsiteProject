<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%-- 這句很重要 不能漏--%>
<%@ page import="java.util.*,java.io.*,top.wetech.czl.model.User"%>
<%
    Object uid = session.getAttribute("uid");
    if(uid == null) {
        response.sendRedirect("login.html");
    }
%>
<html>
    <head>
        <title>发表文章</title>
        <link rel="icon" href="images/favicon-16x16.png" type="image/x-icon" />
    </head>
    <body>
        <h1>Welcome!<%=uid%></h1>
        <form action="personal/welcome.jsp" id="userform" method="post">
            <div id="div1">
                <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
            </div>
            <textarea id="text1" style="width:100%; height:200px;display:none" name="content"></textarea>
            <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
            <script type="text/javascript" src="/js/wangEditor.min.js"></script>
            <script type="text/javascript">
                var E = window.wangEditor
                var editor = new E('#div1')
                var $text1 = $('#text1')
                editor.customConfig.onchange = function(html) {
                    // 监控变化，同步更新到 textarea
                    $text1.val(html)
                }
                editor.create()
                // 初始化 textarea 的值
                $text1.val(editor.txt.html())
            </script>
            <input type="submit" value="发表"/>
        </form>
    </body>
</html>
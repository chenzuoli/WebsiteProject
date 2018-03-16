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
    <style>
        .divbody {
            width: 800px;
            height: 300px;
            border: 10px;
            margin-top: 50px;
            margin-left: auto;
            margin-right: auto;
        }
        .input1 {
            margin-top: 10px;
            margin-left: auto;
            margin-right: auto;
        }
        .input2 {
            margin-top: 10px;
            margin-left: auto;
            margin-right: auto;
        }
        .textarea {
            width:100%;
            height:200px;
            display:none;
        }
    </style>
    <body>
        <div class="divbody">
        <h1>Welcome!<%=uid%></h1>
        <form action="personal/welcome.jsp" id="userform" method="post">
            <div class="title">
                标题:<input class="input1" name="title"></input>
            </div>
            <div class="title">
                标签:<input class="input2" name="tag"></input>
            </div>
            <br>
            <div id="div1"></div>
            <textarea id="text" class="textarea" name="content"></textarea>
            <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
            <script type="text/javascript" src="/js/wangEditor.min.js"></script>
            <script type="text/javascript">
                var E = window.wangEditor
                var editor = new E('#div1')
                var $text1 = $('#text')
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
        </div>
    </body>
</html>
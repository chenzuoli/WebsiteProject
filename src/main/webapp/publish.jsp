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
        body {
            margin: 0;
            padding: 0;
            background: #fff;
            font-family: Arial;
            font-size: 12px;
            background: url(images/login.jpeg) no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
        .divbody {
            width: 70%;
            height: 600px;
            margin-left: auto;
            margin-right: auto;
            margin-top: 100px;
            background: white;
            border-radius: 5px;
        }
        .h {
            margin-left: 20px;
            margin-top: 120px;
        }
        .textarea {
            width:100%;
            display:none
        }
        .title {
            width: 50px;
            height: 20px;
            margin-left: 20px;
        }
        .input1 {
            margin-top: 5px;
            margin-left: 20px;
            float: left;
        }
        .editor {
            margin-left: 20px;
            margin-right: 20px;
        }
        .tag {
            width: 50px;
            height: 20px;
            margin-top:15px;
            margin-left: 20px;
        }
        .input2 {
            margin-top:5px;
            float: left;
            margin-left: 20px;
        }
        .submit {
            float: right;
            width: 100px;
            margin-right:20px;
        }
    </style>
    <body>
        <div class="divbody">
            <div class="h">
                <h1>写点东西吧，<%=uid%>！</h1>
            </div>
            <form action="personal/welcome.jsp" id="userform" method="post">
                <div class="title">标题：</div>
                <div class="input1">
                    <input name="title" type="text"/>
                </div><br><br>
                <div class="tag">标签：</div>
                <div class="input2">
                    <input name="tag" type="text"/>
                </div><br><br><br><br>
                <div id="div1" class="editor"></div>
                <textarea id="text1" class="textarea" name="content"></textarea>
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
                <br><br><br>
                <input class="submit" type="submit" value="发表"/>
            </form>
        </div>
    </body>
</html>
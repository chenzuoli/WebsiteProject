<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%-- 這句很重要 不能漏--%>
<html>
    <head>
    <title>登陆</title>
    <link rel="icon" href="images/favicon-16x16.png" type="image/x-icon" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
    <!-- 引入 Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 Shiv 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <style>
        body {
            margin: 0;
            background-image: url('images/bg.jpeg');
            background-repeat: no-repeat;
            background-position: 0% 0%;
            background-size: cover
        }
        input {
            width: 350px;
            height: 50px;
            margin-top: 25px;
            border-radius: 10px;
        }
        .div {
            width: 500px;
            height: 400px;
            border: 10px;
            background-color: #6495ED;
            margin-top: 150px;
            margin-left: auto;
            margin-right: auto;
            border-radius: 15px
        }
        .divwelcome {
            width: 500px;
            height: 80px;
            font-family: "微软雅黑";
            font-size: 25px;
            font-weight: bold;
            margin-top: 15px;
            margin-left: auto;
            margin-right: auto;
        }
        .divuser {
            float: left;
            width: 100px;
            height: 100px;
            font-size: 20px;
            margin-left: auto;
            margin-right: auto;
        }
        .divpassword {
            float: left;
            width: 100px;
            height: 100px;
            font-size: 20px;
            margin-left: auto;
            margin-right: auto;
        }
        .divsubmit {
            width: 500px;
            height: 100px;
            margin-left: auto;
            margin-right: auto;
        }
        .inputuser {
            float: left;
            width: 400px;
            height: 100px;
            font-size: 20px;
        }
        .inputpassword {
            float: left;
            width: 400px;
            height: 100px;
            font-size: 20px;
        }
        .inputsubmit {
            width: 150px;
            height: 40px;
            font-size: 20px;
            margin-top: 15px;
            margin-left: 175px;
            border-radius: 10px;
        }
        .center {
            text-align: center;
            line-height: 100px;
        }
    </style>
    </head>
    <body>
        <form action="welcome.jsp" method="post">
            <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
            <script src="https://code.jquery.com/jquery.js"></script>
            <!-- 包括所有已编译的插件 -->
            <script src="js/bootstrap.min.js"></script>
            <div class="div">
                <div class="divwelcome">
                    <div class="center">登陆百草园</div>
                </div>
                <div class="divuser">
                    <div class="center">用户名</div>
                </div>
                <div class="inputuser">
                    <input name="name" type="text"/>
                </div>
                <div class="divpassword">
                    <div class="center">密码</div>
                </div>
                <div class="inputpassword">
                    <input name="password" type="password"/>
                </div>
                <div class="divsubmit">
                    <input class="inputsubmit" value="登陆" type="submit">
                    <a href="register.jsp">还没有账号？点击注册</a>
                </div>
            </div>
        </form>
    </body>
</html>

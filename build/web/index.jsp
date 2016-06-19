<%-- 
    Document   : index
    Created on : 2016/06/08, 14:37:27
    Author     : gest
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            String error = request.getParameter("error");
            if(error == null){ error = ""; }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>WorkSpaces</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="common/css/standard.css">
        <link rel="stylesheet" href="common/css/login.css">
        <script src="https://www.google.com/recaptcha/api.js?hl=ja"></script>
        <script src="/WorkSpaces/common/checkform.js"></script>
        <script>Alert('<%=error%>');</script>
    </head>
    <body>
        <div id="page">
            <section id="head">
                <h1>WorkSpaces</h1>
            </section>
            <section id="body">
                <section id="login">
                    <p class="title">ログイン</p>
                    <form action="/WorkSpaces/Login" method="POST">
                    <p class="username">ユーザー名<br>
                    <input type="text" name="userName"></p>
                    <p class="password">パスワード<br>
                    <input type="password" name="passWord"></p>
                    <section id="gc">
                    <div class="g-recaptcha" data-sitekey="6Ldh7yATAAAAAFXQSTDIvh6WOkkP0TXRcB12KZR9" disable="false">
                    </div>
                    </section>
                    <div class="button" >
                    <input type="submit" value="ログイン">
                    </div>
                    </form>
                    <p class="regist">新規登録される方は<a href="Registration">こちら</a></p>
                </section>
            </section>
        </div>
    </body>
</html>

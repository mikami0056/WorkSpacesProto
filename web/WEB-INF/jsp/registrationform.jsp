<%-- 
    Document   : registrationform
    Created on : 2016/06/11, 21:21:46
    Author     : gest
--%>

<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WorkSpaces</title>
        <link rel="stylesheet" href="common/css/standard.css">
        <link rel="stylesheet" href="common/css/registrationform.css">
        <script src="common/registrationform.js" charset="utf-8"></script>
    </head>
    <body>
        <div id="page">
        <header id="pageHead">
        <h1>WorkSpaces</h1>
        </header>
        <div id="form">
        <section class="title">
        <p>新規登録</p>
        </section>
        <form action="/WorkSpaces/Registration" method="POST" name="regist" onSubmit="return checkParameters()">
        <p class="username">ユーザー名<br>
        <input type="text" name="username" placeholder="半角英数字を入力してください" size="20" required></p>
        <p class="password">パスワード<br>
        <input type="password" name="password" placeholder="半角英数字を入力してください" size="20" required></p>
        <p class="repassword">パスワード再入力<br>
        <input type="password" name="repassword" placeholder="半角英数字を入力してください" size="20" required></p>
        <p id="passcheck"></p>
        <p class="mail">メールアドレス<br>
        <input type="email" name="mail" placeholder="半角英数字を入力してください" required></p>
        <p class="question">秘密の質問
        <select name="questionID">
            <c:forEach var="question" items="${questions}">
            <option value="${question.getQuestionID()}">${question.getQuestion()}</option>
            </c:forEach>
        </select></p> 
        <p class="answer"><textarea name="answer" placeholder="回答" cols=40 rows=4></textarea></p>
        <p class="check"><label for="confirm">上記の内容で問題なければチェックしてください</label>
        <input type="checkbox" name="confirm" id="confirm" onClick="submitcheck(this)"></p>
        <input type="submit" disabled="false" value="登録" name="button">
        <input type="hidden" name="operation" value="REGIST">
        </form>
        </div>
        </div>
    </body>
</html>

<%-- 
    Document   : updatemypicture
    Created on : 2016/06/11, 20:55:10
    Author     : gest
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="common/css/standard.css">
        <link rel="stylesheet" href="common/css/globalnavi.css">
        <title>WorkSpaces</title>
    </head>
    <body>
        <div id="page">
        <header id="pageHead">
        <h1 id="siteTitel">WorkSpaces</h1>
            <nav class="global">
                <ul>
                <li><a href="/WorkSpaces/WorkSpaces">ホーム</a></li>
                <li class="current"><a href="/WorkSpaces/WorkSpaces?option=MyPage">マイページ</a></li>
                <li><a href="/WorkSpaces/WorkSpaces?option=Logout">ログアウト</a></li>
                <li><a href="/WorkSpaces/WorkSpaces?option=Contact">お問い合わせ</a></li>
                </ul>
            </nav>
        </header>
        <h1>写真情報更新</h1>
        <img src="${picture4Update.path}">
        <form action="Manage" method="POST">
        <p>題名：<input type="text" name="name" value="${picture4Update.name}"></p>
        <p>投稿者コメント：<input type="text" name="comment" value="${picture4Update.comment}"></p>
        <select name="category">
                <%for(int i = 1; i < 6; i++){%>
                <option value="<%=i%>">カテゴリー<%=i%></option>
                <%}%>
        </select>
        <input type="hidden" name="option" value="Update">
        <input type="submit" value="更新">
        </form>
        </div>
    </body>
</html>


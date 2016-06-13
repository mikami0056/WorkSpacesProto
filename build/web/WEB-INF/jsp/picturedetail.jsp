<%-- 
    Document   : picturedetail
    Created on : 2016/06/12, 12:27:42
    Author     : gest
--%>

<%@page import="Model.PictureDataBeans"%>
<%@page import="Model.CountDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    HttpSession hs = request.getSession();
    PictureDataBeans picture = (PictureDataBeans)hs.getAttribute("picture4Detail");
    String strID = String.valueOf(picture.getPictureID());
    CountDataBeans countData = (CountDataBeans)hs.getAttribute(strID);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="common/css/standard.css">
        <link rel="stylesheet" href="common/css/globalnavi.css">
        <link rel="stylesheet" href="common/css/picturedetail.css">
        <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script src="/WorkSpaces/common/picturedetail.js"></script>
        <title>WorkSpaces</title>
    </head>
    <body>
        <div id="page">
        <header id="pageHead">
            <h1 id="siteTitel">WorkSpaces</h1>
            <nav class="global">
                <ul>
                <li class="current"><a href="/WorkSpaces/WorkSpaces">ホーム</a></li>
                <li><a href="/WorkSpaces/WorkSpaces?option=MyPage">マイページ</a></li>
                <li><a href="/WorkSpaces/WorkSpaces?option=Logout">ログアウト</a></li>
                <li><a href="/WorkSpaces/WorkSpaces?option=Contact">お問い合わせ</a></li>
                </ul>
            </nav>
        </header>
        <section id="outline">
        <p class="title">写真詳細</p>
        <span class="contain" style="background-image: url('${picture4Detail.path}')"></span>
        <%--<p><img src="${picture4Detail.path}"></p>--%>
        <p class="subject">${picture4Detail.name} <br> ${picture4Detail.userName} / ${picture4Detail.dateTime}</p>
        <p class="ownername">投稿者コメント</p>
        <p class="commentblock">${picture4Detail.comment}</p>
    
        <p>評価：
        <button onClick="countBeautiful(${picture4Detail.pictureID})">キレイ</button><span id="beautiful"><%=countData.getBeautiful()%></span>
        <button onClick="countCool(${picture4Detail.pictureID})">カッコイイ</button><span id="cool"><%=countData.getCool()%></span> 
        <button onClick="countStylish(${picture4Detail.pictureID})">オシャレ</button><span id="stylish"><%=countData.getStylish()%></span> 
        <%--合計<span id="sum">[${picture4Detail.sum}]</span>--%></p>
        </section>
        <section id="comment">
            <input type="text" name="comment" placeholder="コメントを入力">
            <button onClick="insertComment(comment, )" value="投稿">
        </section>
        </div>
    </body>
</html>
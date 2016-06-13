<%-- 
    Document   : managemypicture
    Created on : 2016/06/11, 20:15:00
    Author     : gest
--%>

<%@page import="java.util.Map"%>
<%@page import="Model.PictureDataBeans"%>
<%@page import="Model.PictureDataBeans"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Model.UserDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WorkSpaces</title>
        <link rel="stylesheet" href="common/css/standard.css">
        <link rel="stylesheet" href="common/css/globalnavi.css">
        <link rel="stylesheet" href="common/css/managemypicture.css">
    </head>
    <body>
        <div id="page">
        <header id="pageHead">
            <h1 id="siteTitel">WorkSpaces</h1>
            <nav class="global">
                <ul>
                <li><a href="/WorkSpaces/WorkSpaces">ホーム</a></li>
                <li class="current"><a href="/WorkSpaces/WorkSpaces?option=MyPage">マイページ</a></li>
                <li><a href="/WorkSpaces/Logout">ログアウト</a></li>
                <li><a href="/WorkSpaces/Contact">お問い合わせ</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <%
            HttpSession hs = request.getSession();
            Map<Integer, PictureDataBeans> pictures = (HashMap<Integer, PictureDataBeans>)hs.getAttribute("pictures");
            if(!pictures.isEmpty()){
                for(Integer pictureID : pictures.keySet()){
                    PictureDataBeans picture = pictures.get(pictureID);
        %>
            <div id="outline">
                <span class="contain" style="background-image: url('<%=picture.getPath()%>')"></span>
                <p class="subject"><%=picture.getName()%> / <%=picture.getDateTime()%></p>
                <p class="commenttitle">投稿者コメント</p>
                <p class="commentblock"><%=picture.getComment()%></p>
                <a class="update" href="Manage?option=Update&id=<%=picture.getPictureID()%>">投稿情報を変更</a>
                <a class="delete" href="Manage?option=Delete&id=<%=picture.getPictureID()%>">投稿情報を削除</a>
            </div>    
            <%}%>
        <%} else {%>
            <p>投稿された写真はありません</p>
        <%}%>
        </div>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>PolyOE - Video Management</title>
    <style>
        table, th, td { border: 1px solid black; border-collapse: collapse; padding: 5px; }
        a { margin-right: 10px; }
    </style>
</head>
<body>
    <nav>
        <a href="${pageContext.request.contextPath}/video/byUser">Tìm theo User</a>
        <a href="${pageContext.request.contextPath}/video/byKeyword">Tìm theo từ khóa</a>
        <a href="${pageContext.request.contextPath}/video/byVideo">Tìm user theo Video</a>
        <a href="${pageContext.request.contextPath}/video/byFavoriteStatus">Video có/không yêu thích</a>
        <a href="${pageContext.request.contextPath}/reportLikes">Báo cáo lượt thích</a>
        <a href="${pageContext.request.contextPath}/random10">10 Video ngẫu nhiên</a>
        <a href="${pageContext.request.contextPath}/video/reportByYear">Thống kê theo năm</a>
    </nav>
    <hr/>
    <jsp:include page="${view}" />
</body>
</html>
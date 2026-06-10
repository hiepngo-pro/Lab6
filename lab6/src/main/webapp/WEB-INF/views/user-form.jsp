<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>User Form</title></head>
<body>
<h2><c:if test="${empty user}">Thêm mới</c:if><c:if test="${not empty user}">Cập nhật</c:if> User</h2>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
<form action="user" method="post">
    ID: <input type="text" name="id" value="${user.id}" <c:if test="${not empty user}">readonly</c:if>/><br/>
    Password: <input type="password" name="password" value="${user.password}" /><br/>
    Họ tên: <input type="text" name="fullname" value="${user.fullname}" /><br/>
    Email: <input type="email" name="email" value="${user.email}" /><br/>
    Admin: <input type="checkbox" name="admin" ${user.admin ? 'checked' : ''} /><br/>
    Phone: <input type="text" name="phone" value="${user.phone}" /><br/>
    <button type="submit">Lưu</button>
    <a href="user">Hủy</a>
</form>
</body>
</html>
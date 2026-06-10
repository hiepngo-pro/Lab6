<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Quản lý User</title></head>
<body>
<h2>Danh sách Users</h2>
<a href="user?action=add">Thêm mới</a>
<table border="1">
    <tr><th>ID</th><th>Họ tên</th><th>Email</th><th>Admin</th><th>Phone</th><th>Hành động</th></tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td>${u.fullname}</td>
            <td>${u.email}</td>
            <td>${u.admin ? "Có" : "Không"}</td>
            <td>${u.phone}</td>
            <td>
                <a href="user?action=edit&id=${u.id}">Sửa</a>
                <a href="user?action=delete&id=${u.id}" onclick="return confirm('Xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
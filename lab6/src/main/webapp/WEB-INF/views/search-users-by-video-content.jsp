<h2>Tìm người dùng thích video</h2>
<form action="${pageContext.request.contextPath}/searchUsersByVideo" method="post">
    Video ID: <input type="text" name="videoId" value="${videoId}" required/>
    <button type="submit">Tìm</button>
</form>
<c:if test="${not empty users}">
    <table border="1">
        <tr><th>Username</th><th>Fullname</th><th>Email</th><th>Role</th></tr>
        <c:forEach var="u" items="${users}">
            <tr><td>${u.id}</td><td>${u.fullname}</td><td>${u.email}</td><td>${u.admin ? 'Admin' : 'User'}</td></tr>
        </c:forEach>
    </table>
</c:if>
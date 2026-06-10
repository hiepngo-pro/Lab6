<h2>Tìm video yêu thích theo User ID</h2>
<form action="${pageContext.request.contextPath}/searchByUser" method="post">
    User ID: <input type="text" name="userId" value="${userId}" required/>
    <button type="submit">Tìm</button>
</form>
<c:if test="${not empty videos}">
    <h3>Kết quả cho user: ${userId}</h3>
    <table>
        <tr><th>Id</th><th>Title</th><th>Views</th><th>Active</th></tr>
        <c:forEach var="v" items="${videos}">
            <tr><td>${v.id}</td><td>${v.title}</td><td>${v.views}</td><td>${v.active ? 'Có' : 'Không'}</td></tr>
        </c:forEach>
    </table>
</c:if>
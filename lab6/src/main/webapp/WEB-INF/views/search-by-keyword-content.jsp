<h2>Tìm video theo từ khóa trong title</h2>
<form action="${pageContext.request.contextPath}/searchByKeyword" method="post">
    Từ khóa: <input type="text" name="keyword" value="${keyword}" required/>
    <button type="submit">Tìm</button>
</form>
<c:if test="${not empty videos}">
    <table>
        <tr><th>Id</th><th>Title</th><th>Views</th><th>Active</th></tr>
        <c:forEach var="v" items="${videos}">
            <tr><td>${v.id}</td><td>${v.title}</td><td>${v.views}</td><td>${v.active ? 'Có' : 'Không'}</td></tr>
        </c:forEach>
</c:if>
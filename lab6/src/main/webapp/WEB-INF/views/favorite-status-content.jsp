<h2>Video có yêu thích hoặc không</h2>
<p>
    <a href="${pageContext.request.contextPath}/favoriteStatus?favorite=true">Video có yêu thích</a> |
    <a href="${pageContext.request.contextPath}/favoriteStatus?favorite=false">Video không yêu thích</a>
</p>
<c:if test="${not empty videos}">
    <h3>${favorite ? 'Có yêu thích' : 'Không yêu thích'}</h3>
    <table>
        <tr><th>Id</th><th>Title</th><th>Views</th><th>Active</th></tr>
        <c:forEach var="v" items="${videos}">
            <tr><td>${v.id}</td><td>${v.title}</td><td>${v.views}</td><td>${v.active ? 'Có' : 'Không'}</td></tr>
        </c:forEach>
    </table>
</c:if>
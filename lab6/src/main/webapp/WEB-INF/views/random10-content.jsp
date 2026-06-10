<h2>10 Video ngẫu nhiên</h2>
<c:if test="${not empty videos}">
    <table>
        <tr><th>Id</th><th>Title</th><th>Views</th><th>Active</th></tr>
        <c:forEach var="v" items="${videos}">
            <tr><td>${v.id}</td><td>${v.title}</td><td>${v.views}</td><td>${v.active ? 'Có' : 'Không'}</td></tr>
        </c:forEach>
    </table>
</c:if>
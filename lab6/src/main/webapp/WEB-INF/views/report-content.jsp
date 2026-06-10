<h2>Báo cáo tổng hợp lượt thích theo video</h2>
<c:if test="${not empty reports}">
    <table border="1">
        <tr><th>Title</th><th>Favorite Count</th><th>Newest Date</th><th>Oldest Date</th></tr>
        <c:forEach var="r" items="${reports}">
            <tr><td>${r.group}</td><td>${r.likes}</td><td>${r.newest}</td><td>${r.oldest}</td></tr>
        </c:forEach>
    </table>
</c:if>
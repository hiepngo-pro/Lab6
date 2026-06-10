<h2>Thống kê lượt thích theo năm (Stored Procedure)</h2>
<form action="${pageContext.request.contextPath}/reportByYear" method="post">
    Năm: <input type="number" name="year" required/>
    <button type="submit">Xem</button>
</form>
<c:if test="${not empty error}"><p style="color:red">${error}</p></c:if>
<c:if test="${not empty reports}">
    <h3>Kết quả năm ${year}</h3>
    <table>
        <tr><th>Title</th><th>Favorite Count</th><th>Newest Date</th><th>Oldest Date</th></tr>
        <c:forEach var="r" items="${reports}">
            <tr><td>${r.group}</td><td>${r.likes}</td><td>${r.newest}</td><td>${r.oldest}</td></tr>
        </c:forEach>
    </table>
</c:if>
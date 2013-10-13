<h2>Tags</h2>

<ul class="list-unstyled">
<c:forEach items="${tags}" var="tag">
    <li>
        <h4><a href="${contextPath}/tags/${tag.tagName}">${tag.tagName}</a>
        <span class="badge">${tagCounts.get(tag.tagId)}</span></h4>
    </li>
</c:forEach>
</ul>

<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>
<script type="text/javascript">
    $("#homeTab").addClass("active");
    
</script>

<!--Page Content Start-->
<blog-tags:blogPostList blogPosts="${blogPosts}" tagMap="${tagMap}" />


<div class="text-center"> <!--Pagination Start-->
    <ul class="pagination">
        <!--Previous Page-->
        <c:choose>
            <c:when test="${currentPage > 1}">
                <li><a href="${contextPath}/blog?page=${currentPage-1}">&laquo;</a></li>
            </c:when>
            <c:otherwise>
                <li class="disabled"><a disabled>&laquo;</a></li>
            </c:otherwise>
        </c:choose>
        
        <!--pages-->
        <c:forEach begin="1" end="${numPages}" var="page">
            <c:choose>
                <c:when test="${page == currentPage}">
                    <li class="active"><a disabled>${page}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${contextPath}/blog?page=${page}">${page}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
                
        <!--Next Page-->  
        <c:choose>
            <c:when test="${currentPage < numPages}">
                <li><a href="${contextPath}/blog?page=${currentPage+1}">&raquo;</a></li>
            </c:when>
            <c:otherwise>
                <li class="disabled"><a disabled>&raquo;</a></li>
            </c:otherwise>
        </c:choose>
         
    </ul>
</div> <!--Pagination End-->


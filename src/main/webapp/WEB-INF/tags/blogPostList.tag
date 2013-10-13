<%@tag description="Creates a list of blog post summaries" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%@ attribute name="blogPosts" required="true" type="java.util.ArrayList" description="List of blog post objects" %>
<%@ attribute name="tagMap" required="true" type="java.util.HashMap" description="Map of tags for each post" %>


<c:choose>
    <c:when test="${fn:length(blogPosts) > 0}">
        <c:forEach items="${blogPosts}" var="post">
            <c:choose>
                <c:when test="${post.status == 'live'}">
                    <div id="${post.slug}-block" class="blog-post">
                        <h2 id="${post.slug}-title" class="blog-title"><a href="${contextPath}/blog/${post.slug}">${post.title}</a></h2>
                        <p id="${post.slug}-date" class="blog-date text-muted">${post.printPostDate()}</p>
                        <p id="${post.slug}-markup" class="blog-markup">${post.markup}</p>
                        <div id="${post.slug}-footer" class="blog-footer well">
                            <div class="pull-left">
                                Tags:&nbsp;
                            <c:forEach items="${tagMap.get(post.postId)}" var="tag">
                                <a href="${contextPath}/tags/${tag.tagName}">${tag.tagName}</a>&nbsp;
                            </c:forEach>
                            </div>
                            <div class="pull-right">
                                <c:choose>
                                    <c:when test="${post.enableComments == true}">
                                        <a href="${contextPath}/blog/${post.slug}#comments"><span class="glyphicon glyphicon-comment"></span>&nbsp;0</a>
                                        &nbsp;&nbsp;
                                    </c:when>
                                </c:choose>
                                        <a href="${contextPath}/blog/${post.slug}"><span class="glyphicon glyphicon-file"></span>&nbsp;Read More</a>
                            </div>
                            <br/><!--needed for proper well alignment-->
                        </div>
                    </div>
                    <br/>
                </c:when>
            </c:choose>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h2>No posts to display.</h2>
    </c:otherwise>
</c:choose>
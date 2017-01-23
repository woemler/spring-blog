<%@tag description="Creates a list of blog post summaries" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%@ attribute name="blogPosts" required="true" type="java.util.ArrayList" description="List of blog post objects" %>

<c:choose>
    <c:when test="${fn:length(blogPosts) > 0}">
        <c:forEach items="${blogPosts}" var="post">
            <c:choose>
                <c:when test="${post.status == 'active'}">
                    <div id="${post.slug}-block" class="blog-post">
                        <h2 id="${post.slug}-title" class="blog-title"><a href="${contextPath}/blog/${post.slug}">${post.title}</a></h2>
                        <p id="${post.slug}-date" class="blog-date text-muted">${post.printPostDate()} GMT</p>
                        
                        <p id="${post.slug}-content" class="blog-content">${post.getPostPreview()}</p></p>
                        
                        <div id="${post.slug}-footer" class="blog-footer well">
                            <div class="pull-left">
                                Tags:&nbsp;
                            <c:forEach items="${post.tags}" var="tag">
                                <a href="${contextPath}/tags/${tag}">${tag}</a>&nbsp;
                            </c:forEach>
                            </div>
                            <div class="pull-right">
                                <c:choose>
                                    <c:when test="${post.enableComments == true}">
                                        <a><span class="glyphicon glyphicon-comment"></span></a>&nbsp;<a href="${contextPath}/blog/${post.slug}#disqus_thread">Comments</a>
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
                    
        <script type="text/javascript">
            var disqus_shortname = '<spring:eval expression="@environment.getProperty('disqus.shortname')" />'; 

            (function () {
                var s = document.createElement('script'); s.async = true;
                s.type = 'text/javascript';
                s.src = '//' + disqus_shortname + '.disqus.com/count.js';
                (document.getElementsByTagName('HEAD')[0] || document.getElementsByTagName('BODY')[0]).appendChild(s);
            }());
        </script>
                    
    </c:when>
    <c:otherwise>
        <h2>No posts to display.</h2>
    </c:otherwise>
</c:choose>

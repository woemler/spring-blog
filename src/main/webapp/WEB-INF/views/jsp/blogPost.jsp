<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>

<blog-tags:header />

<script type="text/javascript">
    $("#homeTab").addClass("active");   
</script>

<div class="container"><!--Body content start-->
    <div class="row">
        <!--Left Column-->
        <div class="col-sm-9 col-xs-12">

            <!--Post content-->
            <div id="${requestedPost.slug}-block" class="blog-post">
                <h2 id="${requestedPost.slug}-title" class="blog-title">${requestedPost.title}</h2>
                <p id="${requestedPost.slug}-date" class="blog-date text-muted">${requestedPost.printPostDate()} GMT</p>
                <p id="${requestedPost.slug}-tags" class="blog-tags">
                    Tags:&nbsp;
                    <c:forEach items="${postTags}" var="tag">
                        <a>${tag.tagName}</a>&nbsp;
                    </c:forEach>
                </p>
                <p id="${requestedPost.slug}-markup" class="blog-markup">${requestedPost.markup}</p>

                <!--Pager-->
                <ul class="pager">
                    <c:choose>
                        <c:when test="${previousPost != null}">
                            <li class="previous"><a href="${contextPath}/blog/${previousPost.slug}">&larr; Previous Post</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="previous disabled"><a disabled="true">&larr; Previous Post</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${nextPost != null}">
                            <li class="next"><a href="${contextPath}/blog/${nextPost.slug}">Next Post &rarr;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="next disabled"><a disabled="true">Next Post &rarr;</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>

                <!--Comments-->
                <c:choose>
                    <c:when test="${requestedPost.enableComments == true}">
                       <p id="${requestedPost.slug}-comments" class="blog-comments">
                            <h3>Comments</h3>
                            <blog-tags:disqusComments />
                        </p> 
                    </c:when>
                </c:choose>

            </div>
                
        </div><!--Body content end-->

        <div class="col-sm-3 col-xs-12"><!--Sidebar start-->
            <blog-tags:blogSidebar />
        </div><!--Sidebar end-->
        
    </div>
</div>

<blog-tags:footer />

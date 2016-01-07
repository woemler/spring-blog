<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<blog-tags:header />

<div class="container"><!--Body content start-->
    <div class="row">
        <!--Left Column-->
        <div class="col-sm-9 col-xs-12">

            <h2>Tags</h2>

            <ul class="list-unstyled">
            <c:forEach items="${tags}" var="tag">
                <li>
                    <h4><a href="${contextPath}/tags/${tag.tagName}">${tag.tagName}</a>
                    <span class="badge">${tagCounts.get(tag.tagId)}</span></h4>
                </li>
            </c:forEach>
            </ul>
            
        </div><!--Body content end-->

        <div class="col-sm-3 col-xs-12"><!--Sidebar start-->
            <blog-tags:blogSidebar />
        </div><!--Sidebar end-->
        
    </div>
</div>

<blog-tags:header />

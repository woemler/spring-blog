<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<blog-tags:header />

<script type="text/javascript">
    $("#mediaTab").addClass("active");
</script>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-xs-12">
            <h1>Media</h1>
            
            <h3>Images</h3>
            <c:choose>
                <c:when test="${images.size()>0}">
                    <!--Thumbnails-->
                    <c:forEach items="${images}" var="image">
                        <div class="col-xs-3">
                            <a href="${image}" class="thumbnail"><img src="${image}" /></a>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h5>No images to display.</h5>
                </c:otherwise>
            </c:choose>
        </div>
        
        <div class="col-sm-3 col-xs-12"><!--Sidebar start-->
            <blog-tags:blogSidebar />
        </div><!--Sidebar end-->
    </div>
</div>

<blog-tags:footer />

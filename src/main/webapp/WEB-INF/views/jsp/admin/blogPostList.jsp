<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<blog-tags:header />

<div class="container"><!--Body content start-->
    <div class="row">
        <!--Left Column-->
        <div class="col-sm-9 col-xs-12">

            <h2>Blog Posts</h2>

            <a href="${contextPath}/admin/blog/new"><button class="btn btn-info"><span class="glyphicon glyphicon-plus-sign"></span> New Post</button></a>
            <br/><br/>
            <c:choose>
                <c:when test="${fn:length(postList) > 0}">
                    <table class="table table-striped">
                        <tr>
                            <th>Title</th>
                            <th>Slug</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Comments</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach items="${postList}" var="post">
                            <tr>
                                <td><a href="${contextPath}/blog/${post.slug}">${post.title}</a></td>
                                <td>${post.slug}</td>
                                <td>${post.printPostDate()}</td>
                                <td>${post.status}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${post.enableComments == true}">
                                            <span class="glyphicon glyphicon-ok-sign"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="glyphicon glyphicon-remove-sign"></span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><a href="${contextPath}/admin/blog/${post.postId}" title="Edit blog post."><button class="btn btn-info"><span class="glyphicon glyphicon-edit"></span></button></a></td>
                                <td><a onclick="deletePost(${post.postId})" title="Delete blog post."><button class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></button></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    No posts available.
                </c:otherwise>
            </c:choose>
                    
            </div><!--Body content end-->

        <div class="col-sm-3 col-xs-12"><!--Sidebar start-->
            <blog-tags:adminSidebar />
        </div><!--Sidebar end-->
        
    </div>
</div>

<script type="text/javascript">

    function deletePost(id){
        if (confirm("Are you sure you want to delete this post?")){
            $.ajax({
                type: "POST",
                url: "${contextPath}/admin/delete/blog/" + id,
            }).done(function(data){
                alert(data);
                location.reload();
            });
        }
    }

</script>

<blog-tags:footer />

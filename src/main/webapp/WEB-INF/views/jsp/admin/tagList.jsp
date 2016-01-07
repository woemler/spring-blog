<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<blog-tags:header />

<div class="container"><!--Body content start-->
    <div class="row">
        <!--Left Column-->
        <div class="col-sm-9 col-xs-12">    

            <h2>Tags</h2>

            <a href="${contextPath}/admin/tags/new"><button class="btn btn-info"><span class="glyphicon glyphicon-plus-sign"></span> New Tag</button></a>
            <br/><br/>
            <c:choose>
                <c:when test="${fn:length(tags) > 0}">
                    <table class="table table-striped">
                        <tr>
                            <th>Tag</th>
                            <th>Count</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${tags}" var="tag">
                            <tr>
                                <td>${tag.tagName}</td>
                                <td>${tagCounts.get(tag.tagId)}</td>
                                <td><a onclick="deleteTag('${tag.tagId}')" title="Delete tag."><button class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></button></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    No tags available.
                </c:otherwise>
            </c:choose>
                    
        </div><!--Body content end-->

        <div class="col-sm-3 col-xs-12"><!--Sidebar start-->
            <blog-tags:adminSidebar />
        </div><!--Sidebar end-->
        
    </div>
</div>

<script type="text/javascript">

    function deleteTag(id){
        if (confirm("Are you sure you want to delete this tag?")){
            $.ajax({
                type: "POST",
                url: "${contextPath}/admin/delete/tags/" + id,
            }).done(function(data){
                alert(data);
                location.reload();
            });
        }
    }

</script>

<blog-tags:footer />

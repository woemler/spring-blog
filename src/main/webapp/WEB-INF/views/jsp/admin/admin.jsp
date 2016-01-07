<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<blog-tags:header />

<div class="container"><!--Body content start-->
    <div class="row">
        <!--Left Column-->
        <div class="col-sm-9 col-xs-12">           

            <h2>Admin Tools</h2>

            <ul class="list-group">

                <!-- Blog Posts -->
                <li class="list-group-item">
                    <a href="${contextPath}/admin/blog">Blog Posts</a>
                    <div class="pull-right">
                        <span class="badge">${postCount}</span>
                        <a href="${contextPath}/admin/blog/new" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-plus"></i></a>
                    </div>
                </li>

                <!-- Tags -->
                <li class="list-group-item">
                    <a href="${contextPath}/admin/tags">Tags</a>
                    <div class="pull-right">
                        <span class="badge">${tagCount}</span>
                        <!--<a href="${contextPath}/admin/tags/new" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-plus"></i></a>-->
                    </div>
                </li>

            </ul>
                    
        </div><!--Body content end-->

        <div class="col-sm-3 col-xs-12"><!--Sidebar start-->
            <blog-tags:adminSidebar />
        </div><!--Sidebar end-->
        
    </div>
</div>

<blog-tags:footer />

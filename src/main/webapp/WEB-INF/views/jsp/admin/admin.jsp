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
            <a href="${contextPath}/admin/tags/new" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-plus"></i></a>
        </div>
    </li>
    
</ul>
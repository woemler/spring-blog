<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>

<div class="container"><!--Body content start-->
    <div class="row">
        <!--Left Column-->
        <div class="col-sm-9 col-xs-12">

            <h2>Tag: ${tag.tagName}</h2>

            <blog-tags:blogPostList blogPosts="${blogPosts}" tagMap="${tagMap}" />

        </div><!--Body content end-->

        <div class="col-sm-3 col-xs-12"><!--Sidebar start-->
            <blog-tags:blogSidebar />
        </div><!--Sidebar end-->
        
    </div>
</div>
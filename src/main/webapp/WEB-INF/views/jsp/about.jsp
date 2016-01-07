<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>

<blog-tags:header />

<script type="text/javascript">
    $("#aboutTab").addClass("active");
</script>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-xs-12">
            <h1>About</h1>
            
            <h3>This Blog</h3>
            <p>
                Site info goes here.
            </p>
            
            <h3>The Author</h3>
            <p>
                Personal bio goes here.
            </p>
            
            <h3>This Application</h3>
            <p>
                This site is powered by a custom Java Spring MVC web application, 
                built from scratch.  It uses Spring 3, Hibernate 
                4, and Bootstrap 3 to create a simple, modern, and responsive web 
                application.  This application is open-source, you can find the 
                project repository <a href="https://github.com/willOEM/spring-blog" target="_blank">
                here</a>.  Please feel free to contribute, fork, or redistribute 
                the source code as you see fit. 
            </p>
        </div>
        
        <div class="col-sm-3 col-xs-12"><!--Sidebar start-->
            <blog-tags:blogSidebar />
        </div><!--Sidebar end-->
    </div>
</div>

<blog-tags:footer />

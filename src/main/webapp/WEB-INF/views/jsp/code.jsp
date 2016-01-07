<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>

<blog-tags:header />

<script type="text/javascript">
    $("#codeTab").addClass("active");
</script>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-xs-12">
            <h1>Code</h1>
            
            <c:choose>
                <c:when test="${repos.size()>0}">
                    <!--GutHub repos-->
                    <h3>My GitHub Projects</h3>
                    <c:forEach items="${repos}" var="repo" varStatus="status">
                        <div class="well">
                            <h4>
                                <a href="${repo.url}" target="_blank">${repo.title}</a>
                                <span class="pull-right">
                                    <a class="btn btn-success" href="${repo.url}/archive/master.zip">
                                        <span class="glyphicon glyphicon-cloud-download"></span> 
                                        Download
                                    </a>
                                </span>
                            </h4>
                        <p>${repo.description}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise></c:otherwise>
            </c:choose>
        </div>
        
        <div class="col-sm-3 col-xs-12"><!--Sidebar start-->
            <blog-tags:blogSidebar />
        </div><!--Sidebar end-->
    </div>
</div>

<blog-tags:footer />

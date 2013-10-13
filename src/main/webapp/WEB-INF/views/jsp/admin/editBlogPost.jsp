<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>Add/Edit Blog Post</h2>


<form:form id="blogPostForm" cssClass="form-horizontal" method="post" modelAttribute="blogPost">
    <fieldset>
        <div class="row">
            <!--Title-->
            <div class="form-group">
                <label class="control-label col-lg-2" for="titleInput">Title</label>
                <div class="col-lg-7">
                    <form:input id="titleInput" path="title" cssClass="form-control" cssErrorClass="error" maxlength="200" value="${blogPost.title}" onkeypress="updateSlug()" onkeyup="updateSlug()" />                        
                </div>
                <form:errors path="title" cssClass="help-inline spring-form-error" element="span" />
            </div>

            <!--Slug-->
            <div class="form-group">
                <label class="control-label col-lg-2" for="slugInput">Slug</label>
                <div class="col-lg-7">
                    <form:input id="slugInput" path="slug" cssClass="form-control" cssErrorClass="error" maxlength="100" value="${blogPost.slug}" />                        
                </div>
                <form:errors path="slug" cssClass="help-inline spring-form-error" element="span" />
            </div>
            
            <!--Markup-->
            <div class="form-group">
                <label class="control-label col-lg-2" for="markupInput">Markup</label>
                <div class="col-lg-7">
                    <form:textarea id="markupInput" path="markup" cssClass="form-control" cssErrorClass="error" rows="10" value="${blogPost.markup}" />                        
                </div>
                <form:errors path="markup" cssClass="help-inline spring-form-error" element="span" />
            </div>
            
            <!--Tags-->
            <div class="form-group">
                <label class="control-label col-lg-2" for="tagInput">Tags</label>
                <div class="col-lg-7">
                    <input id="tagInput" name="tagString" type="text" class="form-control" maxlength="100" value="${tagString}" />                  
                </div>
                <form:errors path="tags" cssClass="help-inline spring-form-error" element="span" />
            </div>
            
            <!--Status-->
            <div class="form-group">
                <label class="control-label col-lg-2" for="statusSelect">Status</label>
                <div class="col-lg-7">
                    <form:select id="statusSelect" size="1" path="status" cssClass="form-control" value="${blogPost.status}">
                        <form:option value="draft" label="Draft" />
                        <form:option value="live" label="Live" />
                    </form:select>                        
                </div>
                <form:errors path="status" cssClass="help-inline spring-form-error" element="span" />
            </div>
            
            <!--Enable Comments-->
            <div class="checkbox">
                <label class="control-label col-lg-9" for="enableCommentsCheck">
                    <form:checkbox id="enableCommentsCheck" path="enableComments" value="${blogPost.enableComments}" />                    
                    Enable Comments
                </label>
                <form:errors path="enableComments" cssClass="help-inline spring-form-error" element="span" />
            </div>
            
            <br/>
            
            <!--Buttons-->
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-7">
                    <button id="submitButton" class="btn btn-default">Save</button>
                    <a id="resetButton" class="btn btn-default">Reset</a> 
                    <a id="canceltButton" class="btn btn-default" href="${contextPath}/admin/blog/cancel">Cancel</a> 
                </div>
            </div>

       </div>

        <script type="text/javascript">
            
            function updateSlug(){
                $("#slugInput").val($("#titleInput").val().replace(/[^a-zA-Z0-9 ]/g, "").replace(/\s+/g, "-").toLowerCase()); 
            }
            
            $(document).ready(function(){
                
                //Auto-generate slug
                //$("#titleInput").on("keypress", function(){updateSlug()});
                //$("#titleInput").on("keyup", function(){updateSlug()});
                
                
                //Submit button
                $('#submitButton').on("click", function(){
                    $('#blogPostForm').submit(); 
                });

                //Reset button
                $('#resetButton').on("click", function(){
                    $(':input','#blogPostForm')
                        .not(':button, :submit, :reset, :hidden')
                        .val('')
                        .removeAttr('checked')
                        .removeAttr('selected');
                });
                
                //Cancel Button
                $("#cancelButton").on("click", function(){
                   alert("Abort!"); 
                });

                $('.spring-form-error').closest("div .control-group").addClass('error');

            });
        </script>   
    </fieldset>
</form:form>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<blog-tags:header />

<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tag-it/2.0/css/jquery.tagit.css" type="text/css" />

<div class="container"><!--Body content start-->
    <div class="row">
        <!--Left Column-->
        <div class="col-sm-9 col-md-8 col-xs-12">    

            <h2>Add/Edit Blog Post</h2>

            <form:form id="blogPostForm" cssClass="form-horizontal" method="post" modelAttribute="blogPost">
                <fieldset>
                    <div class="row">
                        <!--Title-->
                        <div class="form-group">
                            <label class="control-label col-lg-2" for="titleInput">Title</label>
                            <div div class="col-lg-9">
                                <form:input id="titleInput" path="title" cssClass="form-control" cssErrorClass="error" maxlength="200" value="${blogPost.title}" onkeypress="updateSlug()" onkeyup="updateSlug()" />                        
                            </div>
                            <form:errors path="title" cssClass="help-inline spring-form-error" element="span" />
                        </div>

                        <!--Slug-->
                        <div class="form-group">
                            <label class="control-label col-lg-2" for="slugInput">Slug</label>
                            <div class="col-lg-9">
                                <form:input id="slugInput" path="slug" cssClass="form-control" cssErrorClass="error" maxlength="100" value="${blogPost.slug}" />                        
                            </div>
                            <form:errors path="slug" cssClass="help-inline spring-form-error" element="span" />
                        </div>

                        <!--Markup-->
                        <div class="form-group">
                            <label class="control-label col-lg-2" for="markupInput">Markup</label>
                            <div class="col-lg-9">
                                <form:textarea id="markupInput" path="content" cssClass="form-control" cssErrorClass="error" rows="10" value="${blogPost.content}" />
                            </div>
                            <form:errors path="content" cssClass="help-inline spring-form-error" element="span" />
                        </div>

                        <!--Tags-->
                        <div class="form-group">
                            <label class="control-label col-lg-2" for="tagInput">Tags</label>
                            <div class="col-lg-9">
                                <input id="tagInputHidden" name="tagString" type="text" class="form-control" value="${tagString}" style="display:none" />
                                <ul id="tagInput" class="tag-input-control"></ul>
                            </div>
                            <form:errors path="tags" cssClass="help-inline spring-form-error" element="span" />
                        </div>

                        <!--Status-->
                        <div class="form-group">
                            <label class="control-label col-lg-2" for="statusSelect">Status</label>
                            <div class="col-lg-9">
                                <form:select id="statusSelect" size="1" path="status" cssClass="form-control" value="${blogPost.status}">
                                    <form:option value="draft" label="Draft" />
                                    <form:option value="active" label="Active" />
                                </form:select>                        
                            </div>
                            <form:errors path="status" cssClass="help-inline spring-form-error" element="span" />
                        </div>

                        <!--Enable Comments-->
                        <label class="col-lg-offset-2 checkbox-inline" for="enableCommentsCheck">
                            <form:checkbox id="enableCommentsCheck" path="enableComments" value="${blogPost.enableComments}" />                    
                            Enable Comments
                        </label>
                        <form:errors path="enableComments" cssClass="help-inline spring-form-error" element="span" />

                        <!--Enable Pegdown-->
                        <label class="checkbox-inline" for="enablePegdownCheck">
                            <form:checkbox id="enablePegdownCheck" path="enablePegdown" value="${blogPost.enablePegdown}" />
                            <%--<input type="checkbox" id="enablePegdownCheck" name="enablePegdown" checked="checked" />                    --%>
                            Enable Pegdown
                        </label>
                        
                        <br/><br/>

                        <!--Buttons-->
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-9">
                                <button id="submitButton" class="btn btn-default">Save</button>
                                <a id="resetButton" class="btn btn-default">Reset</a> 
                                <a id="canceltButton" class="btn btn-default" href="${contextPath}/admin/blog/cancel">Cancel</a> 
                            </div>
                        </div>

                    </div>
                
                </fieldset>
            </form:form>
                
        </div><!--Body content end-->

        <div class="col-sm-3 col-md-4 col-xs-12"><!--Sidebar start-->
            <blog-tags:adminBlogEditHelp />
        </div><!--Sidebar end-->
        
    </div>
</div>

<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tag-it/2.0/js/tag-it.min.js"></script>

<script type="text/javascript">
            
    function updateSlug(){
        $("#slugInput").val($("#titleInput").val().replace(/[^a-zA-Z0-9 ]/g, "").replace(/\s+/g, "-").toLowerCase()); 
    }
    
    $(function(){
        ////Tag-It Controls start////
        $("#tagInput").tagit({
            placeholderText: "eg. Type your tags here...",
            autocomplete: {
                source: function(request, response){
                    $.ajax({
                        type: 'POST',
                        url: '${contextPath}/admin/tags/autocomplete',
                        data: "fragment=" + $("#tagInput input:last").val(), //takes the input from the last input to use for autocompletion
                        dataType: "json"
                    }).done(function(data){
                        response(data);
                    }).error(function(x, status, e){
                        response([]);
                        alert(x.responseText);
                    });
                },
                minLength: 1
            }
        });

        //Add class to tag input element to make it fit screen
        $(".tag-input-control input").width("500px");

        //Remove placeholder text for input control
        $(".tag-input-control input").on("keydown", function(){
            $(this).removeAttr("placeholder");
            $(this).width("150px");
        });
        
        //Populate the tag input with preexisting tags
        var existingTags = $("#tagInputHidden").val().split(" ");
        for (var i=0; i<existingTags.length; i++){
            $("#tagInput").tagit("createTag", existingTags[i]);
            $(".tag-input-control input").removeAttr("placeholder").width("150px");
        }
        
        ////Tag-It controls end////
    });

    $(document).ready(function(){

        //Auto-generate slug
        //$("#titleInput").on("keypress", function(){updateSlug()});
        //$("#titleInput").on("keyup", function(){updateSlug()});
        


        //Submit button
        $('#submitButton').on("click", function(){
            var assignedTags = $("#tagInput").tagit("assignedTags");
            $("#tagInputHidden").val(assignedTags);
            $(".tagit-hidden-field").each(function(){
                $(this).removeAttr("name"); //Eliminates conflict between tag field name and model field
            });
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

        $('.spring-form-error').closest("div .form-group").addClass('error');

    });
</script>  

<blog-tags:footer />

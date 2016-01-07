<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<blog-tags:header />

<div class="container"><!--Body content start-->
    <div class="row">
        <!--Left Column-->
        <div class="col-sm-9 col-xs-12">    
            
            <h2>Add/Edit Tag</h2>

            <form:form id="tagForm" cssClass="form-horizontal" method="post" modelAttribute="tag">
                <fieldset>
                    <div class="row">
                        <!--Tag Name-->
                        <div class="form-group">
                            <label class="control-label col-lg-2" for="titleInput">Tag</label>
                            <div class="col-lg-7">
                                <form:input id="tagInput" path="tagName" cssClass="form-control" cssErrorClass="error" maxlength="40" value="${tag.tagName}" />                        
                            </div>
                            <form:errors path="tagName" cssClass="help-inline spring-form-error" element="span" />
                        </div>

                        <br/>

                        <!--Buttons-->
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-7">
                                <a id="submitButton" class="btn btn-default">Save</a>
                                <a id="resetButton" class="btn btn-default">Reset</a> 
                                <a id="canceltButton" class="btn btn-default" href="${contextPath}/admin/tags/cancel">Cancel</a> 
                            </div>
                        </div>

                   </div>

                </fieldset>
            </form:form>
            
        </div><!--Body content end-->

        <div class="col-sm-3 col-xs-12"><!--Sidebar start-->
            <blog-tags:adminSidebar />
        </div><!--Sidebar end-->
        
    </div>
</div>

<script type="text/javascript">

    $(document).ready(function(){

        //Submit button
        $('#submitButton').on("click", function(){
            var tag = $("#tagInput").val();
            if (tag.match(/^[A-Za-z0-9-]+$/)){
                $('#tagForm').submit(); 
            }
            else {
                alert("Invalid tag.  Tags must contain only alphanumeric characters and hyphens.  Whitespace is not allowed.")
            }
        });

        //Reset button
        $('#resetButton').on("click", function(){
            $(':input','#tagForm')
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

<blog-tags:footer />

<%@tag description="Shows PegDown syntax help" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="well-lg alert-info">
    
    <!--PegDown-->
    <h3>PegDown Syntax</h3>
    <p>
        <ul class="list-unstyled">
            <li># This is an H1</li>
            <li>## This is an H2</li>
            <li>etc...</li>
            <li>&nbsp;</li>
            
            <li>&gt; This is a blockquote</li>
            <li>&gt;&gt; This is a nested quote</li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;This is a code block</li>
            <li>`This is a code snippet`</li>
            <li>&nbsp;</li>
            
            <li>- This is an unordered list item</li>
            <li>1. This is an ordered list item</li>
            <li>&nbsp;</li>
            
            <li>Blank lines create paragraphs</li>
            <li>----This is a horizontal rule</li>
            <li>_this has emphasis_</li>
            <li>__this is strong__</li>
            <li>\ Backslashes escape special characters</li>
            <li>&nbsp;</li>
            
            <li>[Link](http://www.url.com)</li>
            <li>[Link](http://www.url.com *Title*)</li>
            <li>[Link](/local/url/)</li>
            <li>&lt;http://www.url.com&gt;</li>
            <li>&lt;someone@email.com&gt;</li>
            <li>![Alt text](/path/to/img.jpg)</li>
            <li>![Alt text](/path/to/img.jpg *Title*)</li>
            <li>&nbsp;</li>
            
            <li>More details <a href="https://github.com/sirthias/pegdown">here</a>.</li>
            
        </ul>
    </p>
    
    <h3>Status</h3>
    <p>
        Posts with a status of "Draft" will not show up on the main page, but can be accessed by direct URL link.  Posts with a status of "Live" can be accessed by any means.
    </p>
    
    <h3>Comments</h3>
    <p>
        Check "Enable Comments" to allow commenting in posts.  Posts that do not have this option checked will not show the commenting tools in the post details, and will not show post counts in summary.
    </p>
    
    
</div>
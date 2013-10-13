<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>

<h2>Tag: ${tag.tagName}</h2>

<blog-tags:blogPostList blogPosts="${blogPosts}" tagMap="${tagMap}" />
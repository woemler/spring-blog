<%@tag description="Page header" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="serverName" value="${fn:replace(pageContext.request.serverName, '.something.com', '')}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="serverUrl" value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, '')}" />
<c:set var="adminFlag" value="${fn:contains(pageContext.request.requestURL, 'admin')}" />

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css" />
    <link rel="stylesheet" href="${contextPath}/static/css/blog-style.css" type="text/css" />

    <title><spring:eval expression="@environment.getProperty('app.blog.title')" /></title>

</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${contextPath}/" style="font-family:'Consolas';color:#cc4800"><spring:eval expression="@environment.getProperty('app.blog.title')" /></a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li id="homeTab"><a href="${contextPath}/">Home</a></li>
                <li id="codeTab"><a href="${contextPath}/code">Code</a></li>
                <li id="mediaTab"><a href="${contextPath}/media">Media</a></li>
                <li id="aboutTab"><a href="${contextPath}/about">About</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Search</button>
            </form>
            <c:choose><c:when test="${adminFlag}">
                <form class="navbar-form navbar-right" action="/logout" method="post">
                    <input type="submit" class="btn btn-danger" value="Logout" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </c:when></c:choose>
        </div><!--/.navbar-collapse -->
    </div>
</div>

<div class="jumbotron">
    <div class="container">
        <!--<div class="page-header">-->
        <c:choose>
            <c:when test="${adminFlag}">
                <div class="page-header">
                    <h1>Site Administration</h1>
                </div>
            </c:when>
            <c:otherwise>
                <div class="page-header">
                    <h1><spring:eval expression="@environment.getProperty('app.blog.title')" /></h1>
                </div>
                <p><span class="pull-right text-muted"><spring:eval expression="@environment.getProperty('app.blog.subtitle')" /></span></p>
            </c:otherwise>
        </c:choose>

        <!--</div>-->
    </div>
</div>

        
            

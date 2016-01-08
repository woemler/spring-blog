<%@ taglib prefix="blog-tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<blog-tags:header />
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" />

<div class="container">
    <div class="row">
        <div class="col-sm-2 col-md-3"></div>
        <div class="col-sm-8 col-md-6">
            <div class="well well-lg">
                <form id="loginForm" name="f" action="/login" method="POST">
                    <h2>Login</h2>
                                        
                    <div class="form-group">
                        <label for="usernameInput">Username</label>
                        <input id="usernameInput" class="form-control" name="username" type="text" />
                    </div>
                    
                    <div class="form-group">
                        <label for="passwordInput">Password</label>
                        <input id="passwordInput" class="form-control" name="password" type="password" />
                    </div>
                        
                    <button type="submit" id="submitButton" class="btn btn-primary">Login</button>
                    <a id="cancelButton" href="${contextPath}/" class="btn btn-danger">Cancel</a>
                    
                    <c:if test="${param.error != null}">
                        <br/><br/>
                        <div class="form-group">
                            <div class="alert alert-danger">
                                <h4>Login Failed:</h4>
                                Error: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                            </div>
                        </div>
                    </c:if>
                        
                    <c:if test="${param.logout != null}">
                        <br/><br/>
                        <div class="form-group">
                            <div class="alert alert-info">
                                <h4>Logout Successful</h4>
                            </div>
                        </div>
                    </c:if>
                    
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    
                </form>
        </div>
        <div class="col-sm-2 col-md-3"></div>
    </div>
</div>

<script type="text/javascript">
    
    $(document).ready(function(){
        $("#usernameInput").focus();
    });
    
</script>
    
<blog-tags:footer />

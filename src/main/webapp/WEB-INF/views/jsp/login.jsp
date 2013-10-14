<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="well well-lg">
                <form id="loginForm" name="f" action="<c:url value='j_spring_security_check' />" method="POST">
                    <h2>Login</h2>
                                        
                    <div class="form-group">
                        <label for="usernameInput">Username</label>
                        <input id="usernameInput" class="form-control" name="j_username" type="text" />
                    </div>
                    
                    <div class="form-group">
                        <label for="passwordInput">Password</label>
                        <input id="passwordInput" class="form-control" name="j_password" type="password" />
                    </div>
                        
                    <a id="submitButton" class="btn btn-primary">Login</a>
                    <a id="cancelButton" href="${contextPath}/" class="btn btn-danger">Cancel</a>
                    
                    <c:if test="${not empty error}">
                        <br/><br/>
                        <div class="form-group">
                            <div class="alert alert-danger">
                                <h4>Login Failed:</h4>
                                Error: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                            </div>
                        </div>
                    </c:if>
                        
                    <c:if test="${not empty logout}">
                        <br/><br/>
                        <div class="form-group">
                            <div class="alert alert-info">
                                <h4>Logout Successful</h4>
                            </div>
                        </div>
                    </c:if>
                    
                </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

<script type="text/javascript">
    
    $(document).ready(function(){
       
        //focus on the username input
        $("#usernameInput").focus();
        
        //Submit
        $("#submitButton").on("click", function(){
           $('#loginForm').submit();
        });
        
    });
    
</script>
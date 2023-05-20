<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 19-1-15
  Time: 下午5:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7, IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.css">
    <script language="JavaScript" src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.js"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/jquery/jquery-migrate-3.0.0.js"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.js"></script>
    <title>Title</title>
    <style>
        .login_form{
            margin-top:10%;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row login_form">
        <div class="col-lg-3 col-md-3"></div>
        <div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
            <form action="${pageContext.request.contextPath}/user/login" method="post" name="registForm">
                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <span class="h3"><B><I><U><span
                                    class="glpyhicon glyphicon-log-in">&nbsp;LOGIN</span></U></I></B></span>
                        </div>
                        <div class="panel-body">
                            <%-- this input allows user to enter username  --%>
                            <div class="row">
                                <div class="col-md-3 col-lg-3"></div>
                                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 input-group form-group">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-user"></span></span>
                                    <input type="text" name="uname" class="form-control" placeholder="Enter your username"/>
                                </div>
                                <div class="col-lg-3 col-md-3">
                                </div>
                            </div>
                            <%-- this input allows user to enter password  --%>
                            <div class="row">
                                <div class="col-md-3 col-lg-3"></div>
                                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 input-group form-group">
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-lock"></span></span>
                                    <input type="text" name="upass" class="form-control" placeholder="Enter your password"/>
                                </div>
                                <div class="col-lg-3 col-md-3">
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="row">
                                <div class="col-md-3 col-lg-3"></div>
                                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                    <div class="col-xs-6 col-s-6 col-md-6 col-lg-6">
                                        <button type="submit" class="btn btn-primary">Login</button>
                                    </div>
                                    <div class="col-xs-6 col-s-6 col-md-6 col-lg-6">
                                        <button type="reset" class="btn btn-primary">Reset</button>
                                    </div>
                                </div>
                                <div class="col-md-3 col-lg-3"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-3 col-md-3"></div>
    </div>
</div>
</div>
</body>
</html>

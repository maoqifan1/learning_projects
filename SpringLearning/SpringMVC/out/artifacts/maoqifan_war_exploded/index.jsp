<%--
  Created by IntelliJ IDEA.
  User: maoqifan
  Date: 2019-01-28
  Time: 22:01
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

</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Welcome</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
        </ul>
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/index/login"><span
                    class="glyphicon glyphicon-log-in"></span>&nbsp;LOGIN</a></li>
            <li><a href="${pageContext.request.contextPath}/index/register"><span
                    class="glyphicon glyphicon-edit"></span>&nbsp;REGISTER</a></li>

        </ul>
    </div><!-- /.navbar-collapse -->
</nav>
</body>
</html>

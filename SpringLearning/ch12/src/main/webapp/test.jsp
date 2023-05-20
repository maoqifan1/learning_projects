<%--
  Created by IntelliJ IDEA.
  User: maoqifan
  Date: 2019-02-04
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7, IE=9"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/bootstrap-3.3.7/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${ctx}/bootstrap-3.3.7/css/bootstrap-theme.css">
<script language="JavaScript" src="${ctx}/jquery/jquery-3.3.1.js"></script>
<script language="JavaScript" src="${ctx}/jquery/jquery-migrate-3.0.0.js"></script>
<script language="JavaScript" src="${ctx}/bootstrap-3.3.7/js/bootstrap.js"></script>
<title>Title</title>
<body>
视图<%out.print("视图渲染结束");%>
</body>
</html>

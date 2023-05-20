<%--
  Created by IntelliJ IDEA.
  User: maoqifan
  Date: 2019-01-31
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7, IE=9"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/icheck/skins/all.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.css">
<script language="JavaScript" src="${pageContext.request.contextPath}/jquery/jquery-3.3.1.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/jquery/jquery-migrate-3.0.0.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/icheck/icheck.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.js"></script>
<title>Title</title>
<script>
    $(document).ready(function(){
        $('input').iCheck({
            checkboxClass: 'icheckbox_minimal-red',
            radioClass: 'iradio_minimal-red',
            increaseArea: '20%' // optional
        });
    });
</script>
<body>
<div class="container-fluid">
    <form:form modelAttribute="user" method="post" action="${pageContext.request.contextPath}/user/save">
        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-2 col-xs-2"></div>
            <div class="col-lg-6 col-md-6 col-sm-10 col-xs-10">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        添加一个用户
                    </div>
                    <div class="panel-body">
                        <div class="row input-group form-group" >
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">用户名:</div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <form:input cssClass="form-control" path="userName"/>
                            </div>
                        </div>
                        <div class="row input-group form-group" >
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">爱好:</div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <form:checkboxes items="${hobbys}" path="hobby"/>
                            </div>
                        </div>
                        <div class="row input-group form-group" >
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">朋友:</div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <form:checkbox  path="friends" value="张三"/>张三
                                <form:checkbox  path="friends" value="李四"/>李四
                                <form:checkbox  path="friends" value="王五"/>王五
                                <form:checkbox  path="friends" value="赵六"/>赵六
                            </div>
                        </div>
                        <div class="row input-group form-group" >
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">职业</div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <form:select path="carrer">
                                    <option/>请选择职业
                                    <form:options items="${carrers}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="row input-group form-group" >
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">户籍:</div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <form:select path="houseRegister">
                                    </option>请选择户籍
                                    <form:options items="${houseRegisters}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="row input-group form-group" >
                            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">个人描述</div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <form:textarea path="remark" rows="5"/>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <input id="reset" type="reset" value="重置" />
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <input id="submit" type="submit" value="添加" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-2 col-xs-2"></div>
        </div>
    </form:form>
</div>
</body>
</html>

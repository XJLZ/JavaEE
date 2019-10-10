<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>客户管理后台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        .container h1 {
            text-align: center;
        }
        td{
            text-align: center;
            line-height: 35px;
        }
        th{
            text-align: center;
            line-height: 35px;
        }
    </style>
    <script type="text/javascript">
        function deleteCustomer(id) {
            if (confirm("您确定要删除么？")) {
                location.href="${pageContext.request.contextPath}/deleteCustomer?id="+id;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h1>客户管理后台 ${username}</h1>
    <c:if test = "${empty username}">
        <div class="text-right">
            <a class="btn btn-info" href="login.jsp">登录</a>
            <!-- <a class="btn btn-info" href="register.html">注册</a> -->
        </div>
    </c:if>
    <c:if test = "${not empty username }">
        <div class="text-right">
            <span>当前用户: ${username} </span>
            <a class="btn btn-danger" href="loginOut">退出登录</a>
            <!-- <a class="btn btn-info" href="register.html">注册</a> -->
        </div>
    </c:if>
    <p></p>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">客户信息</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <c:forEach var="country" items="${countrys}">
                        <c:if test="${country_id == country.countryId}">
                            <li class="active"><a href="${pageContext.request.contextPath}/index?country_id=${country.countryId}">${country.countryName}<span class="sr-only">(current)</span></a></li>
                        </c:if>
                        <c:if test="${country_id != country.countryId}">
                            <li><a href="${pageContext.request.contextPath}/index?country_id=${country.countryId}">${country.countryName}</a></li>
                        </c:if>
                    </c:forEach>

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div class="text-right">
        <a class="btn btn-success"  href="${pageContext.request.contextPath}/addCustomer">添加</a>
    </div>
    <p></p>


    <table class="table table-bordered ">
        <tr>
            <th>客户id</th>
            <th>客户姓名</th>
            <th>客户地址</th>
            <th>客户操作</th>
        </tr>
        <c:forEach var="customer" items="${customers }">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.address}</td>
                <td>
                    <%--<a href="editProductUI?pro_id=${product.p_id }">客户编辑</a>--%>
                    <%--<a href="javascript:del(${product.p_id});">客户删除</a>--%>
                        <%--<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal${customer.id}">--%>
                            <%--客户编辑--%>
                        <%--</button>--%>
                        <a class="btn btn-success" data-toggle="modal" data-target="#myModal${customer.id}">
                            <i class="fa fa-edit"></i> 修改
                        </a>
                        <a class="btn btn-danger"  href="javascript:deleteCustomer(${customer.id});" >
                            <i class="fa fa-remove"></i> 删除
                        </a>
                        <!-- 模态框（Modal） -->
                        <div class="modal fade" id="myModal${customer.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            模态框（Modal）标题
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${pageContext.request.contextPath}/updateCustomer" method="post">
                                            <!--隐藏域 提交ID-->
                                            <input type="hidden" name="id" id="id" value="${customer.id}">
                                            <div class="form-group">
                                                <label for="name">客户姓名</label>
                                                <input type="text" class="form-control" id="name" name="name" value="${customer.name}">
                                            </div>
                                            <div class="form-group">
                                                <label for="address">客户地址</label>
                                                <input type="text" class="form-control" id="address" name="address" value="${customer.address}">
                                            </div>
                                            <button type="submit" class="btn btn-success">提交</button>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-info pull-left" data-dismiss="modal">关闭</button>
                                        <button type="reset"  class="btn btn-danger pull-right">重置</button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>





















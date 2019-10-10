<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!--     导入核心标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门添加</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <!--link导入外部的css,js样式  如果导入的资源和本页面在同一个文件夹下,直接文件夹名就可  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/Ionicons/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bower_components/datatables.net-bs/css/dataTables.bootstrap.min(1).css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body>
<div class="wrapper row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <form class="form-horizontal" action="${pageContext.request.contextPath}/productAddUI" method="post">
            <div class="box-body">
                <div class="form-group">
                    <!--label的for与 input的id一致,type为为文本框 disabled不可修改-->
                    <label for="pId" class="col-sm-2 control-label">商品ID</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="pId" name="pId">
                    </div>
                </div>
                <div class="form-group">
                    <label for="pName" class="col-sm-2 control-label">商品名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="pName" name="pName" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="pPrice" class="col-sm-2 control-label">商品价格</label>
                    <div class="col-sm-10">
                        <input type="number" step="0.01" class="form-control" id="pPrice" name="pPrice" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="cId" class="col-sm-2 control-label">商品分类</label>
                    <div class="col-sm-10">
                        <select id="cId" name="pCategoryId" class="form-control">
                            <option value="cId">--请选择分类--</option>
                            <c:forEach items="${categories}" var="categories">
                                <option value="${categories.cId}" name="pCategoryId">${categories.cName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <button type="submit" class="btn btn-success">添加</button>
                <button type="reset" class="btn btn-info pull-right">重置</button>
            </div>
            <!-- /.box-footer -->
        </form>
    </div>
    <div class="col-md-3"></div>
</div>
</body>
<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/bower_components/datatables.net-bs/js/dataTables.bootstrap.min(1).js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>
<script src="${pageContext.request.contextPath}/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>

</html>

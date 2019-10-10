<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>商品查询</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 1. 导入CSS的全局样式 -->
    <link href="admin/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="admin/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="admin/js/bootstrap.min.js"></script>
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

</head>
<body>
<div class="container">
    <h1>商品查询</h1>
    <p></p>
    <p></p>
    <p></p>
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form action="${pageContext.request.contextPath}/productSearch">
            <div class="input-group">
                <input id="searchPname" type="text" class="form-control" name="pName" placeholder="Search for..."/>
                <span class="input-group-btn">
                    <input type="submit" class="btn btn-success"  value="查询"/>
                </span>
            </div><!-- /input-group -->
            </form>
        </div><!-- /.col-lg-6 -->
</div><!-- /.row -->

<p style="margin-top: 20px"></p>
    <section class="content">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <table id="example1" class="table table-bordered table-striped">
            <tr>
                <th>商品id</th>
                <th>商品名称</th>
                <th>商品价格</th>
                <th>商品操作</th>
            </tr>
            <c:forEach items="${pb.list}" var="products">
                <tr>
                    <td>${products.pId}</td>
                    <td>${products.pName}</td>
                    <td>${products.pPrice}</td>
                    <td>
                        <a class="btn btn-success" data-toggle="modal" data-target="#myModal${products.pId}">
                            <i class="fa fa-edit"></i> 修改
                        </a>
                        <a class="btn btn-danger"  href="javascript:deleteProduct(${products.pId});" >
                            <i class="fa fa-remove"></i> 删除
                        </a>
                        <!-- 模态框（Modal） -->
                        <div class="modal fade" id="myModal${products.pId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel">商品信息修改${ategories.cId}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${pageContext.request.contextPath}/productUpdate?categoryId=${categoryId}" method="post">
                                            <!--隐藏域 提交ID-->
                                            <input type="hidden" name="pId" id="pId" value="${products.pId}">
                                            <div class="form-group">
                                                <label for="pName">商品名称</label>
                                                <input type="text" class="form-control ww" id="pName" name="pName" disabled value="${products.pName}">
                                            </div>
                                            <p style="margin-top: 10px"></p>
                                            <div class="form-group">
                                                <label for="pPrice">商品价格</label>
                                                <input type="number" step="0.01" class="form-control ww" id="pPrice" name="pPrice" value="${products.pPrice}">
                                            </div>

                                            <div class="box-footer">
                                                <input type="submit" class="btn btn-success" onclick="update()" value="修改   "/>
                                                <input type="reset" class="btn btn-info pull-right" data-dismiss="modal" value="关闭"/>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <!--    分页  -->
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li ><a href="${pageContext.request.contextPath}/productSearch?pName=${pName}&categoryId=${categoryId}&currentPage=1&rows=${row}">首页</a></li>
                <c:if test="${pb.currentPage == 1}">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${pb.currentPage != 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/productSearch?pName=${pName}&categoryId=${categoryId}&currentPage=${pb.currentPage - 1}&rows=${row}" aria-label="Previous">
                            </c:if>
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach begin="${begin}" end="${end}" var="i">
                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a href="${pageContext.request.contextPath}/productSearch?pName=${pName}&categoryId=${categoryId}&currentPage=${i}&rows=${row}">${i}</a></li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li><a href="${pageContext.request.contextPath}/productSearch?pName=${pName}&categoryId=${categoryId}&currentPage=${i}&rows=${row}">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${pb.currentPage == pb.totalPage}">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${pb.currentPage != pb.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/productSearch?pName=${pName}&categoryId=${categoryId}&currentPage=${pb.currentPage + 1}&rows=${row}" aria-label="Next">
                            </c:if>
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <li ><a href="${pageContext.request.contextPath}/productSearch?pName=${pName}&categoryId=${categoryId}&currentPage=${pb.totalPage}&rows=${row}">末页</a></li>

                    <span style="font-size: 25px;margin-left: 5px;">
                              共${pb.totalCount}条记录，共${pb.totalPage}页
                            </span>
                </ul>
            </nav>
        </div>
    </div>
    </section>
</body>
<script>
    $(function () {
        $('#example1').DataTable({
            'paging'      : true,
            'lengthChange': true,
            'searching'   : true,
            'ordering'    : true,
            'info'        : true,
            'autoWidth'   : true
        })
        // Date picker
        $("#starttime").datepicker({
            autoclose: true
        })
        $("#Completetime").datepicker({
            autoclose: true
        })
    })
</script>
<script>
    function search() {
        var pName = $("#searchPname").val();
        // pName = encodeURI(encodeURI(pName));
        location.href="${pageContext.request.contextPath}/productSearch?pName="+pName;
    }
</script>
<script type="text/javascript">
    function deleteProduct(id) {
        if (confirm("您确定要删除么？")) {
            location.href="${pageContext.request.contextPath}/productDel?id="+id;
        }
    }
</script>
</html>





















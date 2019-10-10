<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加客户</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style>
        .box{
            position: relative;
            top:10%;
            width: 300px;
            height: 300px;
            margin: 0 auto;
        }
        .Centered{
            position: absolute;
            margin: 0 100px;
        }
    </style>
<body>
    <div class="container box">
        <form action="${pageContext.request.contextPath}/addCustomerUI" method="post">
            <div class="form-group">
                <label for="id">客户id</label>
                <input type="text" class="form-control" id="id" name="id" placeholder="请输入客户ID" >
            </div>
            <div class="form-group">
                <label for="name">客户姓名</label>
                <input type="text" autocomplete="off" class="form-control" id="name" name="name" placeholder="请输入客户姓名" >
            </div>
            <div class="form-group">
                <label for="address">客户地址</label>
                <input type="text" autocomplete="off" class="form-control" id="address" name="address" placeholder="请输入客户地址" >
            </div>
            <div class="form-group">
                <label for="address">客户国籍</label>
                <select  id="countryId" name="countryId" class="form-control">
                    <option value="choice">--请选择国籍--</option>
                    <c:forEach items="${countrys}" var="country">
                        <option value="${country.countryId}" name="countryId">${country.countryName}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-success Centered">提交</button>
        </form>
    </div>

</body>
</html>

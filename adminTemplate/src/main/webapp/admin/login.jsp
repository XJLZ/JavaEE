<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>管理员登录</title>

  <!-- 1. 导入CSS的全局样式 -->
  <link href="admin/css/bootstrap.min.css" rel="stylesheet">
  <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
  <script src="admin/js/jquery-2.1.0.min.js"></script>
  <!-- 3. 导入bootstrap的js文件 -->
  <script src="admin/js/bootstrap.min.js"></script>
  <script src="admin/js/md5.js"></script>
</head>
<body>
<div class="container" style="width: 400px;">
  <h3 style="text-align: center;">管理员登录</h3>
  <form action="${pageContext.request.contextPath}/login" method="post">
    <div class="form-group">
      <label for="username">用户名：</label>
      <input type="text" name="username" autocomplete="off" required class="form-control" id="username" placeholder="请输入用户名"/>
    </div>
    <div class="form-group">
      <label for="password">密码：</label>
      <input type="password" name="password" class="form-control" required id="password" placeholder="请输入密码"/>
    </div>
    <div class="form-inline">
      <label for="vcode">验证码：</label>
      <input type="text" name="checkCode" class="form-control" id="verifycode" required placeholder="请输入验证码" style="width: 120px;"/>
        <img src="${pageContext.request.contextPath}/checkCode" name="checkCode"  title="看不清点击刷新" id="vcode"/>

    </div>
    <hr/>
    <div class="form-group" style="text-align: center;">
      <input class="btn btn btn-primary" type="submit" value="登录">
    </div>
  </form>

  <!-- 出错显示的信息框 -->
  <div id="msgBox" class="alert alert-warning alert-dismissible" role="alert" style="text-align: center">
    <button type="button" class="close" data-dismiss="alert" >
      <span>&times;</span>
    </button>
    <strong id="msg">${login_error}</strong>
  </div>
</div>
<div id="qwe"></div>
</body>
  <script>
    if ($("#msg").html() == null || $("#msg").html() ==""){
        $("#msgBox").css("display","none");
    } else {
        $("#msgBox").css("display","block");
    }

      // 在表单提交之前给密码进行加密/混淆
      $("form").submit(function(){
      	var psd = $("#password").val();
      	psd = md5(psd);
      	$("#password").val(psd);


      });
  </script>
<script type="text/javascript">
    //图片点击事件
    $(function () {
        $("#vcode").click(function () {
            console.log(1);
            this.src="${pageContext.request.contextPath}/checkCode?"+new Date().getTime();
        });

        var ajax = function () {
            // Ajax请求
            $.ajax({
                url:"code",
                type:"post",
                dataType:"json",
                data:{
                    checkCode:$("#verifycode").val()
                },
                success:function (data) {
                    console.log(data);
                    if (data == 0) {
                        console.log("错误");
                        $("#verifycode").css("border","2px solid red");
                    }
                    if (data == 1) {
                        console.log("正确");
                        $("#verifycode").css("border","");
                    }
                },
                error:function (data) {
                   location.href=""
                }
            });
        }

        $("#verifycode").blur(ajax).keyup(ajax);

    });

</script>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/register.css">
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/md5.js"></script>
</head>
<body>
<!--     <span class="box">
        <a href="#"><img src="image/01.jpg" alt=""></a>
    </span> -->
<div id="register">
    <h1>用户注册</h1>
    <form action="${pageContext.request.contextPath}/register" method="post" id="registerForm" onsubmit="return check();" name="myform">
        <input class="input01" type="text" autocomplete="off" id="username" required="required" placeholder="用户名/手机/邮箱" name="username" >
        <input class="input01"  type="password" id="password" required="required" placeholder="密码" name="password">
        <input class="input01"  type="password" id="confirmPassword" required="required" placeholder="确认密码" name="confirmPassword">
        <p>
            <span><a href="login.jsp">已有账号？立即登录</a></span>
        </p>

        <button class="but" type="submit" id="#submit1">注册</button>
    </form>
</div>
</body>
<script>

    //校验表单数据
    //校验用户名
    //单词字符，长度8到20位
    function checkUsername() {
        //1.获取用户名值
        var username = $("#username").val();
        //2.定义正则
        var reg_username = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/;//6到12位数字与字母组合
        //3.判断，给出提示信息
        var flag = reg_username.test(username);
        if(flag){
            //用户名合法
            $("#username").css("border","");
        }else{
            //用户名非法,加一个红色边框
            $("#username").css("border","1px solid red");
        }
        return flag;
    }
    //校验密码
    function checkPassword() {
        //1.获取密码值
        var password = $("#password").val();
        //2.定义正则
        var reg_password = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/;//6到12位数字与字母组合
        //3.判断，给出提示信息
        var flag = reg_password.test(password);
        if(flag){
            //密码合法
            $("#password").css("border","");
        }else{
            //密码非法,加一个红色边框
            $("#password").css("border","1px solid red");
        }
        return flag;
    }
    //确认密码
    function confirmPasseord(){
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        if (password != confirmPassword || confirmPassword == "" || confirmPassword == null){
            $("#confirmPassword").css("border","1px solid red");
        }else{
            $("#confirmPassword").css("border","");
        }
    }
    // 在表单提交之前给密码进行加密/混淆
    $("#registerForm").submit(function(){
        if(checkUsername() && checkPassword()){
            //成功
            var psd = $("#password").val();
            psd = md5(psd);
            $("#password").val(psd);
            var cfpsd = $("#confirmPassword").val();
            cfpsd = md5(cfpsd);
            $("#confirmPassword").val(cfpsd);
            return true;
        }else {
            //失败
            return false;
        }
    });
    //当某一个组件失去焦点是，调用对应的校验方法
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
    $("#confirmPassword").blur(confirmPasseord);
</script>


</html>
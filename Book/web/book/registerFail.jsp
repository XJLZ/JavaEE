<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="js/jquery-3.4.1.min.js" type="text/javascript" charset="utf-8"></script>
    <title>注册失败</title>
    <style type="text/css">
        div{
            font-size: 30px;
            font-weight: bold;
            width: 600px;
            height: 200px;
            margin: 0 auto;
            text-align: center;
            line-height:100px ;
        }
        #tip{
            margin-left: 10px;
            color: red;
        }
    </style>
</head>
<body>
<div>
    ${registerMsg}<span id="tip">3s</span>
    <script>
        var second = 3;
        function tip(){
            if (second>0) {
                setTimeout(tip, 1000);
                $("#tip").html(second + "s");
                second--;
            }else {
                location="${pageContext.request.contextPath}/book/register.jsp";
            }

        }
        tip();
    </script>
</div>
<div>
    <img src="img/fail_bg.png" width="300px" >
</div>
</body>
</html>
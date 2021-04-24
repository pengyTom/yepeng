<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>添加新会员</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
</head>
<body>
<%@ include file="nav.jsp"%>

<section class="rt_wrap content mCustomScrollbar">
    <div class="rt_content">
        <div class="page_title">
            <h2 class="fl">新会员信息</h2>
        </div>
        <form class="form-horizontal" id="form1">
            <div class="form-group">
                <label  class="col-sm-2 control-label">会员名</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="username" name="username" placeholder="请输入会员名">
                </div>
            </div>

            <div class="form-group">
                <label  class="col-sm-2 control-label">会员密码</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="password" name="password" placeholder="请输入会员密码">
                </div>
            </div>

            <div class="form-group">
                <label  class="col-sm-2 control-label">电子邮箱</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="email" name="email" placeholder="请输入会员邮箱">
                </div>
            </div>

            <div class="form-group">
                <label  class="col-sm-2 control-label">联系方式</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入联系方式">
                </div>
            </div>

            <div class="form-group">
                <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
                <div class="col-sm-4">
                    <label class="radio-inline">
                        <input type="radio" name="sex" id="inlineRadio1" value="male"> 男
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="sex" id="inlineRadio2" value="female"> 女
                    </label>
                </div>
            </div>

            <div class="form-group">
                <label for="inlineRadio1" class="col-sm-2 control-label">会员角色</label>
                <div class="col-sm-4">
                    <label class="radio-inline">
                        <input type="radio" name="role" id="inlineRadio3" value=1> 管理员
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="role" id="inlineRadio4" value=10> 游客
                    </label>
                </div>
            </div>


            <div class="form-group">
                <label  class="col-sm-2 control-label">创建时间</label>
                <div class="col-sm-4">
                    <input type="date" class="form-control" id="create_time" name="create_time" value=""<%new Date();%>>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${empty user}">
                            <button type="button" class="btn btn-default" id="sub" >保存</button>
                        </c:when>
                        <c:otherwise>
                            <button type="button"  class="btn btn-default" id="update" >修改</button>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>

        </form>
    </div>
</section>
<script>

    $("#sub").click(function(){
        var username = $("#username").val();//取值
        var password = $("#password").val();
        var email = $("#email").val();
        var phone = $("#phone").val();
        var sex=$('input:radio[name="sex"]:checked').val();
        var role=$('input:radio[name="role"]:checked').val();
        var create_time=$("#create_time").val();

        if(!username){
            alert("会员名必填!");
            $("#username").focus();//获取焦点
            return false;
        }
        if(!password){
            alert("会员密码必填!");
            $("#password").focus();//获取焦点
            return false;
        }

        if(!email){
            alert("邮箱必填!");
            $("#email").focus();//获取焦点
            return false;
        }

        if(!phone){
            alert("联系方式必填!");
            $("#phone").focus();//获取焦点
            return false;
        }

        if(!sex){
            alert("性别必选!");
            $("#sex").focus();//获取焦点
            return false;
        }

        if(!role){
            alert("角色必选!");
            $("#role").focus();//获取焦点
            return false;
        }

        if(!create_time){
            alert("创建时间必选!");
            $("#role").focus();//获取焦点
            return false;
        }

        var data = $("#form1").serialize();
        console.log(data);
        var targetUrl="http://localhost:80/manager/user/saveView";
        $.ajax({
            type:'post',
            url:targetUrl,
            cache: false,
            data:data,
            dataType:'json',
            success:function(result){
                console.log(result);
                if(result.status==200){
                    alert("新增会员添加成功！");
                     window.location.href="http://localhost:80/admin/userlist";
                }else {
                    alert(result.msg);
                }

            },
            error:function(error){

                console.log(error);
            }
        })

    })
</script>
</body>
</html>

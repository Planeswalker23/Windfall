function login() {
    $.ajax({
        type: "POST",
        url: "/user/login",
        data: $('#loginForm').serialize(),// 获取form表单中的数据
        dataType: "json",// 预期服务器返回的数据类型
        success:function (data) {
            if (data.success) {
                // 登录成功转到主页，如登录管理员账号，跳转到后台管理页面
                window.location.href="/"; //在原有窗口打开
            } else {
                // 登录失败提示
                alert(data.message);
            }
        }
    });
}

// 注册函数
function regist() {
    $.ajax({
        type: "POST",
        url: "/user/register",
        data: $('#registerForm').serialize(),// 获取form表单中的数据
        dataType: "json",// 预期服务器返回的数据类型
        success:function (data) {
            if (data.success) {
                // 注册成功转到登录页面
                window.location.href="/"; //在原有窗口打开
            } else {
                // 注册失败提示
                alert(data.message);
            }
        }
    });
}

// 编辑用户信息函数
function edit() {
    $.ajax({
        type: "POST",
        url: "/user/info",
        data: $('#editForm').serialize(),// 获取form表单中的数据
        dataType: "json",// 预期服务器返回的数据类型
        success:function (data) {
            if (data.success) {
                // 注册成功转到登录页面
                window.location.href="/profile"; //在原有窗口打开
            } else {
                // 修改信息失败提示
                alert(data.message);
            }
        }
    });
}
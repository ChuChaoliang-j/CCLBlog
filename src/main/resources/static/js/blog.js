$("#reply_comment").click(function () {
    var blogId = $("#blog_id").val();
    var commentText = $("#comment_text").val();
    if (!commentText) {
        alert("输入内容不能为空！！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "blogId": blogId,
            "content": commentText,
            "type": 1
        }),
        success: function (response) {
            if (response.code = '200') {
                $("#user_comment_text").hide();
                window.location.reload();
            } else {
                alert(response.message);
            }
        },
        dataType: "json"
    });
});

$("#update_userData").click(function () {
    var id = $("#id").val();
    var name = $("#name").val();
    var birthday = $("#birthday").val();
    var region = $("#region").val();
    var intro = $("#intro").val();
    var vocation = $("#vocation").val();
    var nickname = $("#nickname").val();
    var position = $("#position").val();
    var gender = $("#gender").val();
    if (nickname.length == 10) {
        $("#maxlength").css("display", "block");
        return;
    }
    if (!nickname) {
        alert("昵称不能为空哦！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "userData",
        contentType: "application/json",
        data: JSON.stringify({
            "id": id,
            "name": name,
            "birthday": birthday,
            "region": region,
            "intro": intro,
            "vocation": vocation,
            "nickname": nickname,
            "position": position,
            "gender": gender
        }),
        success: function (response) {
            if (response = '200') {
                $("#msg").click();
                setTimeout(function () {
                    window.location.reload();
                }, 1000);
            } else {
                alert(response.message)
            }
        },
        dataType: "json"
    });
});

$(".comment_submit").click(function () {
    if (true) {
        alert("回复有问题（未提供）！！！");
    }
    //获取评论内容
    // var content = $("#comment_son_text").val();
    //获取评论人id
    var userId = $("#user_id").val();
    //获取父评论人id
    var parentId = $("#parent_Id").val();
    var content = $("#comment-text-" + parentId).val();
    //获取此博客的id
    var blogId = $("#blog_id").val();
    $.ajax({
        type: "POST",
        url: "/commentSon",
        contentType: "application/json",
        data: JSON.stringify({
            "content": content,
            "userId": userId,
            "parentId": parentId,
            "blogId": blogId
        }),
        success: function (response) {
            if (response = '200') {
                window.location.reload();
            }
        },
        dataType: "json"
    });
});

function comment_status(e) {
    var id = e.getAttribute("data-id");
    var blog_id = $("#blog_id").val;
    var comments = $("#comment-" + id);
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //关闭二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        //展开二级评论
        $.get("/comment/" + id, function (comment, status) {
            var judgeData = $.isEmptyObject(comment);
            var str = "";
            if (!judgeData) {
                for (var i = 0; i < comment.length; i++) {
                    str +=
                        "<div class='col-lg-12 col-md-12 col-sm-12 col-xs-12'>" +
                        "<div class='media-left'>" +
                        "<span style='color: #00aaaa;font-size: 20px'>" + comment[i].user.name + ":" + "</span>" +
                        "</div>" +
                        "<div class='comment_left'>" +
                        "<div>" + comment[i].content + "</div>" +
                        "<div class='menu'>" +
                        "<span class='pull-right'>" + comment[i].strCreateTime + "</span>" +
                        "</div>" +
                        "</div>" +
                        "<hr class='col-lg-12 col-md-12 col-sm-12 col-xs-12 hr_style'>" +
                        "</div>";
                    document.getElementById("son_comment").innerHTML = str;
                }
            } else {
                return;
            }
        });
        comments.addClass("in");
        e.setAttribute("data-collapse", "in");
        e.classList.add("active");
    }
}

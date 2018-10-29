var validator;
var $messageAddForm = $("#message-add-form");

$(function () {
    validateRule();

    $("#message-add .btn-save").click(function () {
        var name = $(this).attr("name");
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                    $.ajax({
                        url:"../addMessage/add",
                        type:"post",
                        data: $("#message-add-form").serialize(),
                        dataType:"json",
                        success:function (r) {
                            if (r.code === 0) {
                                closeModal();
                                refresh();
                                $MB.n_success(r.msg);
                            } else $MB.n_danger(r.msg);
                        }
                    });
            }
        }

    });

    $("#message-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#message-add-button").attr("name", "save");
    $("#message-add-modal-title").html('新增推送');
    validator.resetForm();
    $MB.closeAndRestModal("message-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $messageAddForm.validate({
        rules: {
            messageName: {
                required: true,
                maxlength: 30
            },
            messageInfo: {
                required: true,
                maxlength: 4000
            },
        },
        messages: {
            messageName: {
                required: icon + "请输入推送名称",
                maxlength: icon + "长度不能超过30个字符"
            },
            messageInfo: {
                required: icon + "请输入推送内容",
                maxlength: icon + "长度不能超过4000个字符"
            },
        }
    });
}
var validator;
var $advertisingAddForm = $("#advertising-add-form");
function dian(num){
    $('#val').val(num)
}
$(function () {
    validateRule();
    gaozhi()
    function gaozhi(){
        $MB.n_danger("请注意！！！上传文件格式后缀为小写！！！");
        $MB.n_danger("目前能上传以mp4，jpg为结尾的文件！！！");
    }
    $("#advertising-add .btn-save").click(function () {
        var start =  $("#advTimes").val();
        var end =  $("#advTimee").val();

        if(start > end){
            $MB.n_warning("请填写正确时间，结束时间不得在起始时间之前！");
        }else{
        var name = $(this).attr("name");
        validator = $("#advertising-add-form").validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                var formData = new FormData($("form")[0]);
                var adds = $('#val').val();
                var file = $('#files').val().substr(length-3);
                //1  轮播图
                //2  视频
                if(adds == 1 && file == 'jpg'){
                    $.ajax({
                        url:"../advertisingFdfs/add",
                        type:"post",
                        async:false,
                        processData:false, //过程数据
                        contentType:false, //内容类型
                        data: formData,
                        dataType:"json",
                        success:function (r) {
                            if (r.code === 0) {
                                closeModal();
                                refresh();
                                alert(r.msg)
                                $MB.n_success(r.msg);
                            } else $MB.n_danger(r.msg);
                        }
                    });
                }else if(adds == 2 && file == 'mp4'){
                    $.ajax({
                        url:"../advertisingFdfs/add",
                        type:"post",
                        async:false,
                        processData:false, //过程数据
                        contentType:false, //内容类型
                        data: formData,
                        dataType:"json",
                        success:function (r) {
                            if (r.code === 0) {
                                closeModal();
                                refresh();
                                alert(r.msg)
                                $MB.n_success(r.msg);
                            } else $MB.n_danger(r.msg);
                        }
                    });
                }else {
                    $MB.n_danger("请选择相应的广告位，文件格式！！！");
                }

            }
        }
        }
    });

    $("#advertising-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#advertising-add-button").attr("name", "save");
    $("#advertising-add-modal-title").html('新增广告');
    validator.resetForm();
    $MB.closeAndRestModal("advertising-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $advertisingAddForm.validate({
        rules: {
            advName: {
                required: true,
                maxlength: 10
            },
            advUSerName: {
                required: true,
                maxlength: 10
            },
            advUserPhone: {
                required: true,
                maxlength: 10
            },
            advTimes: {
                required: true,
                maxlength: 10
            },
            advTimee: {
                required: true,
                maxlength: 10
            },
            advPhoto: {
                required: true,
                maxlength: 1000
            },
        },
        messages: {
            advName: {
                required: icon + "请输入广告名称",
                maxlength: icon + "长度不能超过10个字符"
            },
            compName: {
                required: icon + "请输入公司名称",
                maxlength: icon + "长度不能超过10个字符"
            },
            advUserName: {
                required: icon + "请输入负责人姓名",
                maxlength: icon + "长度不能超过10个字符"
            },
            advUserPhone: {
                required: icon + "请输入负责人手机号",
                maxlength: icon + "长度不能超过11个字符"
            },
            advTimes: {
                required: icon + "请选择开始时间",
                maxlength: icon + "请选择合理时间"
            },
            advTimee: {
                required: icon + "请选择结束时间",
                maxlength: icon + "请选择合理时间"
            },
            advPhoto: {
                required: icon + "请选择广告图片",
                maxlength: icon + "图片过大"
            },
        }
    });
}
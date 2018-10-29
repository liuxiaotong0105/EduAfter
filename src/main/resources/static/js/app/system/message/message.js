$(function() {
    var $messageTableForm = $(".message-table-form");
    var settings = {
        url: ctx + "message/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                messageName: $messageTableForm.find("input[name='messageName']").val().trim(),
                messageInfo: $messageTableForm.find("input[name='messageInfo']").val().trim(),
            };
        },
        columns: [{
            checkbox: true
            },
            {
                field: 'messageId',
                title: '推送编号',
                width: 100
            }, {
                field: 'messageName',
                title: '推送名称',
                width: 100
            }, {
                field: 'messageInfo',
                title: '推送内容',
                width: 100,
                formatter: function (value) {
                    if(value.length>=30){
                        return "<span title='" + value + "'>" + value.substring(0,30)+"..." + "</span>";
                    }else{
                        return "<span title='" + value + "'>" + value + "</span>";
                    }
                }
            }, {
                field: 'messageTime',
                title: '创建时间',
                width: 100
            }, {
                field: 'messageMethods',
                title: '推送操作',
                width: 100,
                formatter(value,rows,index){
                        return "<a href='javascript:sendSMS(\""+rows.messageInfo+"\")' >下发信息</a>";
                }
            }
        ]
    };

    $MB.initTable('messageTable', settings);
});

function search() {
    $MB.refreshTable('messageTable');
}

function refresh() {
    $(".message-table-form")[0].reset();
    search();
}

function deleteMessage() {
    var selected = $("#messageTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的推送！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].messageId;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的推送？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'message/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function sendSMS(messageInfo) {
    $.ajax({
        url: '../message/sendSMS',
        type: 'post',
        data: {messageInfo:messageInfo},
        dataType: 'json',
        success:function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
            } else $MB.n_danger(r.msg);
        }
    })
}

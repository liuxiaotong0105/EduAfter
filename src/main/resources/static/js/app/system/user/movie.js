$(function () {
    var $userTableForm = $(".user-table-form");
    var settings = {
        url:  "user/movie",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
               movieName: $userTableForm.find("input[name='movieName']").val().trim(),
                movieType: $userTableForm.find("select[name='movieType']").val(),
                movieStatus: $userTableForm.find("select[name='movieStatus']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'movieId',
            visible: false
        }, {
            field: 'movieUrl',
            title: '视频预览',
            formatter: function (value, row, index) {
                return '<video controls width="200px" height="120px">' +
                    '    <source src="'+value+'" type="video/mp4"/>' +
                    '</video>'
            }
        }, {
            field: 'movieName',
            title: '视频名称'
        }, {
            field: 'moviePicther',
            title: '视频图片',
            formatter: function (value, row, index) {
                return '<img src="'+value+'" width="200">' ;

            }
        }, {
            field: 'movieClass',
            title: '视频介绍'
        },{
            field: 'movieType',
            title: '视频介绍'
        },{
            field: 'movieInfo',
            title: '视频介绍'
        }, {
            field: 'movieStatus',
            title: '视频状态',
            formatter: function (value, row, index) {
                if (value === 0) return '<a href="javascript:update('+row.movieId+')" >审核通过</a><br><br><br><a href="javascript:updateNo(\''+row.movieId+'\')" >审核拒绝</a>';
                else if (value === 1) return '通过';
                else if (value === 2) return '不通过';
                else return '其他';
            }
        }

        ]
    };

    $MB.initTable('movieTable', settings);
});
function update(x) {
    $.ajax({
        url:'user/updateStatus',
        type:'post',
        data:{movieId:x},
        success:function(data){
            refresh()
        }
    })
}
function updateNo(x) {
    $.ajax({
        url:'user/updateStatusNo',
        type:'post',
        data:{movieId:x},
        success:function(data){
            refresh()
        }
    })
}
function refresh() {

    $MB.refreshTable('movieTable');
}


function search() {
    $MB.refreshTable('movieTable');
}

function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('movieTable');
}
/*
function deleteUsers() {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的用户！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].userId;
        if (i !== (selected_length - 1)) ids += ",";
        if (userName === selected[i].username) contain = true;
    }
    if (contain) {
        $MB.n_warning('勾选用户中包含当前登录用户，无法删除！');
        return;
    }

    $MB.confirm({
        text: "确定删除选中用户？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'user/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportUserExcel() {
    $.post(ctx + "user/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "user/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}*/

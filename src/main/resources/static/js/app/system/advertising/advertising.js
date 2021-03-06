$(function() {
    var $advertisingTableForm = $(".advertising-table-form");
    var settings = {
        url: ctx + "advertising/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                advName: $advertisingTableForm.find("input[name='advName']").val().trim(),
                compName: $advertisingTableForm.find("input[name='compName']").val().trim(),
            };
        },
        columns: [{
                checkbox: true
            },
            {
                field: 'advId',
                title: '广告编号',
            }, {
                field: 'advName',
                title: '广告名称'
            }, {
                field: 'compName',
                title: '公司名称'
            }, {
                field: 'advUserName',
                title: '负责人'
            }, {
                field: 'advUserPhone',
                title: '负责人电话'
            // }, {
            //     field: 'advStatus',
            //     title: '广告状态',
            //     formatter(value,rows,index){
            //         var d = new Date()
            //         var datetime=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
            //         if(rows.advTimes < datetime && datetime < rows.advTimee ){
            //             return "投放中";
            //         }
            //         else{
            //             return "已失效";
            //         }
            //     }
            }, {
                field: 'advPhoto',
                title: '广告展示',
                width: '300',
                formatter(value,rows,index){
                    var str = rows.advUrl.split(".");
                    if(str[4] == "mp4"){
                        return "<video controls width='300px'> <source src='"+rows.advUrl+"' type='video/mp4'/></video>"
                    }
                    else if(str[4] == "jpg" ){
                        return "<img  src='"+rows.advUrl+"' width='300'>"
                    }
                    else {
                        return "文件损坏"
                    }
                }
            }, {
                field: 'advTimes',
                title: '开始时间'
            }, {
                field: 'advTimee',
                title: '结束时间'
            }, {
                field: 'advAdd',
                title: '广告位置',
                formatter(value,rows,index){
                    if(rows.advAdd == 1 ){
                        return "轮播图广告";
                    }
                    if(rows.advAdd == 2 ){
                        return "视频广告";
                    }
                    else{
                        return "文件损坏";
                    }
                }
            }
        ]
    };

    $MB.initTable('advertisingTable', settings);
});

function search() {
    $MB.refreshTable('advertisingTable');
}

function refresh() {
    $(".advertising-table-form")[0].reset();
    search();
}

function deletedvertising() {
    var selected = $("#advertisingTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的广告！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].dictId;
        if (i !== (selected_length - 1)) ids += ",";
    }
    $MB.confirm({
        text: "确定删除选中的广告？",
        confirmButtonText: "确定删除"
    }, function() {
        $.post(ctx + 'advertising/delete', { "ids": ids }, function(r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportAdvertisingExcel(){
	$.post(ctx+"advertising/excel",$(".advertising-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}

function exportAdvertisingCsv(){
	$.post(ctx+"advertising/csv",$(".advertising-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});

function open(urls) {
    alert(urls)
    $.ajax({
        url: '../advertising/openExplorer',
        type : 'post',
        data : {urls : urls},
        success:function () {
        }
    })
}	
}
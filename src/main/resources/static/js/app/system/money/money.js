$(function() {

    var $moneyTableForm = $(".money-table-form");
    var settings = {
        url: ctx + "money/list",
        pageSize: 10,
        queryParams: function(params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
            };
        },
        columns: [
            {
                field: 'moneyId',
                title: '购买编号',
            }, {
                field: 'userName',
                title: '用户名称'
            }, {
                field: 'videoName',
                title: '视频名称'
            }, {
                field: 'videoPrice',
                title: '视频价格',
            }
        ]
    };

    $MB.initTable('moneyTable', settings);
    sums();
});

function search() {
    $MB.refreshTable('moneyTable');
}

function refresh() {
    $(".money-table-form")[0].reset();
    search();
}

function sums() {
    $.ajax({
        url:'../money/sums',
        dataType:"json",
        success:function (data) {
            $('#sum').text(data.sums)
        }
    })
}


function exportMoneyExcel(){
	$.post(ctx+"money/excel",$(".money-table-form").serialize(),function(r){
		if (r.code === 0) {
			window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}
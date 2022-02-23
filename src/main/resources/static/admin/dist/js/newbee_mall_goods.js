function customSelectboxRenderer2(){
// ajax
// call back 
// for build string
// console.log(data);
// return
   var outerlist;
   $.ajax({
            type: 'POST',//方法类型
            url: '/goods/showMoreRevies',
            contentType: 'application/json',
            data: JSON.stringify(10700),
			async: false,
            success: function (result) {
			//サーバーが成功の場合ここ呼ばれる
                if (result.resultCode == 200) {
					
					var list = result.data;
					
					outerlist = list;
                } else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
		//エラーの場合、以下呼ばれる
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
	
	return outerlist;


}
 function customSelectboxRenderer(){
	var data = [
		{"camId":'',"text":"请选择"},
		{"camId":'c001',"text":"30%"},
		{"camId":'c002',"text":"500"},
	]
	var rs = "<select name='cars' id='cars'>";
	
	for(var i = 0; i< data.length; i++){
		rs = rs + "<option value='"+ data[i].camId + "'>"+data[i].text+"</option>";
	}
	rs = rs + "</select>";
	return rs;

}
$(function () {
	//customSelectboxRenderer().then(()=>console.log(ls));
	
	//var list2 = customSelectboxRenderer2();
	
    $("#jqGrid").jqGrid({
        url: '/admin/goods/list?keyword=hi',
        datatype: "json",
        colModel: [
            {label: '商品编号', name: 'goodsId', index: 'goodsId', width: 60, key: true},
            {label: '商品名', name: 'goodsName', index: 'goodsName', width: 120},
            {label: '商品简介', name: 'goodsIntro', index: 'goodsIntro', width: 120},
            {label: '商品图片', name: 'goodsCoverImg', index: 'goodsCoverImg', width: 120, formatter: coverImageFormatter},
            //{label: '商品库存', name: 'stockNum', index: 'stockNum', width: 60},
			{
			name: 'MyCol', index: 'MyCol', formatter: customSelectboxRenderer
			},
            {label: '商品售价', name: 'sellingPrice', index: 'sellingPrice', width: 60},
            {
                label: '上架状态',
                name: 'goodsSellStatus',
                index: 'goodsSellStatus',
                width: 80,
                formatter: goodsSellStatusFormatter
            },
            {label: '创建时间', name: 'createTime', index: 'createTime', width: 60}
        ],
        height: 760,
        rowNum: 20,
        rowList: [20, 50, 80],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
		loadComplete:function(){
			debugger;
			//console.log($("#jqGrid").jqGrid('getRowData'));
			var objectArr = [
				{"goodsId":10906,"camId":"c001"},
				{"goodsId":10905,"camId":"c002"},
				{"goodsId":10903,"camId":"c001"},
				{"goodsId":10895,"camId":"c002"},
				{"goodsId":10894,"camId":"c001"},
				{"goodsId":10893,"camId":""}
			];
			for(var i = 0 ;i < objectArr.length ; i++){
				if (objectArr[i].camId){
					$("#"+objectArr[i].goodsId).find("select").val(objectArr[i].camId);
				}
			}
		},
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    function goodsSellStatusFormatter(cellvalue) {
        //商品上架状态 0-上架 1-下架
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 80%;\">销售中</button>";
        }
        if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 80%;\">已下架</button>";
        }
    }

    function coverImageFormatter(cellvalue) {
        return "<img src='" + cellvalue + "' height=\"80\" width=\"80\" alt='商品主图'/>";
    }

});

/**
 * jqGrid重新加载
 */
function reload() {
    initFlatPickr();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

/**
 * 添加商品
 */
function addGoods() {
    window.location.href = "/admin/goods/edit";
}

/**
 * 修改商品
 */
function editGoods() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    window.location.href = "/admin/goods/edit/" + id;
}

/**
 * 上架
 */
function putUpGoods() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要执行上架操作吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "PUT",
                    url: "/admin/goods/status/0",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("上架成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    )
    ;
}

/**
 * 下架
 */
function putDownGoods() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要执行下架操作吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "PUT",
                    url: "/admin/goods/status/1",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("下架成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    )
    ;
}
$("#zv-cqa-select-sort").change(function() {
    var page = $("#currentPageNo").text();
	console.log("selected value ",$('#zv-cqa-select-sort :selected').text());
    var url = '/goods/qaSort';
	data = {
        "page": page,
    };
	debugger;
	console.log("data",data);
        $.ajax({
            type: 'POST',//方法类型
            url: url,
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
			//サーバーが成功の場合ここ呼ばれる
                if (result.resultCode == 200) {
                    /*swal("保存成功", {
                        icon: "success",
                    });*/
					//获取隐藏元素
					var el;
					if(result.data.list.length > 0){
						$("#ZVCQuestionsArea").find(".zv-cqa").remove();
						
					}
					for(let i = 0; i < result.data.list.length; i++) {
					 el = $(".hiddenQaDiv").clone().removeClass("hiddenQaDiv");
					el.find(".zv-cqa-q-text").html(result.data.list[i].question);
					
					$("#detailFooter").before(el);
					//el.appendTo("#ZVCQuestionsArea");
					}
					
					
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
});

/*$('#zv-cqa-select-sort').on('change', function() {
  alert( this.value );
});*/
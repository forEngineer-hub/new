$(function() {
  // disable previous page
	debugger;
	$(".previousPage").css("pointer-events", "none").css("color","grey");
	
});


$("#zv-cqa-select-sort").change(function() {
	paging(2);
});

$( ".nextPage" ).click(function() {
	paging(0);
	$(".previousPage").css("pointer-events", "auto").css("color","#009e96");
});

$( ".previousPage" ).click(function() {
	paging(1);
});

//レビューをもっと見るクリックイベント
$( "#showMoreReviewsBtn" ).click(function() {
	var goodsId = getGoodsId();
	
	$.ajax({
            type: 'POST',//方法类型
            url: '/goods/showMoreRevies',
            contentType: 'application/json',
            data: JSON.stringify(goodsId),
            success: function (result) {
			//サーバーが成功の場合ここ呼ばれる
                if (result.resultCode == 200) {
					debugger;
					var list = result.data;
					/*if("1" === 1){
						//if failed ope
						swal("string type 1 === int i", {
                        icon: "error",
                    	});
					}
					if("1" == 1){
						//if failed ope
						swal("string type 1 == int i", {
                        icon: "error",
                    	});
					}*/
					if(list === undefined){
						//if failed ope
						swal("error", {
                        icon: "error",
                    	});
					}
					if(list != undefined && list.length != 0){
						for( i =0; i< list.length; i++){
							var el = $(".hiddenList").clone().removeClass("hiddenList");
							el.find(".g-clip").html(list[i].id);
							$(".hiddenList").before(el);
						}	
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

$("#ZVPostQuestionButton").click(function(){
	var question = $("#ZVQuestionTextarea").val();
	//get url
	var goodsId = getGoodsId();
	data = {
        "question": question,
		"goodsId": goodsId
    };
	debugger;
	$.ajax({
            type: 'POST',//方法类型
            url: '/goods/insertQa',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
			//サーバーが成功の場合ここ呼ばれる
                if (result.resultCode == 200) {
					debugger;
                    swal("質問ご登録ありがとうございました", {
                        icon: "success",
                    });
					
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
})
//isNextPage 0是下一页的处理 1上一页
//2的话 是排序
function paging(num) {
	debugger;
  //alert("hello world");
	var page = $("#currentPageNo").text();
	var pageNo = 0;
	console.log("current page: ",page);
    var url = '/goods/qaSort';
	//next page
	if(num == 0){
		pageNo = parseInt(page) + 1;	
	}else if (num == 1){
		pageNo = parseInt(page) - 1;	
	}else{
		pageNo = 1;
	}
	
	data = {
        "page": pageNo,
    };
	console.log("data",data);
        $.ajax({
            type: 'POST',//方法类型
            url: url,
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
			//サーバーが成功の場合ここ呼ばれる
                if (result.resultCode == 200) {
					debugger;
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
	}

function getGoodsId(){
	var path = window.location.pathname;
	// split with /
	var ar = path.split("/");
	// get array length
	var len = ar.length;
	// get goodsId
	var goodsId = ar[len-1];
	
	return goodsId;
}
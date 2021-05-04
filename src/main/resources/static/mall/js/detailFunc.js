$(function() {
  	// disable previous page
	$(".previousPage").css("pointer-events", "none").css("color","grey");
	//閉じるボタンを非表示させる
	$("#closeBtn").hide();
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

$( "#closeBtn" ).click(function() {
	$("#p-reviewMore").hide();
	$("#closeBtn" ).hide();
	$("#showMoreReviewsBtn").show();	
});

//レビューをもっと見るクリックイベント
$( "#showMoreReviewsBtn" ).click(function() {
	debugger;
	var goodsId = getGoodsId();
	
	$.ajax({
            type: 'POST',//方法类型
            url: '/goods/showMoreRevies',
            contentType: 'application/json',
            data: JSON.stringify(goodsId),
            success: function (result) {
			//サーバーが成功の場合ここ呼ばれる
                if (result.resultCode == 200) {
					
					var list = result.data;
					
					$("#p-reviewMore").show();
					
					if(list === undefined){
						//if failed ope
						swal("error", {
                        icon: "error",
                    	});
					}
					if(list != undefined && list.length != 0){
						for( i =0; i< list.length; i++){
							var el = $(".hiddenList").clone().removeClass("hiddenList");
							//el.find(".g-clip").html(list[i].id);
							el.find(".hidSpForRevId").html(list[i].id);
							el.find(".helpNumSpan").on( "click", helpNumClickFunc);
							
							$(".hiddenList").before(el);
						}	
					}
					
					//レビューをもっと見るの非表示
					$("#showMoreReviewsBtn").hide();
					//閉じるボタンを表示させる
					$("#closeBtn").show();
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

function helpNumClickFunc() {
	
  	var reviewId = $( this ).parent().find(".hidSpForRevId").text();
	var data = {
		"reviewId" : reviewId
	}
	var _this = $( this );
	$.ajax({
            type: 'POST',//方法类型
            url: "/goods/helpNum",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
			//サーバーが成功の場合ここ呼ばれる
                if (result.resultCode == 200) {
					debugger;
                    /*swal("成功", {
                        icon: "success",
                    });*/
				console.log(data);
				_this.text("参考になった（"+ result.data +"人）");	
				
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


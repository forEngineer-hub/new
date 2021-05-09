//image index flag
var currentImageIndex = 1;

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
					debugger;
					if(list != undefined && list.length != 0){
						for( i =0; i< list.length; i++){
							var el = $(".hiddenList").clone().removeClass("hiddenList");
							//el.find(".g-clip").html(list[i].id);
							el.find(".hidSpForRevId").html(list[i].id);
							el.find(".helpNumSpan").on( "click", helpNumClickFunc);
							
							var img = "<img src='/goods-img/star.jpg'>";
							var star = list[i].star;
							for(var j=0; j<star; j++){
								el.find(".g-clip").append(img)
							}
							//
							//el.find(".helpNumSpan").text("参考になった（" + list[i].num +"人)" )
							// java get 
							// java set
							// javascript the same method. text()=> get text("xxx")=> set
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

// image click event
function clickImage(src){
	$(".swiper-container").find("img").attr('src',src);
	
	var length = 3;
	for(var i =2; i<= length+1; i++){
		var imageSrc = $( ".slgrow div:nth-child("+ i +")" ).find("img").attr('src'); 
		if(imageSrc == src){
			currentImageIndex = i-1;
		}
	}
}

// num is 1 or -1
function plusSlides(num){
	var nextIndex = currentImageIndex + num;
	if(nextIndex == 0){
		nextIndex = 3;
	}
	if(nextIndex == 4){
		nextIndex = 1;
	}
	console.log(nextIndex);
	var nextIndexInDiv = nextIndex + 1
	var src = $( ".slgrow div:nth-child("+ nextIndexInDiv +")" ).find("img").attr('src'); 
	console.log(src);
	$(".swiper-container ").find("img").attr('src',src);
	currentImageIndex = nextIndex;
}

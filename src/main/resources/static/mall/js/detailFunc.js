//image index flag
var currentImageIndex = 1;
var butText ;

$.fn.stars = function() { 
  return this.each(function() {
    // Get the value
    var val = parseFloat($(this).html()); 
    // Make sure that the value is in 0 - 5 range, multiply to get width
    var size = Math.max(0, (Math.min(5, val))) * 18.4; 
    // Create stars holder
    var $span = $('<span> </span>').width(size); 
    // Replace the numerical value with stars
    $(this).empty().append($span);
  });
}
$(function() {
  	// disable previous page
	$(".previousPage").css("pointer-events", "none").css("color","grey");
	//閉じるボタンを非表示させる
	$("#closeBtn").hide();
	var imgArr = document.getElementsByTagName("img");
	imgArr = [...imgArr];
	$('#testInfoModal').modal('show');
	$('.results-content span.stars').stars();
	
	new AjaxUpload('#uploadGoodsCoverImg', {
        action: '/admin/upload/file',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的文件！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r != null && r.resultCode == 200) {
                $("#goodsCoverImg").attr("src", r.data);
                $("#goodsCoverImg").attr("style", "width: 128px;height: 128px;display:block;");
                return false;
            } else {
                alert("error");
            }
        }
    });
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
	///
});

//レビューをもっと見るクリックイベント
$( "#showMoreReviewsBtn" ).click(function() {
	if ($("#showMoreReviewsBtn").text() === "閉じる"){
		$("#p-reviewMore").hide();
		$("#showMoreReviewsBtn").text(butText);
		return;
	};	
	
	$("#p-reviewMore").find(".g-reviewList_item").not('.hiddenList').remove();
	
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
					
					butText = $("#showMoreReviewsBtn").text();
					//レビューをもっと見るの非表示
					$("#showMoreReviewsBtn").text("閉じる");
					//閉じるボタンを表示させる
					//$("#closeBtn").show();
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
  //alert("hello world");
	var page = $("#currentPageNo").text();
	var pageNo = 0;
	console.log("current page: ",page);
    var url = '/goods/qaSort';
	//next page
	var sortBy ='';
	if(num == 0){
		pageNo = parseInt(page) + 1;	
	}else if (num == 1){
		pageNo = parseInt(page) - 1;	
	}else{
		pageNo = $("#currentPageNo").text(); // sort
		sortBy = $("#zv-cqa-select-sort").val();
	}
	
	data = {
        "page": pageNo,
		"sortBy": sortBy
		// order by question_date
		// question_date is table column name
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
					var list = result.data.list
					debugger;
					$(".ZVCQuestionsArea").find(".zv-cqa").remove();
					for(var el of $(".zv-cqa")){
						if(!el.className.includes("hiddenQaDiv")){
							el.remove();
						}
						// jquery <=> javascript 
						//  el[0] = javascript
						// $(javascript)
					}
					for(var i = 0; i< list.length; i++){
						var cloneEl = $(".hiddenQaDiv").clone();
						cloneEl.find(".zv-cqa-q-text")
						.text(list[i].question);
						
						cloneEl.find(".zv-cqa-a-text").text(list[i].answer)
						cloneEl.removeClass("hiddenQaDiv");
						cloneEl.find(".hiddenQaId").val(i); // set qa id
						cloneEl.find(".zv-helpful-yes")[0].setAttribute('onclick', 'qaHelpNum('+i+')');
						$("#detailFooter").before(cloneEl);
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

function qaHelpNum(e,qaId2){
	console.log(qaId2);
	var data = {studentId:1,studentName:'mac'};
	 $.ajax({
            type: 'POST',//方法类型
            url: '/students',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
			//サーバーが成功の場合ここ呼ばれる
                if (result.resultCode == 200) {
					swal(result.message, {
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
	
	/*var length = 3;
	for(var i =2; i<= length+1; i++){
		var imageSrc = $( ".slgrow div:nth-child("+ i +")" ).find("img").attr('src'); 
		if(imageSrc == src){
			currentImageIndex = i-1;
		}
	}*/
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

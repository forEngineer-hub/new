var MouseOnSearchResultUl;
var index = 0;
$(function () {
    $('#keyword').keypress(function (e) {
        var key = e.which; //e.which是按键的值
        if (key == 13) {
            var q = $(this).val();
            if (q && q != '') {
                window.location.href = '/search?keyword=' + q;
            }
        }
    });
	
});

function slide(leftOrRight){
	console.log(leftOrRight);
	var eles = document.getElementsByClassName("swiper-slide");
	var length = eles.length;
	if(leftOrRight === 'left'){
		index --;
		if (index === -1){
			index = length -1;
		}
	}else{
		index ++;
		if ( index >= length){
			index = 0;
		}
	}
	console.log('index is ' + index);
	
	// 0 = > 1  -500* 1
	// 1 => 2  -500*2 
}

function search() {
	debugger;
    var q = $('#keyword').val();
    if (q && q != '') {
        window.location.href = '/search?keyword=' + q;
    }
}

//ajax与后台通信，查找查询履歴
$( "#keyword" ).focus(function(e) {
	
	var keyword = $("#keyword").val();
	// when input is filled , trigger keyup event
	//トリガー
	if(keyword !=""){
		$( "#keyword" ).trigger("keyup");
	}
	// url:restful api
	$.ajax({
            type: 'POST',//方法类型
            url: '/searchHistory/getSearchHistory',
            contentType: 'application/json',
            //data: JSON.stringify(keyword),
            success: function (result) {
			//サーバーが成功の場合ここ呼ばれる
                if (result.resultCode == 200) {
					
					showResult(result);
					
                } else {
                    swal(result.message, {
                        icon: "error",
                    });
                };
            },
			//エラーの場合、以下呼ばれる
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
});
//delete elements when focus out
$( "#keyword").focusout(function() {
	
	//return means jump out of this function. end.
	if(MouseOnSearchResultUl){
		return;
	}
    clearResultList();
	//hide #searchResultUl
	$("#searchResultUl").hide();
	
})

function clearResultList(){
    //clear #searchResultUl's elements
	//foreach is javascript's method
	//$("#searchResultUl").children() is jquery
	//toArray() convert $("#searchResultUl").children() to javascript array
	$("#searchResultUl").children().toArray().forEach(function(value, index, array){
		// check if include class name which is dumyLi
		// value is dom html element
		var incFlag = $(value).attr('class').includes("dumyLi");
		// delete elements besides dumyLi
		if(!incFlag){
			$(value).remove();
		}
		
	})
}

function showResult(result){
	
	var list = result.data;
	//href="/goods/detail/10700"
	var _href = "/goods/detail/";
	for(var i = 0; i< list.length; i++){
		var el = $(".dumyLi").clone().removeClass("dumyLi");
		var link = el.find("a");
		link.text(list[i].goodsName);
		link.attr("href", _href + list[i].goodsId);
		$(".dumyLi").before(el);
	}
	$("#searchResultUl").show();
	appendToSearchBar($("#searchResultUl"));
}

function showResultForLikeSearch(result){
	
	var list = result.data.list;
	//href="/goods/detail/10700"
	var _href = "/goods/detail/";
	for(var i = 0; i< list.length; i++){
		var el = $(".dumyLi").clone().removeClass("dumyLi");
		var link = el.find("a");
		link.text(list[i].goodsName);
		link.attr("href", _href + list[i].goodsId);
		$(".dumyLi").before(el);
	}
	$("#searchResultUl").show();
	appendToSearchBar($("#searchResultUl"));
}

function appendToSearchBar(el){
	
	var searchBar = $("#keyword"); //jquery object

	//var searchBar = document.getElementById("keyword");// dom
	var rect = searchBar[0].getBoundingClientRect(); //convert jquery object to dom by searchBar[0]
	console.log(rect.top, rect.right, rect.bottom, rect.left);
	var sbHeight = searchBar.height();
	//el.height(rect.top + sbHeight)
	//el.left(rect.left);
	el.css({top: rect.top + sbHeight, left: rect.left, position:'absolute'});
}

$( "#searchResultUl" ).mousemove(function() {

	MouseOnSearchResultUl = true;
	
});

$( "#searchResultUl" ).mouseleave(function(){
	
	MouseOnSearchResultUl = false;
});

$("#keyword").keyup(function(){
	
	var keyword = $("#keyword").val();
	
	$.ajax({
        type:"get",                // method = "POST"
        url:"/goods/search?goodsName="+keyword,        // POST送信先のURL
        //data: keyword,  // JSONデータ本体
        //contentType: 'application/json', // リクエストの Content-Type
        dataType: "json",           // レスポンスをJSONとしてパースする
        success: function(json_data) {   // 200 OK時
			clearResultList();
           	showResultForLikeSearch(json_data);
		//1 取去第一行数据 1-1直接去页面取数据 
		//2 提取keyword  如果入力"ip" 
		// "apple iphone 11"  => 截取结果keyword : iphone
		// insert ajax
        },
        error: function() {         // HTTPエラー時
            alert("Server Error. Pleasy try again later.");
        }
    });

});
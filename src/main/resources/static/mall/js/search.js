var MouseOnSearchResultUl;

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

function search() {

    var q = $('#keyword').val();
    if (q && q != '') {
        window.location.href = '/search?keyword=' + q;
    }
}

//ajax与后台通信，查找查询履歴
$( "#keyword" ).focus(function() {
	//var keyword = $("#keyword").text();
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
	debugger;
	//return means jump out of this function. end.
	if(MouseOnSearchResultUl){
		return;
	}
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
	//hide #searchResultUl
	$("#searchResultUl").hide();
	
})

//ajax あいまい検索
$( "#keyword" ).keyup(function() {
   console.log("Handler for .keyup() called." );
});

function showResult(result){
	debugger;
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

function appendToSearchBar(el){
	debugger;
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
	debugger;
});

$( "#searchResultUl" ).mouseleave(function(){
	debugger;
	MouseOnSearchResultUl = false;
});
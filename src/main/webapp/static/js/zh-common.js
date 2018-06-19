$(document).ready(function(){
	$.ajaxSetup({
		complete: function (XMLHttpRequest,textStatus) {
        	var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");//通过XMLHttpRequest取得响应头,sessionstatus 
		 	 if(sessionstatus == "timeout"){
		 		var sessionoutUrl = XMLHttpRequest.getResponseHeader("sessionoutUrl");
 				 window.location.href = sessionoutUrl;
			}
        }
	});
	
	
	//layui控件初始化;
	layui.use('layer', function(){
        var layer = layui.layer;
    }); 
	//日历控件初始化;
	//WdatePicker({eCont:'calendar',skin:'twoer-1',onpicked:function(dp){alert('你选择的日期是:'+dp.cal.getDateStr())}});
	WdatePicker({eCont:'calendar',skin:'twoer-1'});
	//改变布局高度;
	var _winH = $(window).height();
	$('.main-sidebar').css('height',_winH-50);
	$('.content').css('min-height',_winH-124);
	$(window).resize(function(){
		var _winH = $(window).height();
		$('.main-sidebar').css('height',_winH-50);
		$('.content').css('min-height',_winH-124);
	});
});

//全选反选
function selectAll(_this){
	if($(_this).prop("checked")){
		$(".js_select").prop("checked",true);
		$(".js_selectAll").prop("checked",true);
	}else{
		$(".js_select").prop("checked",false);
		$(".js_selectAll").prop("checked",false);
	}
};
//子复选框的事件
function setselectAll(){
	//当没有选中某个子复选框时，SelectAll取消选中
	if(!$(".js_select").checked){
		$(".js_selectAll").prop("checked",false);
	}
	var _checkboxNum=$(".js_select").length;
	var _selectNum=$(".js_select:checked").length;
	if(_checkboxNum==_selectNum){
		$(".js_selectAll").prop("checked",true);
	}
};

//清空搜索模块,还原为默认值;
function empty(el){
	var _parent = $(el).parent('li').siblings();
	_parent.find('input[type="text"]').val('');
	_parent.find('select option:first').prop('selected','selected');
}

//只允许输入数字
function checkkeyNum(_this) {
	$(_this).keyup(function(){ //keyup事件处理 
		$(this).val($(this).val().replace(/\D|^0/g,''));
	}).on("paste",function(){ //CTR+V事件处理 
		$(this).val($(this).val().replace(/\D|^0/g,''));
	}).css("ime-mode", "disabled"); //CSS设置输入法不可用
};

// //禁止右键复制+粘贴;
// function click(e) { 
// 	if (document.all) { 
// 		if (event.button==1||event.button==2||event.button==3) { 
// 			oncontextmenu='return false'; 
// 		}
// 	}
// 	if (document.layers) { 
// 		if (e.which == 3) { 
// 			oncontextmenu='return false'; 
// 		} 
// 	} 
// }
// if (document.layers) { 
// 	document.captureEvents(Event.MOUSEDOWN); 
// } 
// document.onmousedown=click; 
// document.oncontextmenu = new Function("return false;") 
// var trxdyel=true 
// var hotkey=17 /* hotkey即为热键的键值,是ASII码,这里99代表c键 */ 
// if (document.layers) {
// 	document.captureEvents(Event.KEYDOWN) 
// }
// function gogo(e) { 
// 	if (document.layers) { 
// 		if (e.which==hotkey && trxdyel) { 
// 			alert('操作错误.或许是您按错键了!'); 
// 		} 
// 	}else if (document.all) { 
// 		if (event.keyCode==hotkey&&trxdyel){ 
// 			alert('操作错误.或许是您按错键了!'); 
// 		}
// 	}
// }
// document.onkeydown=gogo;


/*
 * 换肤功能模块
 * @time 2016-10-12
 * @creater 前端工程师——张工 <354631282@qq.com>
 * */
/* skin模块 -S */
//读取cookie;
function readCookie(name) {
  var nameEQ = name + "=";
  var ca = document.cookie.split(';');
  for(var i=0;i < ca.length;i++) {
    var c = ca[i];
    while (c.charAt(0)==' ') c = c.substring(1,c.length);
    if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
  }
  return false;
}
//创建cookie;
function createCookie(name,value,days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    else expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
}
/* skin模块 -E */
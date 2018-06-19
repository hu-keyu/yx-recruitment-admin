//初始化省下拉框

function renderSchool(_index) {
		var provinceArray = "";
		var provicneSelectStr = "";
		for (var i = 0, len = province.length; i < len; i++) {
			provinceArray = province[i];
			provicneSelectStr = provicneSelectStr + "<option value='" + provinceArray[0] + "'>" + provinceArray[1] + "</option>"
		}
		$("div[class='proSelect" + _index + "'] select").html(provicneSelectStr);
	
		//初始化学校列表
		var selectPro = $("div[class='proSelect" + _index + "'] select").val();
		var schoolUlStr = "";
		var schoolListStr = new String(proSchool[selectPro]);
		var schoolListArray = schoolListStr.split(",");
		var tempSchoolName = "";
		for (var i = 0, len = schoolListArray.length; i < len; i++) {
			tempSchoolName = schoolListArray[i];
			//console.log(tempSchoolName.length);
			if (tempSchoolName.length > 13) {
				schoolUlStr = schoolUlStr + "<li class='DoubleWidthLi'>" + schoolListArray[i] + "</li>"
			} else {
				schoolUlStr = schoolUlStr + "<li>" + schoolListArray[i] + "</li>"
			}
		}
		$("div[class='schoolList" + _index + "'] ul").html(schoolUlStr);
		//省切换事件
		$("div[class='proSelect" + _index + "'] select").change(function () {
			if ("99" != $(this).val()) {
				$("div[class='proSelect" + _index + "'] span").show();
				$("div[class='proSelect" + _index + "'] input").hide();
				schoolUlStr = "";
				schoolListStr = new String(proSchool[$(this).val()]);
				schoolListArray = schoolListStr.split(",");
				for (var i = 0, len = schoolListArray.length; i < len; i++) {
					tempSchoolName = schoolListArray[i];
					if (tempSchoolName.length > 13) {
						schoolUlStr = schoolUlStr + "<li class='DoubleWidthLi'>" + schoolListArray[i] + "</li>"
					} else {
						schoolUlStr = schoolUlStr + "<li>" + schoolListArray[i] + "</li>"
					}
				}
				$("div[class='schoolList" + _index + "'] ul").html(schoolUlStr);
			} else {
				$("div[class='schoolList" + _index + "'] ul").html("");
				$("div[class='proSelect" + _index + "'] span").hide();
				$("div[class='proSelect" + _index + "'] input").show();
			}
		});
		//学校列表mouseover事件
		/*$("div[class='schoolList" + _index + "'] ul li").on("mouseover", function () {
			$(this).css("background-color", "#72B9D7");
		});*/
		$(document).on("mouseover","div[class='schoolList" + _index + "'] li",function(){
			$(this).css("background-color", "#2553a0");
		});
		//学校列表mouseout事件
		$(document).on("mouseout","div[class='schoolList" + _index + "'] li",function(){
			$(this).css("background-color", "");
		});
		/*$("div[class='schoolList" + _index + "'] ul li").on("mouseout", function () {
			$(this).css("background-color", "");
		});*/
		//学校列表点击事件
		$(document).on("click","div[class='schoolList" + _index + "'] li",function(){
			$(this).parent().parent().parent().siblings(".school").val($(this).html());
			$("div[class='provinceSchool" + _index + "']").hide();
		});
		/*$("div[class='schoolList" + _index + "'] ul li").on("click", function () {
			//alert(_index);
			$(this).parent().parent().parent().siblings(".school").val($(this).html());
			$("div[class='provinceSchool" + _index + "']").hide();
		});*/
		//按钮点击事件
		
		/*$("div[class='button'] button").on("click", function () {
			var flag = $(this).attr("flag");
			if ("0" == flag) {
				$("div[class='provinceSchool" + _index + "']").hide();
			} else if ("1" == flag) {
				var selectPro = $("div[class='proSelect" + _index + "'] select").val();
				if ("99" == selectPro) {
					$(this).parent().parent().siblings(".school").val($("div[class='proSelect" + _index + "'] input").val());
				}
				$("div[class='provinceSchool" + _index + "']").hide();
			}
			return false;
		});*/
		
		$("#proSchool"+_index).on("click","div[class='button'] button",function(){
			var flag = $(this).attr("flag");
				if ("0" == flag) {
					$("div[class='provinceSchool" + _index + "']").hide();
				} else if ("1" == flag) {
					var selectPro = $("div[class='proSelect" + _index + "'] select").val();
					if ("99" == selectPro) {
						if($("div[class='proSelect" + _index + "'] input").val().length<=100){
							$(this).parent().parent().siblings(".school").val($("div[class='proSelect" + _index + "'] input").val());
						}else{
							alert("最大输入100个字符,请重新输入");
							return;
						}
					}
					$("div[class='provinceSchool" + _index + "']").hide();
				}
				return false;
		});
									
}
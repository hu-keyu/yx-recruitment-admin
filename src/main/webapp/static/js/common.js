// 全局初始化函数
$(function() {

	// 绑定日期选择框插件
	/*$(".input-datetimepicker-year-day").datetimepicker({
		format: 'yyyy-mm-dd',
		autoclose: true,
		startView: 4,
		minView: 2,
		language: 'zh-CN'
	});*/

});

var getLength = function (str) {
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
}

/** 
 * js截取字符串，中英文都能用 
 * @param str：需要截取的字符串 
 * @param len: 需要截取的长度 
 */
function substr(str, len) {
    var str_length = 0;
    var str_len = 0;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4  
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；  
    if (str_length < len) {
        return str;
    }
}

Date.prototype.format = function (format) {
    var args = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var i in args) {
        var n = args[i];
        if (new RegExp("(" + i + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
    }
    return format;
};

// 克隆对象
function clone(target) {
	var buf;
	if (target instanceof Array) {
		buf = []; //创建一个空的数组 
		var i = target.length;
		while (i--) {
			buf[i] = clone(target[i]);
		}
		return buf;
	} else if (target instanceof Object) {
		buf = {}; //创建一个空对象 
		for (var k in target) { //为这个对象添加新的属性 
			buf[k] = clone(target[k]);
		}
		return buf;
	} else {
		return target;
	}
}

//重新绑定上传控件
function rebuild_datetimepicker(areaid) {
	var $objs = $(areaid + " .input-datetimepicker-year-day");
	$objs.datetimepicker('remove');
	$objs.datetimepicker({
		format: 'yyyy-mm-dd',
		autoclose: true,
		startView: 4,
		minView: 2,
		language: 'zh-CN'
	});
}

//上传图片调用方法
function upload_image(uploadapi,imginput, img_box) {

	if (window.upload_editor == undefined) {
		window.upload_editor = KindEditor.editor({
			allowImageUpload: true,
			allowFileManager: false,
			uploadJson: uploadapi,
			filePostName: 'upfile'
		});
	}

	upload_editor.loadPlugin('image', function() {
		upload_editor.plugin.imageDialog({
			showRemote: false,
			imageUrl: $(imginput).val(),
			clickFn: function(url, title, width, height, border, align) {
				$(imginput).val(url);
				upload_editor.hideDialog();
				if (img_box != undefined) {
					$(img_box).find('img').attr('src', basepath + url);
					$(img_box).removeClass('hidden');
				}

			}
		});
	});
}

//上传文件调用方法
function upload_file(uploadapi,input_val_id, callback_func) {

	if (window.upload_editor == undefined) {
		window.upload_editor = KindEditor.editor({
			allowImageUpload: true,
			allowFileManager: false,
			uploadJson: uploadapi,
			filePostName: 'upfile'
		});
	}

	upload_editor.loadPlugin('insertfile', function() {
		upload_editor.plugin.fileDialog({
			fileUrl: $('#input_val_id').val(),
			clickFn: function(url, title) {
				$('#input_val_id').val(url);
				upload_editor.hideDialog();
				if (callback_func != undefined && typeof callback_func === 'function') {
					callback_func(url,title);
				}
			}
		});
	});
}
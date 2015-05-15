/*Flash文件上传组件JS辅助(MYFILEUPLOAD)*/
function MFU(){};

/*上传组件用户配置参数*/
MFU.uploadParameterArray=[];

/*初始化Flash上传组件*/ 
MFU.iniFlashPlayer=function(iniDataJson){ 
	//默认参数
	var setting={
			id:"FileUpload",
			width:128,
			height:128,
			fileSize:"1"
	};
	//自定义参数赋值
	for (items in iniDataJson){
		setting[items]=iniDataJson[items];
	}
	//参数保存
	MFU.uploadParameterArray[setting.id]=setting;
	//实例化上传组件
	var iniFlashStr="";
	iniFlashStr=iniFlashStr+' <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"';
	iniFlashStr=iniFlashStr+' id="'+setting.id+'" width="'+setting.width+'" height="'+setting.height+'"';
	iniFlashStr=iniFlashStr+' codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">';
	iniFlashStr=iniFlashStr+' <param name="movie" value="'+setting.swfPath+'" />';
	iniFlashStr=iniFlashStr+' <param name="quality" value="high" />';
	iniFlashStr=iniFlashStr+' <param name="allowScriptAccess" value="sameDomain" />';
	iniFlashStr=iniFlashStr+' <param NAME=wmode value=transparent>';
	iniFlashStr=iniFlashStr+' <param name="FlashVars" value="id='+setting.id+'">';
	iniFlashStr=iniFlashStr+' <embed src="'+setting.swfPath+'" FlashVars="id='+setting.id+'"';
	iniFlashStr=iniFlashStr+' quality="high" width="'+setting.width+'" wmode="transparent" height="'+setting.height+'" name="'+setting.id+'"';
	iniFlashStr=iniFlashStr+' play="true" quality="high" allowScriptAccess="sameDomain"';
	iniFlashStr=iniFlashStr+' type="application/x-shockwave-flash" pluginspage="http://www.adobe.com/go/getflashplayer"></embed>';
	iniFlashStr=iniFlashStr+' </object>';
	document.getElementById(setting["container"]).innerHTML=iniFlashStr;
}

/*上传文件有效性判断*/
MFU.checkAndUpload=function(id,serverJsonData){
	var serverJson= eval("(" + serverJsonData + ")");
	//获取参数
	var parameters=MFU.uploadParameterArray[id];
	//优先调用扩展的方法
	if(parameters.checkAndUploadExtend){
		parameters.checkAndUploadExtend(id,serverJson);	
	}
}

/*文件上传进度控制*/
MFU.uploadOnProgress=function(id,serverJsonData){
	var serverJson= eval("(" + serverJsonData + ")");
	//获取参数
	var parameters=MFU.uploadParameterArray[id];
	//调用扩展的方法
	if(parameters.uploadOnProgressExtend){
		parameters.uploadOnProgressExtend(id,serverJson);	
	}
}

/*文件上传成功后，将服务器返回值返回到页面*/
MFU.uploadComplete=function(id,serverData){
	//获取参数
	var parameters=MFU.uploadParameterArray[id];
	if(parameters.uploadCompleteExtend){
		parameters.uploadCompleteExtend(id,serverData);	
	}
}

/*Flash初始化参数 FOR Flash组件调用*/
MFU.iniFlashSetting=function(id){
	return MFU.uploadParameterArray[id];
}

/*Flash初始化参数 FOR YOU*/
MFU.getSettingById=function(id){
	return MFU.uploadParameterArray[id];	
}

/*取消*/
MFU.cancelUpload=function(id,obj){
	var flashObj=MFU.getFlash(id);
	flashObj.cancelByHand();
	MFU.deleteFile(obj);
}

/*删除*/
MFU.deleteFile=function(obj){
	obj.parentNode.removeChild(obj);
}

/*获取flash对象*/
MFU.getFlash=function(id) {
    if (navigator.appName.indexOf("Microsoft") != -1) {
        return document.getElementById(id);
    } else {
        return document.embeds[id];
    }
}

/*json对象转字符串形式*/
MFU.jsonToStr=function(o) {
	var arr = [];
	var fmt = function(s) {
		if (typeof s == 'object' && s != null) return json2str(s);
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}
	for (var i in o) arr.push("'" + i + "':" + fmt(o[i]));
	return '{' + arr.join(',') + '}';
}

/*截取字符串*/
MFU.subString=function(str,start,end){
	return str.substring(start,end);
}

/*上传文件计算大小*/
MFU.reComputeSize=function(size){
	if(typeof size=="undefined"){
		return "";
	}
	if(size<1024){
		return size+"B";
	}else if(size<1024*1024){
		return Math.round(size/1024)+"KB";
	}else{
		return Math.round(size/1024/1024)+"M";
	}
}
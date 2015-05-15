<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String jsessionid=session.getId();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Flash文件上传组件DEMO示例</title>
	<script type="text/javascript" src="jquery.js"></script>
	<script type="text/javascript" src="fileUpload.js"></script>
	<style>
		body table td{
			text-align:center;
			font-size:14px;
		}
		.intro td{
			text-align:left;
		}
		.leftTip{
			background:#CCCCCC;
			width:200px;
		}
		.mfuFileItem{
			font-size:12px;
			text-align:left;
			padding:5px;
			padding-left:25px;
			border-bottom:1px solid #BECCDB;
			background:#F5F5F5 url('<%=path%>/includes/FileUpload/attach.png') no-repeat 5px 6px;
		}
		.mfuFileName{
			
		}
		.mfuFileSize{
			color:#9A9A9A;
		}
		.mfuProgress{
			margin-left:5px;
		}
		.mfuCancel,.mfuDelete{
			color:blue;
			margin-left:5px;
			cursor: pointer;
		}
	</style>
	<script>
		//上传组件参数初始化
		var setting={
					id:"FileUpload",
					container:"uploadContainer",
					showContainer:"uploadShowContainer",
					swfPath:"<%=path%>/includes/FileUpload/MyFileUpload.swf",
					serverPath:"<%=path%>/servlet/FileUploadServlet;jsessionid=<%=jsessionid%>",
					imgPath:"<%=path%>/includes/FileUpload/fileUpload.gif",
					width:128,
					height:128,
				 	fileSize:"50",
					noFileType:"*.exe",
				 	checkAndUploadExtend:checkAndUploadExtend,
				 	uploadOnProgressExtend:uploadOnProgressExtend,
				 	uploadCompleteExtend:uploadCompleteExtend
					};
		$(function(){
			//示例化上传组件
			MFU.iniFlashPlayer(setting);
		});
		
		/*上传文件有效性判断*/
		function checkAndUploadExtend(id,serverJson){
			//获取上传组件参数
			var parameters=MFU.getSettingById(id);
			if(serverJson.checkType=="all"){
				//各种校验通过,如果你想在文件正式上传前做点什么的话，你就在这做吧！
				/////////////////////////////////////////////////////////
				$("#fileInfo").html(serverJson.fileName);
				$("#checkInfo").html(MFU.jsonToStr(serverJson));
				/////////////////////////////////////////////////////////
				var fileName=serverJson.fileName;
				var fileSize=MFU.reComputeSize(serverJson.fileSize);
				var newFileStr="";
				newFileStr=newFileStr+"<div  class='mfuFileItem'>";
				newFileStr=newFileStr+"<input name="+id+" type='hidden'/>";
				newFileStr=newFileStr+"<span class='mfuFileName'>"+fileName+"</span>";
				newFileStr=newFileStr+"<span class='mfuFileSize'>("+fileSize+")</span>";
				newFileStr=newFileStr+"<span class='mfuProgress'>0%</span>";
				newFileStr=newFileStr+"<span class='mfuCancel' onclick='MFU.cancelUpload(\""+id+"\",this.parentNode);'>取消</span>";
				newFileStr=newFileStr+"<span class='mfuDelete' onclick='MFU.deleteFile(this.parentNode);' style='display:none;'>删除</span>";
				newFileStr=newFileStr+"</div>";
				$("#"+parameters.showContainer).append(newFileStr);
			}else if(serverJson.checkType=="suffix"){
				//文件后缀名有误
				alert("错误的文件类型，允许上传文件后缀名包括："+parameters.fileType);
			}else if(serverJson.checkType=="noSuffix"){
				//不允许的后缀名出错
				alert("错误的文件类型，不允许上传文件后缀名包括："+parameters.noFileType);
			}else if(serverJson.checkType=="size"){
				//文件大小有误
				alert("文件大小有误，单文件最大上传大小为："+parameters.fileSize+"M");
			}
		}
		
		/*文件上传进度控制*/
		function uploadOnProgressExtend(id,serverJson){
			//获取上传组件参数
			var parameters=MFU.getSettingById(id);
			var percentFormat=Math.floor(serverJson.percent*100)+"%";
			/////////////////////////////////////////////////////////
			$("#progressInfo").html(MFU.jsonToStr(serverJson));
			$("#percentInfo").html(percentFormat);
			/////////////////////////////////////////////////////////
			$(".mfuFileItem:last .mfuProgress").html(percentFormat);
		}
		
		/*文件上传成功后，将服务器返回值返回到页面*/
		function uploadCompleteExtend(id,serverData){
			//获取上传组件参数
			var serverJson= eval("(" + serverData + ")");
			/////////////////////////////////////////////////////////
			$("#returnInfo").html(serverData);
			/////////////////////////////////////////////////////////
			$(".mfuFileItem:last input").val(serverJson[0].UUID);
			$(".mfuFileItem:last .mfuProgress").hide();
			$(".mfuFileItem:last .mfuCancel").hide();
			$(".mfuFileItem:last .mfuDelete").show();
		}
		
	</script>
  </head>
  
  <body>
    <table cellpadding="0" cellspacing="0" border="1" width="80%" style="margin:auto;">
    	<tr><td colspan="2" style="font-weight:bold;text-align:center;height:60px;background:#EFEDDF;">Flash文件上传组件DEMO示例</td></tr>
        <tr>
        	<td width="200" height="40">组件介绍</td>
            <td style="padding:10px;text-align:left;">
            	我是上传组件MFU，首先我要告诉你我很懒，我不管后台文件你怎么存，用什么技术，也不管上传后你页面怎么展现，我只管给你校验文件类型，给你验证文件大小，给你监控上传进度，然后帮你把后台处理的返回值传递到页面。不过话又说回来，虽然懒，但我非常灵活，灵活在哪？你自己琢磨吧^_^。
            </td>
        </tr>
    	<tr>
    		<td>上传示例</td>
    		<td style="padding:5px;">
    			<table cellpadding="0" cellspacing="0" border="1" width="100%">
    				<tr>
    					<td width="200" id="uploadContainer" rowspan="2"></td>
    					<td>文件显示区域</td>
    				</tr>
    				<tr>
    					<td id="uploadShowContainer" style="padding:5px;" valign="top">
    					</td>
    				</tr>
    			</table>
			</td>
    	</tr>
        <tr>
        	<td width="200" height="40">文件信息</td>
            <td id="fileInfo">暂无
            	
            </td>
        </tr>
    	<tr>
    		<td width="200"  height="40">上传进度</td>
    		<td id="percentInfo">暂无
            	
    		</td>
    	</tr>
    	<tr>
    		<td width="200"  height="40">有效性判断Json返回值,对应(checkAndUploadExtend)</td>
    		<td id="checkInfo">暂无
            	
    		</td>
    	</tr>
    	<tr>
    		<td width="200"  height="40">进度控制Json返回值,对应(uploadOnProgressExtend)</td>
    		<td id="progressInfo">暂无
            	
    		</td>
    	</tr>     
    	<tr>
    		<td width="200"  height="40">上传成功后后台Json返回值，对应(uploadCompleteExtend)</td>
    		<td id="returnInfo">暂无
            	
    		</td>
    	</tr>     
        <tr>
    		<td width="200"  height="40">使用代码示例</td>
    		<td style="text-align:left;">
            	<img src="howTo.png"/>
    		</td>
    	</tr>   
        <tr>
    		<td width="200"  height="40">setting上传参数说明</td>
    		<td style="text-align:left;">
            	<table class="intro">
                	<tr><td class="leftTip">id</td><td>flash组件唯一编号，用于js和flash间通信</td></tr>
                	<tr><td class="leftTip">container</td><td>flash组件显示区的DOM元素ID</td></tr>
                	<tr><td class="leftTip">showContainer</td><td>flash组件上传文件信息显示区的DOM元素ID</td></tr>
                    <tr><td class="leftTip">swfPath</td><td>swf源文件路径</td></tr>
                    <tr><td class="leftTip">serverPath</td><td>文件上传后台处理URL，组件本身不关注怎么实现，只是将文件传递到后台，将后台返回值传递到页面，其中jsessionid参数是必要的，如果不传，在后台会session丢失（注意格式：http://example.com/app;jsessionid=blahblah?q=blah，注意jsessionid要拼在路径之后，参数之前）</td></tr>
                    <tr><td class="leftTip">imgPath</td><td>上传组件背景图，可以相对swf也可以相对于整个应用路径，如不指定，默认为透明flash，透明的...</td></tr>
                    <tr><td class="leftTip">width</td><td>flash组件宽度,有背景图就跟着背景图的宽度走吧</td></tr>
                    <tr><td class="leftTip">height</td><td>flash组件高度,有背景图就跟着背景图的高度走吧</td></tr>
                    <tr><td class="leftTip">fileSize</td><td>允许上传的文件大小，单位M</td></tr>
                    <tr><td class="leftTip">fileType</td><td>允许上传的文件格式</td></tr>
                    <tr><td class="leftTip">noFileType</td><td>不允许上传的文件格式，此外还有一参数，允许上传的文件格式，fileType,二者选其一使用，两个都写也行</td></tr>
                    <tr><td class="leftTip">checkAndUploadExtend</td><td>文件大小和类型校验，不扩展则不会在前台显示任何校验出错信息，建议扩展</td></tr>
                    <tr><td class="leftTip">uploadOnProgressExtend</td><td>上传进度控制，如果不想要什么进度条，上传百分比的话，这个可以不要</td></tr>
                    <tr><td class="leftTip">uploadCompleteExtend</td><td>上传完成，文件上传完了，你应该有事情做吧，在这做</td></tr>
                </table>
    		</td>
    	</tr>
    	<tr>
    		<td width="200"  height="40">上传方法说明</td>
    		<td style="text-align:left;">
            	<table class="intro">
                	<tr><td class="leftTip">MFU.getSettingById(id)</td><td>参数为当前flash组件唯一编号，根据id获取setting参数</td></tr>
                	<tr><td class="leftTip">MFU.cancelUpload(id,obj)</td><td>取消上传，obj需要删除的dom元素</td></tr>
                	<tr><td class="leftTip">MFU.deleteFile(obj)</td><td>删除页面的某文件区域，obj需要删除的dom元素</td></tr>
                	<tr><td class="leftTip">MFU.subString(str,start,end)</td><td>截取字符串</td></tr>
                	<tr><td class="leftTip">MFU.reComputeSize(size)</td><td>文件大小格式转换,根据实际大小转换成合适的B,KB,M</td></tr>
                </table>
    		</td>
    	</tr>       
    </table>
    <div style="text-align:center;font-size:12px;color:#999999;margin-top:5px;">liuyong#chinasofti.com|liuyongcic#163.com</div>
  </body>
</html>
/*---------------------------------------------------------------------
 * SimpleMessage弹出窗口组件v1.0
 * By LiuYong 2010/04/30
 * 使用方法：见demo
 */

function $(id){
	return document.getElementById(id);	
}
//simpleMessage下images目录相对于应用上下文根的路径
//例如"tocas/Components/SimpleMessage/"
var imgPath="";
var tocas = function(){};
var simpleMessageObj=null;
//消息框类
tocas.SimpleMessage = function(){
	//--------------主要配置参数开始----------------//
	this.width=300;
	this.height=110;
	this.soundOn=true;
	this.title="消息提示框";
	this.autoClose=false;
	//--------------主要配置参数结束----------------//
	this.windowObj=window;
	this.sound=['sound.wav','sound.wav','sound.wav'];
	this.type=[1,2,3];
	this.pic=['images/mail.gif','images/mail.gif','images/mail.gif'];
	this.messageArray=new Array();
	this.MessageBox=null;
	this.currentIndex=0;
	//控制隔多少秒去执行【关闭消息框定时器】定时器
	this.TimerToClose=null;
	//关闭消息框定时器
	this.closeTimer=null;
	this.showSpeed=1;
	this.closeSpeed=4;
	simpleMessageObj=this;
	
};

//消息体
tocas.Message = function(){
	this.title='';
	this.content='';
	this.link='';
	this.type='';
	
}

tocas.Message.prototype.pop=function(){
	tocas.SimpleMessage.getInstance().popMessage(this);
}

//弹出框调用入口
tocas.SimpleMessage.prototype.popMessage = function(messageObject){
	var sm=this;
	sm.messageArray.reverse();
	sm.messageArray.push(messageObject);
	sm.messageArray.reverse();
	this.currentIndex=0;
	//有新消息时，先将【关闭定时器】清除，待会新窗口弹出后重新设定
	clearTimeout(sm.TimerToClose);
	clearTimeout(sm.closeTimer);
	sm.buildMessageBox(messageObject);
}

//构建消息框
tocas.SimpleMessage.prototype.buildMessageBox = function(messageObject){
	var sm=this;
	var documentObj=sm.windowObj.document;
	//初次弹出，创建新的Div
	if(sm.MessageBox==null){
		sm.MessageBox=documentObj.createElement("div");
		sm.MessageBox.id = "ly_messageBox"
		sm.MessageBox.style.width = sm.width+"px";
		sm.MessageBox.style.height ="0px";
		//弹出框头部
		var topDiv=documentObj.createElement("div");
		topDiv.id="ly_messageBox_top";
		topDiv.style.width=sm.width+"px";
		topDiv.style.height="26px";
		topDiv.innerHTML="<div id='ly_messageBox_top_left' style='width:50%;height:100%;'><div id='ly_messageBox_top_title'></div></div><div id='ly_messageBox_top_right' style='width:50%;height:100%;'><img src='"+imgPath+"images/close.gif' title='关闭' onclick='tocas.SimpleMessage.sm_close();'/></div>";
		//弹出框中间部分
		var middleDiv=documentObj.createElement("div");
		middleDiv.id="ly_messageBox_middle";
		middleDiv.style.width=sm.width+"px";
		middleDiv.style.height=(sm.height-26-2)+"px";
		middleDiv.innerHTML="<div id='ly_messageBox_middle_left' style='width:2px;height:100%;'></div><div id='ly_messageBox_middle_middle' style='width:"+(sm.width-4)+"px;height:100%;'><div id='ly_messageBox_middle_middle_l' style='width:60px;'><img style='width:38px;height:30px;' id='ly_messageBox_middle_img'/></div><div id='ly_messageBox_middle_middle_r' style='width:"+(sm.width-4-60)+"px;'><div id='ly_messageBox_middle_middle_r1'></div><div id='ly_messageBox_middle_middle_r2'></div><div id='ly_messageBox_middle_middle_r3'><img src='"+imgPath+"images/left.gif' title='上一条' onclick='tocas.SimpleMessage.previous();'/>&nbsp;<span id='ly_messageBox_middle_page'></span>&nbsp;<img src='"+imgPath+"images/right.gif' title='下一条' onclick='tocas.SimpleMessage.next();'/></div></div></div><div id='ly_messageBox_middle_right' style='width:2px;height:100%;'></div>";
		//弹出框下面
		var bottomDiv=documentObj.createElement("div");
		bottomDiv.id="ly_messageBox_bottom";
		bottomDiv.style.width=sm.width+"px";
		bottomDiv.style.height="2px";
		bottomDiv.innerHTML="<div id='ly_messageBox_bottom_left' style='width:50%;height:100%;'></div><div id='ly_messageBox_bottom_right' style='width:50%;height:100%;'></div>";
		
		sm.MessageBox.appendChild(topDiv);
		sm.MessageBox.appendChild(middleDiv);
		sm.MessageBox.appendChild(bottomDiv);
		
		documentObj.body.appendChild(sm.MessageBox);
		
		//添加声音元素
		if(sm.soundOn){
			var messageSound=documentObj.createElement("bgsound");
			messageSound.id="messageSound";
			documentObj.body.appendChild(messageSound);
		}
		
		
	}
	//初始化数据
	sm.iniData(messageObject);
	//显示窗口
	sm.display(messageObject);
}

//初始化数据
tocas.SimpleMessage.prototype.iniData=function(messageObject){
	
	var sm=this;
	//数据初始化
	$("ly_messageBox_top_title").innerHTML=sm.title;
	$("ly_messageBox_middle_img").src=imgPath+sm.pic[messageObject.type==''?1:messageObject.type];
	$("ly_messageBox_middle_middle_r1").innerHTML=messageObject.link==""?messageObject.title:"<a href='"+messageObject.link+"' target='_blank'>"+messageObject.title+"</a>";
	$("ly_messageBox_middle_middle_r2").innerHTML=messageObject.link==""?messageObject.content:"<a href='"+messageObject.link+"' target='_blank'>"+messageObject.content+"</a>";
	$("ly_messageBox_middle_page").innerHTML=(sm.currentIndex+1)+"/"+sm.messageArray.length
}

//显示消息框
tocas.SimpleMessage.prototype.display=function(messageObject){
	var sm=this;
	//播放声音
	if(sm.soundOn){
		$("messageSound").src=imgPath+sm.sound[messageObject.type==''?1:messageObject.type];
	}
	//控制新消息来时，有个重新由下往上显示的过程
	sm.MessageBox.style.height="0px";
	//控制分页是否显示
	$("ly_messageBox").style.display="";
	if(sm.messageArray.length==1){
		$("ly_messageBox_middle_middle_r3").style.display="none";
	}else{
		$("ly_messageBox_middle_middle_r3").style.display="";
	}
	if (sm.MessageBox.style.height.split("px")[0]<sm.height){
		var t=setInterval(function(){
			var tem=parseInt(sm.MessageBox.style.height.split("px")[0])+sm.showSpeed;
			if (tem>=sm.height){
				sm.MessageBox.style.height=sm.height+"px";
				if(sm.soundOn){
					//避免窗口从最小化到重新显示，声音重新播放
					$("messageSound").src=null;
				}
				clearInterval(t);
				//自动关闭提示框
				if(sm.autoClose){
					sm.TimerToClose=setTimeout(tocas.SimpleMessage.sm_autoClose,5000);
				}
			}else{
				sm.MessageBox.style.height=tem+"px";
			}
		},20);  
	}

}

//往前查看
tocas.SimpleMessage.previous = function(){
	var sm=simpleMessageObj;
	sm.currentIndex=(sm.currentIndex-1)<0?(sm.messageArray.length-1):(sm.currentIndex-1);
	sm.iniData(sm.messageArray[sm.currentIndex]);
}

//往后查看
tocas.SimpleMessage.next = function(){
	var sm=simpleMessageObj;
	sm.currentIndex=(sm.currentIndex+1)>=sm.messageArray.length?0:(sm.currentIndex+1);
	sm.iniData(sm.messageArray[sm.currentIndex]);
}

//关闭，并初始化消息数组
tocas.SimpleMessage.sm_close=function(){
	$("ly_messageBox").style.display="none";
	simpleMessageObj.messageArray=new Array();
}

//自动关闭，不重新初始化
tocas.SimpleMessage.sm_autoClose=function(){
	var sm=simpleMessageObj;
	sm.closeTimer=setInterval(function(){
	var tem=parseInt(sm.MessageBox.clientHeight)-sm.closeSpeed;
	if (tem<=0){
		sm.MessageBox.style.height="0px";
		$("ly_messageBox").style.display="none";
		clearInterval(sm.closeTimer);
	}else{
		sm.MessageBox.style.height=tem+"px";
	}
},5);  
}

//原则上SimpleMessage对象在window.top定义
tocas.SimpleMessage.getInstance=function(){
	return typeof(window.simpleMessage)=="undefined"?window.top.simpleMessage:window.simpleMessage;
}






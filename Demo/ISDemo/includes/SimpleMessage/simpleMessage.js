/*---------------------------------------------------------------------
 * SimpleMessage�����������v1.0
 * By LiuYong 2010/04/30
 * ʹ�÷�������demo
 */

function $(id){
	return document.getElementById(id);	
}
//simpleMessage��imagesĿ¼�����Ӧ�������ĸ���·��
//����"tocas/Components/SimpleMessage/"
var imgPath="";
var tocas = function(){};
var simpleMessageObj=null;
//��Ϣ����
tocas.SimpleMessage = function(){
	//--------------��Ҫ���ò�����ʼ----------------//
	this.width=300;
	this.height=110;
	this.soundOn=true;
	this.title="��Ϣ��ʾ��";
	this.autoClose=false;
	//--------------��Ҫ���ò�������----------------//
	this.windowObj=window;
	this.sound=['sound.wav','sound.wav','sound.wav'];
	this.type=[1,2,3];
	this.pic=['images/mail.gif','images/mail.gif','images/mail.gif'];
	this.messageArray=new Array();
	this.MessageBox=null;
	this.currentIndex=0;
	//���Ƹ�������ȥִ�С��ر���Ϣ��ʱ������ʱ��
	this.TimerToClose=null;
	//�ر���Ϣ��ʱ��
	this.closeTimer=null;
	this.showSpeed=1;
	this.closeSpeed=4;
	simpleMessageObj=this;
	
};

//��Ϣ��
tocas.Message = function(){
	this.title='';
	this.content='';
	this.link='';
	this.type='';
	
}

tocas.Message.prototype.pop=function(){
	tocas.SimpleMessage.getInstance().popMessage(this);
}

//������������
tocas.SimpleMessage.prototype.popMessage = function(messageObject){
	var sm=this;
	sm.messageArray.reverse();
	sm.messageArray.push(messageObject);
	sm.messageArray.reverse();
	this.currentIndex=0;
	//������Ϣʱ���Ƚ����رն�ʱ��������������´��ڵ����������趨
	clearTimeout(sm.TimerToClose);
	clearTimeout(sm.closeTimer);
	sm.buildMessageBox(messageObject);
}

//������Ϣ��
tocas.SimpleMessage.prototype.buildMessageBox = function(messageObject){
	var sm=this;
	var documentObj=sm.windowObj.document;
	//���ε����������µ�Div
	if(sm.MessageBox==null){
		sm.MessageBox=documentObj.createElement("div");
		sm.MessageBox.id = "ly_messageBox"
		sm.MessageBox.style.width = sm.width+"px";
		sm.MessageBox.style.height ="0px";
		//������ͷ��
		var topDiv=documentObj.createElement("div");
		topDiv.id="ly_messageBox_top";
		topDiv.style.width=sm.width+"px";
		topDiv.style.height="26px";
		topDiv.innerHTML="<div id='ly_messageBox_top_left' style='width:50%;height:100%;'><div id='ly_messageBox_top_title'></div></div><div id='ly_messageBox_top_right' style='width:50%;height:100%;'><img src='"+imgPath+"images/close.gif' title='�ر�' onclick='tocas.SimpleMessage.sm_close();'/></div>";
		//�������м䲿��
		var middleDiv=documentObj.createElement("div");
		middleDiv.id="ly_messageBox_middle";
		middleDiv.style.width=sm.width+"px";
		middleDiv.style.height=(sm.height-26-2)+"px";
		middleDiv.innerHTML="<div id='ly_messageBox_middle_left' style='width:2px;height:100%;'></div><div id='ly_messageBox_middle_middle' style='width:"+(sm.width-4)+"px;height:100%;'><div id='ly_messageBox_middle_middle_l' style='width:60px;'><img style='width:38px;height:30px;' id='ly_messageBox_middle_img'/></div><div id='ly_messageBox_middle_middle_r' style='width:"+(sm.width-4-60)+"px;'><div id='ly_messageBox_middle_middle_r1'></div><div id='ly_messageBox_middle_middle_r2'></div><div id='ly_messageBox_middle_middle_r3'><img src='"+imgPath+"images/left.gif' title='��һ��' onclick='tocas.SimpleMessage.previous();'/>&nbsp;<span id='ly_messageBox_middle_page'></span>&nbsp;<img src='"+imgPath+"images/right.gif' title='��һ��' onclick='tocas.SimpleMessage.next();'/></div></div></div><div id='ly_messageBox_middle_right' style='width:2px;height:100%;'></div>";
		//����������
		var bottomDiv=documentObj.createElement("div");
		bottomDiv.id="ly_messageBox_bottom";
		bottomDiv.style.width=sm.width+"px";
		bottomDiv.style.height="2px";
		bottomDiv.innerHTML="<div id='ly_messageBox_bottom_left' style='width:50%;height:100%;'></div><div id='ly_messageBox_bottom_right' style='width:50%;height:100%;'></div>";
		
		sm.MessageBox.appendChild(topDiv);
		sm.MessageBox.appendChild(middleDiv);
		sm.MessageBox.appendChild(bottomDiv);
		
		documentObj.body.appendChild(sm.MessageBox);
		
		//�������Ԫ��
		if(sm.soundOn){
			var messageSound=documentObj.createElement("bgsound");
			messageSound.id="messageSound";
			documentObj.body.appendChild(messageSound);
		}
		
		
	}
	//��ʼ������
	sm.iniData(messageObject);
	//��ʾ����
	sm.display(messageObject);
}

//��ʼ������
tocas.SimpleMessage.prototype.iniData=function(messageObject){
	
	var sm=this;
	//���ݳ�ʼ��
	$("ly_messageBox_top_title").innerHTML=sm.title;
	$("ly_messageBox_middle_img").src=imgPath+sm.pic[messageObject.type==''?1:messageObject.type];
	$("ly_messageBox_middle_middle_r1").innerHTML=messageObject.link==""?messageObject.title:"<a href='"+messageObject.link+"' target='_blank'>"+messageObject.title+"</a>";
	$("ly_messageBox_middle_middle_r2").innerHTML=messageObject.link==""?messageObject.content:"<a href='"+messageObject.link+"' target='_blank'>"+messageObject.content+"</a>";
	$("ly_messageBox_middle_page").innerHTML=(sm.currentIndex+1)+"/"+sm.messageArray.length
}

//��ʾ��Ϣ��
tocas.SimpleMessage.prototype.display=function(messageObject){
	var sm=this;
	//��������
	if(sm.soundOn){
		$("messageSound").src=imgPath+sm.sound[messageObject.type==''?1:messageObject.type];
	}
	//��������Ϣ��ʱ���и���������������ʾ�Ĺ���
	sm.MessageBox.style.height="0px";
	//���Ʒ�ҳ�Ƿ���ʾ
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
					//���ⴰ�ڴ���С����������ʾ���������²���
					$("messageSound").src=null;
				}
				clearInterval(t);
				//�Զ��ر���ʾ��
				if(sm.autoClose){
					sm.TimerToClose=setTimeout(tocas.SimpleMessage.sm_autoClose,5000);
				}
			}else{
				sm.MessageBox.style.height=tem+"px";
			}
		},20);  
	}

}

//��ǰ�鿴
tocas.SimpleMessage.previous = function(){
	var sm=simpleMessageObj;
	sm.currentIndex=(sm.currentIndex-1)<0?(sm.messageArray.length-1):(sm.currentIndex-1);
	sm.iniData(sm.messageArray[sm.currentIndex]);
}

//����鿴
tocas.SimpleMessage.next = function(){
	var sm=simpleMessageObj;
	sm.currentIndex=(sm.currentIndex+1)>=sm.messageArray.length?0:(sm.currentIndex+1);
	sm.iniData(sm.messageArray[sm.currentIndex]);
}

//�رգ�����ʼ����Ϣ����
tocas.SimpleMessage.sm_close=function(){
	$("ly_messageBox").style.display="none";
	simpleMessageObj.messageArray=new Array();
}

//�Զ��رգ������³�ʼ��
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

//ԭ����SimpleMessage������window.top����
tocas.SimpleMessage.getInstance=function(){
	return typeof(window.simpleMessage)=="undefined"?window.top.simpleMessage:window.simpleMessage;
}






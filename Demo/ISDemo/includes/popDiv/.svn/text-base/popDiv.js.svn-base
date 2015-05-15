/*弹出层控件，暂不依赖于jquery*/

//弹出层全局参数，用于记录当前页面弹出层的id及相应配置信息
var PopDiv_ObjectIdArray = [];

function PopDiv(){
	//event对象，用于定位，如果不在调用时传递，之后就获取不到了。哎
	this.eventObj=null;
	this.defaults = {
		id: "defaultPopDivId",
		width:	300,
		height:	null,
		cache: true,
		parentId:"",
		buttonArea:true,
		buildNewButtonArea:null,
		content:"<div class='loading'>正在加载请稍候...</div>"
	};
}

PopDiv.prototype={
	//参数初始化
	int:function(eventObj,setting){
		this.eventObj=eventObj;
		//settting参数初始化
		for(var key in this.defaults){
			if(setting[key]!= undefined){
				this.defaults[key]=setting[key];
			}
		}
	},
	//构建并弹出层
	pop:function(){
		//缓存设置为true时，如果当前弹出层已经存在就直接弹出，否则重新构建
		//if(this.defaults.cache && document.getElementById(this.defaults.id)!=null){
		if(  document.getElementById(this.defaults.id)!=null){
			this.setPositon();
		}else{
			this.bulid();
		}
	},
	rePop:function(newContent){
		this.defaults.content=newContent;
		var contentDivObj=document.getElementById(this.defaults.id+"_content");
		contentDivObj.innerHTML="";
		//根据内容类型选择不同添加方式
		if(typeof this.defaults.content =='string'){
			contentDivObj.innerHTML=this.defaults.content;
		}else{
			contentDivObj.appendChild(this.defaults.content);
		}
	},
	//构建整个弹出菜单的html元素
	bulid:function(){
		//创建新的弹出DIV层
		var newPopDiv=document.createElement("div");
		newPopDiv.className="popDiv";
		newPopDiv.id=this.defaults.id;
		newPopDiv.style.width=(this.defaults.width+16)+"px";
		if(this.defaults.height!=null){newPopDiv.style.height=(this.defaults.height+16)+"px";}
		//创建内容区域
		var contentDiv=document.createElement("div");
		contentDiv.className="contentDiv";
		contentDiv.id=this.defaults.id+"_content";
		contentDiv.style.width=this.defaults.width+"px";
		if(this.defaults.height!=null){contentDiv.style.height=(this.defaults.height-20)+"px";}
		
		//根据内容类型选择不同添加方式
		if(typeof this.defaults.content =='string'){
			contentDiv.innerHTML=this.defaults.content;
		}else{
			contentDiv.appendChild(this.defaults.content);
		}
		newPopDiv.appendChild(contentDiv);
		//创建按钮区域
		if(this.defaults.buttonArea){
			if(this.defaults.buildNewButtonArea==null){
				//创建关闭按钮区域
				var closeDiv=document.createElement("div");
				closeDiv.className="closeDiv";
				closeDiv.innerHTML="关闭"
				var currentPopDivId=this.defaults.id;
				closeDiv.onclick=function(){
					PopDiv.closeMe(currentPopDivId);
				}
				newPopDiv.appendChild(closeDiv);
			}else{
				var buttonAreaDiv=document.createElement("div");
				buttonAreaDiv.className="buttonAreaDiv";
				buttonAreaDiv.innerHTML=this.defaults.buildNewButtonArea(this.defaults.id);
				newPopDiv.appendChild(buttonAreaDiv);
			}
		}
		newPopDiv.style.display="none";
		if(this.defaults.parentId!=""){
			//自定义弹出层的父容器
			document.getElementById(this.defaults.parentId).appendChild(newPopDiv);
		}else{
			//默认为body
			document.body.appendChild(newPopDiv);
		}
		
		PopDiv_ObjectIdArray[this.defaults.id]=[];
		PopDiv_ObjectIdArray[this.defaults.id]['obj']=newPopDiv;
		PopDiv_ObjectIdArray[this.defaults.id]['defaults']=this.defaults;
		this.setPositon();
	},
	//设置弹出菜单的位置（暂时这样简单定位，将来可能写个专门控制页面元素定位的类）
	setPositon:function(){
		var screenWidth=$(document.body).width();
		//触发弹出层的事件对象
		var obj = this.eventObj.srcElement ? this.eventObj.srcElement : this.eventObj.target
		//获取触发事件元素位置信息
		var offset = $(obj).offset();
		var x=offset.left;
		//当弹出框在最右时，弹出框的位置要做调整
		if(x+$("#"+this.defaults.id).width()>screenWidth){
			x=x+$(obj).width()-this.defaults.width-32;
		}
		var y=0;
		if(this.defaults.parentId!=""){
			var boxOffset=$("#"+this.defaults.parentId).offset();
			y=offset.top+$(obj).height()+$("#"+this.defaults.parentId).scrollTop()-boxOffset.top;
		}else{
			y=offset.top+$(obj).height()+$(document.body).scrollTop();
		}
		var popDivObject=document.getElementById(this.defaults.id);
		popDivObject.style.left=x+"px";
		popDivObject.style.top=y+"px";
		PopDiv.show(this.defaults.id);
	}
}

//弹出层显示方法，在页面覆盖此方法用户自定义弹出层显示方式
PopDiv.show=function(popDivId){
	var popDivObject=document.getElementById(popDivId);
	popDivObject.style.display="";
}

//弹出层关闭函数 静态方法
PopDiv.closeMe=function(popDivId){
	var defaults=PopDiv_ObjectIdArray[popDivId].defaults;
	var curPopDiv=PopDiv_ObjectIdArray[popDivId].obj;
	if(defaults.cache){
		curPopDiv.style.display="none";
	}else{
		curPopDiv.parentNode.removeChild(curPopDiv);
	}
}

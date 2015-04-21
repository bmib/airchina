/*
*	辅助css实现页面效果，比如表格行移入移出，标签页切换等
*	LIUYONG
*/
$(function(){
	yIniTableStyle();
	yIniTab();
	iniButtonEffect();
	iniNewsTab();
});

/*
*设置HTC文件相对于当前页面的位置，如果当前页面找不到PIE.htc就没有圆角等效果了
*因为css的behavior属性是相对于当前页面的,所以当文件存放路径发生改变时，需要同时改变yStyleHTCPath变量值
*除首页少量使用外，其他页面尽量不要使用PIE.htc
*/
var yStyleHTCPath="../includes/styleBase/PIE.htc";
function reIniHTCPath(yStyleHTCPath){
	if(!$.browser.msie){
		return;
	}
	try{
		var yStyleSheet=null;
		var yStyleSheets=document.styleSheets;
		for(var i=0;i<yStyleSheets.length;i++){
			if(yStyleSheets[i].href!=null && yStyleSheets[i].href.indexOf("yStyle.css")>=0){
				yStyleSheet=yStyleSheets[i];
			}
		}
		var yRule=yStyleSheet.rules?yStyleSheet.rules:yStyleSheet.cssRules;
		for(var i=0;i<yRule.length;i++){
			if(yRule[i].style.behavior!=""){
				yRule[i].style.behavior="url('"+yStyleHTCPath+"')";
			}
		}
	}catch(e){
		alert("未正确配置样式表，请检查！"+e);
	}
}

//给表格体添加样式
function yIniTableStyle(){
	$(".yTable>tbody>tr").each(function(i){
		if($(this).parent().parent().attr("class").indexOf("yTableNoEffect")>=0){return;}
		if(i%2!=0){
			$(this).attr("class","yTableTrDouble");
		}
	});
}

//给Tab添加事件
function yIniTab(){
	$(".yT").each(function(i){
		$(this).bind("click",function(){
			$(".yTContent").each(function (j){
				if(i==j){
					$(this).show();
				}else{
					$(this).hide();	
				}							   
			});
			$(".yT").removeClass("yTabHOn");
			$(".yT").addClass("yTabH");
			$(this).removeClass("yTabH");
			$(this).addClass("yTabHOn");
		});					  
	});
	
	$(".yTIframe").each(function(){
		$(this).bind("click",function(){
			$("#yTIframe").attr("src",$(this).attr("url"));
			$(".yTIframe").removeClass("yTabHOn");
			$(".yTIframe").addClass("yTabH");
			$(this).removeClass("yTabH");
			$(this).addClass("yTabHOn");
		});							 
	});
}

//给图标按钮添加样式
function iniButtonEffect(){
	$(".yButton").bind("mouseover",function(){
		$(this).addClass("yButtonOver");
	});
	$(".yButton").bind("mouseout",function(){					 
		$(this).removeClass("yButtonOver");
	});		
	$(".yNavButton").bind("mouseover",function(){
		$(this).addClass("yNavButtonOver");
	});
	$(".yNavButton").bind("mouseout",function(){					 
		$(this).removeClass("yNavButtonOver");
	});	
}

//全选，反选按钮初始化
function iniCheckBox(allButtonId,childClass){
	checkBoxControl(allButtonId,childClass);
	$("."+childClass).live("click",function(){
		checkBoxControl(allButtonId,childClass);
	});
	$("#"+allButtonId).live("click",function(){
		if($(this).attr("checked")=="checked" || $(this).attr("checked")==true){
			$("."+childClass).attr("checked","checked");
		}else{
			$("."+childClass).removeAttr("checked");
		}
	});
}
//初始化首页新闻框Tab页面
function iniNewsTab(){
	$(".TabGroup").each(function(){
		var tabGroupObj=$(this);
		var tabObj=$(this).find(".yNewsTitleTab");
		var contentObj=$(this).find(".yNewsTabContent");
		tabObj.each(function(i){
			$(this).live("click",function(){
				tabGroupObj.find(".yNewsTitleTabOn").removeClass("yNewsTitleTabOn");
				$(this).addClass("yNewsTitleTabOn");
				contentObj.each(function(j){
					if(i==j){
						$(this).show();
					}else{
						$(this).hide();
					}
				});	
			});
		});
	});	
}
//checkbox控制
function checkBoxControl(allButtonId,childClass){
	var flag=false;
	$("."+childClass).each(function(){
		if($(this).attr("checked")=="checked" || $(this).attr("checked")==true){
		}else{
			flag=true;
		}
	});
	if(flag){
		$("#"+allButtonId).removeAttr("checked");	
	}else{
		$("#"+allButtonId).attr("checked","checked");
	}		
}
//获取
function getCheckBoxValues(checkBoxClass){
	var orgUUIDArray = new Array();
	$("."+checkBoxClass).each(function(){
		var chkStr=$(this).attr("checked");
		if(chkStr=="checked" || chkStr==true){
			orgUUIDArray.push($(this).val());
		}
	});
	return orgUUIDArray;
}
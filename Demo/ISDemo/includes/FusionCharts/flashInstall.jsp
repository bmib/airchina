<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
    String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base target="_self">
    <title>flash安装页面</title>
<style>
<!--
body{
margin:0px;
padding:0px;
font-size:12px;
color:#236296;
background-color:#D4E2F4;
}

#listTable{
font-size:12px;
background-color:#ABC7EC;
text-align:center;
}

#listTable tr{
background-color:white;
height:22px;
}

#listTable th{
background:url('<%=path %>/include/images/extPanelSubjectBig.gif');
height:24px;
}
-->
</style>
<script>
</script>
  </head>
  
  <body>
   <div style="background:url('<%=path %>/include/images/extPanelSubjectBig.gif');padding-top:10px;height:20px;background-color:#D4EBFE;text-align:center;color:#236296;"><h2>下载FLASH控件</h2></div>
   <div style="background-color:#E4F3FF;margin-bottom:2px;" class="text01">
   <table id="listTable" border="0" class="text01" cellpadding="1" cellspacing="1" width="100%">
   <tr>
   		<td width="100" >
   		<img src="<%=path %>/include/images/flashplaye.jpg">
   		</td>
   		<td>
   		<a href="<%=path %>/include/ocx/swflash.cab">点击下载FLASH控件</a>
   		</td>
   </tr>
   </table>
   
   </div>
  </body>
</html>

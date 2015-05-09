function _updateCharts(chartdiv,chartid,swf,width,height,classname,params,rootPath)
{
		var chartObj = new FusionCharts(swf, chartid, width, height, "0", "1");
		var fullparams = "chartid=" +  chartid + "&chartdiv=" + chartdiv + "&" + params;
		var xmlname = getStr(rootPath,classname,fullparams);
		try
		{
			_beforeChart(chartObj,chartdiv,chartid,params);
		}
		catch(e) 
		{
		}
		chartObj.setDataURL(xmlname);
		chartObj.render(chartdiv);
		try
		{
			_aftreChart(chartObj,chartdiv,chartid,params);
		}
		catch(e) 
		{
		}
		try
		{
			_aftreChart(chartdiv,chartid,params);
		}
		catch(e) 
		{
		}
}

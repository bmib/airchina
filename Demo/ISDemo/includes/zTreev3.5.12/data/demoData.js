var fxNodes =[
		{id:9, pId:0,name:"风险指标",open:true},
		{id:1, pId:9, name:"环境风险度",open:true},
		{id:102, pId:1,name:"机型机队混编/混合机队构型" },
		{id:103, pId:1,name:"飞机复杂性的影响" },
		{id:104, pId:1,name:"雇员结构复杂性的影响" },
		{id:105, pId:1,name:"外包（维修、培训、地服）" },
		{id:106, pId:1,name:"季节性运行" },
		{id:107, pId:1,name:"设施的增添"} ,
		{id:108, pId:1,name:"搬迁或关闭" },
		{id:109, pId:1,name:"租赁协定" },
		{id:110, pId:1,name:"局方正常办公时间以外的航空承运人活动（非办公时间活动）"} ,
			
		{id:2, pId:9, name:"运行稳定性",open:true},
		{id:201, pId:2,name:"高层管理人员的改变" },
		{id:202, pId:2,name:"其它管理人员的改变" },
		{id:203, pId:2,name:"人员流动" },
		{id:204, pId:2,name:"缩减员工" },
		{id:205, pId:2,name:"运营规模的重大变化" },
		{id:206, pId:2,name:"兼并或接管"},
		{id:207, pId:2,name:"劳资关系"} ,				   
			
		{id:3, pId:9, name:"绩效历史表现",open:true},
		{id:301, pId:3,name:"整改措施的执行" },
		{id:302, pId:3,name:"事故/事故征候/不安全事件" },
		{id:303, pId:3,name:"航空承运人的信息"},		   
			
		{id:4, pId:9, name:"航空承运人动态性",open:true},
		{id:401, pId:4,name:"大纲的重大改变" },
		{id:402, pId:4,name:"安全管理体系" },
		{id:403, pId:4,name:"与局方的合作关系"} 			   
];

var sjdNodes =[
		{id:9, pId:0,name:"审计单",open:true},
		{id:5, pId:9, name:"组织管理",open:false},
		{id:1, pId:9, name:"飞行",open:true},
		{id:102, pId:1,name:"OP_EPI_1.1.2适当的运行设备" },
		{id:103, pId:1,name:"OP_EPI_2.1.1手册更新" },
		{id:104, pId:1,name:"OP_SAI_1.1.2适当的运行设备" },
		{id:105, pId:1,name:"OP_SAI_2.1.2相关手册内容一致性" },
		{id:106, pId:1,name:"OP_SOI_8.1.1停机坪监察" },
		{id:107, pId:1,name:"OP_SOI_8.1.2连续下降最后进近（CDFA）"} ,
			
		{id:2, pId:9, name:"机务",open:false},	   
			
		{id:3, pId:9, name:"运控",open:false},
			
		{id:4, pId:9, name:"客舱",open:false},	
		{id:6, pId:9, name:"地服",open:false},	
		{id:7, pId:9, name:"货运",open:false},	
		{id:8, pId:9, name:"保卫",open:false},		   
];
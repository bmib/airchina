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


var problemTypeNodes = [
		{ id: 1, pId: 0, name: "程序", open: true },
		{ id: 102, pId: 1, name: "人员（例如：人力资源不足、人员资质不符合要求等）" },
		{ id: 103, pId: 1, name: "工具和设备" },
		{ id: 104, pId: 1, name: "技术资料（例如：厂家提供的手册、OEB、SB等）" },
		{ id: 105, pId: 1, name: "政策、程序、指令或信息" },
		{ id: 106, pId: 1, name: "材料（例如：航材等）" },
		{ id: 107, pId: 1, name: "设施（例如：厂房、车间、机库、教室等）" },
		{ id: 108, pId: 1, name: "预测结果（与预设目标不符）" },
		{ id: 109, pId: 1, name: "其他" },

		{ id: 2, pId: 0, name: "控制", open: true },
		{ id: 202, pId: 2, name: "人员（例如：人力资源不足、人员资质不符合要求等）" },
		{ id: 203, pId: 2, name: "工具和设备" },
		{ id: 204, pId: 2, name: "技术资料（例如：厂家提供的手册、OEB、SB等）" },
		{ id: 205, pId: 2, name: "政策、程序、指令或信息" },
		{ id: 206, pId: 2, name: "材料（例如：航材等）" },
		{ id: 207, pId: 2, name: "设施（例如：厂房、车间、机库、教室等）" },
		{ id: 208, pId: 2, name: "预测结果（与预设目标不符）" },
		{ id: 209, pId: 2, name: "其他" },

		{ id: 3, pId: 0, name: "流程测量", open: true },
		{ id: 302, pId: 3, name: "人员（例如：人力资源不足、人员资质不符合要求等）" },
		{ id: 303, pId: 3, name: "工具和设备" },
		{ id: 304, pId: 3, name: "技术资料（例如：厂家提供的手册、OEB、SB等）" },
		{ id: 305, pId: 3, name: "政策、程序、指令或信息" },
		{ id: 306, pId: 3, name: "材料（例如：航材等）" },
		{ id: 307, pId: 3, name: "设施（例如：厂房、车间、机库、教室等）" },
		{ id: 308, pId: 3, name: "预测结果（与预设目标不符）" },
		{ id: 309, pId: 3, name: "其他" },

		{ id: 4, pId: 0, name: "结构", open: true },
		{ id: 402, pId: 4, name: "人员（例如：人力资源不足、人员资质不符合要求等）" },
		{ id: 403, pId: 4, name: "工具和设备" },
		{ id: 404, pId: 4, name: "技术资料（例如：厂家提供的手册、OEB、SB等）" },
		{ id: 405, pId: 4, name: "政策、程序、指令或信息" },
		{ id: 406, pId: 4, name: "材料（例如：航材等）" },
		{ id: 407, pId: 4, name: "设施（例如：厂房、车间、机库、教室等）" },
		{ id: 408, pId: 4, name: "预测结果（与预设目标不符）" },
		{ id: 409, pId: 4, name: "其他" },

        { id: 5, pId: 0, name: "责任和权力", open: true },
        { id: 502, pId: 5, name: "人员（例如：人力资源不足、人员资质不符合要求等）" },
		{ id: 503, pId: 5, name: "工具和设备" },
		{ id: 504, pId: 5, name: "技术资料（例如：厂家提供的手册、OEB、SB等）" },
		{ id: 505, pId: 5, name: "政策、程序、指令或信息" },
		{ id: 506, pId: 5, name: "材料（例如：航材等）" },
		{ id: 507, pId: 5, name: "设施（例如：厂房、车间、机库、教室等）" },
		{ id: 508, pId: 5, name: "预测结果（与预设目标不符）" },
		{ id: 509, pId: 5, name: "其他" }
];
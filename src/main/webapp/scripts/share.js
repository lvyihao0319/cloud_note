function moreSearchShare() {
	//获取参数
	var keyword=$("#search_note").val().trim();
	page=page+1;
	//发送ajax请求加载列表
	searchSharePage(keyword,page);
};

function sureSearchShare(event){
	 var code = event.keyCode;
	 if(code==13){//回车键
		 //清除原有列表结果
		 //$("#pc_part_6 ul").empty();
		 //显示搜索结果列表,其他列表隐藏
		 //$("#pc_part_2").hide();
		 //$("#pc_part_4").hide();
		 //$("#pc_part_6").show();
		 //$("#pc_part_7").hide();
		 //$("#pc_part_8").hide();
		 //获取请求参数
		 $("#search_ul li").remove();
		 var keyword=$("#search_note").val().trim();
		 //var page = 1;//设置初始值1
		 //发送Ajax请求
		 page=1;
		searchSharePage(keyword,page);
	 }
 };

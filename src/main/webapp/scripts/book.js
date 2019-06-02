//确定创建笔记本
function addBook(){
	 //获取请求参数
	 var name = $("#input_notebook").val().trim();
	 var userId = getCookie("userId");
	 //格式检查
	 var ok = true;
	 if(name==""){
		 ok = false;
		 $("#notebook_span").html("笔记本名为空");
	 }
	 if(userId==null){
		 ok = false;
		 window.location.href="login.html";
	 }
	 alert(userId+"=="+name)
	 //发送Ajax请求
	 if(ok){
		 $.ajax({
			 url:path+"/book/add.do",
			 type:"post",
			 data:{"userId":userId,"name":name},
			 dataType:"json",
			 success:function(result){
				 if(result.status==0){
					 //关闭对话框
					 closeAlertWindow();
					 //生成笔记本li元素
					 var bookId = 
						result.data.cn_notebook_id;
					 var bookName = 
						 result.data.cn_notebook_name;
					 creatBookLi(bookId,bookName);
					 //提示成功
					 alert(result.msg);
				 }
			 },
			 error:function(){
				 alert("创建笔记本异常");
			 }
		 });
	 }
 };


//根据用户id显示笔记本列表
function loadUserBooks() {
	//获取userId
	var userId=getCookie("userId");
	//判断是否获取到有效的userId
	if (userId==null) {
		window.location.href="login.html";
	}else {//发送ajax请求
		$.ajax({
			url:path+"/book/loadbooks.do",
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				//判断查询是否成功
				if (result.status==0) {
					//获取笔记本集合
					var books=result.data;
					for (var i = 0; i < books.length; i++) {
						//获取笔记本id
						var bookId=books[i].cn_notebook_id;
						//获取笔记本名称
						var bookName=books[i].cn_notebook_name;
						//创建一个笔记本列表的li元素
						creatBookLi(bookId,bookName);
					}
				}
				
			},
			error:function(){
				alert("笔记本加载失败！")
			}
		});
	}
};
//创建一个笔记本li元素
function creatBookLi(bookId,bookName) {
	var sli="";
	sli+='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+='</i>';
	sli+=bookName;
	sli+='</a>';
	sli+='</li>';
	//将sli字符串转换成jquery对象li元素
	var $li=$(sli);
	//将bookId值与jquery对象绑定
	$li.data("bookId",bookId);
	//将li元素添加到笔记本ul列表区
	$("#book_ul").append($li);
}
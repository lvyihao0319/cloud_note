//分页加载搜索分享笔记
function searchSharePage(keyword,page){
 $.ajax({
		 url:path+"/share/search.do",
		 type:"post",
		 data:{"keyword":keyword,"page":page},
		 dataType:"json",
		 success:function(result){
			 //获取数据
			 var shares=result.data;
			 for(var i=0;i<shares.length;i++){
				 //获取shareId
				 var shareId=shares[i].cn_share_id;
				 //获取shareTitle
				 var shareTitle=shares[i].cn_share_title;
				 //获取li对象
				 var sli="";
				 sli+='<li class="online">';
				 sli+='<a>';
				 sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				 sli+=shareTitle;
				 sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				 sli+='</a>';
				 sli+='</li>';
				 var $li=$(sli);
				 //绑定shareId
				 $li.data("shareId",shareId);
				 //将li对象添加到ul当中
				 $("#search_ul").append($li);
				 //切换显示区域
				 $("#pc_part_2").hide();
				 $("#pc_part_6").show();
			 }
//			 if(result.status==0){
//				 //获取服务器返回的搜索结果
//				 var shares = result.data;
//				 //循环解析生成列表li元素
//				 for(var i=0;i<shares.length;i++){
//					 var shareId = //分享ID
//						 shares[i].cn_share_id;
//					 var shareTitle = //分享标题
//						 shares[i].cn_share_title;
//					 //生成一个li
//				var sli = "";
//				sli+='<li class="online">';
//				sli+='	<a>';
//				sli+='		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
//				sli+= shareTitle;
//				sli+='		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-star"></i></button>';
//				sli+='	</a>';
//				sli+='</li>';
//				var $li = $(sli);
//				$li.data("shareId",shareId);
//					 //添加到搜索结果ul中
//					 $("#pc_part_6 ul").append($li);
//				 }
//			 }
		 },
		 error:function(){
			 alert("搜索失败");
		 }
	 });
};

//分享笔记
function shareNote(){
	 //获取请求参数
	 var $li = $(this).parents("li");
	 var noteId = $li.data("noteId");
	 //alert(noteId);
	 //发送Ajax请求
	 $.ajax({
		 url:path+"/share/add.do",
		 type:"post",
		 data:{"noteId":noteId},
		 dataType:"json",
		 success:function(result){
			 var noteTitle=$li.text();
			 var sli="";
			 sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
			 sli+=noteTitle;
			 sli+='<i class="fa fa-sitemap"></i>';
			 sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
			 //将笔记li元素的<a>标记内容替换
			 $li.find("a").html(sli);
			 alert(result.msg);
			 
			 //			 if(result.status==0){
//				 //添加分享图标
//				 var img = '<i class="fa fa-sitemap"></i>';
//				 $li.find(".btn_slide_down").before(img);
//				 //提示成功
//				 alert(result.msg);
//			 }else if(result.status==1){
//				 //提示已分享过
//				 alert(result.msg);
//			 }
		 },
		 error:function(){
			 alert("分享笔记异常");
		 }
	 });
 };

//隐藏笔记菜单
function hideNoteMenu(){
	 //隐藏所有笔记菜单
	 $("#note_ul div").hide();
 };
//弹出笔记菜单
function popNoteMenu(){
	 //隐藏所有笔记菜单
	 $("#note_ul div").hide();
	 //显示点击的笔记菜单
	 var $menu = $(this).parent().next();
	 $menu.slideDown(500);
	 //设置点击笔记选中效果
	 $("#note_ul a").removeClass("checked");
	 $(this).parent().addClass("checked");
	 //阻止事件向li,body冒泡
	 return false;
 };

//创建笔记操作
function addNote(){
	
	
	//获取请求参数
	 
	 var title = //获取对话框中笔记名称
		$("#input_note").val().trim();
	 var userId = getCookie("userId");
	 var $li = //获取选中的笔记本li元素
		$("#book_ul a.checked").parent();
	 var bookId = $li.data("bookId");
	 //检查格式
	 var ok = true;
	 
	 if(title==""){
		 ok = false;
		 $("#note_span").html("笔记名称为空");
	 }
	 if(userId==null){
		 ok = false;
		 window.location.href="login.html";
	 }
	 //alert(userId+noteTitle+bookId);
	 //发送Ajax请求
	 if(ok){
		 $.ajax({
			 url:path+"/note/add.do",
			 type:"post",
			 data:{"userId":userId,
				 "bookId":bookId,
				 "title":title},
			 dataType:"json",
			 success:function(result){
				 var note=result.data;
				 if(result.status==0){
					 
					 //生成笔记列表li
					 var id = note.cn_note_id;//获取服务器返回的笔记ID
					 var title=note.cn_note_title;
					 createNoteLi(id,title);
					 //弹出提示
					 alert(result.msg);
				 }
			 },
			 error:function(){
				 alert("创建笔记异常");
			 }
		 });
	 }
 };
 
 
//"保存笔记"按钮的处理
function updateNote() {
	 //获取选中的笔记li元素
	 var $li = $("#note_ul a.checked").parent();
	 //获取笔记Id
	 var noteId = $li.data("noteId");
	 //获取笔记标题和内容
	 var noteTitle = $("#input_note_title").val().trim();
	 var noteBody = um.getContent();
	 //alert(noteId+noteTitle+noteBody);
	 $.ajax({
			url:path+"/note/update.do",
			type:"post",
			data:{"noteId":noteId,
				  "title":noteTitle,
				  "body":noteBody},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//更新列表li中标题
					var sli = "";
					sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli+=noteTitle;
					sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					//将选中li元素的a内容替换
					$li.find("a").html(sli);
					//提示成功
					alert(result.msg);
				}
			},
			error:function(){
				alert("保存笔记异常");
			}
		});
};


//根据笔记ID加载笔记信息
function loadNote(){
	 //设置笔记选中状态
	 $("#note_ul a").removeClass("checked");
	 $(this).find("a").addClass("checked");
	 //获取请求参数
	 var noteId = $(this).data("noteId");
	//alert(noteId);
	 //发送Ajax请求
	 $.ajax({
		 url:path+"/note/load.do",
		 type:"post",
		 data:{"noteId":noteId},
		 dataType:"json",
		 success:function(result){
			 if(result.status==0){
				 var title = //获取笔记标题
					 result.data.cn_note_title;
				 var body = //获取笔记内容
					 result.data.cn_note_body;
				 //设置到编辑区域
				 $("#input_note_title").val(title);
				 um.setContent(body);
			 }
		 },
		 error:function(){
			 alert("加载笔记异常");
		 }
	 });
 };




//加载笔记本笔记
function loadBookNotes(){
		//设置选中效果
		$("#book_ul a").removeClass("checked");
		$(this).find("a").addClass("checked");
 		//获取请求参数
 		 var bookId = $(this).data("bookId");
 		//alert(bookId);
 		 //发送Ajax请求
 		 $.ajax({
 			 url:path+"/note/loadnotes.do",
 			 type:"post",
 			 data:{"bookId":bookId},
 			 dataType:"json",
 			 success:function(result){
 				 //获取笔记信息
 				 var notes=result.data;
 				 //清除原列表信息
 				 $("#note_ul").empty();
 				
 				 //循环添加li
 				 for(var i=0;i<notes.length;i++){
 					 //获取笔记id
 					 var noteId=notes[i].cn_note_id;
 					 //获取笔记标题
 					 var noteTitle=notes[i].cn_note_title;
 					 
 					 createNoteLi(noteId, noteTitle);
 					
 				 }
 			 },
 			 error:function(){
 				 alert("加载笔记列表异常");
 			 }
 		 });
 	};


//创建笔记列表li元素
 function createNoteLi(noteId,noteTitle){
	var sli = "";
	sli+='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli+=noteTitle;
	sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli+='</a>';
	sli+='<div class="note_menu" tabindex="-1">';
	sli+='<dl>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli+='</dl>';
	sli+='</div>';
    sli+='</li>';
	//将noteId绑定到li元素上
	var $li = $(sli);
	$li.data("noteId",noteId);
	//将li元素添加到笔记列表ul中
	$("#note_ul").append($li); 
 };
 
 
//게시판 목록 조회 요청
function boardList() {
	$.ajax({
		url:'boards',
		type:'GET',
		error:function(xhr,status,msg){
			alert("상태값 :" + status + " Http에러메시지 :"+msg);
		},
		success:boardListResult
	});
}//boardList

//게시판 목록 조회 응답
function boardListResult(xhr) {
	console.log(xhr);
	$("tbody").empty();
	$.each(xhr,function(idx,item){
		$('<tr>')
		.append($('<td>').html(item.idx))
		.append($('<td>').html(item.title))
		.append($('<td>').html(item.userName + "(" + item.userId +")"))
		.append($('<td>').html(item.regDate))
		.append($('<td>').html(item.modDate))
		.append($('<td>').html(item.hit))
		.append($('<td>').html('<button id=\'btnSelect\'>조회</button>'))
		.append($('<td>').html('<button id=\'btnDelete\'>삭제</button>'))
		.append($('<input type=\'hidden\' id=\'hidden_idx\'>').val(item.idx))
		.appendTo('tbody');
	});//each
	console.log($("tbody").html());
}//boardListResult

//게시판 조회 요청
function boardSelect() {
	//조회 버튼 클릭
	$('body').on('click','#btnSelect',function(){
		var idx = $(this).closest('tr').find('#hidden_idx').val();
		//특정 게시판 조회
		$.ajax({
			url:'boards/'+idx,
			type:'GET',
			error:function(xhr,status,msg){
				alert("상태값 :" + status + " Http에러메시지 :"+msg);
			},
			success:boardSelectResult
		});
	}); //조회 버튼 클릭
}//boardSelect

//게시판 조회 응답
function boardSelectResult(xhr) {
	var board = xhr;
	$('input:hidden[name="idx"]').val(board.idx);
	$('input:text[name="userId"]').val(board.userId);
	$('input:text[name="userName"]').val(board.userName);
	$('input:text[name="title"]').val(board.title);
	$('textarea[name="content"]').val(board.content);
	$('select[name="kind"]').val(board.kind).attr("selected", "selected");
}//boardSelectResult

//초기화
function init() {
	//초기화 버튼 클릭
	$('#btnInit').on('click',function(){
		$('#boardform').each(function(){
			this.reset();
		});
	});
}//init

//게시판 삭제 요청
function boardDelete() {
	//삭제 버튼 클릭
	$('body').on('click','#btnDelete',function(){
		var idx = $(this).closest('tr').find('#hidden_idx').val();
		var result = confirm("게시물을 정말로 삭제하시겠습니까?");
		if(result) {
			$.ajax({
				url:'boards/'+idx,  
				type:'DELETE',
				//contentType:'application/json',
				dataType:'text',
				success:function(xhr) {
					console.log(xhr);
					boardList();
				},
				error:function(xhr,status,msg){
					console.log("상태값 :" + status + " Http에러메시지 :"+msg);
				}
			});      
		}//if
	}); //삭제 버튼 클릭
}//boardDelete

//게시판 수정 요청
function boardUpdate() {
	//수정 버튼 클릭
	$('#btnUpdate').on('click',function(){
		var idx = $('input:hidden[name="idx"]').val();
		var userId = $('input:text[name="userId"]').val();
		var userName = $('input:text[name="userName"]').val();
		var title = $('input:text[name="title"]').val();
		var content = $('textarea[name="content"]').val();
		var kind = $('select[name="kind"]').val();
		//alert(JSON.stringify({ idx: idx, userId: userId, userName: userName, title: title, content: content, kind: kind }));
		$.ajax({ 
			url: "boards", 
			type: 'PUT', 
			dataType: 'text', 
			data: JSON.stringify({ idx: idx, userId: userId, userName: userName, title: title, content: content, kind: kind }),
			contentType: 'application/json',
			success: function(xhr) {
				if(xhr == 'SUCCESS') {
					boardList();
				}
			},
			error:function(xhr,status,msg){
				console.log("상태값 :" + status + " Http에러메시지 :"+msg);
			}
	});
	});//수정 버튼 클릭
}//boardUpdate

//게시판 등록 요청
function boardInsert(){
	//등록 버튼 클릭
	$('#btnInsert').on('click',function(){
		var userId = $('input:text[name="userId"]').val();
		var userName = $('input:text[name="userName"]').val();
		var title = $('input:text[name="title"]').val();
		var content = $('textarea[name="content"]').val();
		var kind = $('select[name="kind"]').val();
		if(!title){
			alert('제목을 입력하세요.');
			return;
		}else if(!content){
			alert('내용을 입력하세요.');
			return;
		}
		$.ajax({ 
			url: "boards",  
			type: 'POST',  
			dataType: 'text', 
			data: JSON.stringify({ userId: userId, userName: userName, title: title, content: content, kind: kind }),
			contentType: 'application/json'
		})
		// HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨. 
		.done(function(xhr) {
			if(xhr == 'SUCCESS') {
				boardList();
			}
		}) 
		// HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨. 
		.fail(function(xhr, status, msg) { 
				console.log("상태값 :" + status + " Http에러메시지 :"+msg);
		}) 
		.always(function(xhr, status) { 
			console.log("요청이 완료되었습니다!"); 
		});
	});//등록 버튼 클릭
}//boardInsert


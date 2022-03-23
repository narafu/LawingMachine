function goBoardList() {
	$('#content').val('');
	let form = $('#boardForm');
	form.attr('method', 'get');
	form.attr('action', '/board/free/list');
	form.attr('target', '');
	form.submit();
}

function goBoardForm() {
	let url = '/board/free/inputForm';
//	let data = $('#boardForm').serialize();
//	$.get(url, data, function(result) {
//		$('#boardContent').replaceWith(result);
//	})
	$('#boardForm').attr('action', url)
	$('#boardForm').attr('target', '')
	$('#boardForm').submit();
}

function goBoardInfo(brdMstrInfoSeq) {
	$('#brdMstrInfoSeq').val(brdMstrInfoSeq);
	let url = '/board/free/infoView';
//	let data = $('#boardForm').serialize();
//	$.get(url, data, function(result) {
//		$('#boardContent').replaceWith(result);
//	})
	$('#boardForm').attr('action', url)
	$('#boardForm').attr('target', '')
	$('#boardForm').submit();
}

function delBoardInfo() {
	common_modal_alert('삭제하시겠습니까?', function() {
		let url = '/board/free/infoView/delete';
		let data = $('#boardForm').serialize();
		$.post(url, data).done(function(result) {
			common_modal_alert(result.message, function() {
				goBoardList();
			})
		});
	})
}

function insertBoardInfo() {
	if(validation()) {
		common_modal_confirm('등록하시겠습니까?', function() {
			let url = '/board/free/infoView/insert';
			let data = $('#boardForm').serialize();
			$.post(url, data).done(function(result) {
				common_modal_alert(result.message, function() {
					goBoardList();
				})
			})
		})
	}
}

function updateBoardInfo(brdMstrInfoSeq) {
	if(validation()) {
		common_modal_confirm('저장하시겠습니까?', function() {
			let url = '/board/free/infoView/update';
			let data = $('#boardForm').serialize();
			$.post(url, data).done(function(result) {
				common_modal_alert(result.message, function() {
					goBoardInfo(brdMstrInfoSeq);
				});
			})
		})
	}
}

function validation() {
	
	let message;

	if (!$('#brdTypeCd').val()) {
		message = '게시판을 선택해주세요.';
		common_modal_alert(message);
		return false;
	}

	if (!$('#subjectTypeCd').val()) {
		message = '과목을 선택해주세요.';
		common_modal_alert(message);
		return false;
	}

	if (!$('#title').val()) {
		message = '제목을 입력해주세요.';
		common_modal_alert(message);
		return false;
	}

	let boardCntntHtml = boardCntnt.getHTML();
	if (boardCntntHtml == '<p><br></p>') {
		$('#content').val('');
	} else {
		$('#content').val(boardCntntHtml);
	}
	
	return true;
}
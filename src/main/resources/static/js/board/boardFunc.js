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
	let data = $('#boardForm').serialize();
	$.get(url, data, function(result) {
		$('#boardContent').html(result);
	})
}

function goBoardInfo(brdMstrInfoSeq) {
	$('#brdMstrInfoSeq').val(brdMstrInfoSeq);
	let url = '/board/free/infoView';
	let data = $('#boardForm').serialize();
	$.get(url, data, function(result) {
		$('#boardContent').html(result);
	})
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

	let message;

	if (!$('#brdTypeCd').val()) {
		message = '게시판을 선택해주세요.';
		common_modal_alert(message);
		return;
	}

	if (!$('#subjectTypeCd').val()) {
		message = '과목을 선택해주세요.';
		common_modal_alert(message);
		return;
	}

	if (!$('#title').val()) {
		message = '제목을 입력해주세요.';
		common_modal_alert(message);
		return;
	}

	let boardCntntHtml = boardCntnt.getHTML();
	if (boardCntntHtml == '<p><br></p>') {
		$('#content').val('');
	} else {
		$('#content').val(boardCntntHtml);
	}

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

function updateBoardInfo(brdMstrInfoSeq) {

	let boardCntntHtml = boardCntnt.getHTML();

	if (boardCntntHtml == '<p><br></p>') {
		$('#content').val('');
	} else {
		$('#content').val(boardCntntHtml);
	}

	if (boardCntntHtml == '<p><br></p>') {
		$('#content').val('');
	} else {
		$('#content').val(boardCntntHtml);
	}

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
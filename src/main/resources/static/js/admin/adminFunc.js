function goBoardList() {
    $('#content').val('');
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/admin/board/quiz/list');
    form.attr('target', '');
    form.submit();
}

function goBoardForm() {
    let url = '/admin/board/quiz/inputForm';
//    let data = $('#boardForm').serialize();
//    $.get(url, data, function (result) {
//        $('#boardContent').replaceWith(result);
//    })
	$('#boardForm').attr('action', url);
	$('#boardForm').attr('target', '');
	$('#boardForm').submit();
}

function goBoardInfo(quizMstrInfoSeq) {
    $('#quizMstrInfoSeq').val(quizMstrInfoSeq);
    let url = '/admin/board/quiz/infoView';
//    let data = $('#boardForm').serialize();
//    $.get(url, data, function (result) {
//        $('#boardContent').replaceWith(result);
//    })
	$('#boardForm').attr('action', url);
	$('#boardForm').attr('target', '');
	$('#boardForm').submit();
}

function goApprovalList() {
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/admin/board/approval/list');
    form.attr('target', '');
    form.submit();
}

function goApprovalInfo(userId) {
    $('#userId').val(userId);
    let url = '/admin/board/approval/infoView';
//    let data = $('#boardForm').serialize();
//    $.get(url, data, function (result) {
//        $('#boardContent').replaceWith(result);
//    })
	$('#boardForm').attr('action', url);
	$('#boardForm').attr('target', '');
	$('#boardForm').submit();
}

function approval() {

    let userIdArr = [];
    userIdArr.push($('#userId').val());

	common_modal_confirm("승인하시겠습니까?", function() {
	    let url = '/admin/board/approval/infoView';
	    let data = {'userIdArr': userIdArr};
		$.post(url, data).done(function(result) {
			common_modal_alert(result.message);
		})
	})
}

function multiApproval(obj) {

    if (!$('input[type=checkbox]:checked').length) {
        let message = '승인할 대상을 선택해주세요.';
		common_modal_alert(message);
		return;
    } 
}

function insertBoardInfo() {

	let message;

    if(!$('#examYear').val()) {
        message = '시험년도를 선택해주세요.';
        common_modal_alert(message);
        return;
    }

    if(!$('#examGrpCd').val()) {
        message = '시험구분을 선택해주세요.';
        common_modal_alert(message);
        return;
    }

    if(!$('#examNo').val()) {
        message = '시험회차를 선택해주세요.';
        common_modal_alert(message);
        return;
    }

    if(!$('#subjectTypeCd').val()) {
        message = '시험과목을 선택해주세요.';
        common_modal_alert(message);
        return;
    }

    if(!$('[name=answer]:checked').val()) {
        message = '정답을 선택해주세요.';
        common_modal_alert(message);
        return;
    }

    let editorCntntHtml = editorCntnt.getHTML();
    if(editorCntntHtml == '<p><br></p>') {
        $('#content').val('');
    } else {
        $('#content').val(editorCntntHtml);
    }

    let editorCmntrHtml = editorCmntr.getHTML();
    if(editorCmntrHtml == '<p><br></p>') {
        $('#cmntr').val('');
    } else {
        $('#cmntr').val(editorCmntrHtml);
    }

    common_modal_confirm('등록하시겠습니까?', function() {
	    let url = '/admin/board/quiz/infoView/insert';
	    let data = $('#boardForm').serialize();
        $.post(url, data).done(function(result) {
		    common_modal_alert(result.message, function() {
    	        goBoardList();
			})
		})
	})
}

function updateBoardInfo(quizMstrInfoSeq) {

    let editorCntntHtml = editorCntnt.getHTML();
    if (editorCntntHtml == '<p><br></p>') {
        $('#content').val('');
    } else {
        $('#content').val(editorCntntHtml);
    }

    let editorCmntrHtml = editorCmntr.getHTML();
    if (editorCmntrHtml == '<p><br></p>') {
        $('#cmntr').val('');
    } else {
        $('#cmntr').val(editorCmntrHtml);
    }
    
    common_modal_confirm('저장하시겠습니까?', function() {
	    let url = '/admin/board/quiz/infoView/update';
	    let data = $('#boardForm').serialize();
		$.post(url, data).done(function(result) {
			common_modal_alert(result.message, function() {
	            goBoardInfo(quizMstrInfoSeq);
			});			
		})
	})
}

function delBoardInfo() {
	common_modal_alert('삭제하시겠습니까?', function() {
        let url = '/admin/board/quiz/infoView/delete';
        let data = $('#boardForm').serialize();
        $.post(url, data).done(function (result) {
			common_modal_alert(result.message, function() {
	            goBoardList();
			})
        });
	})	
}

function goBoardList() {
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/admin/board/quiz/list');
    form.attr('target', '');
    form.submit();
}

function goBoardForm() {
    let url = '/admin/board/quiz/inputForm';
    let data = $('#boardForm').serialize();
    $.get(url, data, function (result) {
        $('#boardContent').html(result);
    })
}

function goBoardInfo(quizMstrInfoSeq) {
    $('#quizMstrInfoSeq').val(quizMstrInfoSeq);
    let url = '/admin/board/quiz/infoView';
    let data = $('#boardForm').serialize();
    $.get(url, data, function (result) {
        $('#boardContent').html(result);
    })
}

function delBoardInfo() {
    $.confirm({
        title: '',
        content: '삭제하시겠습니까?',
        buttons: {
            '삭제': function () {
                $.alert('삭제되었습니다.');
                let url = '/admin/quiz/infoView/delete';
                let data = $('#boardForm').serialize();
                $.post(url, data, function (result) {
                    goBoardList();
                });
            },
            '취소': function () {
            }
        }
    })
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
    let data = $('#boardForm').serialize();
    $.get(url, data, function (result) {
        $('#boardContent').html(result);
    })
}

function approval() {

}

function multiApproval() {

}
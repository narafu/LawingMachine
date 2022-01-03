
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
    let url = '/admin/quiz/infoView/delete';
    let data = $('#boardForm').serialize();
    $.post(url, data, function (result) {
        goBoardList();
    });
}
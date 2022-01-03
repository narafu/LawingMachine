
function goBoardList() {
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/admin/board/quiz/list');
    form.attr('target', '');
    form.submit();
}

function goBoardForm() {
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/admin/board/quiz/inputForm');
    form.attr('target', '');
    form.submit();
}

function goBoardInfo(quizMstrInfoSeq) {
    $('#quizMstrInfoSeq').val(quizMstrInfoSeq);
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/admin/board/quiz/infoView');
    form.attr('target', '');
    form.submit();
}

function regBoardInfo() {
    let form = $('#boardForm');
    let url = '/admin/quiz/infoView';
    $.post(url, form.serialize(), function (result) {
        if (alert(result['message'])) {
            goBoardList();
        }
    });
}
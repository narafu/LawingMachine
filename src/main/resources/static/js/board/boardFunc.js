
function goBoardList() {
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/board/qna/list');
    form.attr('target', '');
    form.submit();
}

function goBoardForm() {
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/board/qna/inputForm');
    form.attr('target', '');
    form.submit();
}

function goBoardInfo(brdMstrInfoSeq) {
    $('#brdMstrInfoSeq').val(brdMstrInfoSeq);
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/board/qna/infoView');
    form.attr('target', '');
    form.submit();
}

function insertBoardInfo() {
    let form = $('#boardForm');
    let url = '/board/qna/infoView/insert';
    $.post(url, form.serialize(), function (result) {
        if (alert(result['message'])) {
            goBoardList();
        }
    });
}

function updateBoardInfo() {
    let form = $('#boardForm');
    let url = '/board/qna/infoView/update';
    $.post(url, form.serialize(), function (result) {
        if (alert(result['message'])) {
            goBoardList();
        }
    });
}

function delBoardInfo() {
    let form = $('#boardForm');
    let url = '/board/qna/infoView/delete';
    $.post(url, form.serialize(), function (result) {
        if (alert(result['message'])) {
            goBoardList();
        }
    });
}
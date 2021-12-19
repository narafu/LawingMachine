
function goBoardList() {
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/board/list');
    form.attr('target', '');
    form.submit();
}

function goBoardForm() {
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/board/inputForm');
    form.attr('target', '');
    form.submit();
}

function goBoardInfo(brdMstrInfoSeq) {
    $('#brdMstrInfoSeq').val(brdMstrInfoSeq);
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/board/infoView');
    form.attr('target', '');
    form.submit();
}

function regBoardInfo() {
    let form = $('#boardForm');
    let url = '/board/infoView';
    $.post(url, form.serialize(), function (result) {
        if (alert(result['message'])) {
            goBoardList();
        }
    });
}
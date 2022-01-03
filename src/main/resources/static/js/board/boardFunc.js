
function goBoardList() {
    let form = $('#boardForm');
    form.attr('method', 'get');
    form.attr('action', '/board/free/list');
    form.attr('target', '');
    form.submit();
}

function goBoardForm() {
    let url = '/board/free/inputForm';
    let data = $('#boardForm').serialize();
    $.get(url, data, function (result) {
        $('#boardContent').html(result);
    })
}

function goBoardInfo(brdMstrInfoSeq) {
    $('#brdMstrInfoSeq').val(brdMstrInfoSeq);
    let url = '/board/free/infoView';
    let data = $('#boardForm').serialize();
    $.get(url, data, function (result) {
        $('#boardContent').html(result);
    })
}

function delBoardInfo() {
    let url = '/board/free/infoView/delete';
    let data = $('#boardForm').serialize();
    $.post(url, data, function (result) {
        goBoardList();
    });
}
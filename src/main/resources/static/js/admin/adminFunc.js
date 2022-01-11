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
    var header = $("meta[name='_csrf_header']").attr('content');
    var token = $("meta[name='_csrf']").attr('content');
    $.confirm({
        title: '',
        content: '삭제하시겠습니까?',
        buttons: {
            '삭제': function () {
                let url = '/admin/quiz/infoView/delete';
                let data = $('#boardForm').serialize();
                var request = $.ajax({
                    type: "POST",
                    url: url,
                    data: data,
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                });
                request.done(function (result) {
                    $.alert(result['message']);
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

    let userIdArr = [];
    userIdArr.push($('#userId').val());
    let url = '/admin/board/approval/infoView';
    let data = {'userIdArr': userIdArr};
    var header = $("meta[name='_csrf_header']").attr('content');
    var token = $("meta[name='_csrf']").attr('content');

    if (confirm("승인하시겠습니까?")) {
        var request = $.ajax({
            type: "POST",
            url: url,
            data: data,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
        });
        request.done(function (result) {
            alert(result['message']);
        });
    }
}

function multiApproval(obj) {
    if (!$('input[type=checkbox]:checked').length) {
        let url = '/modal/alert';
        let modalId = 'multiApproval';
        let modalText = '승인할 대상을 선택해주세요.';
        modal(url, modalId, modalText);
    } else {

    }
}
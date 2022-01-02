function updateUserInfo() {
    let url = '/mypage/myprofile';
    let param = $('#myprofileForm').serialize();
    $.post(url, param, function (result) {
        $('#myprofileForm').html(result);
    })
}

function getReviewNoteSubject(obj, subjectTypeCd) {
    $('#schSubjectTypeCd').val(subjectTypeCd);
    $(obj).closest('ul').find('.active').removeClass('active');
    $(obj).closest('ul').find('li a').css('color', 'black');
    $(obj).addClass('active');
    $(obj).find('a').css('color', 'white');
    selectQuizResultData();
}

function selectQuizResultData() {
    $.get('/mypage/reviewNote/data', $('#reviewNoteForm').serialize(), function (result) {
        $('#quizResultData').html(result);
    })
}

function quizResultInfoModal(obj, quizMstrInfoSeq) {

    let url = '/mypage/quizResultInfoModal';
    let modalId = 'quizResultConfirm';
    let modalText = '';
    let actBtnText = '이동';
    let quizNo = $(obj).find('.quizNo').text();

    let data = {
        'quizNo': quizNo,
        'quizMstrInfoSeq': quizMstrInfoSeq,
        'modalId': modalId,
        'modalText': modalText,
        'actBtnText': actBtnText
    };

    $.get(url, data).done(function (modalHtml) {
        $('#modalDiv').html(modalHtml);
        let modal = new bootstrap.Modal(document.getElementById(modalId));
        modal.show();
    })

}

function toggleCmntr() {
    $('#cmntrDiv').slideToggle();
    $('html,body').animate({ scrollTop: $('#cmntrDiv').offset().top }, 100);
}

function toggleDtlCmntr(obj) {
    $(obj).closest('.exDiv').find('.cmntrDtl').slideToggle();
}
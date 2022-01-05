function updateUserInfo() {
    // let examTicketHtml = examTicket.getHTML();
    // if(examTicketHtml == '<p><br></p>') {
    //     $('#examTicket').val('');
    // } else {
    //     $('#examTicket').val(examTicketHtml);
    // }
    let url = '/mypage/myprofile';
    let param = $('#myprofileForm').serialize();
    $.post(url, param, function (result) {
        alert(result['message']);
        // let url = '/modal/alert';
        // let modalId = 'quizNotYetAlert';
        // let modalText = result['message'];
        // modal(url, modalId, modalText);
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
function uploadImage() {

    if (!$("#imageInput").val()) {
        return;
    }

    var header = $("meta[name='_csrf_header']").attr('content');
    var token = $("meta[name='_csrf']").attr('content');

    let url = '/mypage/myprofile/uploadImage';
    let formData = new FormData();
    formData.append("examTicketFile", $("#imageInput")[0].files[0]); // input 추가

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: url,
        data: formData,
        processData: false,
        contentType: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (result) {
            let image = result['value'];
            console.log(image);
            $("#examTicket").attr("src", image);
        },
        error: function (result) {
            alert(result['message']);
        }
    });
}

function updateUserInfo() {

    // 이미지 업로드
    uploadImage();

    let url = '/mypage/myprofile';
    let data = $('#myprofileForm').serialize();
    $.post(url, data, function (result) {
        let url = '/modal/alert';
        let modalId = 'updateUserInfo';
        let modalText = result['message'];
        modal(url, modalId, modalText);
        $.get('/mypage/myprofile');
    })
}

function getReviewNoteSubject(obj, subjectTypeCd) {
    $('#schSubjectTypeCd').val(subjectTypeCd);
    $(obj).closest('ul').find('.active').removeClass('active');
    $(obj).closest('ul').find('li a').css('color', 'black');
    $(obj).addClass('active');
    $(obj).find('a').css('color', 'white');
    selectReviewNoteData();
}

function selectReviewNoteData() {
    $.get('/mypage/reviewNote/data', $('#reviewNoteForm').serialize(), function (result) {
        $('#reviewNoteData').html(result);
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
    $('html,body').animate({scrollTop: $('#cmntrDiv').offset().top}, 100);
}

function toggleDtlCmntr(obj) {
    $(obj).closest('.exDiv').find('.cmntrDtl').slideToggle();
}

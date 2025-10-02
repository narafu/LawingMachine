// 프로필 텍스트 정보 업데이트 (파일 업로드 성공 후 호출될 함수)
function updateProfileTextData() {
    $.post('/mypage/myprofile', $('#myprofileForm').serialize(), function (result) {
        common_modal_alert(result.message, function() {
            location.reload();
        });
    }).fail(function(xhr) {
        common_modal_alert('프로필 업데이트 중 오류가 발생했습니다: ' + xhr.responseText);
    });
}

// 이미지 업로드 함수 (콜백 함수를 인자로 받도록 수정)
function uploadImage(callback) {
    // 파일이 선택되지 않았으면 콜백을 바로 실행 (프로필 텍스트 정보만 업데이트)
    if (!$("#imageInput").val()) {
        if (callback) callback();
        return;
    }

    let url = '/mypage/myprofile/uploadImage';
    let formData = new FormData();
    formData.append("examTicketFile", $("#imageInput")[0].files[0]);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: url,
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            // 이미지 업로드 성공 시, 콜백 함수 실행 (프로필 텍스트 정보 업데이트)
            if (callback) callback();
        },
        error: function (result) {
            // 이미지 업로드 실패 시, 오류 메시지 표시 후 중단
            common_modal_alert('이미지 업로드 중 오류가 발생했습니다.');
        }
    });
}

// 최종 프로필 수정 함수 (전체 흐름 제어)
function updateUserInfo() {
    common_modal_confirm('저장하시겠습니까?', function() {
        // 항상 이미지 업로드를 먼저 시도하고,
        // 성공 콜백으로 텍스트 정보 업데이트를 실행
        uploadImage(updateProfileTextData);
	});
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
    $('#cmntrDiv').toggle();
//    $('html,body').animate({scrollTop: $('#cmntrDiv').offset().top}, 100);
}

function toggleDtlCmntr(obj) {
    $(obj).closest('.exDiv').find('.cmntrDtl').slideToggle();
}

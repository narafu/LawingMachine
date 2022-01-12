window.onload = function () {

    let duration = 60 * 30;
    let timer = $('#count-down-timer');
    let min = parseInt(duration / 60);
    let sec = parseInt(duration % 60);

    timer.text(`${paddedFormat(min)}:${paddedFormat(sec)}`);
    startCountDown(--duration, timer);
}

function startCountDown(duration, timer) {

    let secondsRemaining = duration;
    let min = 0;
    let sec = 0;

    let countInterval = setInterval(function () {

        min = parseInt(secondsRemaining / 60);
        sec = parseInt(secondsRemaining % 60);

        timer.text(`${paddedFormat(min)}:${paddedFormat(sec)}`);
        secondsRemaining = secondsRemaining - 1;

        if (secondsRemaining < 0) {
            clearInterval(countInterval);
            $('#count-down-timer').css('color', 'red');
        }

    }, 1000);
}

function paddedFormat(num) {
    return num < 10 ? "0" + num : num;
}

function goExamPage() {
    let form = $('#examMainForm');
    form.attr('method', 'get');
    form.attr('action', '/training/exam/quiz');
    form.attr('target', '');
    form.submit();
}

function getAjaxQuizMstrInfo(quizMstrInfoSeq) {

    $('#quizMstrInfoSeq').val(quizMstrInfoSeq);

    let url = '/training/exam/quiz/ajax';
    let data = $('#examMainForm').serialize();

    $.get(url, data, function (result) {
        $('#quizMstrInfoDiv').replaceWith(result);

        // 진행률 반영
        let prgrsData = $('#progress-bar').text().split('/');
        let prgrsLvl = prgrsData[0] / prgrsData[1] * 100;
        $("#progress-bar").css("width", prgrsLvl + '%');

    });

    // nav
    getQuizNavAjax(quizMstrInfoSeq);
}

function getQuizNavAjax(quizMstrInfoSeq) {

    $('#quizMstrInfoSeq').val(quizMstrInfoSeq);

    let url = '/training/exam/quiz/nav/ajax';
    let data = $('#examMainForm').serialize();

    $.get(url, data, function (result) {

        $('#quizAnswerNavListDiv').replaceWith(result);

        // active 스타일 반영
        let curSubjectTypeCd = $('#subjectTypeCd').val();
        $('#quizAnswerNavListDiv .quizNavTltDiv').each(function () {
            let navSubjectTypeCd = $(this).find('.subjectTypeCd').val();
            if (curSubjectTypeCd == navSubjectTypeCd) {
                $(this).removeClass('text-primary');
                $(this).addClass('bg-primary');
                $(this).addClass('text-light');
            }
            $('html,body').animate({ scrollTop: $('#examMainForm').offset().top }, 100);
        })
    });
}

function eraser(obj) {
    let exCntnt = $(obj).closest('.exDiv').find('.exCntnt');
    let eraseYn = $(obj).closest('.exDiv').find('.eraseYn');
    if (exCntnt.hasClass('text-decoration-line-through')) {
        exCntnt.removeClass('text-decoration-line-through');
        eraseYn.val('N');
    } else {
        exCntnt.addClass('text-decoration-line-through');
        eraseYn.val('Y');
    }
}

function chkAnswer(obj) {
    let quizDtlSrtNo = $(obj).siblings('.quizDtlSrtNo').val();
    if ($(obj).hasClass('list-group-item-dark')) {
        $(obj).removeClass('list-group-item-dark');
        $('#userAnswer').val('');
    } else {
        $('.list-group-item-dark').removeClass('list-group-item-dark');
        $(obj).addClass('list-group-item-dark');
        $('#userAnswer').val(quizDtlSrtNo);
    }
}

function goQuiz(obj) {

    let quizMstrInfoSeq = $('#quizMstrInfoSeq').val();
    let quizIndex = '';

    $('#quizAnswerNavListDiv .quizMstrInfoSeq').each(function (index) {
        if (quizMstrInfoSeq == $(this).val()) {
            quizIndex = index;
        }
    })

    if (obj == 'prev') {

        if (quizIndex) {
            quizIndex--;
            let prevQuizMstrInfoSeq = $('.quizMstrInfoSeq:eq(' + quizIndex + ')').val();
            quizAnsSave(prevQuizMstrInfoSeq, 'N', 'N');
        } else {
            // if (mobileYn) {
            // alert('첫 번째 문제입니다.');
            // } else {
            //     let url = '/quiz/modal/firstPageModal';
            //     $.ajax(url).done(function (modalHtml) {
            //         $('#modalDiv').html(modalHtml);
            //         let firstPageModal = new bootstrap.Modal(document.getElementById('firstPageModal'));
            //         firstPageModal.show();
            //     })
            // }
            let url = '/modal/alert';
            let modalId = 'fstQuizAlert';
            let modalText = '첫 번째 문제입니다.';
            modal(url, modalId, modalText);
        }
    }

    if (obj == 'next') {

        let lastQuizMstrInfoSeq = $('.quizMstrInfoSeq:last').val();

        if (quizMstrInfoSeq == lastQuizMstrInfoSeq) {
            // if (mobileYn) {
            quizAnsSave(quizMstrInfoSeq, 'N', 'Y');
            // } else {
            //     let url = '/quiz/modal/lastPageModal?quizMstrInfoSeq=' + quizMstrInfoSeq;
            //     $.ajax(url).done(function (modalHtml) {
            //         $('#modalDiv').html(modalHtml);
            //         let lastPageModal = new bootstrap.Modal(document.getElementById('lastPageModal'));
            //         lastPageModal.show();
            //     })
            // }
        } else {
            quizIndex++;
            let nextQuizMstrInfoSeq = $('.quizMstrInfoSeq:eq(' + quizIndex + ')').val();
            quizAnsSave(nextQuizMstrInfoSeq, 'N', 'N');
        }
    }
}

function quizAnsSave(quizMstrInfoSeq, modalYn, resultYn) {
    let url = '/training/exam/userAnswer';
    let param = $('#examMainForm').serialize();
    $.post(url, param, function (result) {
        if (result) {
            // if (modalYn == 'Y') {
            //     $('#modalDiv .modal').modal('hide');
            // }
            if (resultYn == 'Y') {
                getAjaxQuizMstrInfo(quizMstrInfoSeq);
                let url = '/modal/alert';
                let modalId = 'lstQuizAlert';
                let modalText = '마지막 문제입니다. <br> 모든 문제의 정답을 입력하면 [제출하기]가 활성화됩니다.';
                modal(url, modalId, modalText);
            } else {
                getAjaxQuizMstrInfo(quizMstrInfoSeq);
            }
        } else {
            let url = '/modal/alert';
            let modalId = 'fstQuizAlert';
            let modalText = '오류가 발생하였습니다.';
            modal(url, modalId, modalText);
        }
    });
}

function quizResultConfirm() {
    let url = '/modal/quizResultConfirm';
    let modalId = 'quizResultConfirm';
    let modalText = '시험을 제출하고 결과로 이동하시겠습니까?';
    let actBtnText = '이동';
    modal(url, modalId, modalText, actBtnText);
}

function moveResultPage() {
    let form = $('#examMainForm');
    $('#subjectTypeCd').val('');
    // form.attr('action', '/mypage/reviewNote');
    form.attr('action', '/mypage/quizResult'); // 임시
    form.attr('target', '');
    form.submit();
}

function quizNotYetAlert() {
    let url = '/modal/alert';
    let modalId = 'quizNotYetAlert';
    let modalText = '아직 풀지 않은 문제가 있습니다.';
    modal(url, modalId, modalText);
}
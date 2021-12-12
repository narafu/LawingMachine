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
        ;

    }, 1000);
}

function paddedFormat(num) {
    return num < 10 ? "0" + num : num;
}

function getAjaxQuizMstrInfo() {

    let url = '/training/exam/ajax/quizMstrInfo';
    let data = $('#hiddenForm').serialize();

    $.get(url, data, function (result) {

        $('#quizMstrInfoDiv').replaceWith(result);

        // active 스타일 반영
        let curSubjectTypeCd = $('#subjectTypeCd').val();
        $('#quizAnswerNavListDiv .quizNavTltDiv').each(function () {
            let navSubjectTypeCd = $(this).find('.subjectTypeCd').val()
            if (curSubjectTypeCd == navSubjectTypeCd) {
                $(this).removeClass('text-primary');
                $(this).addClass('bg-primary');
                $(this).addClass('text-light');
            }
        })

        // 진행률 반영
        let prgrsData = $('#progress-bar').text().split('/');
        let prgrsLvl = prgrsData[0] / prgrsData[1] * 100;
        $("#progress-bar").css("width", prgrsLvl + '%');
        ;

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
    let dtlInfoSrtNo = $(obj).siblings('.dtlInfoSrtNo').val();
    if ($(obj).hasClass('list-group-item-dark')) {
        $(obj).removeClass('list-group-item-dark');
        $('#userAnswer').val('');
    } else {
        $('.list-group-item-dark').removeClass('list-group-item-dark');
        $(obj).addClass('list-group-item-dark');
        $('#userAnswer').val(dtlInfoSrtNo);
    }
}

function goQuiz(obj) {

    let quizMstrInfoSeq = $('#quizMstrInfoSeq').val();
    let quizIndex = '';

    $('#sidebar a').each(function (index) {
        if (quizMstrInfoSeq == $(this).siblings('.quizMstrInfoSeq').val()) {
            quizIndex = index;
        }
    })

    if (obj == 'prev') {

        if (quizIndex) {
            quizIndex--;
            let prevQuizMstrInfoSeq = $('#sidebar a:eq(' + quizIndex + ')').siblings('.quizMstrInfoSeq').val();
            quizAnsSave(prevQuizMstrInfoSeq, 'N');
        } else {
            if (mobileYn) {
                alert('첫 번째 문제입니다!');
            } else {
                let url = '/quiz/modal/firstPageModal';
                $.ajax(url).done(function (modalHtml) {
                    $('#modalDiv').html(modalHtml);
                    let firstPageModal = new bootstrap.Modal(document.getElementById('firstPageModal'));
                    firstPageModal.show();
                })
            }
        }
    }

    if (obj == 'next') {

        let lastQuizMstrInfoSeq = $('#sidebar a:last').siblings('.quizMstrInfoSeq').val();

        if (quizMstrInfoSeq == lastQuizMstrInfoSeq) {
            if (mobileYn) {
                if (confirm('마지막 문제입니다.\n(풀지 않은 문제는 오답처리 됩니다.)\n결과로 이동하시겠습니?')) {
                    quizAnsSave(quizMstrInfoSeq, 'N', 'Y');
                }
            } else {
                let url = '/quiz/modal/lastPageModal?quizMstrInfoSeq=' + quizMstrInfoSeq;
                $.ajax(url).done(function (modalHtml) {
                    $('#modalDiv').html(modalHtml);
                    let lastPageModal = new bootstrap.Modal(document.getElementById('lastPageModal'));
                    lastPageModal.show();
                })
            }
        } else {
            quizIndex++;
            let nextQuizMstrInfoSeq = $('#sidebar a:eq(' + quizIndex + ')').siblings('.quizMstrInfoSeq').val();
            quizAnsSave(nextQuizMstrInfoSeq, 'N');
        }
    }
}
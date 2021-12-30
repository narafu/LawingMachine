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

function getAjaxQuizMstrInfo(quizMstrInfoSeq) {
    $('#quizMstrInfoSeq').val(quizMstrInfoSeq);

    let url = '/training/exam/quizMstrInfo';
    let data = $('#examMainForm').serialize();

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
            alert('첫 번째 문제입니다!');
            // } else {
            //     let url = '/quiz/modal/firstPageModal';
            //     $.ajax(url).done(function (modalHtml) {
            //         $('#modalDiv').html(modalHtml);
            //         let firstPageModal = new bootstrap.Modal(document.getElementById('firstPageModal'));
            //         firstPageModal.show();
            //     })
            // }
        }
    }

    if (obj == 'next') {

        let lastQuizMstrInfoSeq = $('.quizMstrInfoSeq:last').val();

        if (quizMstrInfoSeq == lastQuizMstrInfoSeq) {
            // if (mobileYn) {
            if (confirm('마지막 문제입니다.\n(풀지 않은 문제는 오답처리 됩니다.)\n결과로 이동하시겠습니?')) {
                quizAnsSave(quizMstrInfoSeq, 'N', 'Y');
            }
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
                moveResultPage();
            } else {
                getAjaxQuizMstrInfo(quizMstrInfoSeq);
            }
        } else {
            alert("오류가 발생하였습니다.");
        }
    });
}

function moveResultPage() {
    let form = $('#examMainForm');
    form.attr('action', '/mypage/reviewNote');
    form.attr('target', '');
    form.submit();
}

function getReviewNoteSubject(obj, subjectTypeCd) {
    $('#schSubjectTypeCd').val(subjectTypeCd);
    $(obj).closest('ul').find('.active').removeClass('active');
    $(obj).closest('ul').find('li a').css('color', 'black');
    $(obj).addClass('active');
    $(obj).find('a').css('color', 'white');
    loadQuizRatioChart();
}

function getReviewNoteQuizChk(obj) {

    // if($(obj).attr('id') == 'schAllChk') {
    //     $('#quizSchChkDiv').find('input[type=checkbox]:gt(0)').attr('checked', false);
    // } else {
    //     $('#quizSchChkDiv').find('input[type=checkbox]:eq(0)').attr('checked', false);
    // }
    //
    // if($('#quizSchChkDiv input[type=checkbox]:checked').length == 0) {
    //     $('#schAllChk').attr('checked', true);
    // }

    loadQuizRatioChart();
}

function loadQuizRatioChart() {

    $.get('/mypage/reviewNote/chart', $('#reviewNoteForm').serialize(), function (result) {

        let axisXList = new Array();
        let axisYList = result.axisYList;
        let dataList = result.dataList;

        let chartWidth = $('#chart-area').width();
        let chartHeight = 200 + (50 * axisYList.length);

        let maxQuizCnt = Math.max.apply(null, result.axisXList);
        let i = 1;
        while(i <= maxQuizCnt) {
            axisXList.push(i++);
        }

        chart.setData({
            categories: {
                x: axisXList,
                y: axisYList
            },
            series: dataList
        });

        chart.setOptions({
            chart: {width: chartWidth, height: chartHeight},
            xAxis: {title: '문제번호'},
            yAxis: {title: '과목'},
            tooltip: {formatter: (value) => `${value}%`},
            legend: {align: 'bottom'},
            series: {
                selectable: true, dataLabels: {visible: true}
            },
            theme
        });
    })
}
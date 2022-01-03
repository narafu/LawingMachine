
function getBillboardSubject(obj, subjectTypeCd) {
    $('#schSubjectTypeCd').val(subjectTypeCd);
    $(obj).closest('ul').find('.active').removeClass('active');
    $(obj).closest('ul').find('li a').css('color', 'black');
    $(obj).addClass('active');
    $(obj).find('a').css('color', 'white');
    // selectQuizResultData();
}

function goExamNoticePage() {
    let form = $('#indexForm');
    form.attr('method', 'get');
    form.attr('action', '/training/exam/notice');
    form.attr('target', '');
    form.submit();
}
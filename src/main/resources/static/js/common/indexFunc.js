
function goExamNoticePage() {
    let form = $('#indexForm');
    form.attr('method', 'get');
    form.attr('action', '/training/exam/notice');
    form.attr('target', '');
    form.submit();
}
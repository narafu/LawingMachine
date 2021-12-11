
function goExamPage() {
    let form = $('#indexForm');
    form.attr('method', 'get');
    form.attr('action', '/training/exam');
    form.attr('target', '');
    form.submit();
}
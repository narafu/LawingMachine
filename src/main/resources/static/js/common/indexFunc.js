function getBillboardSubject(obj, subjectTypeCd) {
    $(obj).closest('ul').find('.active').removeClass('active');
    $(obj).closest('ul').find('li a').css('color', 'black');
    $(obj).addClass('active');
    $(obj).find('a').css('color', 'white');

    $('#subjectTypeCd').val(subjectTypeCd);
    let url = '/index/billboard';
    let data = $('#indexForm').serialize();

    $.get(url, data, function (result) {
        $('#billboard').replaceWith(result);
    })
}

function goLoginPage() {
    let form = $('#indexForm');
    form.attr('method', 'get');
    form.attr('target', '');
    form.attr('action', '/login');
    form.submit();
}

function goExamNoticePage(membershipCd) {

    if (membershipCd == 10) {
        let url = '/modal/alert';
        let modalId = 'membershipCd';
        let modalText = '마이페이지에서 수험번호와 수험표 인증 후 이용할 수 있습니다.';
        modal(url, modalId, modalText);
        return;
    }

    let form = $('#indexForm');
    form.attr('method', 'get');
    form.attr('target', '');
    form.attr('action', '/training/exam/notice');
    form.submit();
}
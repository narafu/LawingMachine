
function modal(url, modalId, modalText, actBtnText, modalTitle, callback) {

    let data = {
        'modalId': modalId,
        'modalText': modalText,
        'actBtnText': actBtnText
    };

	let modal;
    $.get(url, data).done(function (modalHtml) {
        $('#modalDiv').html(modalHtml);
        modal = new bootstrap.Modal(document.getElementById(modalId));
        modal.show();
    })
    
   	$('#modalDiv').on('show.bs.modal', function () {
       $('#modalActBtn').on('click', function () {
			if(callback && $.isFunction(callback)) {
				callback();
		        modal.hide();
			}
		});
	});
}

function common_modal_alert(modalText, callback, actBtnText, modalTitle) {

	let url = '/modal/alert';
	let modalId = 'modalAlert';
	if(!actBtnText) { actBtnText = '확인' };

    let data = {
        'modalId': modalId,
        'modalText': modalText,
        'actBtnText': actBtnText
    };

	let modal;
    $.get(url, data).done(function (modalHtml) {
        $('#modalDiv').html(modalHtml);
        modal = new bootstrap.Modal(document.getElementById(modalId));
        modal.show();
    })
    
   	$('#modalDiv').on('show.bs.modal', function () {
       $('#modalAlertBtn').on('click', function () {
			if(callback && $.isFunction(callback)) {
				callback();
		        modal.hide();
			}
		});
	});
}

function common_modal_confirm(modalText, callback, actBtnText, clsBtnText, modalTitle) {

	let url = '/modal/confirm';
	let modalId = 'modalConfirm';
	if(!actBtnText) { actBtnText = '저장' };
	if(!clsBtnText) { clsBtnText = '닫기' };

    let data = {
        'modalId': modalId,
        'modalText': modalText,
        'actBtnText': actBtnText,
        'clsBtnText': clsBtnText
    };

	let modal;
    $.get(url, data).done(function (modalHtml) {
        $('#modalDiv').html(modalHtml);
        modal = new bootstrap.Modal(document.getElementById(modalId));
        modal.show();
    })
    
   	$('#modalDiv').on('show.bs.modal', function () {
       $('#modalConfrimBtn').on('click', function () {
			if(callback && $.isFunction(callback)) {
				callback();
		        modal.hide();
			}
		});
	});
}

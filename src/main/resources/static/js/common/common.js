
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

  var common = common || {};
  /**
   * 공통 AJAX
   * @type {{get: common.ajax.get, post: common.ajax.post}}
   */
  common.ajax = {
      get: function (url, param, callback) {
          var async = arguments[3];
          if (async == null) async = true;

          $.ajax({
              url: url,
              type: 'GET',
              async: async,
              data: param,
              cache: false,
//              dataType: 'json',
              success: function (data) {
                  if (callback) callback(data);
              },
              error: function (data) {
                  if (data.responseText) {
                      common.modal.alert(JSON.parse(data.responseText).errorMessage);
                  } else {
                      common.modal.alert('서버에 요청중 문제가 발생했습니다.<br />관리자에게 문의하여 주십시오.');
                  }
              }
          });
      },
      post: function (url, param, callback) {
          var async = arguments[3];
          if (async == null) async = true;

          var options = {
              url: url,
              type: 'POST',
              async: async,
              data: param,
              cache: false,
//              dataType: 'json',
              headers: { 'AJAX': true },
              success: function (data) {
                  if (callback) callback(data);
              },
              error: function (data) {
            	  if (data.responseText) {
                      var objError = JSON.parse(data.responseText);
                      if (objError.errors && objError.errors.length > 0) {
                          gfnShowErrors(objError.screenId, objError.errors);
                      } else {
                          common.modal.alert(objError.errorMessage);
                      }
                  } else {
                      common.modal.alert('서버에 요청중 문제가 발생했습니다.<br />관리자에게 문의하여 주십시오.');
                  }
              }
          };

      $("#loadingDiv").hide();
          if (async) {
              // 비동기
              $.ajax(options);
          } else {
              // 동기
              setTimeout(function () {
                  $.ajax(options);
              }, 1000);
          }
      },
      
      file: function (id, sheet, start, callback) {
	
    	  var formFile; 
    	  if( document.getElementById(id).files.length < 1){
    		  formFile = cloneFiles[0];
    	  }else{
    		  formFile = document.getElementById(id).files[0];
    	  }
    	  
          var formData = new FormData();
          formData.append('upload', formFile);

          var options = {
              url: '/com/util/excel/'+sheet+'/' + start,
              data: formData,
              dataType: 'text',
              processData: false,
              contentType: false,
              type: 'POST',
              headers: { 'AJAX': true },
              success: function (data) {
                  if (callback) callback(data);
              },
              error: function (data) {
                  if (data.responseText) {
                      var objError = JSON.parse(data.responseText);
                      if (objError.errors && objError.errors.length > 0) {
                          gfnShowErrors(objError.screenId, objError.errors);
                      } else {
                          common.modal.alert(objError.errorMessage);
                      }
                  } else {
                      common.modal.alert('서버에 요청중 문제가 발생했습니다.<br />관리자에게 문의하여 주십시오.');
                  }
              }
          };

          $('#loading').fadeIn();
          $.ajax(options);
      }
  };

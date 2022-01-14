
function modal(url, modalId, modalText, actBtnText, modalTitle) {

    let data = {
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


  /**
   * 공통 모달
   * @type {{alert: common.modal.alert, confirm: common.modal.confirm, dialog: common.modal.dialog, close: common.modal.close}}
   */
  common.modal = {
      alert: function (message, callback) {

          var options = {
              title: '알림'
              , content: message
              , closeIcon: false
              , buttons: {
                  ok: {
                      text: '확인'
                      , btnClass:'button btn_pop_basic'
                  }
              }
          };

          if (callback && $.isFunction(callback)) {
              options.buttons.ok.action = callback;
              $.confirm(options);
          } else {
              $.alert(options);
          }
      },
      confirm: function (message, callback) {

          var options = {
              title: '확인'
              , content: message
              , buttons: {
                  ok: {
                      text: '예'
                      , btnClass:'button btn_pop_confirm'
                  },
		          close: {
		              text: '아니요'
		              , btnClass:'button btn_pop_basic'
		          }
              }
          };

          if (callback && $.isFunction(callback)) {
              options.buttons.ok.action = callback;
          }

          if (arguments[2] && $.isFunction(arguments[2])) {
              options.buttons.close.action = arguments[2];
          }

          $.confirm(options);
      },
      dialog: function (title, content, buttons, width) {

      	//width가 지정되어 있지 않을 경우 기본사이즈 고정
      	if(width == '' || width == undefined){
      		width = '720px';
      	}

          var options = {
              title: title
              , content: content
              , boxWidth: width
              , buttons: {
                  close: {
                      text: '닫기'
                      , btnClass:'button btn_pop_basic'
                  }
              }
          };

          if (buttons) options.buttons = buttons;

          options.onContentReady = function () {
              if (content.indexOf('iframe') !== -1) {
                  this.$el.addClass('pop_margin_control');
                  options.autoResize = true;
              }
              $(window).unbind('resize.' + this._id);
              $('.jconfirm').css('z-index', '10000');
              $('.jconfirm-holder').css('overflow', 'auto');
          };

          return $.confirm(options);
      },
      close: function () {
          jconfirm.instances.pop().close();
      }
  };

  /**
   * 첨부파일
   * @type {{download: common.attchfile.download}}
   */
  common.attchfile = {
      defaults: {
          baseUrl: '/com/fileDownload'
      },
      download: function (fileDstgNo, attFileSq) {
          var _this = this;
          common.attchfile.defaults.baseUrl = '/com/fileDownload/';
          $.fileDownload(_this.defaults.baseUrl + fileDstgNo +"/" + attFileSq +'.do', {
              httpMethod: 'GET'
          }).done(function () {
          }).fail(function () {
              common.modal.alert('파일을 다운로드 할 수 없습니다.');
          });
      },
      fielddownload: function (fileDstgNo, fieldId) {
          var _this = this;
          common.attchfile.defaults.baseUrl = '/com/fieldFileDownload/';
          $.fileDownload(_this.defaults.baseUrl + fileDstgNo +"/" + fieldId + '.do', {
              httpMethod: 'GET'
          }).done(function () {
          }).fail(function () {
              common.modal.alert('파일을 다운로드 할 수 없습니다.');
          });
      }
  };

  common.popup = {
		  comCode: function(){
				// 공통코드 찾기 팝업 호출
				var topPx = (window.screen.height / 2) - (800 / 2);
				var leftPx = (window.screen.width / 2) - (650 / 2);
				var popupOption = "top="+topPx+",left="+leftPx+",width=570,height=620,status=yes,toolbar=no,menubar=no,directories=no,scrollbars=no,resizable=no";
				var popupWin = window.open("/com/popup/codePopupList.do", "CODE_SEARCH_POPUP", popupOption);
				popupWin.window.focus();
		  },

		  comCategory: function(){
				// 카테고리 분류 찾기 팝업 호출
				var topPx = (window.screen.height / 2) - (800 / 2);
				var leftPx = (window.screen.width / 2) - (650 / 2);
				var popupOption = "top="+topPx+",left="+leftPx+",width=570,height=605,status=yes,toolbar=no,menubar=no,directories=no,scrollbars=no,resizable=no";
				var popupWin = window.open("/com/popup/categoryPopupList.do", "CODE_SEARCH_POPUP", popupOption);
				popupWin.window.focus();
		  }
  }

  /**
   * 유효성 검증 메시지 출력
   * @param param
   */
  function gfnShowErrors(screenId, errors) {
      var form = $('#' + screenId);

      form.find('label[class="error"]').remove();
      form.find('.error').removeClass('error');

      $.each(errors, function(i, error) {
          var field = error['field'];
          var element = form.find('[name="'+field+'"]');

          if (element && element.length > 0) {
              element.addClass('error');
              element.after('<label class="error" for="' + field + '">' + error.defaultMessage + '</label>');
          }
      });
  }

  /**
   * 에러 메시지 표시
   * @param obj
   * @param message
   */
  function gfnShowError(obj, message) {
      if (obj && obj.length > 0) {
          obj.addClass('error');
          obj.after('<label class="error" for="' + obj.attr('id') + '">' + message + '</label>');
      }
  }

  /**
   * 에러테그 삭제
   * @param obj
   */
  function gfnRemoveError(obj) {
      obj.find('label[class="error"]').remove();
      obj.find('.error').removeClass('error');
  }

  /**
   * uuid 생성
   * @returns {string}
   */
  function uuid() {
      function s4() {
          return ((1 + Math.random()) * 0x10000 | 0).toString(16).substring(1);
      }
      return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
  }


  $(document).ajaxComplete(function(jqXHR, status) {
      $('#loading').fadeOut();
  });

  $(document).ajaxError(function(jqXHR, status) {
      $('#loading').fadeOut();
  });

  $(document).on("keyup", "input:text[data-numberonly]", function(e) {
		$(this).val($(this).val().replace(/[^0-9:\.]/gi,""));
		$(this).val(Number($(this).val().replace(/,/g, '')).toLocaleString());
  });

  $(document).on("keyup", "input:password[data-numberonly]", function(e) {
		$(this).val($(this).val().replace(/[^0-9:\.]/gi,""));
  });

  $(document).on("keyup", "input:text[data-AN]", function(e) {
		$(this).val($(this).val().replace(/[^0-9A-Za-z_ /.]/gi,""));
  });

  $(document).on("keyup", "input:text[data-integeronly]", function(e) {
		$(this).val($(this).val().replace(/[^0-9]/gi,""));
});
  
  /**
   * 숫자만 입력
   * @returns
   */
  function onlyNumber() {
  	if(event.keyCode == 8 || event.keyCode == 9 			// 8 : Backspace, 9 : Tab
  		|| (event.keyCode >= 48 && event.keyCode <= 57) 	// 48~57 : 0~9
  		|| (event.keyCode >= 96 && event.keyCode <= 105)	// 96~105 : Numpad 0~9
  		|| event.keyCode == 35 || event.keyCode == 36		// 35 : End, 36 : Home
  		|| event.keyCode == 37 || event.keyCode == 39		// 37 : LeftArrow, 39 : RightArrow
  		|| event.keyCode == 46) {							// 46 : Delete
  		return;
  	} else {
  		return false;
  	}
  }

  /**
   * 입력제한 함수(특수문자 제외한 나머지 문자)
   */
  function checkNumber() {
  	var objEv = event.srcElement;
  	var num ="{}[]()<>?_|~`!@#$%^&*+\"'\\/ ";    //입력을 막을 특수문자 기재.

  	for (var i=0;i<objEv.value.length;i++) {
  		if(-1 != num.indexOf(objEv.value.charAt(i))) {
  			parent.common.modal.alert("특수문자는 입력하실 수 없습니다.");
  			objEv.value = objEv.value.substring(0, objEv.value.length-1);
  		}
  	}
  }

  /**
   * 주소복사
   * @param val
   * @returns
   */
  function copyToClipboard(val) {
  	document.getElementById('clipboard').value = val;
  	document.getElementById('clipboard').select();
  	document.execCommand('copy');
  	alert('주소가 복사되었습니다.');
  }

  /**
   * 바이트 체크
   * @param field
   * @param limitLength
   * @returns
   */
  function byteCheck(field, limitLength) {
  	var tmpStr;
  	var temp = 0;
  	var onechar;
  	var tcount;
  	tcount = 0;
  	tmpStr = new String($(field).val());
  	temp = tmpStr.length;
  	for (var k = 0; k < temp; k++) {
  		onechar = tmpStr.charAt(k);
  		if (escape(onechar).length > 4) {
  			tcount += 2;
  		} else {
  			tcount++;
  		}
  		if (tcount > limitLength) {
  			netsCheck(field, limitLength);
  			field.focus();
  			return false;
  		}
  	}
  	return true;
  }

  /**
   * 바이트 수 초과할 경우 글자제거 및 alert
   * @param field
   * @param limitLength
   * @returns
   */
  function netsCheck(field, limitLength) {
  	var tmpStr;
  	var temp = 0;
  	var onechar;
  	var tcount;
  	tcount = 0;
  	tmpStr = new String($(field).val());
  	temp = tmpStr.length;
  	for (k = 0; k < temp; k++) {
  		onechar = tmpStr.charAt(k);
  		if (escape(onechar).length > 4) {
  			tcount += 2;
  		} else {
  			tcount++;
  		}

  		if (tcount > limitLength) {
  			tmpStr = tmpStr.substring(0, k);
  			common.modal.alert($(field).closest('tr').find('th').text() + "의 입력길이는 한글 " + Number(limitLength / 2) + "자, 영문/숫자 " + limitLength + "자 입니다.");
  			break;
  		}
  	}

  	//마지막에 엔터가 들어가면 무한 반복및 용량초과 되는것 방지용
  	/*for (j = k; j > 40; j--) {
  		onechar = tmpStr.charAt(j - 1);
  		if (onechar == "\r" || onechar == "\n") {
  			tcount--;
  		} else {
  			tmpStr = tmpStr.substring(0, j);
  			break;
  		}
  	}*/

    // 초과된 길이를 자른 후 해당 필드에 적용.
  	$(field).val(tmpStr);
  }

  /**
   * 설 명 : 도로명주소 조회 창을 띄운다.
   * 리턴값 ::
   * form		: 우편번호, 주소값을 리턴받을 form name
   * zipNo	: 우편번호 객체명
   * addr		: 도로명주소 객체명
   * addr2	: 지번주소 객체명
   */
  function openPostCode(form, zipNo, addr, addr2) {
  	var param = "?formName=";
  	if (form != undefined) {
  		param += form;
  	}
  	if (zipNo != undefined) {
  		param += "&zipNo=" + zipNo;
  	}
  	if (addr != undefined) {
  		param += "&addr=" + addr;
  	}
  	if (addr2 != undefined) {
  		param += "&addr2=" + addr2;
  	}

  	var buttons = {
          close: {
              text: '닫기'
              , btnClass: 'btn-secondary'
          }
      }; /**/
  	common.modal.dialog('주소검색', 'url:/u000/Address.do'+param,buttons);
  }

  /**
   * 직원 자동검색
   */
  function autoSearch(obj, ulId){
  	var keyCode = event.keyCode;
  	if(keyCode == 13 && obj.value){
  		userSearch(obj, ulId);
  		obj.value = "";
  		$("#"+ulId).html("");
  	}
  }

  /**
   * 직원 검색
   * @returns
   */
  function userSearch(obj, ulId){
	var srchDeptId = "";
	if($(obj).parent().find(".searchDept")){
		srchDeptId = $(obj).parent().find(".searchDept").val();
	}

  	common.ajax.post('/u000/A03L2.do', {userName : obj.value, deptId : srchDeptId}, function(result){
  		var resultList = result.resultMessage;
  		if(result.resultCode == "SUCCESS"){
  			if(resultList.length == 1){
  				$(obj).parent().find(".uname").val(resultList[0].userName);
  				$(obj).parent().find(".uid").val(resultList[0].userId);
  				if($(obj).parent().find(".ugradNm").length > 0){
  					$(obj).parent().find(".ugradNm").val(resultList[0].gradeName);
  				}
  			}else if(resultList.length > 1){
  				$(resultList).each(function(index, item){
  					$("#"+ulId).append("<li><a href=\"javascript:userChk('"+item.userId+"','"+item.userName+"','"+ulId+"','"+item.gradeName+"')\">" + item.userName + "("+item.deptName+")</a></li>");
  				});
  				$("#"+ulId).css("display","block");
  			}
  		}
  	});
  }

  /**
   * 수신자 선택 이벤트
   * @param userId
   * @param userName
   * @returns
   */
  function userChk(userId, userName, ulId, gradeName){
  	$("#"+ulId).parent().find(".uname").val(userName);
  	$("#"+ulId).parent().find(".uid").val(userId);
  	if($("#"+ulId).parent().find(".ugradNm").length > 0){
  		$("#"+ulId).parent().find(".ugradNm").val(gradeName);
	}
  	$("#"+ulId).html("");
  	$("#"+ulId).css("display","none");
  }

  /**
   * 파라미터 이름으로 파라미터 값 가져오기
   * @param name
   * @returns
   */
  function getParameterByName(name) {
	  name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	  var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex.exec(location.search);
	  return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
  }

  function paging(obj, linkUrl, linkScript){
	  $("#"+obj).html = '<bizTag:Paginate items="${paging}" linkUrl="' + linkUrl + '" linkScript="' + linkScript + '"/>';
  }


  /**
   *  URL Parameter 전부 가져오기
   *  2021.02.24
   **/
  $.getUrlVars = function(){
      var vars = [], hash;
      var urlIndex = window.location.href.indexOf('?');
      if(urlIndex > 0){
  	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
  	    for(var i = 0; i < hashes.length; i++) {
  	        hash = hashes[i].split('=');
  	        if($.trim(hash[0]).length > 0){
  		        vars.push(hash[0]);
  		        vars[hash[0]] = hash[1];
  	        }
  	    }
      }
      return vars;
  };

  /**
   *  URL Parameter 하나씩 가져오기
   *  2021.02.24
   **/
  $.getUrlVar = function(name) {
  	var val = $.getUrlVars()[name];
  	if(typeof val === 'undefined')
  		val = null;
      return val;
  };

  /**
   *  검색조건 유지 returnUrl 만들기
   *  2021.02.24
   **/
  $.getAddParamUrl = function(basicUrl) {
  	var urlVars = $.getUrlVars();

  	for ( var i = 0 ; i < urlVars.length ; i++ ) {
  		if ( null != $.getUrlVar(i) ) {
  			if  ( basicUrl.indexOf("?" + urlVars[i] + "=") == -1 && basicUrl.indexOf("&" + urlVars[i] + "=") == -1 )  {
  				if ( basicUrl.indexOf("?") == -1 ) {
  					basicUrl += "?" + urlVars[i] + "=" + $.getUrlVar(urlVars[i]);
  				} else {
  					basicUrl += "&" + urlVars[i] + "=" + $.getUrlVar(urlVars[i]);
  				}
  			}
  		}
  	}

  	return basicUrl;
  };

  /**
   *  deleteParam 제외 후 검색조건 유지 returnUrl 만들기
   *  2021.02.24
   **/
  $.getRemoveParamUrl = function(basicUrl, deleteParamArray) {
  	var deleteParam = deleteParamArray.split(",");
  	var urlVars = $.getUrlVars();
  	var useAt = true;

  	for ( var i = 0 ; i < urlVars.length ; i++ ) {
  		if ( null != $.getUrlVar(i) ) {
  			for  ( var j = 0 ; j < deleteParam.length ; j++ )  {
  				if  ( deleteParam[j] == urlVars[i] )  {
  					useAt = false;
  					break;
  				}  else  {
  					useAt = true;
  				}
  			}

  			if  ( useAt )  {
  				if  ( basicUrl.indexOf("?" + urlVars[i] + "=") == -1 && basicUrl.indexOf("&" + urlVars[i] + "=") == -1 )  {
  					if ( basicUrl.indexOf("?") == -1 ) {
  						basicUrl += "?" + urlVars[i] + "=" + $.getUrlVar(urlVars[i]);
  					} else {
  						basicUrl += "&" + urlVars[i] + "=" + $.getUrlVar(urlVars[i]);
  					}
  				}
  			}
  		}
  	}

  	return basicUrl;
  };


  function layer_popup(el){

      var $el = $(el);        //레이어의 id를 $el 변수에 저장
      var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

      isDim ? $('.dim-layer').fadeIn() : $el.fadeIn(); $el.draggable();


      var $elWidth = ~~($el.outerWidth()),
          $elHeight = ~~($el.outerHeight()),
          docWidth = $(document).width(),
          docHeight = $(document).height();

      // 화면의 중앙에 레이어를 띄운다.
      if ($elHeight < docHeight || $elWidth < docWidth) {
          $el.css({
              marginTop: -$elHeight /2,
              marginLeft: -$elWidth/2
          })
      } else {
          $el.css({top: 0, left: 0});
      }

      $el.find('#btn_close').click(function(){
          isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
          return false;
      });

      $('.layer .dimBg').click(function(){
          $('.dim-layer').fadeOut();
          return false;
      });

  }
  
  function setCookie(name, value, expiredays){
	  var todayDate = new Date();
	   todayDate.setDate (todayDate.getDate() + expiredays);
	   document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
	 }


  function setDatepicker(option){
	  var defaultOption = {
			  dateFormat: 'yy-mm-dd',
			  prevText: '이전 달',
			  nextText: '다음 달',
			  monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			  monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			  dayNames: ['일', '월', '화', '수', '목', '금', '토'],
			  dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
			  dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
			  showMonthAfterYear: true,
			  changeMonth: true, //월변경가능
			  changeYear: true, //년변경가능
			  minDate: '',
			  //maxDate: '+800d',
			  yearSuffix: ''
	  }

	  if(option != null && option != ''){
		  defaultOption = option;
	  }

	  //월달력 팝업
	  $.datepicker.setDefaults(defaultOption);
  }
  
  function currencyFormat(amount){
  	return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g,',');
  }
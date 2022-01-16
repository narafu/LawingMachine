$(function() {
	let url = '/mypage/quizResult/data';
	let data = $('#quizResultForm').serialize();

	$.get(url, data).done(function(result) {
		setQuizResultMsg(result);
		if (result['RESULT_CNT']) {
			calTblTotal($('#calTblTotal'));
		}
	})
})

// 진단결과
function setQuizResultMsg(result) {

	$('.nickname').text(result['NICKNAME']);
	$('#memberTotalCnt').text(result['memberTotalCnt']);
	$('#rank').text(result['RANK']);
	$('#regDt').text(result['REG_DT']);

	$('#subjectCd_70').val(result['subjectCd_70']);
	$('#subjectCd_80').val(result['subjectCd_80']);
	$('#subjectCd_90').val(result['subjectCd_90']);

	// 점수계산
	let cut10 = 895.85;
	let cut9 = 900.29;
	let cut8 = 905.55;
	let avg = 187.5 + 455;
	let myPoint = result['RESULT_CNT'] * 2.5;
	let myTotalPoint = avg + myPoint

	// 진단결과 예측
	if (result['RESULT_CNT']) {
		$('.myTotalPoint').text(myTotalPoint);
		$('.quizResultY').show();
		$('.quizResultN').hide();
	} else {
		$('.quizResultY').hide();
		$('.quizResultN').show();
	}

	let count = 0;
	if (myTotalPoint < cut10) { $('#result10th').text("불합격"); }
	else { $('#result10th').text("합격"); count++; }

	if (myTotalPoint < cut9) { $('#result9th').text("불합격"); }
	else { $('#result9th').text("합격"); count++; }

	if (myTotalPoint < cut8) { $('#result8th').text("불합격"); }
	else { $('#result8th').text("합격"); count++; }

	if (count >= 3) { $('#resultMsg').text('높습니다.'); }
	else if (count == 2) { $('#resultMsg').text('다소 높습니다.'); }
	else if (count == 1) { $('#resultMsg').text('다소 낮습니다.'); }
	else if (count == 0) { $('#resultMsg').text('낮습니다.'); }
}

function calTblTotal(obj) {

	// 각 총점
	let total = 0;
	$(obj).find('input').each(function(index, item) {
		let temp = parseFloat($(item).val());
		if (isNaN(temp)) { temp = 0 }
		total += temp;
	})
	$(obj).find('.total').text(total);
	$(obj).find('.totalPoint').text(total * 2.5);

	// 4. 합격 기대가능성 내 점수
	let myTotal = 0;
	$(obj).closest('#happCalculator').find('input').each(function(index, item) {
		let temp = parseFloat($(item).val());
		if (isNaN(temp)) { temp = 0 }
		if (index < 3) { temp = temp * 2.5 }
		myTotal += temp;
	})
	$(obj).closest('#happCalculator').find('.myTotalPoint').text(myTotal);

	// 4. 합격 기대가능성 합격여부 (1~10회)
	let count = 0;
	$('#happCalculator .myTotalPoint').not(':first').each(function(index, item) {
		let selectedTr = $(item).closest('tr');
		let mypoint = parseFloat($(item).text());
		let examCut = parseFloat(selectedTr.find('.examCut').text());

		if (mypoint - examCut > 0) {
			selectedTr.find('.myPassYn').text('합격');
			selectedTr.find('.myPassYn').css('color', 'blue');
			count++;
		} else {
			selectedTr.find('.myPassYn').text('불합격');
			selectedTr.find('.myPassYn').css('color', 'red');
		}
	})

	// 4. 합격 기대가능성 합격여부 - 11회 (종합)
	if (count == 10) { $('#myPassYn').text('높음'); $('#myPassYn').css('color', 'blue'); }
	else if (count == 9) { $('#myPassYn').text('다소높음'); $('#myPassYn').css('color', 'blue'); }
	else if (count == 8) { $('#myPassYn').text('보통'); $('#myPassYn').css('color', 'black'); }
	else if (count == 7) { $('#myPassYn').text('다소낮음'); $('#myPassYn').css('color', 'red'); }
	else { $('#myPassYn').text('낮음'); $('#myPassYn').css('color', 'red'); }
}

// 겍컷 계산기
$('.calculator input').on('keyup', function() {
	let result = parseFloat($('#cal-start').val());
	$('.calculator input').not(':first').not(':last').each(function(index, item) {
		result = result - $(item).val();
	})
	$('#cal-result').val(Math.ceil(result / 2.5));
})

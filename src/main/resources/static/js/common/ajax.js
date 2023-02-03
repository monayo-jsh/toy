const HTTP_METHOD = {
  POST: "POST",
  GET: "GET"
}

const HTTP_CONTENT_TYPE = {
  APPLICATION_JSON: 'application/json',
  APPLICATION_FORM: 'application/x-www-form-urlencoded'
}

const defaultOptions = {
  async: false, //동기 호출
  contentType: HTTP_CONTENT_TYPE.APPLICATION_JSON,
  dataType: 'json', //서버 데이터 반환 타입
  callback: ajaxCallBackSuccess
}

function requestAjax(method, url, options, data) {
  options = $.extend({}, defaultOptions, options);

  $.ajax({
    async: options.async,
    url : url,
    type : method,
    contentType: options.contentType,
    data : data || '',
    dataType: options.dataType,
    beforeSend : function(xmlHttpRequest) {
      xmlHttpRequest.setRequestHeader('AJAX', 'true');
    },
    error : ajaxCallBackError,
    success : options.callback
  });
}

function ajaxCallBackSuccess(res) {
  console.log(res);
  alert("처리되었습니다.");
}

function ajaxCallBackError(res){
  console.log(res);
  if (res.responseText) {
    alert(res.responseText);
    return;
  }

  alert("status:{0}\nmessage:{1}".format(res.status, res.statusText));
}
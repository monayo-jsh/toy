const HTTP_METHOD = {
  POST: "POST",
  GET: "GET"
}

const defaultOptions = {
  async: false, //동기 호출
  contentType: 'application/json',
  callback: ajaxCallBackSuccess
}

function requestAjax(method, url, options, data) {
  options = $.extend({}, defaultOptions, options);

  $.ajax({
    type : method,
    url : url,
    async: options.async,
    contentType: options.contentType,
    data : data || '',
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
    //return;
  }

  alert("status:{0}\nmessage:{1}".format(res.status, res.statusText));
}
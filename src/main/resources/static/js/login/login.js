function login(loginForm) {
  let $loginForm = $(loginForm);

  let formData = $loginForm.serialize();

  requestAjax(HTTP_METHOD.POST, "/api/user/verify", {contentType: HTTP_CONTENT_TYPE.APPLICATION_FORM, callback: callbackLogin}, formData);
}

function signup(signupForm) {
  let $signupForm = $(signupForm);

  let data = convJSONObject($signupForm.serializeArray());
  data["subscription_type"] = "WEB";

  requestAjax(HTTP_METHOD.POST, '/api/user', {callback: callbackSignup}, JSON.stringify(data));
}

function callbackLogin(res) {
  window.location = res.result.start;
}

function callbackSignup(res) {
  alert('회원가입 되었습니다.');
}
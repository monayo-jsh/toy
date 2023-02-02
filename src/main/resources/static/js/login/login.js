function login(loginForm) {
  let $loginForm = $(loginForm);

  let data = $loginForm.serializeArray();
}

function signup(signupForm) {
  let $signupForm = $(signupForm);

  let data = convFormValue($signupForm.serializeArray());
  data["subscription_type"] = "WEB";

  requestAjax(HTTP_METHOD.POST, '/user', {callback: callbackSignup}, JSON.stringify(data));
}

function callbackSignup(res) {
  alert('회원가입 되었습니다.');
}
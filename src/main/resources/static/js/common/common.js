String.prototype.format = function() {
  let formatted = this;
  for( let arg in arguments ) {
    formatted = formatted.replace("{" + arg + "}", arguments[arg]);
  }
  return formatted;
}

function convJSONObject(dataArray) {
  let jsonObject = {};

  for (let data of dataArray) {
    jsonObject[data.name] = data.value;
  }

  return jsonObject;
}

function convFormData(dataArray) {
  let formData = new FormData();

  for (let data of dataArray) {
    formData.append(data.name, data.value);
  }

  return formData;
}
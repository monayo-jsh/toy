String.prototype.format = function() {
  let formatted = this;
  for( let arg in arguments ) {
    formatted = formatted.replace("{" + arg + "}", arguments[arg]);
  }
  return formatted;
}

function convFormValue(dataArray) {
  let result = {};

  for (let data of dataArray) {
    result[data.name] = data.value;
  }

  return result;
}
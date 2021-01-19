exports.fromCelsius = function () {
  let val = parseFloat(document.getElementById("celsius").value);
  document.getElementById("fahrenheit").value = ((val * 9) / 5 + 32).toFixed(2);
  document.getElementById("kelvin").value = (val + 273.15).toFixed(2);
};

exports.fromFahrenheit = function () {
  let val = parseFloat(document.getElementById("fahrenheit").value);
  document.getElementById("celsius").value = (((val - 32) * 5) / 9).toFixed(2);
  document.getElementById("kelvin").value = (
    ((val - 32) * 5) / 9 + 273.15
  ).toFixed(2);
};

exports.fromKelvin = function () {
  let val = parseFloat(document.getElementById("kelvin").value);
  document.getElementById("celsius").value = (val - 273.15).toFixed(2);
  document.getElementById("fahrenheit").value = (
    ((val - 273.15) * 9) / 5 + 32
  ).toFixed(2);
};

const temp = require("./temp.js");
const distance = require("./distance.js");

window.onload = function () {
  document
    .getElementById("fahrenheit")
    .addEventListener("change", temp.fromFahrenheit);
  document
    .getElementById("fahrenheit")
    .addEventListener("input", temp.fromFahrenheit);
  document
    .getElementById("celsius")
    .addEventListener("change", temp.fromCelsius);
  document
    .getElementById("celsius")
    .addEventListener("input", temp.fromCelsius);
  document
    .getElementById("kelvin")
    .addEventListener("change", temp.fromKelvin);
  document
    .getElementById("kelvin")
    .addEventListener("input", temp.fromKelvin);
  document
    .getElementById("miles")
    .addEventListener("change", distance.fromMiles);
  document
    .getElementById("miles")
    .addEventListener("input", distance.fromMiles);
  document
    .getElementById("kilometers")
    .addEventListener("change", distance.fromKilometers);
  document
    .getElementById("kilometers")
    .addEventListener("input", distance.fromKilometers);
};

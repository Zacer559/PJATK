exports.fromKilometers = function () {
  let val = parseFloat(document.getElementById("kilometers").value);
  document.getElementById("miles").value = (val * 0.62137).toFixed(2);
};

exports.fromMiles = function () {
  let val = parseFloat(document.getElementById("miles").value);
  document.getElementById("kilometers").value = (val / 0.62137).toFixed(2);
};

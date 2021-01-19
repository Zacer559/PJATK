!(function (e) {
  var t = {};
  function n(o) {
    if (t[o]) return t[o].exports;
    var l = (t[o] = { i: o, l: !1, exports: {} });
    return e[o].call(l.exports, l, l.exports, n), (l.l = !0), l.exports;
  }
  (n.m = e),
    (n.c = t),
    (n.d = function (e, t, o) {
      n.o(e, t) || Object.defineProperty(e, t, { enumerable: !0, get: o });
    }),
    (n.r = function (e) {
      "undefined" != typeof Symbol &&
        Symbol.toStringTag &&
        Object.defineProperty(e, Symbol.toStringTag, { value: "Module" }),
        Object.defineProperty(e, "__esModule", { value: !0 });
    }),
    (n.t = function (e, t) {
      if ((1 & t && (e = n(e)), 8 & t)) return e;
      if (4 & t && "object" == typeof e && e && e.__esModule) return e;
      var o = Object.create(null);
      if (
        (n.r(o),
        Object.defineProperty(o, "default", { enumerable: !0, value: e }),
        2 & t && "string" != typeof e)
      )
        for (var l in e)
          n.d(
            o,
            l,
            function (t) {
              return e[t];
            }.bind(null, l)
          );
      return o;
    }),
    (n.n = function (e) {
      var t =
        e && e.__esModule
          ? function () {
              return e.default;
            }
          : function () {
              return e;
            };
      return n.d(t, "a", t), t;
    }),
    (n.o = function (e, t) {
      return Object.prototype.hasOwnProperty.call(e, t);
    }),
    (n.p = ""),
    n((n.s = 0));
})([
  function (e, t, n) {
    const o = n(1),
      l = n(2);
    window.onload = function () {
      document
        .getElementById("fahrenheit")
        .addEventListener("change", o.fromFahrenheit),
        document
          .getElementById("fahrenheit")
          .addEventListener("input", o.fromFahrenheit),
        document
          .getElementById("celsius")
          .addEventListener("change", o.fromCelsius),
        document
          .getElementById("celsius")
          .addEventListener("input", o.fromCelsius),
        document
          .getElementById("kelvin")
          .addEventListener("change", o.fromKelvin),
        document
          .getElementById("kelvin")
          .addEventListener("input", o.fromKelvin),
        document
          .getElementById("miles")
          .addEventListener("change", l.fromMiles),
        document
        .getElementById("miles")
        .addEventListener("input", l.fromMiles),
        document
          .getElementById("kilometers")
          .addEventListener("change", l.fromKilometers),
        document
          .getElementById("kilometers")
          .addEventListener("input", l.fromKilometers);
    };
  },
  function (e, t) {
    (t.fromCelsius = function () {
      let e = parseFloat(document.getElementById("celsius").value);
      (document.getElementById("fahrenheit").value = ((9 * e) / 5 + 32).toFixed(
        2
      )),
        (document.getElementById("kelvin").value = (e + 273.15).toFixed(2));
    }),
      (t.fromFahrenheit = function () {
        let e = parseFloat(document.getElementById("fahrenheit").value);
        (document.getElementById("celsius").value = (
          (5 * (e - 32)) /
          9
        ).toFixed(2)),
          (document.getElementById("kelvin").value = (
            (5 * (e - 32)) / 9 +
            273.15
          ).toFixed(2));
      }),
      (t.fromKelvin = function () {
        let e = parseFloat(document.getElementById("kelvin").value);
        (document.getElementById("celsius").value = (e - 273.15).toFixed(2)),
          (document.getElementById("fahrenheit").value = (
            (9 * (e - 273.15)) / 5 +
            32
          ).toFixed(2));
      });
  },
  function (e, t) {
    (t.fromKilometers = function () {
      let e = parseFloat(document.getElementById("kilometers").value);
      document.getElementById("miles").value = (0.62137 * e).toFixed(2);
    }),
      (t.fromMiles = function () {
        let e = parseFloat(document.getElementById("miles").value);
        document.getElementById("kilometers").value = (e / 0.62137).toFixed(2);
      });
  },
]);
